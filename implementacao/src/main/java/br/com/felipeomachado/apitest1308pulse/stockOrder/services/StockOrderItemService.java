package br.com.felipeomachado.apitest1308pulse.stockOrder.services;

import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.Stock;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrder;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrderItem;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockPK;
import br.com.felipeomachado.apitest1308pulse.stockOrder.enums.OrderItemStatus;
import br.com.felipeomachado.apitest1308pulse.stockOrder.repositories.StockOrderItemRepository;
import br.com.felipeomachado.apitest1308pulse.stockOrder.repositories.StockOrderRepository;
import br.com.felipeomachado.apitest1308pulse.stockOrder.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StockOrderItemService {
    @Autowired
    private StockOrderItemRepository stockOrderItemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockOrderRepository stockOrderRepository;

    public Optional<StockOrderItem> findById(Long id){
        return stockOrderItemRepository.findById(id);
    }

    @Transactional
    public void addStockOrderItem(StockOrderItem newStockOrderItem) {

        Optional<Stock> optionalStock = stockRepository.findById(new StockPK(newStockOrderItem.getStockOrder().getBranch(), newStockOrderItem.getProduct()));

        if (optionalStock.isPresent()) {
            Stock stockFound = optionalStock.get();

            if (newStockOrderItem.getQuantity() <= stockFound.getQuantity()) {

                Optional<StockOrderItem> orderItemFound = stockOrderItemRepository.findByStockOrderAndProductAndStatusActiveOrProcessed(newStockOrderItem.getStockOrder().getId(), newStockOrderItem.getProduct().getId());

                if (orderItemFound.isPresent()) {
                    throw new ValidationException("Duplicated Product in this order!");
                }

                Optional<StockOrder> optionalStockOrder = stockOrderRepository.findById(newStockOrderItem.getStockOrder().getId());

                if (!optionalStockOrder.isPresent()) {
                    throw new ValidationException("Stock Order not found!");
                }

                StockOrder stockOrderFound = optionalStockOrder.get();

                stockOrderFound.increaseTotals(newStockOrderItem);

                stockOrderItemRepository.save(newStockOrderItem);
                stockOrderRepository.save(stockOrderFound);

                return;
            }
        }

        throw new ValidationException("There is no stock for this product");
    }

    @Transactional
    public void removeStockOrderItem(StockOrderItem stockOrderItem) {

        if (stockOrderItem.getStatus() == OrderItemStatus.CANCELED) {
            throw new ValidationException("Item is already canceled");
        }

        stockOrderItem.getStockOrder().decreaseTotals(stockOrderItem);

        stockOrderItem.setStatus(OrderItemStatus.CANCELED);
        stockOrderItem.setUpdatedAt(LocalDateTime.now());

        stockOrderRepository.save(stockOrderItem.getStockOrder());
        stockOrderItemRepository.save(stockOrderItem);
    }
}
