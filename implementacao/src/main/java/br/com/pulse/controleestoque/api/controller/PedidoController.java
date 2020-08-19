package br.com.pulse.controleestoque.api.controller;

import br.com.pulse.controleestoque.api.model.PedidoSaidaModel;
import br.com.pulse.controleestoque.api.model.input.PedidoInput;
import br.com.pulse.controleestoque.domain.service.CadastroPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CadastroPedidoService cadastraPedidoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saida")
    public PedidoSaidaModel salvarSaida(@RequestBody PedidoInput pedidoInput) {
        System.out.println(pedidoInput);
        return null;
    }
}
