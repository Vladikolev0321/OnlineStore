package com.example.onlineStore.services;

import com.example.onlineStore.dto.OrderDto;
import com.example.onlineStore.entities.Order;
import com.example.onlineStore.entities.Payment;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.mappers.OrderMapper;
import com.example.onlineStore.repositories.OrderRepo;
import com.example.onlineStore.repositories.PaymentRepo;
import com.example.onlineStore.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepository;
    private final UserRepo userRepository;
    private final PaymentRepo paymentRepository;
    private final OrderMapper orderMapper;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.convertDtoToEntity(orderDto);

        User user = userRepository.findById(orderDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Payment payment = paymentRepository.findById(orderDto.paymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        order.setUser(user);
        order.setPayment(payment);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.convertEntityToDto(savedOrder);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.convertEntityToDto(order);
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setTotalAmount(orderDto.totalAmount());

        if (!existingOrder.getUser().getId().equals(orderDto.userId())) {
            User user = userRepository.findById(orderDto.userId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingOrder.setUser(user);
        }
        if (!existingOrder.getPayment().getId().equals(orderDto.paymentId())) {
            Payment payment = paymentRepository.findById(orderDto.paymentId())
                    .orElseThrow(() -> new RuntimeException("Payment not found"));
            existingOrder.setPayment(payment);
        }

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.convertEntityToDto(updatedOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}
