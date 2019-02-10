package top.ccxh.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import top.ccxh.product.common.vo.Result;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class IControllerAdvice {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder binder
     */
    @InitBinder
    @ResponseStatus
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model 模型
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 错误处理
     *
     * @param e     异常
     * @param model model
     * @return Object
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object errorHandler(Exception e, Model model, HttpServletRequest request) {

        if (e instanceof BindException) {
            //参数校验异常
            String defaultMessage = ((BindException) e).getFieldError().getDefaultMessage();
            Result<Object> result = new Result<>();
            result.setCode(2);
            result.setMsg(defaultMessage);
            return result;
        }


        String servletPath = request.getServletPath();
        log.error(servletPath + "\n" + e.getMessage(), e);
        Result<Object> result = new Result<>();
        result.setCode(1);
        result.setMsg(e.getMessage());
        return result;
    }

}
