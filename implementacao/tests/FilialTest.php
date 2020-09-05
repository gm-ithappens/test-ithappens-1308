<?php

namespace Test;

use App\Filial;
use Laravel\Lumen\Testing\DatabaseMigrations;
use Test\TestCase;
use App\User;

class FilialTest extends TestCase
{
    use DatabaseMigrations;

    private $user;

    public function setUp(): void
    {
        parent::setUp();
        $this->user = factory(User::class)->create();
    }

    public function testCriarFilial()
    {
        $this->actingAs($this->user)
            ->json('POST', route('filiais.store'), ['nome' => 'Cohama'])
            ->seeJsonStructure(['nome'])
            ->seeStatusCode(201)
            ->seeInDatabase('filiais', ['nome' => 'Cohama']);
    }

    public function testBuscarFilial()
    {
        $filial = factory(Filial::class)->create();
        $this->actingAs($this->user)
            ->get(route('filiais.show', ['id' => $filial->id]))
            ->seeJsonStructure(['nome'])
            ->seeStatusCode(200);
    }

    public function testListarFiliais()
    {
        factory(Filial::class, 3)->create();
        $this->actingAs($this->user)
            ->get(route('filiais.index'))
            ->seeJsonStructure(['*' => ['nome']])
            ->seeStatusCode(200);
    }

    public function testAtualizarFilial()
    {
        $filial = factory(Filial::class)->create();
        $rota = route('filiais.update', ['id' => $filial->id]);
        $this->actingAs($this->user)
            ->json('PATCH', $rota, ['nome' => 'Cohab',])
            ->seeJsonStructure(['nome'])
            ->seeStatusCode(200)
            ->seeInDatabase('filiais', ['nome' => 'Cohab',]);
    }
}
