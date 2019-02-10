package top.ccxh.orderservice.exception;

/**
 * 订单异常
 * @author ccxh
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public OrderException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
    public static void assertTrue(boolean b,String message){
        if (!b){
            throw  new OrderException(message);
        }
    }
}
