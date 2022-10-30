package br.com.payment.api.service.payment;

import br.com.payment.api.model.entity.Status;
import br.com.payment.api.model.vo.PaymentRequestVO;
import br.com.payment.api.service.OrderService;
import br.com.payment.api.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class PaypalService extends BasePaymentService {

    public PaypalService(ProductService productService, OrderService orderService) {
        super(productService, orderService);
    }

    @Override
    public Status sendAGatewayAuthorization(PaymentRequestVO paymentRequestVO) {
        return Status.RECEIVED;
    }
}

