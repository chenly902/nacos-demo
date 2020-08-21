package com.easybii.nacos.nacosprivider.nacos.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.*;

/**
 * Copyright (C), 2020,
 * Author: linyu902
 * Date: 2020/7/27 16:07
 * FileName: WebImage
 */
public class WebImage {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     * 文字识别ja
     */
    public static String webImage( String filePath) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        WebImage.downloadFile("aaaa","adaddd");
        String s = WebImage.webImage("C:\\Users\\EDZ\\Desktop\\DINGTALK_IM_2854648382.JPG");
        System.out.println("result ------------>" + s);
    }

    /**
     * FileUtils下载网络文件
     *
     * @param serverUrl   ：网络文件地址
     * @return
     */
    public static String downloadFile(String serverUrl) throws Exception {
        String savePath = "C:\\Users\\EDZ\\Desktop\\photos";
        String zipSavePath = "C:\\Users\\EDZ\\Desktop\\photos\\1.png";
        String result;
        File f = new File(savePath);
        if (!f.exists()) {
            if (!f.mkdirs()) {
                throw new Exception("makdirs: '" + savePath + "'fail");
            }
        }
        URL url = new URL(serverUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而放回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 5.0;Windows NT;DigExt)");
        if (conn.getHeaderField("Content-Length") != null && !"".equals(conn.getHeaderField("Content-Length"))){
            Long totalSize = Long.parseLong(conn.getHeaderField("Content-Length"));
            if (totalSize > 0) {
                FileUtils.copyURLToFile(url, new File(zipSavePath));
                result = "success";
            } else {
                throw new Exception("can not find serverUrl :{}" + serverUrl);
            }
            return result;
        }

        return null;
    }

    /**
     * 删除本地文件
     * @param filePath
     */
    public static void deleteImage( String filePath) {
        filePath = "C:\\Users\\EDZ\\Desktop\\photos\\1.png";
        File file = new File(filePath);
        file.delete();
    }

}
