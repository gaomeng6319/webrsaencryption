package com.wying.webrsaencryption.controller;

import com.wying.webrsaencryption.util.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final  Logger logger= LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("checkLogin")
    @ResponseBody
    public Map<String,Object> checkLogin(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resMap=new HashMap<>();
        String userName=request.getParameter("userName");
        String userPassword=request.getParameter("userPassword");
        logger.info("密文userName："+userName+" userPassword："+userPassword);
        try {
            userName=RSAUtils.myEncryptByPublicKey(userName,request);
            userPassword=RSAUtils.myEncryptByPublicKey(userPassword,request);
            logger.info("原文userName："+userName+" userPassword："+userPassword);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("code","301");
            resMap.put("message","操作失败!");
            return  resMap;
        }
        resMap.put("code","200");
        resMap.put("message","操作成功!");
        return  resMap;


    }
}
