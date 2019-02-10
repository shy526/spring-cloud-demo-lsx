package top.ccxh.orderservice.service;

import top.ccxh.orderservice.dto.OrderDto;

/**
 * 订单操作
 * @author ccxh
 */
public interface OrderService {
   OrderDto createOrder(OrderDto orderDto);
}
