package top.ccxh.orderservice.dto;

import lombok.Data;

/**
 * @author ccxh
 */
@Data
public class CartDto {
    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

}