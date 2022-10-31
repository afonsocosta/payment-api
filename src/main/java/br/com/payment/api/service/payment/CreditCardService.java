package br.com.payment.api.service.payment;

import br.com.payment.api.model.entity.Status;
import br.com.payment.api.model.vo.PaymentRequestVO;
import br.com.payment.api.service.OrderService;
import br.com.payment.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class CreditCardService extends BasePaymentService {


    public CreditCardService(ProductService productService, OrderService orderService) {
        super(productService, orderService);
    }

    @Override
    public Status sendAGatewayAuthorization(PaymentRequestVO paymentRequestVO) {
        log.info("CREDIT CARD GATEWAY REQUEST");
        return Status.APPROVED;
    }
}
