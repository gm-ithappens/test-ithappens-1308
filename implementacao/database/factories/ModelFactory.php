<?php

/** @var \Illuminate\Database\Eloquent\Factory $factory */

use App\Cliente;
use App\Filial;
use App\Item;
use App\Pagamento;
use App\Pedido;
use App\Produto;
use App\User;
use Faker\Factory as Factory;

$faker = Factory::create('pt_BR');

$factory->define(User::class, function () use ($faker) {
    return [
        'name' => $faker->name,
        'email' => $faker->email,
    ];
});

$factory->define(Produto::class, function () use ($faker) {
    return [
        'nome' => $faker->name,
        'ean' => $faker->ean13,
    ];
});

$factory->define(Filial::class, function () use ($faker) {
    return [
        'nome' => $faker->name,
    ];
});

$factory->define(Cliente::class, function () use ($faker) {
    return [
        'nome' => $faker->name,
        'cpf' => $faker->unique()->cpf,
    ];
});

$factory->define(Pedido::class, function () {
    $filial = factory(Filial::class)->create();
    $user = factory(User::class)->create();
    $cliente = factory(Cliente::class)->create();
    $pgto = Pagamento::where('forma', 'Cartao')->first();

    return [
        'tipo' => 'E', # Entrada
        'filial_id' => $filial->id,
        'usuario_id' => $user->id,
        'cliente_id' => $cliente->id,
        'pagamento_id' => $pgto->id,
        'observacao' => 'Pedido do cliente: ' . $cliente->nome
    ];
});

$factory->define(Item::class, function () {
    $pedido = factory(Pedido::class)->create();
    $produto = factory(Produto::class)->create();

    return [
        'pedido_id' => $pedido->id,
        'produto_id' => $produto->id,
        'quantidade' => 2,
        'valor' => 10.2,
        'total' => 20.4,
        'situacao' => 'A' # Ativo
    ];
});
