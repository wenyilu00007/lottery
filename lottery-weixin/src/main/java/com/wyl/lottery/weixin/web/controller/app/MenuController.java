package com.wyl.lottery.weixin.web.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Auther: wang.xu
 * @Date: 2018-11-08 2018/11/8:11:17
 */
@Controller
@RequestMapping("${server.servlet.context-path}/menu")
public class MenuController   {

    /**
     * 寄快递
     * @return
     */
    @GetMapping("/placeorderrouter")
    public String placeOrderRouter() {
        return "redirect:/wxweb/placeorderrouter";
    }

    /**
     * 寄快递
     * @return
     */
    @GetMapping("/search")
    public String search() {
        return "redirect:/wxweb/search";
    }
    /**
     * 我的订单
     * @return
     */
    @GetMapping("/orderlist")
    public String orderlist() {
        return "redirect:/wxweb/orderlist";
    }

}
