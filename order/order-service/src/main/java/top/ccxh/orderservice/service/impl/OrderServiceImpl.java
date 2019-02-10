package top.ccxh.orderservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import top.ccxh.orderservice.dao.OrderDetailDao;
import top.ccxh.orderservice.dao.OrderMasterDao;
import top.ccxh.orderservice.dto.OrderDto;
import top.ccxh.orderservice.exception.OrderException;
import top.ccxh.orderservice.po.OrderDetail;
import top.ccxh.orderservice.po.OrderMaster;
import top.ccxh.orderservice.service.OrderService;
import top.ccxh.product.client.controller.ProductController;
import top.ccxh.product.common.vo.InOrderStockeVo;
import top.ccxh.product.common.vo.OutPutProductVo;
import top.ccxh.product.common.vo.Result;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private RestTemplate restTemplate;
    private String forkOrder = "http://product/product/fork/order";
    @Autowired
    private ProductController productController;
    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        String orderId="O"+System.currentTimeMillis();
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
        BigDecimal sum = new BigDecimal(0);
        List<String> collect = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        HttpEntity<List<String>> httpEntity = new HttpEntity<List<String>>(collect, null);
        ResponseEntity<Result<List<OutPutProductVo>>> response = restTemplate.exchange(forkOrder, HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<Result<List<OutPutProductVo>>>() {
                });

        OrderException.assertTrue(response.getStatusCode().equals(HttpStatus.OK), "请求失败");
        Result<List<OutPutProductVo>> body = response.getBody();
        OrderException.assertTrue(body.getCode().equals(0), "请求错误");
        List<OutPutProductVo> data = body.getData();
        OrderException.assertTrue(data.size() == orderDetailList.size(), "产品不匹配");
        ArrayList<InOrderStockeVo> inOrderStockeVos = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            for (OutPutProductVo outPutProductVo : data) {
                if (orderDetail.getProductId().equals(outPutProductVo.getProductId())) {
                    BigDecimal price = outPutProductVo.getProductPrice();
                    BigDecimal num = new BigDecimal(orderDetail.getProductQuantity());
                    sum = price.multiply(num).add(sum);
                    BeanUtils.copyProperties(outPutProductVo,orderDetail);
                    orderDetail.setDetailId("D"+System.nanoTime());
                    orderDetail.setOrderId(orderId);
                    orderDetailDao.saveAndFlush(orderDetail);
                    InOrderStockeVo orderStockeVo = new InOrderStockeVo();
                    orderStockeVo.setProductId(orderDetail.getProductId());
                    orderStockeVo.setProductQuantity(orderDetail.getProductQuantity());
                    inOrderStockeVos.add(orderStockeVo);
                }
            }
        }
        Result<List<OutPutProductVo>> result = productController.decreaseStockProcess(inOrderStockeVos);
        OrderException.assertTrue(result.getCode().equals(0),"库存不足");
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(sum);
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        orderMasterDao.save(orderMaster);
        return orderDto;
    }
}
