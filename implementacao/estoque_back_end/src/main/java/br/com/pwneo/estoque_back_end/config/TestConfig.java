package br.com.pwneo.estoque_back_end.config;

import br.com.pwneo.estoque_back_end.models.*;
import br.com.pwneo.estoque_back_end.models.supports.Operation;
import br.com.pwneo.estoque_back_end.models.supports.PaymentMethod;
import br.com.pwneo.estoque_back_end.models.supports.Status;
import br.com.pwneo.estoque_back_end.models.users.Client;
import br.com.pwneo.estoque_back_end.models.users.Employee;
import br.com.pwneo.estoque_back_end.repositories.*;
import br.com.pwneo.estoque_back_end.repositories.supports.OperationRepository;
import br.com.pwneo.estoque_back_end.repositories.supports.PaymentMethodRepository;
import br.com.pwneo.estoque_back_end.repositories.supports.StatusRepository;
import br.com.pwneo.estoque_back_end.repositories.users.ClientRepository;
import br.com.pwneo.estoque_back_end.repositories.users.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockProductRepository stockProductRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Operation input = new Operation(null, "ENTRADA");
        Operation output = new Operation(null, "SAIDA");
        this.operationRepository.saveAll(Arrays.asList(input, output));

        Status status1 = new Status(null, "ATIVO");
        Status status2 = new Status(null, "CANCELADO");
        Status status3 = new Status(null, "PROCESSADO");
        this.statusRepository.saveAll(Arrays.asList(status1, status2, status3));

        PaymentMethod paymentMethod1 = new PaymentMethod(null, "A VISTA");
        PaymentMethod paymentMethod2 = new PaymentMethod(null, "BOLETO");
        PaymentMethod paymentMethod3 = new PaymentMethod(null, "CARTAO");
        this.paymentMethodRepository.saveAll(Arrays.asList(paymentMethod1, paymentMethod2, paymentMethod3));

        Product notebook = new Product(null, "12345678901010", "Notebook Dell Inspiron 15");
        Product monitor = new Product(null, "12345678901011", "Monitor Samsung 22 Polegadas");
        Product backpack = new Product(null, "12345678901012", "Mochila Dell Urban");
        Product headphone = new Product(null, "12345678901013", "Fone de Ouvido Sem Fio JBL");
        Product miniMac = new Product(null, "12345678901014", "Mini Mac");
        this.productRepository.saveAll(Arrays.asList(notebook, monitor, backpack, headphone, miniMac));

        Stock stock1 = new Stock(null, "Estoque da Filial 1");
        Stock stock2 = new Stock(null, "Estoque da Filial 2");
        Stock stock3 = new Stock(null, "Estoque da Filial 3");
        Stock stock4 = new Stock(null, "Estoque da Filial 4");
        this.stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4));

        Subsidiary sub1 = new Subsidiary(null, "Filial - Zona Norte", "1234567890", "Rua teste 1", "72",
                "Imaginario 1", "Ficticia 1", "Maranhão", stock1);

        Subsidiary sub2 = new Subsidiary(null, "Filial - Zona Sul", "1234567891", "Rua teste 2", "73",
                "Imaginario 2", "Ficticia 2", "Maranhão", stock2);

        Subsidiary sub3 = new Subsidiary(null, "Filial - Zona Leste", "1234567892", "Rua teste 3", "74",
                "Imaginario 3", "Ficticia 3", "Maranhão", stock3);

        Subsidiary sub4 = new Subsidiary(null, "Filial - Zona Oeste", "1234567893", "Rua teste 4", "75",
                "Imaginario 4", "Ficticia 4", "Maranhão", stock4);
        this.subsidiaryRepository.saveAll(Arrays.asList(sub1, sub2, sub3, sub4));

        StockProduct item1 = new StockProduct(null, notebook, 10, 4500.0, stock1);
        StockProduct item2 = new StockProduct(null, notebook, 5, 4500.0, stock2);
        StockProduct item3 = new StockProduct(null, notebook, 2, 4500.0, stock3);
        StockProduct item4 = new StockProduct(null, notebook, 7, 4500.0, stock4);

        StockProduct item5 = new StockProduct(null, backpack, 23, 150.0, stock1);
        StockProduct item6 = new StockProduct(null, backpack, 30, 150.0, stock2);
        StockProduct item7 = new StockProduct(null, backpack, 16, 150.0, stock3);
        StockProduct item8 = new StockProduct(null, backpack, 60, 150.0, stock4);

        StockProduct item9 = new StockProduct(null, monitor, 15, 750.0, stock1);
        StockProduct item10 = new StockProduct(null, monitor, 6, 750.0, stock2);
        StockProduct item11 = new StockProduct(null, monitor, 10, 750.0, stock3);
        StockProduct item12 = new StockProduct(null, monitor, 0, 750.0, stock4);

        StockProduct item13 = new StockProduct(null, headphone, 12, 450.0, stock1);
        StockProduct item14 = new StockProduct(null, headphone, 20, 450.0, stock2);
        StockProduct item15 = new StockProduct(null, headphone, 25, 450.0, stock3);
        StockProduct item16 = new StockProduct(null, headphone, 30, 450.0, stock4);

        StockProduct item17 = new StockProduct(null, miniMac, 4, 6000.0, stock1);
        StockProduct item18 = new StockProduct(null, miniMac, 1, 6000.0, stock2);
        StockProduct item19 = new StockProduct(null, miniMac, 5, 6000.0, stock3);
        StockProduct item20 = new StockProduct(null, miniMac, 5, 6000.0, stock4);

        List<StockProduct> notebooks = Arrays.asList(item1, item2, item3, item4);
        List<StockProduct> backpacks = Arrays.asList(item5, item6, item7, item8);
        List<StockProduct> monitors = Arrays.asList(item9, item10, item11, item12);
        List<StockProduct> headphones = Arrays.asList(item13, item14, item15, item16);
        List<StockProduct> miniMacs = Arrays.asList(item17, item18, item19, item20);

        List<StockProduct> stockProducts = Stream.of(notebooks, backpacks, monitors, headphones, miniMacs)
                                                    .flatMap(Collection::stream)
                                                    .collect(Collectors.toList());

        this.stockProductRepository.saveAll(stockProducts);

        Client client1 = new Client(null, "Carlos", "carlos@gmail.com", "1234567891",
                "12345678916", "556670", "Rua A", "17-B", "Aurora",
                "São Luis", "Maranhão");
        Client client2 = new Client(null, "Patricia", "carlos@gmail.com", "1234567890",
                "12345678910", "5566789", "Rua B", "200", "Barreto",
                "São Luis", "Maranhão");
        clientRepository.saveAll(Arrays.asList(client1, client2));

        Employee employee1 = new Employee(null, "Paulo", "paulo@ithappens.com.br", "0987654321", "251201");
        Employee employee2 = new Employee(null, "Maria", "maria@ithappens.com.br", "5463424279", "251202");
        Employee employee3 = new Employee(null, "Julia", "julia@ithappens.com.br", "0000000001", "251203");
        this.employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3));

        StockOrder so1 = new StockOrder(null, "Deixar na porta", sub1, client1, employee3, paymentMethod1, output);
        StockOrder so2 = new StockOrder(null, "Entregar na portaria", sub1, client1, employee3, paymentMethod1, output);

        this.stockOrderRepository.saveAll(Arrays.asList(so1, so2));

        OrderItem notebookItem = new OrderItem(null, 1, 4500.0, status1, item1, so1);
        OrderItem miniMacItem = new OrderItem(null, 1, 6000.0, status1, item13, so1);
        OrderItem backpackItem = new OrderItem(null, 1, 150.0, status1, item4, so2);

        this.orderItemRepository.saveAll(Arrays.asList(notebookItem, backpackItem, miniMacItem));
    }
}