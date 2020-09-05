<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Pedido extends Model
{
    protected $table = 'pedidos';

    protected $fillable = [
        'tipo',
        'filial_id',
        'usuario_id',
        'cliente_id',
        'pagamento_id',
        'quantidade',
        'total',
        'observacao',
    ];

    public function itens()
    {
        return $this->hasMany(Item::class);
    }
}
