
package com.weizhi.libra.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @author weizhi
 * @version Id: TradeBaseAblitityService.java, v 0.1 2016/8/16 10:19 weizhi
 *          Exp $$
 * @Description TODO
 */
@Service
public class BaseService {
    private static final String OPEN_ON = "on";


    @Resource
    //    private PartnerMapper           partnerMapper;
    /**
     * 加密数据
     * 
     * @param text
     * @return
     */
    public String encryptDbPwd(String text) {
        //        return passwordDecrypter.encrypt(text);
        return null;
    }
}
