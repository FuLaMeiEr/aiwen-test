package com.example.demo.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @author WangJiang
 * @ClassName ParseLocation
 * @apiNote todo what
 * @date 2020/6/4 10:52
 **/
@SuppressWarnings("unchecked")
public class ParseLocation {
    public static void main(String[] args) {
        File file = new File("E:\\IdeaProjects\\aiwen-test\\aiwen-test\\src\\main\\resources\\file\\LocList_en-US.xml");
        Map<String, LocationBean> result = getLocationInfo(file, null, true);
        file = new File("E:\\IdeaProjects\\aiwen-test\\aiwen-test\\src\\main\\resources\\file\\LocList_zh-CN.xml");
        result = getLocationInfo(file, result, false);
        System.out.println(result);
    }


    public Map<String, LocationBean> locationResult() {

        File file = new File("E:\\IdeaProjects\\aiwen-test\\aiwen-test\\src\\main\\resources\\file\\LocList_en-US.xml");
        Map<String, LocationBean> result = getLocationInfo(file, null, true);
        file = new File("E:\\IdeaProjects\\aiwen-test\\aiwen-test\\src\\main\\resources\\file\\LocList_zh-CN.xml");
        result = getLocationInfo(file, result, false);

        return result;
    }


    public static Map<String, LocationBean> getLocationInfo(File file, Map<String, LocationBean> result, boolean isEn) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Assert.notNull(document, "国际化版地址无法加载document对象");
            Element rootElement = document.getRootElement();
            Assert.notNull(rootElement, "国际化版地址无法加载根节点");
            result = result == null ? new HashMap<>(100) : result;
            for (Element countryElement : (List<Element>) rootElement.elements()) {
                LocationBean country = result.getOrDefault(countryElement.attributeValue("Code"), new LocationBean());
                country.setCode(countryElement.attributeValue("Code"));
                if (isEn) {
                    country.setEnName(countryElement.attributeValue("Name"));
                    result.put(countryElement.attributeValue("Code"), country);
                } else {
                    country.setZhName(countryElement.attributeValue("Name"));
                    result.put(country.getZhName(), country);
                    result.remove(country.getCode());
                }
                List<Element> provinces = provinces(countryElement);
                if (country.getChildren() == null) {
                    country.setChildren(new HashMap<>(12));
                    country.setData(new HashMap<>(12));
                }
                for (Element provinceElement : provinces) {
                    LocationBean province = country.getChildren().getOrDefault(provinceElement.attributeValue("Code"), new LocationBean());
                    province.setCode(provinceElement.attributeValue("Code"));
                    if (isEn) {
                        province.setEnName(provinceElement.attributeValue("Name"));
                    } else {
                        province.setZhName(provinceElement.attributeValue("Name"));
                        country.getData().put(province.getEnName(), province.getZhName());
                    }
                    country.getChildren().put(provinceElement.attributeValue("Code"), province);
                    for (Element cityElement : (List<Element>) provinceElement.elements()) {
                        LocationBean city = country.getChildren().getOrDefault(cityElement.attributeValue("Code"), new LocationBean());
                        city.setCode(cityElement.attributeValue("Code"));
                        if (isEn) {
                            city.setEnName(cityElement.attributeValue("Name"));
                        } else {
                            city.setZhName(cityElement.attributeValue("Name"));
                            country.getData().put(city.getEnName(), city.getZhName());
                        }
                        country.getChildren().put(cityElement.attributeValue("Code"), city);
                        for (Element districtElement : (List<Element>) cityElement.elements()) {
                            LocationBean district = country.getChildren().getOrDefault(districtElement.attributeValue("Code"), new LocationBean());
                            district.setCode(districtElement.attributeValue("Code"));
                            if (isEn) {
                                district.setEnName(districtElement.attributeValue("Name"));
                            } else {
                                district.setZhName(districtElement.attributeValue("Name"));
                                country.getData().put(district.getEnName(), district.getZhName());
                            }
                            country.getChildren().put(districtElement.attributeValue("Code"), district);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(result).orElseGet(Collections::emptyMap);
    }

    private static List<Element> provinces(Element countryElement) {
        List<Element> provinces = countryElement.elements();
        for (Element e : provinces) {
            if ("State".equals(e.getName()) && StringUtils.isEmpty(e.attributeValue("Name"))) {
                provinces = e.elements();
                break;
            }
        }
        return provinces;
    }

}
