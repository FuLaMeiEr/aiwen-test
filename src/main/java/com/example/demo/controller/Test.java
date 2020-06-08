package com.example.demo.controller;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 14:15 2020/6/4
 **/
public class Test {
    public static void main(String[] args) {

        /*ResultTest resultTest = new ResultTest();

        resultTest.test();*/

        String i = "Sao Paulo";
        String s = "Sao Paulo";

        String a1 = i.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "");
        String a2 = s.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "");
        boolean c = a1.equals(a2);
        System.out.println(c);

    }
}
