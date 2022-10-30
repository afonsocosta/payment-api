package br.com.payment.api.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column(name =  "name")
    private String name;

    @Column(name =  "code")
    private String code;

    @Column(name =  "currency")
    private String currency;

    @Column(name =  "price")
    private BigDecimal price;

}
