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
