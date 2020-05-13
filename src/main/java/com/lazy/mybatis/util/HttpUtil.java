package com.lazy.mybatis.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class HttpUtil {

    public static String httpGet(String urlStr){

        String entityString =null;

        try {
            entityString = EntityUtils.toString(httpGetHttpResponse(urlStr).getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("===> HttpGet请求出错！");
        }


        return entityString;
    }
    public static String httpGet(String urlStr,String charset){
        String entityString =null;
        try {
            entityString = EntityUtils.toString(httpGetHttpResponse(urlStr).getEntity(),charset);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("===> HttpGet请求出错！");
        }
        return entityString;
    }
    public static String httpGet(String urlStr,String charset,String header){
        //httpGet.setHeader();
        String entityString =null;
        try {
            entityString = EntityUtils.toString(httpGetHttpResponse(urlStr).getEntity(),charset);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("===> HttpGet请求出错！");
        }
        return entityString;
    }

    public static HttpResponse httpGetHttpResponse(String urlStr){
        HttpClient httpClient = HttpClientBuilder.create().build();
        String entityString =null;
        HttpResponse httpResponse = null;
        try {
            URL url = new URL(urlStr);
            URI uri = new URI(url.getProtocol(),url.getHost(),url.getPath(),url.getQuery(),null);//解决特殊字符问题
            httpGet.setURI(uri);
             httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("===> HttpGet请求出错！");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.error("===> 获取URI请求出错！");
        }
        //Header[] allHeaders = httpResponse.getAllHeaders();
        //Header header = allHeaders[0];
        //header.ge

        return httpResponse;
    }

    private static HttpGet httpGet;

    static {
        httpGet = new HttpGet();
        httpGet.setHeader(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        // httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36");
    }
}
