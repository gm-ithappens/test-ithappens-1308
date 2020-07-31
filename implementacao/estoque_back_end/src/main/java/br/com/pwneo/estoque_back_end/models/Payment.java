package br.com.pwneo.estoque_back_end.models;

import br.com.pwneo.estoque_back_end.models.supports.PaymentMethod;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private PaymentMethod paymentMethod;

    private StockOrder stockOrder;
}
