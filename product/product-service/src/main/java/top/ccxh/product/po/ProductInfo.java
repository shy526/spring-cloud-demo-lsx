package top.ccxh.product.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ccxh
 */
@Data
@Entity
@Table(name="product_info")
public class ProductInfo implements Serializable {
    @Id
    /**产品Id*/
    private String productId;
    /**名称*/
    private String productName;
    /**单价*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDescription;
    /**小图*/
    private String productIcon;
    /**状态 0 上架 1 下架*/
    private Integer productStatus;
    /**产品分类*/
    private Integer categoryType;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
