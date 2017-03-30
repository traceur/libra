package com.weizhi.libra.common.security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

import org.junit.Test;

import javax.naming.event.ObjectChangeListener;

/**
 * Created by weizhi on 2015/8/4.
 */
public class ProviderTest {

    @Test
    public void test(){
        for(Provider p: Security.getProviders()){
            System.out.println(p);

            for(Map.Entry<Object, Object> entry:p.entrySet()){
                System.out.println("\t" + entry.getKey() + "\t"+entry.getValue());
            }
        }
    }
}
