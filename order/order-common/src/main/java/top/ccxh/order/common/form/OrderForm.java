package top.ccxh.order.common.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 创建订单数据
 * @author ccxh
 */
@Data
public class OrderForm implements Serializable {
    /**收件人*/
    @NotEmpty(message ="买家姓名不能为空" )
    private String name;
    /**电话**/
    @NotEmpty(message ="买家电话不能为空" )
    @Length(min = 11,max = 11,message = "买家电话格式不正确")
    private String phone;
    /**地址*/
    @NotEmpty(message ="买家地址不能为空" )
    private String address;
    /**wx openId*/
    @NotEmpty(message ="openid 不能为空" )
    private String openid;
    /**购买的商品集合*/
    @NotNull(message = "购买商品不能为空")
    @Size(min=1,message = "购买商品不能为空")
    private String items;
}
