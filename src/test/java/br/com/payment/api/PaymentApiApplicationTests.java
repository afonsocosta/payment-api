package br.com.payment.api;

import br.com.payment.api.model.entity.Product;
import br.com.payment.api.model.vo.ProductVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
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
