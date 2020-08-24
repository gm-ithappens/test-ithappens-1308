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
