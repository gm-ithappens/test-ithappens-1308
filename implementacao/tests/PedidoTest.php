<?php

namespace Test;

use Laravel\Lumen\Testing\DatabaseMigrations;
use App\Cliente;
use App\Filial;
use App\Item;
use App\Pagamento;
use App\Produto;
use App\User;
use Test\TestCase;

class PedidoTest extends TestCase
{
    use DatabaseMigrations;

    private $user;

    public function setUp(): void
    {
        parent::setUp();
        $this->user = factory(User::class)->create();
    }

    public function testAddPedidoComItem()
    {
        $filial = factory(Filial::class)->create();
        $user = factory(User::class)->create();
        $cliente = factory(Cliente::class)->create();
        $pgto = Pagamento::where('forma', 'Cartao')->first();

        $pedido = $this->actingAs($this->user)
            ->json('POST', route('pedidos.store'), [
                'tipo' => 'E', # Entrada
                'filial_id' => $filial->id,
                'usuario_id' => $user->id,
                'cliente_id' => $cliente->id,
                'pagamento_id' => $pgto->id,
                'observacao' => 'Pedido do cliente: ' . $cliente->nome
            ])
            ->seeJsonStructure(['tipo', 'filial_id', 'usuario_id', 'cliente_id', 'pagamento_id', 'observacao'])
            ->seeStatusCode(201)
            ->seeInDatabase('pedidos', [
                'tipo' => 'E', # Entrada
                'filial_id' => $filial->id,
                'usuario_id' => $user->id,
                'cliente_id' => $cliente->id,
                'pagamento_id' => $pgto->id,
                'quantidade' => 0,
                'total' => 0,
                'observacao' => 'Pedido do cliente: ' . $cliente->nome
            ]);

        $produto = factory(Produto::class)->create();

        $this->actingAs($this->user)
            ->json('POST', route('itens.store'), [
                'pedido_id' => $pedido->response['id'],
                'produto_id' => $produto->id,
                'quantidade' => 2,
                'valor' => 10.2
            ])
        ->seeJsonStructure(['pedido_id', 'produto_id', 'quantidade', 'valor'])
        ->seeStatusCode(201)
        ->seeInDatabase('itens', [
            'pedido_id' => $pedido->response['id'],
            'produto_id' => $produto->id,
            'quantidade' => 2,
            'valor' => 10.2,
            'total' => 20.4,
            'situacao' => 'A'
        ]);

        $this->seeInDatabase('pedidos', [
            'tipo' => 'E', # Entrada
            'filial_id' => $filial->id,
            'usuario_id' => $user->id,
            'cliente_id' => $cliente->id,
            'pagamento_id' => $pgto->id,
            'quantidade' => 2,
            'total' => 20.4,
            'observacao' => 'Pedido do cliente: ' . $cliente->nome
        ]);
    }

    public function testCancelamentoDeItemNoPedido()
    {
        $itemPedido = factory(Item::class)->create();

        $this->actingAs($this->user)
            ->get(route('itens.cancel', ['id' => $itemPedido->id]))
            ->seeJsonStructure(['pedido_id', 'produto_id', 'quantidade', 'valor'])
            ->seeStatusCode(200)
            ->seeInDatabase('itens', [
                'pedido_id' => $itemPedido->pedido_id,
                'produto_id' => $itemPedido->produto_id,
                'quantidade' => 2,
                'valor' => 10.2,
                'total' => 20.4,
                'situacao' => 'C'
            ]);

        $this->seeInDatabase('pedidos', [
            'id' => $itemPedido->pedido_id,
            'quantidade' => 0,
            'total' => 0
        ]);
    }

    public function testAddProdutoJaExistenteNoPedido()
    {
        $itemPedido = factory(Item::class)->create();

        $this->actingAs($this->user)
            ->json('POST', route('itens.store'), [
                'pedido_id' => $itemPedido->pedido_id,
                'produto_id' => $itemPedido->produto_id,
                'quantidade' => 2,
                'valor' => 10.2
            ])
        ->seeJsonStructure(['pedido_id', 'produto_id', 'quantidade', 'valor'])
        ->seeStatusCode(201)
        ->seeInDatabase('itens', [
            'pedido_id' => $itemPedido->pedido_id,
            'produto_id' => $itemPedido->produto_id,
            'quantidade' => 4,
            'valor' => 10.2,
            'total' => 40.8,
            'situacao' => 'A'
        ]);

        $this->seeInDatabase('pedidos', [
            'id' => $itemPedido->pedido_id,
            'quantidade' => 4,
            'total' => 40.8
        ]);
    }
}
