package com.tiny.security.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

/**
 * Jackson转换工具类
 */
public class JsonUtils {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    /** 日起格式化 */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        //对象的所有字段全部列入
        JSON_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JSON_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //取消默认转换timestamps形式
        JSON_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转json的错误
        JSON_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        JSON_MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd
        JSON_MAPPER.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        JSON_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        //大小写脱敏 默认为false 
        JSON_MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
    }

    public static ObjectMapper getJsonMapper(){
        return JSON_MAPPER;
    }

    public static ObjectNode newObject(){
        return JSON_MAPPER.createObjectNode();
    }

    /**
     * object转JsonNode
     * @param obj
     * @return
     */
    public static ObjectNode object2JsonNode(Object obj){
        if (obj == null) {
            return null;
        }
        try {
            return (ObjectNode) JSON_MAPPER.valueToTree(obj);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("转换Json出错", e);
        }
    }
    public static ObjectNode string2JsonNode(String str){
        if (org.springframework.util.StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (ObjectNode) JSON_MAPPER.readTree(str);
        } catch (IOException e) {
            throw new RuntimeException("json转换出错", e);
        }
    }
    /**
     * 对象转Json格式字符串
     * @param obj 对象
     * @return Json格式字符串
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : JSON_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("转换Json出错", e);
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("转换Json出错", e);
        }
    }

    /**
     * 字符串转换为自定义对象
     * @param str 要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    public static <T> T string2Obj(String str, Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : JSON_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            throw new RuntimeException("json转换出错", e);
        }
    }

    public static <T> T obj2Class(Object obj, Class<T> clazz){
        if(obj == null || clazz == null){
            return null;
        }
        try {
            String str = obj instanceof String ? (String) obj : JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return clazz.equals(String.class) ? (T) str : JSON_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            throw new RuntimeException("json转换出错", e);
        }
    }

    /**
     * 字符串转换为自定义对象集合
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2List(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : JSON_MAPPER.readValue(str, typeReference));
        } catch (IOException e) {
            throw new RuntimeException("json转换出错", e);
        }
    }

    public static <T> T obj2List(Object obj, TypeReference<T> typeReference) {
        if (obj == null || typeReference == null) {
            return null;
        }
        try {
            String str = obj2String(obj);
            return (T) JSON_MAPPER.readValue(str, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("json转换出错", e);
        }
    }

    /**
     * 字符串转换为map
     * @param str
     * @return
     */
    public static Map<String, Object> string2Map(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return JSON_MAPPER.readValue(str, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException("json转换出错", e);
        }
    }

    public static Map<String, Object> obj2Map(Object ob) {
        if (null == ob) {
            return null;
        }
        try {
            String str = obj2String(ob);
            return JSON_MAPPER.readValue(str, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException("json转换出错", e);
        }
    }


    public static JsonNode newJsonNode(String json){
        try {
            return JSON_MAPPER.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException("json转换出错", e);
        }
    }
}
