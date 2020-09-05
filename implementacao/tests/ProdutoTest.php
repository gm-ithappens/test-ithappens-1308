<?php

namespace Test;

use Laravel\Lumen\Testing\DatabaseMigrations;
use Test\TestCase;
use App\Produto;
use App\User;

class ProdutoTest extends TestCase
{
    use DatabaseMigrations;

    private $user;

    public function setUp(): void
    {
        parent::setUp();
        $this->user = factory(User::class)->create();
    }

    public function testCriarProduto()
    {
        $this->actingAs($this->user)
            ->json('POST', route('produtos.store'), [
                'nome' => 'Alvejante VI',
                'ean' => '7898357417892'
            ])
            ->seeJsonStructure(['nome', 'ean'])
            ->seeStatusCode(201)
            ->seeInDatabase('produtos', [
                'nome' => 'Alvejante VI',
                'ean' => '7898357417892'
            ]);
    }

    public function testBuscarProduto()
    {
        $produto = factory(Produto::class)->create();
        $this->actingAs($this->user)
            ->get(route('produtos.show', ['id' => $produto->id]))
            ->seeJsonStructure(['nome', 'ean'])
            ->seeStatusCode(200);
    }

    public function testListarProdutos()
    {
        factory(Produto::class, 3)->create();
        $this->actingAs($this->user)
            ->get(route('produtos.index'))
            ->seeJsonStructure(['*' => ['nome', 'ean']])
            ->seeStatusCode(200);
    }

    public function testAtualizarProduto()
    {
        $produto = factory(Produto::class)->create();
        $rota = route('produtos.update', ['id' => $produto->id]);
        $this->actingAs($this->user)
            ->json('PATCH', $rota, [
                'nome' => 'Alvejante XY',
            ])
            ->seeJsonStructure(['nome', 'ean'])
            ->seeStatusCode(200)
            ->seeInDatabase('produtos', [
                'nome' => 'Alvejante XY',
            ]);
    }
}
