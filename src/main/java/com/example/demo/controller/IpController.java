package com.example.demo.controller;

import com.example.demo.bean.IpBean;
import com.example.demo.util.IPLocate;
import com.example.demo.util.LocationBean;
import com.example.demo.util.ParseLocation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 10:45 2020/5/25
 **/
@RestController
public class IpController {

    private IPLocate ipLocate;

    ParseLocation parseLocation = new ParseLocation();

    boolean b = false;
    boolean b1 = false;
    private String ip;

    public IpController() {
        String fileName = "C:\\Users\\Wahaha\\Downloads\\IP_trial_2020M05_single_WGS84_dat\\IP_trial_20" +
                "20M05_single_WGS84.dat";

        ipLocate = IPLocate.loadDat(fileName);
    }


    @RequestMapping(value = "/ipPosition")
    public IpBean ipPosition(String ip) {


        IpBean ipBean = new IpBean();

        String result = ipLocate.locate_ip(ip);

        String[] ipResult = result.split("\\|");
        ipBean.setIp(ip);
        try {

            int length = ipResult.length;
            ipBean.setCountryCode(length < 4 ? "" : ipResult[3]);
            ipBean.setCountry(length < 6 ? "" : ipResult[5]);
            ipBean.setProvince(length < 7 ? "" : ipResult[6]);
            ipBean.setCity(length < 8 ? "" : ipResult[7]);
        } catch (Exception e) {
            System.out.println(ip);
            e.printStackTrace();
        }


        return ipBean;

    }


    @RequestMapping("/resultTest")
    @ResponseBody
    public String resultTest(String ip) {


        String pro = null;
        String city = null;

        IpBean ipBean = this.ipPosition(ip);


        Map<String, LocationBean> locationBeanMap = parseLocation.locationResult();

        LocationBean countryMap = locationBeanMap.get(ipBean.getCountry());


        Map<String, LocationBean> children = countryMap.getChildren();

        String country = countryMap.getZhName();
     /*    for (String key : children.keySet()) {
            LocationBean location = children.get(key);
            if (ipBean.getProvince().contains(location.getZhName())) {
                pro = location.getZhName();
            }
            if (ipBean.getCity().contains(location.getZhName())) {
                city = location.getZhName();
            }
        }*/


        String province = ipBean.getProvince().replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "");
        String ct = ipBean.getCity().replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "");
        String enName;
        for (String key : children.keySet()) {
            LocationBean location = children.get(key);
            enName = location.getEnName().replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "");
            if (province.contains(enName)) {
                pro = enName;
            }
            if (ct.contains(enName)) {
                city = enName;
            }
        }


        System.out.println(country + "|" + pro + "|" + city);

        return null;
    }


}
