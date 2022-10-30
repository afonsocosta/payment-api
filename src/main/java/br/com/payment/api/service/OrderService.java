package br.com.payment.api.service;

import br.com.payment.api.model.entity.Order;
import br.com.payment.api.model.entity.Product;
import br.com.payment.api.repository.OrderRepository;
import br.com.payment.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
