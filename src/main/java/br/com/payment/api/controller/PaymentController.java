package br.com.payment.api.controller;

import br.com.payment.api.factory.PaymentServiceFactory;
import br.com.payment.api.model.vo.PaymentRequestVO;
import br.com.payment.api.service.ProductService;
import br.com.payment.api.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ProductService productService;

    private final PaymentServiceFactory paymentServiceFactory;

    @PostMapping
    public ResponseEntity<Void> payment(@RequestBody PaymentRequestVO paymentRequestVO) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(paymentRequestVO.getPaymentType());
        paymentService.execute(paymentRequestVO);
        return ResponseEntity.ok().build();
    }

}
