package br.com.payment.api.model.vo;

import br.com.payment.api.model.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {

    private Long productId;

    private String productCode;

    private String currency;

    private BigDecimal price;

    private BigDecimal totalValue;

    private Integer quantity;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.productId);
        product.setCode(this.productCode);
        product.setPrice(this.price);
        product.setCurrency(this.currency);
        return  product;
    }
}
