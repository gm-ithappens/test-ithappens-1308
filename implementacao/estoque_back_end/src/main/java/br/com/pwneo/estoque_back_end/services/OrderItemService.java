package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.OrderItem;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemDTO;
import br.com.pwneo.estoque_back_end.repositories.OrderItemRepository;
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
     * Caso contrário ele cria o novo item.
     */
    public OrderItem create(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        AtomicInteger index = new AtomicInteger(-1);
        List<OrderItem> orderItems = this.orderItemRepository.findAll();

        orderItems.forEach(orderItemCurrent -> {
            Integer stockOrder = orderItemCurrent.getStockOrder().getId();
            Integer productId = orderItemCurrent.getStockProduct().getId();

            if (stockOrder.equals(orderItemDTO.getStockOrder()) && productId.equals(orderItemDTO.getProductId())) {
                index.set(1);
            }
        });

        if (index.get() > -1) {
            throw new DatabaseException("Produto já existe na lista de itens!");
        } else {
            Double priceProduct = this.stockProductRepository.findById(orderItemDTO.getProductId()).get().getPrice();
            Integer quantityInStock = stockProductRepository.findById(orderItemDTO.getProductId()).get().getQuantity();

            if (orderItemDTO.getQuantity() < 0) {
                throw new RuntimeException("Quantidade de produtos é inválida!");
            }

            if (orderItemDTO.getQuantity() > quantityInStock) {
                throw new RuntimeException("Quantidade de produtos é maior do que a existente no estoque!");
            }

            orderItem.setStatus(this.statusRepository.findByDescription("ATIVO"));
            orderItem.setTotal(orderItemDTO.getQuantity() * priceProduct);
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setStockOrder(orderItem.getStockOrder());
            orderItem.setStockProduct(this.stockProductRepository.findById(orderItemDTO.getProductId()).get());
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
     * */
    public OrderItem updateQuantity(Integer id, OrderItemDTO orderItemDTO) {
        try {
            OrderItem orderItem = this.orderItemRepository.getOne(id);

            if (orderItemDTO.getQuantity() < 0) {
                throw new RuntimeException("A quantidade do item é inválida");
            }

            orderItem.setQuantity(orderItemDTO.getQuantity());
            return this.orderItemRepository.save(orderItem);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}