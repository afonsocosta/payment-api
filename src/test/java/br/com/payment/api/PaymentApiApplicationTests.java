package br.com.payment.api;

import br.com.payment.api.model.entity.Product;
import br.com.payment.api.model.vo.ProductVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PaymentApiApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldTestCreateProduct() {

		ProductVO productVO = new ProductVO();
		productVO.setProductCode("ABCDE");
		productVO.setCurrency("BRL");
		productVO.setPrice(BigDecimal.TEN);

		HttpEntity<ProductVO> httpEntity = new HttpEntity<>(productVO);

		ResponseEntity<Product> response = this.testRestTemplate
				.exchange("/rest/v1/product", HttpMethod.POST, httpEntity, Product.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getCode(), "ABCDE");
	}

}
