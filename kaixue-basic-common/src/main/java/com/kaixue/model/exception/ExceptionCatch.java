package com.kaixue.model.exception;


import com.alibaba.druid.sql.visitor.functions.If;
import com.google.common.collect.ImmutableMap;
import com.kaixue.model.response.CommonCode;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 这个类的详细作用可以查看https://www.cnblogs.com/UncleWang001/p/10949318.html
// 这个注解表示这个类中被@ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，
// 会作用在所有@Controller类中被 @RequestMapping 注解的方法上,控制器增强
// 异常捕获有三种方式详见https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html
// 异常一旦捕获不会再传递
@ControllerAdvice
public class ExceptionCatch
{
    // 因为这个类在manage-cms中被扫描，被引用，所以实际上是使用了manage-cms配置的日志文件
    // slf4j相当于是一个接口规范，具体实现是manage-cms中使用的logback
    // https://blog.csdn.net/chszs/article/details/8653460
    // https://www.cnblogs.com/hanszhao/p/9754419.html
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    // 这个类是谷歌的工具类，这个map线程安全且不可更改
    // 泛型具体见https://blog.csdn.net/fw0124/article/details/42296283
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> immutableMap;

    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    static {
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }

    // ExceptionHandler注解的方法支持入参为Exception类，
    // 被@SessionAttribute和@RequestAttribute标注的参数，
    // HttpServletResponse，HttpServletRequest， HttpSession
    // 可以返回@ResponseBody，ModelAndView等
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ResponseResult customExceptionHandler(CustomException customException)
    {
        LOGGER.error("捕获异常:{}\r\nexception:",customException.getMessage(),customException);
        return new ResponseResult(customException.getResultCode());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception exception)
    {
        LOGGER.error("catch exception:{},",exception.getMessage(),exception);
        if (immutableMap == null)
            immutableMap = builder.build();
        ResultCode resultCode = immutableMap.get(exception.getClass());
        if (resultCode == null)
            return new ResponseResult(CommonCode.SERVER_ERROR);
        return new ResponseResult(resultCode);
    }
}
