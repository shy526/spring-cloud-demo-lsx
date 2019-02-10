package top.ccxh.product.client.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ccxh.product.common.vo.InOrderStockeVo;
import top.ccxh.product.common.vo.OutPutProductVo;
import top.ccxh.product.common.vo.ProductVo;
import top.ccxh.product.common.vo.Result;

import java.util.List;

/**
 * @author ccxh
 */
@FeignClient(name = "product")
@RequestMapping("/product")
public interface ProductController {

    @GetMapping("list")
    Result<List<ProductVo>> list();

    @PostMapping("fork/order")
    Result<List<OutPutProductVo>> forkOrder(@RequestBody List<String> productIds);

    @PostMapping("stock")
    Result<List<OutPutProductVo>> decreaseStockProcess(@RequestBody List<InOrderStockeVo> inOrderStockeVos);
}