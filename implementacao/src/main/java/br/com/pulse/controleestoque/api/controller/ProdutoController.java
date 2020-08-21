package br.com.pulse.controleestoque.api.controller;

import br.com.pulse.controleestoque.api.assembler.ProdutoInputDisassembler;
import br.com.pulse.controleestoque.api.assembler.ProdutoModelAssembler;
import br.com.pulse.controleestoque.api.model.ProdutoModel;
import br.com.pulse.controleestoque.api.model.input.ProdutoInput;
import br.com.pulse.controleestoque.api.openapi.controller.ProdutoControllerOpenApi;
import br.com.pulse.controleestoque.domain.model.Produto;
import br.com.pulse.controleestoque.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoControllerOpenApi {

    private final ProdutoInputDisassembler produtoInputDisassembler;
    private final ProdutoModelAssembler produtoModelAssembler;
    private final ProdutoRepository produtoRepository;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProdutoModel salvar(@Valid @RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        Produto produtoSalvo = produtoRepository.save(produto);

        return produtoModelAssembler.toModel(produtoSalvo);
    }
}
