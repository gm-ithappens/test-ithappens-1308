package br.com.pulse.controleestoque.api.controller;

import br.com.pulse.controleestoque.api.assembler.ProdutoInputDisassembler;
import br.com.pulse.controleestoque.api.assembler.ProdutoModelAssembler;
import br.com.pulse.controleestoque.api.model.ProdutoModel;
import br.com.pulse.controleestoque.api.model.input.ProdutoInput;
import br.com.pulse.controleestoque.api.openapi.controller.ProdutoControllerOpenApi;
import br.com.pulse.controleestoque.domain.filter.ProdutoFilter;
import br.com.pulse.controleestoque.domain.model.Produto;
import br.com.pulse.controleestoque.domain.repository.ProdutoRepository;
import br.com.pulse.controleestoque.domain.service.CadastroProdutoService;
import br.com.pulse.controleestoque.infrastructure.repository.spec.ProdutoSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoControllerOpenApi {

    private final ProdutoInputDisassembler produtoInputDisassembler;
    private final ProdutoModelAssembler produtoModelAssembler;
    private final CadastroProdutoService cadastroProduto;
    private final ProdutoRepository produtoRepository;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProdutoModel salvar(@Valid @RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        Produto produtoSalvo = cadastroProduto.salvar(produto);

        return produtoModelAssembler.toModel(produtoSalvo);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscarPorId(@PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(produtoId);
        return produtoModelAssembler.toModel(produto);
    }

    @GetMapping("/codigo-barras/{codigoBarras}")
    public ProdutoModel buscarPorCodigoBarras(@PathVariable String codigoBarras) {
        Produto produto = cadastroProduto.buscarPorCodigoBarras(codigoBarras);
        return produtoModelAssembler.toModel(produto);
    }

    @GetMapping
    public List<ProdutoModel> buscar(ProdutoFilter filtro, @PageableDefault Pageable pageable) {
        Page<Produto> produtosPage = produtoRepository.findAll(ProdutoSpecs.usandoFiltro(filtro), pageable);

        List<ProdutoModel> produtosModel = produtoModelAssembler.toCollectionModel(produtosPage.getContent());

        return produtosModel;
    }
}
