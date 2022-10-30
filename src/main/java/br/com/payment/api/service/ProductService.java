package br.com.payment.api.service;

import br.com.payment.api.model.entity.Product;
import br.com.payment.api.model.vo.ProductVO;
import br.com.payment.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product load(String code) {
        return productRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product save(ProductVO productVO) {
        Product product = productVO.toProduct();
        System.out.println("produto: " + product);
        return productRepository.save(product);
    }

}
