package top.ccxh.product.exception;

public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }

    public static void assertTrue(boolean v,String message){
        if (!v){
            throw  new  ProductException(message);
        }
    }
}
