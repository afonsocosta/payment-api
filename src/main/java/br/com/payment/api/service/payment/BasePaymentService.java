package br.com.payment.api.service.payment;

import br.com.payment.api.model.entity.Order;
import br.com.payment.api.model.entity.Product;
import br.com.payment.api.model.vo.PaymentRequestVO;
import br.com.payment.api.service.OrderService;
import br.com.payment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public abstract sealed class BasePaymentService implements PaymentService permits CreditCardService, PixService, PaypalService, BankSlipService{

    private final ProductService productService;

    private final OrderService orderService;

    @Override
    public void validate(PaymentRequestVO paymentRequestVO) {
        Product product = productService.load(paymentRequestVO.getProductVO().getProductCode());
        BigDecimal totalValue = product.getPrice().multiply(BigDecimal.valueOf(paymentRequestVO.getProductVO().getQuantity())).setScale(2);
        if(totalValue.compareTo(paymentRequestVO.getProductVO().getTotalValue()) != 0){
            log.error("Total value is not valid", paymentRequestVO.getProductVO().getProductCode());
            throw new RuntimeException("Total value is not valid");
        }
    }

    @Override
    public Order saveOrder(Order order) {
        return orderService.save(order);
    }
}
