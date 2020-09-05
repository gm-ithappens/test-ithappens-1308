<?php

namespace App\Http\Controllers;

use App\Filial;
use Illuminate\Http\Request;

class FilialController extends Controller
{
    public function index()
    {
        return response()->json(Filial::all(), 200);
    }

    public function store(Request $request)
    {
        $filial = Filial::create($request->all());
        return response()->json($filial, 201);
    }

    public function show($id)
    {
        return response()->json(Filial::find($id), 200);
    }

    public function update(Request $request, $id)
    {
        $filial = Filial::findOrFail($id);
        $filial->update($request->all());

        return response()->json($filial, 200);
    }
}
