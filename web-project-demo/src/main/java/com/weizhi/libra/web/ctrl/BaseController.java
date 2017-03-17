
package com.weizhi.libra.web.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weizhi.libra.web.common.BaseConstants;
import com.weizhi.libra.web.service.BaseService;

/**
 * @author weizhi
 * @version Id: BaseController.java, v 0.1 2016/8/16 10:17
 *          weizhi Exp $$
 * @Description TODO
 */
@Slf4j
@Controller
@RequestMapping(value = "/demo")
public class BaseController {
    @Resource
    private BaseService baseService;


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                BaseConstants.DATE_FORMAT_YYYYMMMDDHHMMSS);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    /**
     * @param text
     * @return
     */
    @RequestMapping(value = "/encrypt/db")
    @ResponseBody
    public String encryptDbPlainText(String text) {
        return baseService.encryptDbPwd(text);
    }


    private Integer tryString2Integer(String val) {
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {

        }
        return null;
    }

}
