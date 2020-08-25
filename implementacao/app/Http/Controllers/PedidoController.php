<?php

namespace App\Http\Controllers;

use App\Estoque;
use App\Pedido;
use Illuminate\Http\Request;

class PedidoController extends Controller
{
    public function store(Request $request)
    {
        $pedido = Pedido::create($request->all());
        return response()->json($pedido, 201);
    }

    public function process($id)
    {
        $pedido = Pedido::find($id);
        $pedido->save();

        foreach ($pedido->itens as $item) {
            $item->situacao = 'P';
            $item->save();
            $this->atualizaEstoque($item);
        }

        return response()->json($pedido, 200);
    }

    private function atualizaEstoque($item)
    {
        $estoque = Estoque::where('filial_id', $item->pedido->filial_id)
            ->where('produto_id', $item->produto_id)
            ->first();

        if (!$estoque) {
            return Estoque::create([
                'filial_id' => $item->pedido->filial_id,
                'produto_id' => $item->produto_id,
                'quantidade' => $item->quantidade
            ]);
        };

        if ($item->pedido->tipo == 'E') {
            $estoque->quantidade += $item->quantidade;
        } elseif ($item->pedido->tipo == 'S' && $estoque->quantidade > 0) {
            $estoque->quantidade -= $item->quantidade;
        }

        $estoque->save();
        return $estoque;
    }
}
