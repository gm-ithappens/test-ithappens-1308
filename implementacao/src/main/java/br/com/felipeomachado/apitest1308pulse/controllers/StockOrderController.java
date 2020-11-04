package br.com.felipeomachado.apitest1308pulse.controllers;


import br.com.felipeomachado.apitest1308pulse.entities.*;
import br.com.felipeomachado.apitest1308pulse.entities.request.*;
import br.com.felipeomachado.apitest1308pulse.enums.OrderItemStatus;
import br.com.felipeomachado.apitest1308pulse.enums.PaymentMethod;
import br.com.felipeomachado.apitest1308pulse.repositories.StockOrderItemRepository;
import br.com.felipeomachado.apitest1308pulse.repositories.StockOrderRepository;
import br.com.felipeomachado.apitest1308pulse.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/stockOrder")
public class StockOrderController {

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private StockOrderItemRepository stockOrderItemRepository;

    @Autowired
    private StockRepository stockRepository;


    @RequestMapping(value = "/in", method = RequestMethod.POST)
    @Transactional
    public void createInStockOrder(@Valid @RequestBody RequestStockOrderIn requestStockOrderIn) {
        StockOrderIn newStockOrderIn = requestStockOrderIn.build();

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
    }

    @RequestMapping(value = "/out", method = RequestMethod.POST)
    @Transactional
    public void createOutStockOrder(@Valid @RequestBody RequestStockOrderOut requestStockOrderOut) {
        StockOrderOut newStockOrderOut = requestStockOrderOut.build();

        stockOrderRepository.save(newStockOrderOut);
    }


    @RequestMapping(value = "/out/item", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity addStockOrderItem(@Valid @RequestBody RequestAddOrderItem requestAddOrderItem) {
        StockOrderItem newStockOrderItem = requestAddOrderItem.build();

        Optional<Stock> optionalStock = stockRepository.findById(new StockPK(new Branch(requestAddOrderItem.getBranchId()), newStockOrderItem.getProduct()));

        if (optionalStock.isPresent()) {
            Stock stockFound = optionalStock.get();

            if (requestAddOrderItem.getQuantity() <= stockFound.getQuantity()) {

                Optional<StockOrderItem> orderItemFound = stockOrderItemRepository.findByStockOrderAndProductAndStatusActiveOrProcessed(requestAddOrderItem.getStockOrderId(), requestAddOrderItem.getProductId());

                if (orderItemFound.isPresent()) {
                    throw new ValidationException("Duplicated Product in this order!");
                }

                Optional<StockOrder> optionalStockOrder = stockOrderRepository.findById(requestAddOrderItem.getStockOrderId());

                if (!optionalStockOrder.isPresent()) {
                    throw new ValidationException("Stock Order not found!");
                }

                StockOrder stockOrderFound = optionalStockOrder.get();

                stockOrderFound.increaseTotals(newStockOrderItem);

                stockOrderItemRepository.save(newStockOrderItem);
                stockOrderRepository.save(stockOrderFound);

                return ResponseEntity.ok().build();
            }
        }

        throw new ValidationException("There is no stock for this product");
    }


    @RequestMapping(value = "/out/item", method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity removeStockOrderItem(@Valid @RequestBody RequestRemoveOrderItem requestRemoveOrderItem) {
        Optional<StockOrderItem> optionalStockOrderItem = stockOrderItemRepository.findById(requestRemoveOrderItem.getStockOrderItemId());

        if (optionalStockOrderItem.isPresent()) {
            StockOrderItem stockOrderItemFound = optionalStockOrderItem.get();

            if (stockOrderItemFound.getStatus() == OrderItemStatus.CANCELED) {
                throw new ValidationException("Item also canceled");
            }

            stockOrderItemFound.getStockOrder().decreaseTotals(stockOrderItemFound);

            stockOrderItemFound.setStatus(OrderItemStatus.CANCELED);

            stockOrderRepository.save(stockOrderItemFound.getStockOrder());
            stockOrderItemRepository.save(stockOrderItemFound);

            return ResponseEntity.ok().build();

        }

        throw new ValidationException("Item not found!");
    }

    @RequestMapping(value = "/out/paymentMethod", method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity setPaymentMethod(@Valid @RequestBody RequestSetPaymentMethodStockOrder requestSetPaymentMethodStockOrder ) {
        Optional<StockOrder> optionalStockOrder = stockOrderRepository.findById(requestSetPaymentMethodStockOrder.getStockOrderId());

        if (optionalStockOrder.isPresent()) {

            StockOrder stockOrderFound = optionalStockOrder.get();

            stockOrderFound.setPaymentMethod(PaymentMethod.valueOf(requestSetPaymentMethodStockOrder.getPaymentMethodCode()));

            stockOrderRepository.save(stockOrderFound);

            return ResponseEntity.ok().build();

        }

        throw new ValidationException("Order not found!");
    }

    @RequestMapping(value = "/out/finish/{id}", method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity finishStockOrder(@PathVariable Long id) {
        Optional<StockOrder> optionalStockOrder = stockOrderRepository.findById(id);

        if (optionalStockOrder.isPresent()) {

            StockOrder stockOrderFound = optionalStockOrder.get();

            for(StockOrderItem stockOrderItem : stockOrderFound.getItems()){
                if(stockOrderItem.getStatus() == OrderItemStatus.ACTIVE) {
                    Optional<Stock> optionalStock = stockRepository.findById(new StockPK(stockOrderFound.getBranch(), stockOrderItem.getProduct()));

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

            return ResponseEntity.ok().build();

        }

        throw new ValidationException("Order not found!");
    }



}
