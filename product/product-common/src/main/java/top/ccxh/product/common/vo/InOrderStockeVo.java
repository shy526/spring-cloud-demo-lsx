package top.ccxh.product.common.vo;

import lombok.Data;

/**
 * 库存修改对象
 * @author ccxh
 */
@Data
public class InOrderStockeVo {
    private String productId;
    private  Integer productQuantity;
}
