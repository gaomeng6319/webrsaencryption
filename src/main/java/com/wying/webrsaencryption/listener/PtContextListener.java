package com.wying.webrsaencryption.listener;

import com.wying.webrsaencryption.util.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

@WebListener
public class PtContextListener implements ServletContextListener {

    private static Logger logger= LoggerFactory.getLogger(PtContextListener.class);

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * 启动时生成 公钥 私钥
     * @param arg0
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        logger.info(" MyContextListener contextInitialized ");


        try {
            HashMap<String, Object> map = RSAUtils.getKeys();
            //生成公钥和私钥
            RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
            //私钥
            arg0.getServletContext().setAttribute("privateKey", privateKey);


            //公钥信息，用于加密 公钥指数
            String publicKeyExponent = publicKey.getPublicExponent().toString(16);
            logger.info("publicKeyExponent:"+publicKeyExponent);
            //模
            String  publicKeyModulus = publicKey.getModulus().toString(16);
            logger.info("publicKeyModulus:"+publicKeyModulus);

            arg0.getServletContext().setAttribute("publicKeyExponent", publicKeyExponent);
            arg0.getServletContext().setAttribute("publicKeyModulus", publicKeyModulus);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
