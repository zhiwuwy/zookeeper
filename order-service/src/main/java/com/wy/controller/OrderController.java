package com.wy.controller;

import com.wy.pojo.Order;
import com.wy.pojo.Product;
import com.wy.utils.LoadBalance;
import com.wy.utils.RandomLoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyang
 * @date 2020/9/3 12:55
 * @description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    private LoadBalance loadBalance = new RandomLoadBalance();

    @RequestMapping("get/{id}")
    public Object getOrder(HttpServletRequest request, @PathVariable("id") Integer id){
        String host = loadBalance.chooseServiceHost();
        Product product = restTemplate.getForObject("http://" + host + "/product/get/1", Product.class);
        return new Order(id,"order",product);
    }
}
