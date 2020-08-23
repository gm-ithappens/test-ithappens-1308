package br.com.pulse.controleestoque.api.openapi.controller;

import br.com.pulse.controleestoque.api.model.PedidoModel;
import br.com.pulse.controleestoque.api.model.input.PedidoInput;
import io.swagger.annotations.*;

@Api(tags = "Pedido")
public interface PedidoControllerOpenApi {

    @ApiOperation("Cadastra um pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido Cadastrado")
    })
    PedidoModel salvar(
            @ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput
    );
}
