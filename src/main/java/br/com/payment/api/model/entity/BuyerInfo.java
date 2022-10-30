package br.com.payment.api.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "buyer_info")
@Data
public class BuyerInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column(name =  "name")
    private String name;

    @Column(name =  "email")
    private String email;

    @Column(name =  "address")
    private String address;

    @Column(name =  "document")
    private String document;


}
