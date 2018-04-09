package com.wu.boot.controller;

import com.wu.boot.dto.MessageDto;
import com.wu.boot.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() throws Exception {
        System.out.println("调用页面开始...");
        String ttt="bbb";
        String ddddd="aaaa";
        return "index";
    }
    @RequestMapping(value = "/test1",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test1(HttpServletRequest request,String name) throws Exception {
        String id=request.getParameter("id");
        System.out.println("参数："+id);
        System.out.println("参数："+name);
        return "汉字";
    }
    @RequestMapping(value = "/test2/{str}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<User> test2(User user,@PathVariable  String str) throws Exception {
        System.out.println("path:"+str);
        List<User> users=new ArrayList<>();
        users.add(user);
        return users;
    }
    @RequestMapping(value = "/test3",produces = "text/xml;charset=UTF-8")
    @ResponseBody
    public User test3() throws Exception {
        return new User(2,"李四");
    }
    @RequestMapping(value = "/test4",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test4(HttpServletRequest request) throws Exception {
        request.setAttribute("message","消息内容999999");
        if(true){
            throw new NullPointerException("内容有误");
        }
        return "成功";
    }

    @RequestMapping(value = "/test5",produces = "application/wucq;charset=UTF-8")
    @ResponseBody
    public MessageDto test5(@RequestBody  MessageDto dto) throws Exception {
        System.out.println(dto.getCode()+";"+dto.getMessage());
        return  dto;
    }

    @RequestMapping(value = "/test6")
    @ResponseBody
    public String test6(String dto) throws Exception {
        System.out.println(dto);
        return "成功";
    }
}
