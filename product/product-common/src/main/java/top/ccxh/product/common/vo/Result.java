package top.ccxh.product.common.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 统一返回对象
 * @author ccxh
 */
@Data
public class Result<T> {
    /**错误码*/
    private Integer code;
    /**提示信息*/
    private String msg;
    /**返回对象*/
    private T data;

    public static <T> Result<T> succeed(T obj) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

}
