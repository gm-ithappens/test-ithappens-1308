package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer productId;
    private Integer quantity;

    public ProductDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductDTO() {
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
