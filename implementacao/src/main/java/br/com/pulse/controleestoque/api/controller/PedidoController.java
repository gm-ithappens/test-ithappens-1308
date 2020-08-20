package br.com.pulse.controleestoque.api.controller;

import br.com.pulse.controleestoque.api.assembler.PedidoInputDisassembler;
import br.com.pulse.controleestoque.api.model.PedidoSaidaModel;
import br.com.pulse.controleestoque.api.model.input.PedidoInput;
import br.com.pulse.controleestoque.domain.model.Pedido;
import br.com.pulse.controleestoque.domain.service.CadastroPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CadastroPedidoService cadastraPedidoService;
    private final PedidoInputDisassembler pedidoInputDisassembler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PedidoSaidaModel salvarSaida(@RequestBody PedidoInput pedidoInput) {
        Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

        cadastraPedidoService.salvar(pedido);
        return null;
    }
}
