package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.StockOrder;
import br.com.pwneo.estoque_back_end.models.dtos.StockOrderNewDTO;
import br.com.pwneo.estoque_back_end.repositories.StockOrderRepository;
import br.com.pwneo.estoque_back_end.repositories.SubsidiaryRepository;
import br.com.pwneo.estoque_back_end.repositories.supports.OperationRepository;
import br.com.pwneo.estoque_back_end.repositories.users.ClientRepository;
import br.com.pwneo.estoque_back_end.repositories.users.EmployeeRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockOrderService {

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OperationRepository operationRepository;


    public List<StockOrder> findAll() {
        return this.stockOrderRepository.findAll();
    }

    public StockOrder findById(Integer id) {
        return this.stockOrderRepository.findById(id).get();
    }

    /*
     * O método é responsável por receber os objeto DTO de StockOrder com os id's dos atributos e montar um objeto,
     * que deve ser armazenado no banco, posteriomente.
     * Com isso, deve criar um novo pedido de estoque no estoque escolhido.
     * Caso o produto já exista retornará um erro.
     * */
    public StockOrder create(StockOrderNewDTO stockOrderNewDTO) {
        StockOrder stockOrder = new StockOrder();
        stockOrder.setNote(stockOrderNewDTO.getNote());
        stockOrder.setSubsidiary(this.subsidiaryRepository.findById(stockOrderNewDTO.getSubsidiary()).get());
        stockOrder.setClient(this.clientRepository.findById(stockOrderNewDTO.getClient()).get());
        stockOrder.setEmployee(this.employeeRepository.findById(stockOrderNewDTO.getEmployee()).get());
        stockOrder.setOperation(this.operationRepository.findByDescription("SAIDA"));

        return this.stockOrderRepository.save(stockOrder);
    }

    public void delete(Integer id) {
        try {
            stockOrderRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}