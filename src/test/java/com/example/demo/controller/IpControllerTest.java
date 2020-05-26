package com.example.demo.controller;

import com.example.demo.bean.IpBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 15:38 2020/5/25
 **/
@SpringBootTest
class IpControllerTest {

    @Autowired
    private IpController ipController;

    @Test
    void ipPosition() {

        IpBean ipBean = ipController.ipPosition("1.11.255.25");
        //IpBean ipBean = ipController.ipPosition("17563647");
        System.out.println(ipBean);
    }


}