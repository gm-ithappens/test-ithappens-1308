package br.com.felipeomachado.apitest1308pulse.stockOrder.resources;

import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrderItem;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request.RequestAddOrderItem;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request.RequestRemoveOrderItem;
import br.com.felipeomachado.apitest1308pulse.stockOrder.services.StockOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/stockOrderItem")
public class StockOrderItemController {

    @Autowired
    private StockOrderItemService stockOrderItemService;

    @RequestMapping(value = "/out/add", method = RequestMethod.POST)
    public void addStockOrderItem(@Valid @RequestBody RequestAddOrderItem requestAddOrderItem) {
        stockOrderItemService.addStockOrderItem(requestAddOrderItem.toModel());
    }

    @RequestMapping(value = "/out/remove", method = RequestMethod.PUT)
    public void removeStockOrderItem(@Valid @RequestBody RequestRemoveOrderItem requestRemoveOrderItem) {
        Optional<StockOrderItem> optionalStockOrderItem = stockOrderItemService.findById(requestRemoveOrderItem.getStockOrderItemId());

        if (!optionalStockOrderItem.isPresent()) {
            throw new ValidationException("Item not found!");
        }

        stockOrderItemService.removeStockOrderItem(optionalStockOrderItem.get());
    }
}
