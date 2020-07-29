package br.com.pwneo.estoque_back_end.config;

import br.com.pwneo.estoque_back_end.models.*;
import br.com.pwneo.estoque_back_end.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

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
    private StockItemRepository stockItemRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        Operation operation1 = new Operation(null, "ENTRADA");
        Operation operation2 = new Operation(null, "SAIDA");
        this.operationRepository.saveAll(Arrays.asList(operation1, operation2));

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

        Subsidiary sub1 = new Subsidiary(null, "Filial 1", "1234567890", "Rua teste 1", "72",
                "Imaginario 1", "Cinematografica 1", "Maranhão");
        Subsidiary sub2 = new Subsidiary(null, "Filial 2", "1234567891", "Rua teste 2", "73",
                "Imaginario 2", "Cinematografica 2", "Maranhão");
        Subsidiary sub3 = new Subsidiary(null, "Filial 1", "1234567892", "Rua teste 3", "74",
                "Imaginario 3", "Cinematografica 3", "Maranhão");
        Subsidiary sub4 = new Subsidiary(null, "Filial 1", "1234567893", "Rua teste 4", "75",
                "Imaginario 4", "Cinematografica 4", "Maranhão");
        this.subsidiaryRepository.saveAll(Arrays.asList(sub1, sub2, sub3, sub4));

        StockItem item1 = new StockItem(null, notebook, 10, 4500.0, sub1);
        StockItem item2 = new StockItem(null, notebook, 5, 4500.0, sub2);
        StockItem item3 = new StockItem(null, notebook, 2, 4500.0, sub4);
        StockItem item4 = new StockItem(null, backpack, 23, 150.0, sub1);
        StockItem item5 = new StockItem(null, backpack, 3, 150.0, sub3);
        StockItem item6 = new StockItem(null, backpack, 1, 150.0, sub4);
        StockItem item7 = new StockItem(null, monitor, 20, 750.0, sub1);
        StockItem item8 = new StockItem(null, monitor, 15, 750.0, sub2);
        StockItem item9 = new StockItem(null, monitor, 6, 750.0, sub3);
        StockItem item10 = new StockItem(null, monitor, 10, 750.0, sub4);
        StockItem item11 = new StockItem(null, headphone, 13, 450.0, sub1);
        StockItem item12 = new StockItem(null, headphone, 2, 450.0, sub4);
        StockItem item13 = new StockItem(null, miniMac, 4, 6000.0, sub1);
        this.stockItemRepository.saveAll(Arrays
                .asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13));

        Client c1 = new Client(null, "Carlos", "carlos@gmail.com", "1234567891",
                "12345678916", "556670", "Rua A", "17-B", "Aurora",
                "São Luis", "Maranhão");
        Client c2 = new Client(null, "Patricia", "carlos@gmail.com", "1234567890",
                "12345678910", "5566789", "Rua B", "200", "Barreto",
                "São Luis", "Maranhão");
        clientRepository.saveAll(Arrays.asList(c1, c2));

        Employee e1 = new Employee(null, "Paulo", "paulo@ithappens.com.br", "0987654321", "251201");
        Employee e2 = new Employee(null, "Maria", "maria@ithappens.com.br", "5463424279", "251202");
        Employee e3 = new Employee(null, "Julia", "julia@ithappens.com.br", "0000000001", "251203");
        this.employeeRepository.saveAll(Arrays.asList(e1, e2, e3));
    }
}