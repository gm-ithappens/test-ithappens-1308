<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Estoque extends Model
{
    protected $table = 'estoques';

    protected $fillable = ['filial_id', 'produto_id', 'quantidade'];
}
