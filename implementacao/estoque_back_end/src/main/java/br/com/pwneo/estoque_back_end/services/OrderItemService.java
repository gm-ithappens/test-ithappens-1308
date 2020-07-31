package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.OrderItem;
import br.com.pwneo.estoque_back_end.models.StockOrder;
import br.com.pwneo.estoque_back_end.models.StockProduct;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemDTO;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemQuantityDTO;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemStatusDTO;
import br.com.pwneo.estoque_back_end.models.supports.Status;
import br.com.pwneo.estoque_back_end.repositories.OrderItemRepository;
import br.com.pwneo.estoque_back_end.repositories.StockOrderRepository;
import br.com.pwneo.estoque_back_end.repositories.StockProductRepository;
import br.com.pwneo.estoque_back_end.repositories.supports.StatusRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StockProductRepository stockProductRepository;

    @Autowired
    private StockOrderRepository stockOrderRepository;


    public List<OrderItem> findAll() {
        return this.orderItemRepository.findAll();
    }

    public OrderItem findById(Integer id) {
        return this.orderItemRepository.findById(id).get();
    }


    /**
     * Método responsável por salvar no banco de dados um novo item do pedido de estoque.
     * Ele verifica primeiramente se já existe o item neste pedido.
     * Para isso ele compara o id do pedido de estoque e id do produto.
     * Caso encontre ele um item que já tem o id do pedido e o id do produto, ele dispara uma exception informando o ocorrido.
     * Caso contrário ele cria o novo item e atualiza em StockOrder o valor total e a quantidade de itens da lista.
     */
    public OrderItem create(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        AtomicInteger index = new AtomicInteger(-1);
        List<OrderItem> orderItems = this.orderItemRepository.findAll();

        orderItems.forEach(orderItemCurrent -> {
            Integer stockOrderCurrent = orderItemCurrent.getStockOrder().getId();
            Integer productIdCurrent = orderItemCurrent.getStockProduct().getId();

            /*
             * Caso os id's de pediddo de estoque e produtos sejam iguais ao que veio no DTO então ele achou o pedido de estoque.
             * index.Set(1) representa que o elemento foi encontrado
             **/
            if (stockOrderCurrent.equals(orderItemDTO.getStockOrder()) && productIdCurrent.equals(orderItemDTO.getProduct())) {
                index.set(1);
            }
        });

        /*Pedido de estoque já existe no banco? Não faz nada, apenas dispara uma exception*/
        if (index.get() > -1) {
            throw new RuntimeException("Produto já existe na lista de itens!");
        }

        /*
         * Se não achou, então vamos criar um novo pedido de estoque para ser armazenado, mas antes:
         * - Verificamos se a quantidade não é menor que 1 e se ela é menor ou igual a que existe no estoque.
         * - Atualizamos a quantidade total de produtos do pedido, somando o total ja existente com a quantidade do novo item.
         * - Atualizamos o valor total do pedido de estoque, somando o valorTotal (quantidade x preço) de cada item.
         */
        else {
            System.out.println("Order Item DTO Atual ===============: " + orderItemDTO);

            StockOrder newStockOrder =  this.stockOrderRepository.findById(orderItemDTO.getStockOrder()).get();
            StockProduct newStockProduct = this.stockProductRepository.findById(orderItemDTO.getProduct()).get();
            Double priceProduct = this.stockProductRepository.findById(orderItemDTO.getProduct()).get().getPrice();
            Integer quantityInStock = this.stockProductRepository.findById(orderItemDTO.getProduct()).get().getQuantity();

           if (orderItemDTO.getQuantity() < 1) {
                throw new RuntimeException("Quantidade de produtos é inválida!");
            }

            if (orderItemDTO.getQuantity() > quantityInStock) {
                throw new RuntimeException("Quantidade de produtos é maior do que a existente no estoque!");
            }

            orderItem.setStatus(this.statusRepository.findByDescription("ATIVO"));
            orderItem.setTotal( orderItemDTO.getQuantity().doubleValue() * priceProduct);
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setStockOrder(newStockOrder);
            orderItem.setStockProduct(newStockProduct);

            this.stockOrderRepository.findById(orderItemDTO.getStockOrder())
                    .ifPresent(stockOrderCurrent -> {
                        stockOrderCurrent.setQuantity(stockOrderCurrent.getQuantity() + orderItem.getQuantity());
                        stockOrderCurrent.setTotalPrice(stockOrderCurrent.getTotalPrice() + orderItem.getTotal());
                    });
        }
        return this.orderItemRepository.save(orderItem);
    }

    public void delete(Integer id) {
        try {
            orderItemRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /*
     * O Método é responsável por atualizar a quantidade do item selecionado no pedido de estoque.
     * Ele verifica se o valor digitado é maior que zero. Caso contrário ele não insere e retorna uma exception.
     */
    public OrderItem updateQuantity(Integer id, OrderItemQuantityDTO orderItemQuantityDTO) {
        try {
            OrderItem orderItem = this.orderItemRepository.getOne(id);

            if (orderItemQuantityDTO.getQuantity() < 0) {
                throw new RuntimeException("A quantidade do item é inválida");
            }

            orderItem.setQuantity(orderItemQuantityDTO.getQuantity());
            return this.orderItemRepository.save(orderItem);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    /*
     * O Método é responsável por atualizar o status do item do pedido.
     */
    public OrderItem updateStatus(Integer id, OrderItemStatusDTO orderItemStatusDTO) {
        try {
            Status statusCode = this.statusRepository.findByDescription(orderItemStatusDTO.getStatusMessage());
            OrderItem orderItem = this.orderItemRepository.getOne(id);
            orderItem.setStatus(statusCode);
            return this.orderItemRepository.save(orderItem);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}