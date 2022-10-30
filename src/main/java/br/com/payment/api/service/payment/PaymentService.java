package br.com.payment.api.service.payment;

import br.com.payment.api.model.entity.Order;
import br.com.payment.api.model.entity.Status;
import br.com.payment.api.model.vo.PaymentRequestVO;

public interface PaymentService {

    default Order execute(PaymentRequestVO paymentRequestVO){
        validate(paymentRequestVO);
        Order order = new Order();
        order.setBuyerInfo(paymentRequestVO.getBuyerVO().toBuyerInfo());
        order.setQuantity(paymentRequestVO.getProductVO().getQuantity());
        order.setTotalValue(paymentRequestVO.getProductVO().getTotalValue());
        order.setProduct(paymentRequestVO.getProductVO().toProduct());
        Status status = sendAGatewayAuthorization(paymentRequestVO);
        order.setStatus(status);
        return order;
    };

    void validate(PaymentRequestVO paymentRequestVO);

    Status sendAGatewayAuthorization(PaymentRequestVO paymentRequestVO);

    Order saveOrder(Order order);

}
