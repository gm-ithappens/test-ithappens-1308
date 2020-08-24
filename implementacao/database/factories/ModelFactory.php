<?php

/** @var \Illuminate\Database\Eloquent\Factory $factory */

use App\Cliente;
use App\Filial;
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
