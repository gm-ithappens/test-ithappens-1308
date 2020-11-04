package br.com.felipeomachado.apitest1308pulse.stockOrder.resources;


import br.com.felipeomachado.apitest1308pulse.paymentMethod.entities.PaymentMethod;
import br.com.felipeomachado.apitest1308pulse.paymentMethod.services.PaymentMethodService;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrder;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request.RequestFinishStockOrder;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request.RequestSetPaymentMethodStockOrder;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request.RequestStockOrderIn;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request.RequestStockOrderOut;
import br.com.felipeomachado.apitest1308pulse.stockOrder.services.StockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/stockOrder")
public class StockOrderController {

    @Autowired
    private StockOrderService stockOrderService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @RequestMapping(value = "/in", method = RequestMethod.POST)
    public void createInStockOrder(@Valid @RequestBody RequestStockOrderIn requestStockOrderIn) {
        stockOrderService.createInStockOrder(requestStockOrderIn.toModel());
    }

    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public void createOutStockOrder(@Valid @RequestBody RequestStockOrderOut requestStockOrderOut) {
        stockOrderService.createOutStockOrder(requestStockOrderOut.toModel());
    }

    @RequestMapping(value = "/out/paymentMethod", method = RequestMethod.PUT)
    public void setPaymentMethod(@Valid @RequestBody RequestSetPaymentMethodStockOrder requestSetPaymentMethodStockOrder) {
        Optional<StockOrder> optionalStockOrder = stockOrderService.findById(requestSetPaymentMethodStockOrder.getStockOrderId());

        if (!optionalStockOrder.isPresent()) {
            throw new ValidationException("Stock Order not found!");
        }

        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodService.findById(requestSetPaymentMethodStockOrder.getPaymentMethodId());

        if(!optionalPaymentMethod.isPresent()) {
            throw new ValidationException("Payment Method not found!");
        }

        stockOrderService.setPaymentMethod(optionalStockOrder.get(), optionalPaymentMethod.get());
    }

    @RequestMapping(value = "/out/finish", method = RequestMethod.PUT)
    public void finishStockOrder(@Valid @RequestBody RequestFinishStockOrder requestFinishStockOrder) {
        Optional<StockOrder> optionalStockOrder = stockOrderService.findById(requestFinishStockOrder.getStockOrderId());

        if (!optionalStockOrder.isPresent()) {
            throw new ValidationException("Stock Order not found!");
        }

        stockOrderService.finishStockOrder(optionalStockOrder.get());
    }


}
