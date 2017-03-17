
package com.weizhi.libra.web.validate;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.weizhi.libra.web.enums.ErrorCode;

/**
 * @author weizhi
 * @version Id: TradeValidator.java, v 0.1 2016/8/8 16:31 weizhi Exp $$
 * @Description 交易基础校验功能
 */
@Slf4j
@Component
public class BaseValidator {

    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;


    /**
     * 统一入参校验
     *
     * @param object
     * @param groups
     * @throws
     */
    public <T> void validate(T object, Class<?>... groups) throws Exception {
        Set<ConstraintViolation<T>> constraintViolations = localValidatorFactoryBean.validate(
                object, groups);
        if (constraintViolations != null && constraintViolations.size() > 0) {
            ConstraintViolation c = constraintViolations.iterator().next();
            throw new Exception(ErrorCode.FAIL_UNKNOWN_ERROR.getCode()
                    + ErrorCode.FAIL_UNKNOWN_ERROR.getMsg() + c.getMessage());
        }
    }


    /**
     * 获取IP
     * 
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
