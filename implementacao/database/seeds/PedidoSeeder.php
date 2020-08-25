<?php

use App\Cliente;
use App\Filial;
use App\Item;
use App\Pagamento;
use App\Pedido;
use App\Produto;
use App\User;
use Illuminate\Database\Seeder;

class PedidoSeeder extends Seeder
{
    public function run()
    {
        $filial = Filial::first();
        $usuario = factory(User::class)->create();
        $cliente = factory(Cliente::class)->create();
        $pgto = Pagamento::where('forma', 'Cartao')->first();
        $produto = Produto::first();

        for ($i = 0; $i < 5; $i++) {
            $pedido = Pedido::create([
                'tipo' => 'E', # Entrada
                'filial_id' => $filial->id,
                'usuario_id' => $usuario->id,
                'cliente_id' => $cliente->id,
                'pagamento_id' => $pgto->id,
                'observacao' => 'Pedido do cliente: ' . $cliente->nome
            ]);

            Item::create([
                'pedido_id' => $pedido->id,
                'produto_id' => $produto->id,
                'quantidade' => 2,
                'valor' => 10.2,
                'total' => 20.4,
                'situacao' => 'A' # Ativo
            ]);
        }
    }
}
