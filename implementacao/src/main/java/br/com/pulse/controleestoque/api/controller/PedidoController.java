package br.com.pulse.controleestoque.api.controller;

import br.com.pulse.controleestoque.api.assembler.PedidoInputDisassembler;
import br.com.pulse.controleestoque.api.assembler.PedidoModelAssembler;
import br.com.pulse.controleestoque.api.model.PedidoModel;
import br.com.pulse.controleestoque.api.model.input.PedidoInput;
import br.com.pulse.controleestoque.api.openapi.controller.PedidoControllerOpenApi;
import br.com.pulse.controleestoque.domain.model.Pedido;
import br.com.pulse.controleestoque.domain.service.CadastroPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

    private final CadastroPedidoService cadastraPedidoService;
    private final PedidoInputDisassembler pedidoInputDisassembler;
    private final PedidoModelAssembler pedidoModelAssembler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PedidoModel salvar(@RequestBody PedidoInput pedidoInput) {
        Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        Pedido pedidoSalvo = cadastraPedidoService.salvar(pedido);

        return pedidoModelAssembler.toModel(pedidoSalvo);
    }
}
