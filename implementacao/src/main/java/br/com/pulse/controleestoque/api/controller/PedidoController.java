package br.com.pulse.controleestoque.api.controller;

import br.com.pulse.controleestoque.api.assembler.PedidoInputDisassembler;
import br.com.pulse.controleestoque.api.assembler.PedidoModelAssembler;
import br.com.pulse.controleestoque.api.model.PedidoModel;
import br.com.pulse.controleestoque.api.model.input.PedidoInput;
import br.com.pulse.controleestoque.api.openapi.controller.PedidoControllerOpenApi;
import br.com.pulse.controleestoque.domain.exception.EntidadeNaoEncontradaException;
import br.com.pulse.controleestoque.domain.exception.NegocioException;
import br.com.pulse.controleestoque.domain.model.Pedido;
import br.com.pulse.controleestoque.domain.service.CadastroPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

    private final CadastroPedidoService cadastraPedidoService;
    private final PedidoInputDisassembler pedidoInputDisassembler;
    private final PedidoModelAssembler pedidoModelAssembler;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PedidoModel salvar(@Valid @RequestBody PedidoInput pedidoInput) {

        try {
            Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            Pedido pedidoSalvo = cadastraPedidoService.salvar(pedido);

            return pedidoModelAssembler.toModel(pedidoSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}
