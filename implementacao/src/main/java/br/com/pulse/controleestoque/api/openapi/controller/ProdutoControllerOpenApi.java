package br.com.pulse.controleestoque.api.openapi.controller;

import br.com.pulse.controleestoque.api.model.ProdutoModel;
import br.com.pulse.controleestoque.api.model.input.ProdutoInput;
import io.swagger.annotations.*;

@Api(tags = "Produto")
public interface ProdutoControllerOpenApi {

    @ApiOperation("Cadastra um produto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto Cadastrado")
    })

    ProdutoModel salvar(
            @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);
}
