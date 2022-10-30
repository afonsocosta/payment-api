package br.com.payment.api.model.vo;

import br.com.payment.api.model.entity.BuyerInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BuyerVO {

    private String name;

    private String document;

    private String email;

    private String address;

    @JsonIgnore
    public BuyerInfo toBuyerInfo() {
        BuyerInfo buyerInfo = new BuyerInfo();
        buyerInfo.setAddress(this.address);
        buyerInfo.setDocument(this.document);
        buyerInfo.setName(this.name);
        buyerInfo.setEmail(this.email);
        return buyerInfo;
    }

}
