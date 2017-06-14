package com.weizhi.libra.test.collection;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author Linchong
 * @version Id: CollectionTest.java, v 0.1 2017/5/16 11:16 Linchong Exp $$
 * @Description TODO
 */
public class CollectionTest {

    public  static void main(String[] args){

        List<String> list = new ArrayList<String>();
        list.add("weizhi");
        list.add("linchong");
        list.add("Linchong");
        list.add("linchong");


        Set<String> receSet = new HashSet<String>(list);

        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(receSet));


        System.out.println(JSON.parseArray(JSON.toJSONString(receSet),String.class));

        String url  = "http://hd-nn-1.meizu.gz:8088/proxy/application_1492421472032_546597/history/application_1492421472032_546597/1";

        List<String> appids = Arrays.asList(url.split("/"));
        for(String id:appids){
            if(id.startsWith("application_")){
                System.out.println(id);
            }
        }
    }
}


