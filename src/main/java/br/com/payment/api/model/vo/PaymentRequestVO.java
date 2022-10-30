package br.com.payment.api.model.vo;

import br.com.payment.api.model.entity.PaymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PaymentRequestVO {

    private ProductVO productVO;

    private BuyerVO buyerVO;

    private PaymentType paymentType;


}
