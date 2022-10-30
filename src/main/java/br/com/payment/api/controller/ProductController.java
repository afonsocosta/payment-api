package br.com.payment.api.controller;

import br.com.payment.api.model.entity.Product;
import br.com.payment.api.model.vo.ProductVO;
import br.com.payment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{code}")
    public ResponseEntity<Product> load(@PathVariable("code") String code) {
        return ResponseEntity.ok(productService.load(code));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductVO productVO) {
        return ResponseEntity.ok(productService.save(productVO));
    }
}
