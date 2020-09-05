<?php

namespace App\Http\Controllers;

use App\Item;
use App\Pedido;
use App\Produto;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Http\Request;

class ItemController extends Controller
{
    public function store(Request $request)
    {
        $item = Item::where('pedido_id', $request->pedido_id)
            ->where('produto_id', $request->produto_id)
            ->get();

        if (!$item->count()) {
            $item = Item::create($request->all());
        } else {
            $item = $item->first();
            $item->quantidade += $request->quantidade;
        }

        $item->total = $item->valor * $item->quantidade;
        $item->save();

        $this->atualizaPedido($item->pedido_id);

        return response()->json($item, 201);
    }

    private function atualizaPedido($idPedido)
    {
        $pedido = Pedido::find($idPedido);
        $itens = $pedido->itens->where('situacao', '<>', 'C');
        $valor = $quantidade = 0;

        foreach ($itens as $item) {
            $quantidade += $item->quantidade;
            $valor += $item->total;
        }

        $pedido->quantidade = $quantidade;
        $pedido->total = $valor;
        $pedido->save();
    }

    public function cancel($id)
    {
        $item = Item::findOrFail($id);
        $item->situacao = 'C'; # Cancelado
        $item->save();

        return response()->json($item, 200);
    }
}
