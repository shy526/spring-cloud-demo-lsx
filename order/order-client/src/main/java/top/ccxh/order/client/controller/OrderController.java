package top.ccxh.order.client.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ccxh.order.common.form.OrderForm;
import top.ccxh.product.common.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * 订单
 * @author ccxh
 */
@FeignClient(name="order")
@RequestMapping("/order")
public interface OrderController {
    @PostMapping("create")
    /**
     *创建订单
     * @param  form 表单数据
     * @error  error 错误结果
     * @return  Result<List<Map<String,String>>>
     */
    Result<List<Map<String,Object>>>createOrder(@Validated OrderForm form);

}
