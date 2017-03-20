package com.weizhi.libra.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * java bean 反射的方法
 * 
 * @author Michael
 */
public class BeanRefUtil {

    /**
     * 取Bean的属性和值对应关系的MAP
     * 
     * @param bean
     * @return Map
     */
    public static Map<String, Object> getFieldValueMap(Object bean) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
                Object result = null;
                if (null != fieldVal) {
                    result = fieldVal;
                }
                valueMap.put(field.getName(), result);
            } catch (Exception e) {
                continue;
            }
        }
        return valueMap;

    }


    /**
     * 取Bean的属性和值对应关系的json
     * 
     * @param bean
     * @return JSONObject
     */
    public static JSONObject getFieldValueJSON(Object bean) {
        JSONObject ret = new JSONObject();
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
                Object result = null;
                if (null != fieldVal) {
                    result = fieldVal;
                    if (fieldVal instanceof Date) {
                        result = ((Date) fieldVal).getTime();
                    }
                }

                ret.put(field.getName(), result);
            } catch (Exception e) {
                continue;
            }
        }
        return ret;

    }


    /**
     * set属性的值到Bean
     * 
     * @param bean
     */
    public static void setFieldValue(Object bean, Object valueObject) {
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldSetName = parSetName(field.getName());
                if (!checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                Object value = null;
                if (valueObject instanceof Map) {
                    value = ((Map) valueObject).get(field.getName());
                } else if (valueObject instanceof JSONObject) {
                    value = ((JSONObject) valueObject).get(field.getName());
                }
                if (null != value && !"".equals(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        fieldSetMet.invoke(bean, value);
                    } else if ("Date".equals(fieldType)) {
                        Date temp = parseDate(value.toString());
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value.toString());
                        fieldSetMet.invoke(bean, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value.toString());
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value.toString());
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value.toString());
                        fieldSetMet.invoke(bean, temp);
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }

    }


    /**
     * 格式化string为Date
     * 
     * @param datestr
     * @return date
     */
    public static Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0) {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            } else {

                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 日期转化为String
     * 
     * @param date
     * @return date string
     */
    public static String fmtDate(Date date) {
        if (null == date) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 判断是否存在某属性的 set方法
     * 
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断是否存在某属性的 get方法
     * 
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    private static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equalsIgnoreCase(met.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 拼接某属性的 get方法
     * 
     * @param fieldName
     * @return String
     */
    private static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }


    /**
     * 拼接在某属性的 set方法
     * 
     * @param fieldName
     * @return String
     */
    private static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

}
