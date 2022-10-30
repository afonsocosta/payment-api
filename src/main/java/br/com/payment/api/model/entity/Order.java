package br.com.payment.api.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "_order")
@Data
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name =  "quantity")
    private Integer quantity;

    @Column(name =  "total_value")
    private BigDecimal totalValue;

    @Column(name =  "status")
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bayer_info_id")
    private BuyerInfo buyerInfo;


}
