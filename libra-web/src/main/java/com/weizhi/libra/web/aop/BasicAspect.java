
package com.weizhi.libra.web.aop;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;

import com.weizhi.libra.web.util.SeqNoUtil;

/**
 * @author weizhi
 * @version Id: BasicAspect.java, v 0.1 2016/7/27 11:29 weizhi Exp $$
 * @Description 切面类，用于输出请求和返回日志，处理Exception
 */
@Slf4j
public class BasicAspect {
    private static final String LOG_TRACE_ID = "traceid";


    public String logServiceAccess(ProceedingJoinPoint joinPoint) {
        String clazzName = joinPoint.getTarget().getClass().getSimpleName(); //获得类名
        String methodName = joinPoint.getSignature().getName(); //获得方法名
        Object[] args = joinPoint.getArgs(); //获得参数列表
        //        Object argObject = args[0]; //参数
        initMDC(args);
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        log.info("Start to handle request-[{}.{}]-[{}]", clazzName, methodName,
                ArrayUtils.toString(args));
        String response = null;
        try {
            response = (String) joinPoint.proceed();
        } catch (Throwable throwable) {
            //            if (throwable instanceof CarinaServiceException) {
            //                CarinaServiceException e = (CarinaServiceException) throwable;
            //                response = new CarinaResult();
            //                response.setCode(e.getErrorCode());
            //                response.setMsg(e.getErrorMsg());
            //                response.setDesc(e.getErrorDesc());
            //            } else {
            //                log.error("unknown error", throwable);
            //                response = new CarinaResult();
            //                response.setCode("999999");
            //                response.setMsg("未知异常");
            //                response.setDesc(throwable.getMessage());
            //            }
        }
        stopwatch.stop();
        log.info("Finish handling reponse-[{}.{}]-[{}] ,elapsed:{}", clazzName, methodName,
                response, stopwatch.toString());
        return response;
    }


    protected void initMDC(Object[] args) {
        if (args != null && args.length > 0) {
            MDC.put(LOG_TRACE_ID, SeqNoUtil.getLogSeqNo());
        }
    }
}
