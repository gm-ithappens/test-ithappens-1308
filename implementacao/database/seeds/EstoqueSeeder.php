<?php

use App\Estoque;
use App\Filial;
use App\Produto;
use Illuminate\Database\Seeder;

class EstoqueSeeder extends Seeder
{
    public function run()
    {
        $filial = factory(Filial::class)->create();
        $produtos = factory(Produto::class, 80)->create();

        foreach ($produtos as $produto) {
            Estoque::create([
                'filial_id' => $filial->id,
                'produto_id' => $produto->id,
                'quantidade' => 10,
            ]);
        }
    }
}
