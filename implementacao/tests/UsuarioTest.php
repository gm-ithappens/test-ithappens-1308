<?php

namespace Test;

use Laravel\Lumen\Testing\DatabaseMigrations;
use Test\TestCase;
use App\User;

class UsuarioTest extends TestCase
{
    use DatabaseMigrations;

    private $user;

    public function setUp(): void
    {
        parent::setUp();
        $this->user = factory(User::class)->create();
    }

    public function testCriarUsuario()
    {
        $this->actingAs($this->user)
            ->json('POST', route('users.store'), [
                'name' => 'Rogerio da Silva',
                'email' => 'email@example.com',
                'cpf' => '01234567899',
            ])
            ->seeJsonStructure(['name', 'email', 'cpf'])
            ->seeStatusCode(201)
            ->seeInDatabase('users', [
                'name' => 'Rogerio da Silva',
                'email' => 'email@example.com',
                'cpf' => '01234567899',
            ]);
    }

    public function testBuscarUsuario()
    {
        $this->actingAs($this->user)
            ->get(route('users.show', ['id' => $this->user->id]))
            ->seeJsonStructure(['name', 'email', 'cpf'])
            ->seeStatusCode(200);
    }

    public function testListarUsuarios()
    {
        factory(User::class, 2)->create();
        $this->actingAs($this->user)
            ->get(route('users.index'))
            ->seeJsonStructure(['*' => ['name', 'email', 'cpf']])
            ->seeStatusCode(200);
    }

    public function testAtualizarUsuario()
    {
        $rota = route('users.update', ['id' => $this->user->id]);
        $this->actingAs($this->user)
            ->json('PATCH', $rota, [
                'name' => 'Felipe da Silva',
            ])
            ->seeJsonStructure(['name', 'email', 'cpf'])
            ->seeStatusCode(200)
            ->seeInDatabase('users', [
                'name' => 'Felipe da Silva',
            ]);
    }
}
