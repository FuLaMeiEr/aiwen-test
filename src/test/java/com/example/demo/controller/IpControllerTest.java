package com.example.demo.controller;

import com.example.demo.bean.IpBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;


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

    private String ip;

    private IpBean ipBean;

    @Test
    void ipPosition() throws IOException {


        Runtime r = Runtime.getRuntime();
        r.gc();

        long startTime = System.currentTimeMillis();
        long startMem = r.freeMemory();

        ArrayList<String> ipLists = this.readFromTextFile();

        for (int i = 0; i < ipLists.size(); i++) {
            ipBean = ipController.ipPosition(ipLists.get(i));
        }

        long endMem = r.freeMemory();

        long endTime = System.currentTimeMillis();

        long consumeMem = startMem - endMem;
        long consumeTime = endTime - startTime;

        System.out.println(ipBean);
        System.out.println("消耗内存" + consumeMem + "ms");
        System.out.println("所用时间" + consumeTime + "b");
    }


    public ArrayList<String> readFromTextFile() throws IOException {
        ArrayList<String> strArray = new ArrayList<String>();
        File filename = new File("src/main/resources/file/IpFile.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while (line != null) {
            strArray.add(line);
            line = br.readLine();
        }
        return strArray;
    }


}