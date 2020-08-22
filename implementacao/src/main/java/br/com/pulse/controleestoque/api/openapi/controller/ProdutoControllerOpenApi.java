package br.com.pulse.controleestoque.api.openapi.controller;

import br.com.pulse.controleestoque.api.exceptionhandler.Problem;
import br.com.pulse.controleestoque.api.model.ProdutoModel;
import br.com.pulse.controleestoque.api.model.input.ProdutoInput;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "Produto")
public interface ProdutoControllerOpenApi {

    @ApiOperation("Cadastra um produto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto Cadastrado")
    })
    ProdutoModel salvar(
            @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);

    @ApiOperation("Busca um produto pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do produto inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
    })
    ProdutoModel buscarPorId(@ApiParam(value = "ID de um produto", example = "1") Long produtoId);

    @ApiOperation("Busca um produto pelo Código de barras")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Código de barras inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
    })
    ProdutoModel buscarPorCodigoBarras(@ApiParam(value = "Código de barras do produto", example = "123456789") String codigoBarras);
}
