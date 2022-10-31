package br.com.payment.api.service.payment;

import br.com.payment.api.model.entity.Order;
import br.com.payment.api.model.entity.PaymentType;
import br.com.payment.api.model.entity.Product;
import br.com.payment.api.model.entity.Status;
import br.com.payment.api.model.vo.BuyerVO;
import br.com.payment.api.model.vo.PaymentRequestVO;
import br.com.payment.api.model.vo.ProductVO;
import br.com.payment.api.service.OrderService;
import br.com.payment.api.service.ProductService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class CreditCardServiceTest {

    @InjectMocks
    @Spy
    private CreditCardService creditCardService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    private Product product;

    @BeforeEach
    public void init() {
        this.product = new Product();
        this.product.setId(1l);
        this.product.setCode("ABCDE");
        this.product.setPrice(BigDecimal.valueOf(25.00d));
        this.product.setCurrency("BRL");
    }

    @Test
    public void givenRequestPaymentWhenExecutePaymentShouldCreateOrder() {
        ProductVO productVO = new ProductVO();
        productVO.setProductId(1l);
        productVO.setProductCode("ABCDE");
        productVO.setQuantity(1);
        productVO.setTotalValue(BigDecimal.valueOf(25.00d));
        BuyerVO buyerVO = new BuyerVO();
        buyerVO.setName("TESTE");
        buyerVO.setEmail("email@teste.com");
        buyerVO.setDocument("12345678909");
        buyerVO.setAddress("Rua tal, 55 - bairro, cidade - uf, cep");
        PaymentRequestVO paymentRequestVO = new PaymentRequestVO();
        paymentRequestVO.setPaymentType(PaymentType.CREDIT_CARD);
        paymentRequestVO.setBuyerVO(buyerVO);
        paymentRequestVO.setProductVO(productVO);

        Mockito.doReturn(this.product).when(this.productService).load(Mockito.anyString());

        Order order = creditCardService.execute(paymentRequestVO);

        assertEquals(Status.APPROVED, order.getStatus());

        Mockito.verify(creditCardService, Mockito.times(1)).sendAGatewayAuthorization(eq(paymentRequestVO));

    }

    public void givenRequestPaymentWithMoreThanOneItemAndWrongTotalValueWhenExecutePaymentShouldNotCreateOrder() {
        ProductVO productVO = new ProductVO();
        productVO.setProductId(1l);
        productVO.setProductCode("ABCDE");
        productVO.setQuantity(2);
        productVO.setTotalValue(BigDecimal.valueOf(25.00d));
        BuyerVO buyerVO = new BuyerVO();
        buyerVO.setName("TESTE");
        buyerVO.setEmail("email@teste.com");
        buyerVO.setDocument("12345678909");
        buyerVO.setAddress("Rua tal, 55 - bairro, cidade - uf, cep");
        PaymentRequestVO paymentRequestVO = new PaymentRequestVO();
        paymentRequestVO.setPaymentType(PaymentType.CREDIT_CARD);
        paymentRequestVO.setBuyerVO(buyerVO);
        paymentRequestVO.setProductVO(productVO);

        Mockito.doReturn(this.product).when(this.productService).load(Mockito.anyString());

        Throwable exception = assertThrows(RuntimeException.class,
                ()->{creditCardService.execute(paymentRequestVO);} );

        Mockito.verify(creditCardService, Mockito.times(0)).sendAGatewayAuthorization(eq(paymentRequestVO));


    }

}