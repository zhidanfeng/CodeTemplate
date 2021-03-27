package com.zhi.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@Slf4j
public class RepeatSubmitUtil {
    public static String digestParamMD5(String paramJson, String... excluedKey) {
        String digestMD5 = paramJson;
        TreeMap paramTreeMap = JSON.parseObject(paramJson, TreeMap.class);
        if(excluedKey != null) {
            List<String> excludeKeyList = Arrays.asList(excluedKey);
            if(!excludeKeyList.isEmpty()) {
                for (String excludeKey : excludeKeyList) {
                    paramTreeMap.remove(excludeKey);
                }
            }
        }
        String json = JSON.toJSONString(paramTreeMap);
        digestMD5 = jdkMD5(json);
        return digestMD5;
    }

    private static String jdkMD5(String src) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] mdBytes = messageDigest.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(mdBytes);
        } catch (Exception e) {
            log.error("",e);
        }
        return res;
    }
}
