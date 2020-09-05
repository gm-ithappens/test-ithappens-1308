<?php

namespace Test;

use App\Cliente;
use Laravel\Lumen\Testing\DatabaseMigrations;
use Test\TestCase;
use App\User;

class ClienteTest extends TestCase
{
    use DatabaseMigrations;

    private $user;

    public function setUp(): void
    {
        parent::setUp();
        $this->user = factory(User::class)->create();
    }

    public function testCriarCliente()
    {
        $this->actingAs($this->user)
            ->json('POST', route('clientes.store'), [
                'nome' => 'Osvaldo de Oliveira',
                'cpf' => '012345678999'
            ])
            ->seeJsonStructure(['nome', 'cpf'])
            ->seeStatusCode(201)
            ->seeInDatabase('clientes', [
                'nome' => 'Osvaldo de Oliveira',
                'cpf' => '012345678999'
            ]);
    }

    public function testBuscarCliente()
    {
        $cliente = factory(Cliente::class)->create();
        $this->actingAs($this->user)
            ->get(route('clientes.show', ['id' => $cliente->id]))
            ->seeJsonStructure(['nome', 'cpf'])
            ->seeStatusCode(200);
    }

    public function testListarClientes()
    {
        factory(Cliente::class, 3)->create();
        $this->actingAs($this->user)
            ->get(route('clientes.index'))
            ->seeJsonStructure(['*' => ['nome', 'cpf']])
            ->seeStatusCode(200);
    }

    public function testAtualizarCliente()
    {
        $cliente = factory(Cliente::class)->create();
        $rota = route('clientes.update', ['id' => $cliente->id]);
        $this->actingAs($this->user)
            ->json('PATCH', $rota, ['nome' => 'Pedro Antonio',])
            ->seeJsonStructure(['nome', 'cpf'])
            ->seeStatusCode(200)
            ->seeInDatabase('clientes', ['nome' => 'Pedro Antonio',]);
    }
}
