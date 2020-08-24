<?php

$router->get('/', function () use ($router) {
    return $router->app->version();
});

$router->group(['prefix' => 'users'], function () use ($router) {
    $router->get('/', ['uses' => 'UserController@index' , 'as' => 'users.index']);
    $router->get('/{id}', ['uses' => 'UserController@show' , 'as' => 'users.show']);
    $router->post('/', ['uses' => 'UserController@store' , 'as' => 'users.store']);
    $router->patch('/{id}', ['uses' => 'UserController@update' , 'as' => 'users.update']);
});

$router->group(['prefix' => 'produtos'], function () use ($router) {
    $router->get('/', ['uses' => 'ProdutoController@index' , 'as' => 'produtos.index']);
    $router->get('/{id}', ['uses' => 'ProdutoController@show' , 'as' => 'produtos.show']);
    $router->post('/', ['uses' => 'ProdutoController@store' , 'as' => 'produtos.store']);
    $router->patch('/{id}', ['uses' => 'ProdutoController@update' , 'as' => 'produtos.update']);
});

$router->group(['prefix' => 'filiais'], function () use ($router) {
    $router->get('/', ['uses' => 'FilialController@index' , 'as' => 'filiais.index']);
    $router->get('/{id}', ['uses' => 'FilialController@show' , 'as' => 'filiais.show']);
    $router->post('/', ['uses' => 'FilialController@store' , 'as' => 'filiais.store']);
    $router->patch('/{id}', ['uses' => 'FilialController@update' , 'as' => 'filiais.update']);
});

$router->group(['prefix' => 'clientes'], function () use ($router) {
    $router->get('/', ['uses' => 'ClienteController@index' , 'as' => 'clientes.index']);
    $router->get('/{id}', ['uses' => 'ClienteController@show' , 'as' => 'clientes.show']);
    $router->post('/', ['uses' => 'ClienteController@store' , 'as' => 'clientes.store']);
    $router->patch('/{id}', ['uses' => 'ClienteController@update' , 'as' => 'clientes.update']);
});
