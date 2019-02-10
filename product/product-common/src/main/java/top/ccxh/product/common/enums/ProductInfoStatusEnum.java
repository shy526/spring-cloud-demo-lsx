package top.ccxh.product.common.enums;

import lombok.Getter;

/**
 * 产品上下架枚举
 * @author ccxh
 */

@Getter
public enum ProductInfoStatusEnum {
    /**上架*/
    UP(0,"上架"),
    /**下架*/
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductInfoStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
