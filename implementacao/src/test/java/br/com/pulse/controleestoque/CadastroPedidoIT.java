package br.com.pulse.controleestoque;

import br.com.pulse.controleestoque.domain.model.*;
import br.com.pulse.controleestoque.domain.repository.*;
import br.com.pulse.controleestoque.util.DatabaseCleaner;
import br.com.pulse.controleestoque.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroPedidoIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FilialRepository filialRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TipoPedidoRepository tipoPedidoRepository;

    private String jsonCorretoPedido;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/pedidos";

        jsonCorretoPedido = ResourceUtils.getContentFromResource("/json/correto/pedido-1.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarPedido() {
        given()
                .body(jsonCorretoPedido)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    private void prepararDados() {
        var usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Bruce Wayne");

        usuarioRepository.save(usuario);

        var cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Steve Rogers");

        clienteRepository.save(cliente);

        var filial = new Filial();
        filial.setId(1L);
        filial.setDescricao("Unidade do Cohatrac IV");

        filialRepository.save(filial);

        var formaPagamento = new FormaPagamento();
        formaPagamento.setId(1L);
        formaPagamento.setDescricao("AVISTA");

        formaPagamentoRepository.save(formaPagamento);

        var tipoPedido = new TipoPedido();
        tipoPedido.setId(1L);
        tipoPedido.setDescricao("Saida");

        tipoPedidoRepository.save(tipoPedido);

        var produto1 = new Produto();
        produto1.setId(1L);
        produto1.setCodigoBarras("123456789");
        produto1.setDescricao("Arroz");
        produto1.setValor(BigDecimal.valueOf(17.9));

        var produto2 = new Produto();
        produto2.setId(2L);
        produto2.setCodigoBarras("987654321");
        produto2.setDescricao("Feijão");
        produto2.setValor(BigDecimal.valueOf(5.50));

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
    }
}
