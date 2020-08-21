package com.easybii.nacos.nacosprivider.nacos.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Copyright (C), 2020,
 * Author: linyu902
 * Date: 2020/7/28 12:37
 * FileName: Sample
 */
public class Sample {

    //设置APPID/AK/SK
    public static final String APP_ID = "21629463";
    public static final String API_KEY = "myKZUVqMv48cRMEql4ziHFI0";
    public static final String SECRET_KEY = "yYHA1sYLqoAGzrzoDRVBRIUHVYA88eN1";

    public static AipOcr getAipOcr() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        return client;

        // 调用接口
//        String path = "test.jpg";
//        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));

    }

    public static String sample(AipOcr client,String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 通用文字识别, 图片参数为远程url图片
        JSONObject res = client.basicGeneralUrl(url, options);
        return res.toString();

    }
}
