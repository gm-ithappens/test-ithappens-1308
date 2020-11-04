package br.com.felipeomachado.apitest1308pulse.stockOrder.services;

import br.com.felipeomachado.apitest1308pulse.paymentMethod.entities.PaymentMethod;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.*;
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
public class StockOrderService {

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private StockOrderItemRepository stockOrderItemRepository;

    @Autowired
    private StockRepository stockRepository;

    public Optional<StockOrder> findById(Long id) {
        return stockOrderRepository.findById(id);
    }

    @Transactional
    public void createInStockOrder(StockOrderIn newStockOrderIn) {
        for (StockOrderItem stockOrderItem : newStockOrderIn.getItems()) {
            Optional<Stock> optionalStock = stockRepository.findById(new StockPK(newStockOrderIn.getBranch(), stockOrderItem.getProduct()));

            Stock stock;

            if (optionalStock.isPresent()) {
                stock = optionalStock.get();
                stock.increaseQuantity(stockOrderItem.getQuantity());
            } else {
                stock = new Stock(newStockOrderIn.getBranch(), stockOrderItem.getProduct(), stockOrderItem.getQuantity());
            }
            stockRepository.save(stock);
        }

        stockOrderRepository.save(newStockOrderIn);
    }

    @Transactional
    public void createOutStockOrder(StockOrderOut newStockOrderOut) {
        stockOrderRepository.save(newStockOrderOut);
    }


    @Transactional
    public void setPaymentMethod(StockOrder stockOrder, PaymentMethod paymentMethod) {
        stockOrder.setPaymentMethod(paymentMethod);
        stockOrder.setUpdatedAt(LocalDateTime.now());
        stockOrderRepository.save(stockOrder);
    }

    @Transactional
    public void finishStockOrder(StockOrder stockOrder) {

        for (StockOrderItem stockOrderItem : stockOrder.getItems()) {
            if (stockOrderItem.getStatus() == OrderItemStatus.ACTIVE) {
                Optional<Stock> optionalStock = stockRepository.findById(new StockPK(stockOrder.getBranch(), stockOrderItem.getProduct()));

                if (!optionalStock.isPresent()) {
                    throw new ValidationException("Stock not found!");
                }

                Stock stockFound = optionalStock.get();

                stockFound.decreaseQuantity(stockOrderItem.getQuantity());
                stockOrderItem.setStatus(OrderItemStatus.PROCESSED);

                stockRepository.save(stockFound);
                stockOrderItemRepository.save(stockOrderItem);
            }
        }

        stockOrder.setUpdatedAt(LocalDateTime.now());
        stockOrderRepository.save(stockOrder);
    }
}


