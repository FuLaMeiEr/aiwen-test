package com.example.demo.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author WangJiang
 * @ClassName LocationBean
 * @apiNote todo what
 * @date 2020/6/4 10:42
 **/
@Data
public class LocationBean implements Serializable {

    private String code;

    private String zhName;

    private String enName;

    private Map<String, LocationBean> children;

    private Map<String, String> data;

}
