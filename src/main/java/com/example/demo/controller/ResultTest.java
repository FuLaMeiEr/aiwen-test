package com.example.demo.controller;

import com.example.demo.bean.IpBean;
import com.example.demo.util.LocationBean;
import com.example.demo.util.ParseLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 14:04 2020/6/4
 **/
public class ResultTest {

    String ip = "2.16.36.8";

    ParseLocation parseLocation = new ParseLocation();

    private boolean b = false;


    String country = null;

    String pro = null;
    String city = null;

    public void test() {
        IpBean ipBean = new IpBean();
        ipBean.setCountry("中国");
        ipBean.setProvince("福建省");
        ipBean.setCity("福州市");



        Map<String, LocationBean> locationBeanMap = parseLocation.locationResult();

        LocationBean countryMap = locationBeanMap.get(ipBean.getCountry());


        Map<String, LocationBean> children = countryMap.getChildren();

        country = countryMap.getZhName();
        for (String key : children.keySet()){
            LocationBean location = children.get(key);
            if (ipBean.getProvince().contains(location.getZhName())){
                pro = location.getZhName();
            }
            if (ipBean.getCity().contains(location.getZhName())){
                city = location.getZhName();
            }
        }




       /* Map<String, LocationBean> children = countryMap.getChildren();
        LocationBean locationBean = children.get(ipBean.getCity());
        System.out.println(locationBean.getData());*/

       /*
        Map<String, String> data = countryMap.getData();

        for(String key : data.keySet()){
            String value = data.get(key);
            if (ipBean.getProvince().contains(value)){
                pro = value;

                if (ipBean.getCity().contains(value)){
                    city = value;
                }
            }

        }
*/

        System.out.println(country + "|" + pro + "|" + city);



    }

/*

    String i = "";
    String s = "";
    b = data.containsValue(ipBean.getProvince());

        if (b = true) {
        i = ipBean.getProvince();
    }

    boolean b1 = data.containsValue(ipBean.getCity());
        if (b1 = true) {
        s = ipBean.getCity();
    }

    String name = countryMap.getEnName();
        System.out.println(name + "-------" + i + "-------" + s);*/
}