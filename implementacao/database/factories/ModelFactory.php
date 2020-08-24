<?php

/** @var \Illuminate\Database\Eloquent\Factory $factory */

use App\Produto;
use App\User;
use Faker\Generator as Faker;

/*
|--------------------------------------------------------------------------
| Model Factories
|--------------------------------------------------------------------------
|
| This directory should contain each of the model factory definitions for
| your application. Factories provide a convenient way to generate new
| model instances for testing / seeding your application's database.
|
*/


$factory->define(User::class, function (Faker $faker) {
    $faker->locale = 'pt_BR';
    return [
        'name' => $faker->name,
        'email' => $faker->email,
    ];
});

$factory->define(Produto::class, function (Faker $faker) {
    $faker->locale = 'pt_BR';
    return [
        'nome' => $faker->name,
        'ean' => $faker->ean13,
    ];
});
