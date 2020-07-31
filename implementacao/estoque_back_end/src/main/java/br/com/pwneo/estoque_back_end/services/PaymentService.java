package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.OrderItem;
import br.com.pwneo.estoque_back_end.models.Payment;
import br.com.pwneo.estoque_back_end.models.dtos.PaymentDTO;
import br.com.pwneo.estoque_back_end.repositories.OrderItemRepository;
import br.com.pwneo.estoque_back_end.repositories.PaymentRepository;
import br.com.pwneo.estoque_back_end.repositories.StockOrderRepository;
import br.com.pwneo.estoque_back_end.repositories.StockProductRepository;
import br.com.pwneo.estoque_back_end.repositories.supports.PaymentMethodRepository;
import br.com.pwneo.estoque_back_end.repositories.supports.StatusRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private StockProductRepository stockProductRepository;


    public List<Payment> findAll() {
        return this.paymentRepository.findAll();
    }

    public Payment findById(Integer id) {
        return this.paymentRepository.findById(id).get();
    }

    public Payment create(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        List<OrderItem> orderItems = new ArrayList<>();

        payment.setPaymentMethod(this.paymentMethodRepository.findById(paymentDTO.getPaymentMethod()).get());
        payment.setStatus(this.statusRepository.findById(paymentDTO.getStatus()).get());
        payment.setStockOrder(this.stockOrderRepository.findById(paymentDTO.getStockOrder()).get());

        this.orderItemRepository
                .findAll()
                .forEach(orderItem -> {
                    if (orderItem.getStockOrder().equals(payment.getStockOrder())) {
                        payment.setTotal(payment.getStockOrder().getTotalPrice());
                        orderItems.add(orderItem);
                    }
                });

        this.operationsAfterPayment();

        Payment paymentMade = this.paymentRepository.save(payment);
        return this.paymentRepository.save(paymentMade);
    }

    public boolean delete(Integer id) {
        try {
            paymentRepository.deleteById(id);
            return true;

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void operationsAfterPayment() {
        this.orderItemRepository.findAll().forEach(orderItem -> {
            orderItem.setStatus(this.statusRepository.findByDescription("PROCESSADO"));
            this.stockProductRepository.findAll().forEach(stockProductCurrent -> {
                if (orderItem.getStockProduct().getId().equals(stockProductCurrent.getId())) {
                    stockProductCurrent.setQuantity(stockProductCurrent.getQuantity() - orderItem.getStockProduct().getQuantity());
                }
            });
        });
    }
}