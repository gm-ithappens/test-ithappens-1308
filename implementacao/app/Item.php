<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Item extends Model
{
    protected $table = 'itens';

    protected $fillable = [
        'pedido_id',
        'produto_id',
        'quantidade',
        'valor',
        'total',
        'situacao'
    ];

    public function pedido()
    {
        return $this->belongsTo(Pedido::class);
    }
}
