<?php

namespace Test;

use App\Estoque;
use App\Item;
use App\User;
use Laravel\Lumen\Testing\DatabaseMigrations;
use Test\TestCase;

class EstoqueTest extends TestCase
{
    use DatabaseMigrations;

    private $user;

    public function setUp(): void
    {
        parent::setUp();
        $this->user = factory(User::class)->create();
    }

    public function testEntradaNoEstoque()
    {
        $item = factory(Item::class)->create();

        $this->actingAs($this->user)
            ->get(route('pedidos.process', ['id' => $item->pedido_id]))
            ->seeJsonStructure([
                'tipo',
                'filial_id',
                'usuario_id',
                'cliente_id',
                'pagamento_id',
                'observacao'
            ])
            ->seeStatusCode(200)
            ->seeInDatabase('pedidos', [
                'tipo' => 'E', # Entrada
                'filial_id' => $item->pedido->filial_id,
                'usuario_id' => $item->pedido->usuario_id,
                'cliente_id' => $item->pedido->cliente_id,
                'pagamento_id' => $item->pedido->pagamento_id
            ])
            ->seeInDatabase('itens', [
                'pedido_id' => $item->pedido_id,
                'produto_id' => $item->produto_id,
                'situacao' => 'P' # Processado
            ])
            ->seeInDatabase('estoques', [
                'filial_id' => $item->pedido->filial_id,
                'produto_id' => $item->produto_id,
                'quantidade' => 2
            ]);
    }

    public function testSaidaDoEstoque()
    {
        $item = factory(Item::class)->create();
        $item->pedido->tipo = 'S'; # Saida
        $item->pedido->save();

        Estoque::create([
            'filial_id' => $item->pedido->filial_id,
            'produto_id' => $item->produto_id,
            'quantidade' => 30
        ]);

        $this->actingAs($this->user)
            ->get(route('pedidos.process', ['id' => $item->pedido_id]))
            ->seeJsonStructure([
                'tipo',
                'filial_id',
                'usuario_id',
                'cliente_id',
                'pagamento_id',
                'observacao'
            ])
            ->seeStatusCode(200)
            ->seeInDatabase('pedidos', [
                'tipo' => 'S', # Entrada
                'filial_id' => $item->pedido->filial_id,
                'usuario_id' => $item->pedido->usuario_id,
                'cliente_id' => $item->pedido->cliente_id,
                'pagamento_id' => $item->pedido->pagamento_id
            ])
            ->seeInDatabase('itens', [
                'pedido_id' => $item->pedido_id,
                'produto_id' => $item->produto_id,
                'situacao' => 'P' # Processado
            ])
            ->seeInDatabase('estoques', [
                'filial_id' => $item->pedido->filial_id,
                'produto_id' => $item->produto_id,
                'quantidade' => 28
            ]);
    }
}
