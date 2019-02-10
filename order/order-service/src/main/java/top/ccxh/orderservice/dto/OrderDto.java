package top.ccxh.orderservice.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import top.ccxh.order.common.form.OrderForm;
import top.ccxh.orderservice.po.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信Openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付
     */
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;


    public static OrderDto orderForm2OrderDto(OrderForm form){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress(form.getAddress());
        orderDto.setBuyerName(form.getName());
        orderDto.setBuyerPhone(form.getPhone());
        orderDto.setBuyerOpenid(form.getOpenid());
        List<OrderDetail> orderDetails = JSON.parseArray(form.getItems(), OrderDetail.class);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }
}
