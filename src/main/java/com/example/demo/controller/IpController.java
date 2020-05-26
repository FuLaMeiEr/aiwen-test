package com.example.demo.controller;

import com.example.demo.bean.IpBean;
import com.example.demo.util.IPLocate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 10:45 2020/5/25
 **/
@RestController
public class IpController {


    @RequestMapping(value = "/ipPosition")
    public IpBean ipPosition(String ip) {
        String fileName = "C:\\Users\\Wahaha\\Downloads\\IP_trial_2020M05_single_WGS84_dat\\IP_trial_2020M05_single_WGS84.dat";
        IPLocate iplocate = IPLocate.loadDat(fileName);

        IpBean ipBean = new IpBean();

        String result = iplocate.locate_ip(ip);

        String[] ipResult = result.split("\\|");
        ipBean.setIp(ip);
        ipBean.setCountry(ipResult[5]);
        ipBean.setProvince(ipResult[6]);
        ipBean.setCity(ipResult[7]);

        return ipBean;
    }


}
