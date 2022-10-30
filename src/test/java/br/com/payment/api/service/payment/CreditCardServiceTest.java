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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTest {

    @InjectMocks
    @Spy
    private CreditCardService creditCardService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    private Product product;

    @Before
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

    @Test(expected = RuntimeException.class)
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

        creditCardService.execute(paymentRequestVO);

        Mockito.verify(creditCardService, Mockito.times(0)).sendAGatewayAuthorization(eq(paymentRequestVO));


    }

}