package com.niezhiliang.restful.api.trans.safe.controller;

import com.alibaba.fastjson.JSON;
import com.niezhiliang.restful.api.trans.safe.mo.LoginMO;
import com.niezhiliang.restful.api.trans.safe.utils.AesEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @Date 2019/2/16 上午11:11
 */
@Controller
@Slf4j
public class LoginController {

    private final static String USER_NAME = "admin";

    private final static String PASSWORD = "123456";

    @GetMapping(value = "login")
    public String loginUI() {
        return "login";
    }


    @PostMapping(value = "login")
    @ResponseBody
    public String login(String params) {
        try {
            params = AesEncryptUtil.desEncrypt(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginMO loginMO = JSON.parseObject(params,LoginMO.class);

        if (USER_NAME.equals(loginMO.getUsername()) && PASSWORD.equals(loginMO.getPassword())) {
            log.info("登录成功,时间：{}",System.currentTimeMillis());
        } else {
            log.info("登录失败,账号或密码错误。时间：{}",System.currentTimeMillis());
        }

        String resule = null;
        try {
            resule = AesEncryptUtil.encrypt("hello world");

            log.info("响应结果加密:{}",resule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resule;
    }

}
