package com.wy.controller;

import com.wy.pojo.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyang
 * @date 2020/9/3 11:37
 * @description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/get/{id}")
    public Object getProject(HttpServletRequest request, @PathVariable("id")Integer id){
        int localPort = request.getLocalPort();
        return new Product(id,"productName:" + localPort);
    }
}
