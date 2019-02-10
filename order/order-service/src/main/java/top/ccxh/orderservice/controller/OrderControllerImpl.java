package top.ccxh.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ccxh.order.client.controller.OrderController;
import top.ccxh.order.common.form.OrderForm;
import top.ccxh.orderservice.dto.OrderDto;
import top.ccxh.orderservice.exception.OrderException;
import top.ccxh.orderservice.po.OrderDetail;
import top.ccxh.orderservice.service.OrderService;
import top.ccxh.product.common.vo.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 实现类
 * @author ccxh
 */
@Controller
public class OrderControllerImpl implements OrderController  {
    @Autowired
    private OrderService orderService;
    @Override
    @ResponseBody
   public Result<List<Map<String,Object>>> createOrder(@Validated OrderForm form) {

        OrderDto orderDto = OrderDto.orderForm2OrderDto(form);
        if (orderDto.getOrderDetailList().size()<=0){
            throw new OrderException(0,"该订单没有商品");
        }
        orderDto = orderService.createOrder(orderDto);
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (OrderDetail orderDetail:orderDetailList){
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("productId",orderDetail.getProductId());
            objectObjectHashMap.put("orderId",orderDetail.getOrderId());
            objectObjectHashMap.put("number",orderDetail.getProductQuantity()+"");
            maps.add(objectObjectHashMap);
        }
        return  Result.succeed(maps);
    }

}
