package com.kaixue.model.web;


import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController
{
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    //在同一个控制器中，注解了@ModelAttribute的方法实际上会在@RequestMapping方法之前被调用。
    //这里用于绑定参数
    //https://www.cnblogs.com/lemon-coke-pudding/p/12740365.html
    @ModelAttribute
    public void bindHttp(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
}
