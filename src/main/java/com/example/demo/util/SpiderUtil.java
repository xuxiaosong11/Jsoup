package com.example.demo.util;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

/**
 * 文章爬取工具类
 * @author qp
 */
public class SpiderUtil {

    /**
     * 微信公众号文章域名
     */
    private static final String WX_DOMAIN = "https://mp.weixin.qq.com";
    // 文章返回前端统一key常量
    // 文章标题
    private static final String KEY_TITLE = "title";
    // 文章封面图链接
    private static final String KEY_COVER_URL = "coverLink";
    // 文章出处作者
    private static final String KEY_REFER_NAME = "referName";
    // 文章出处链接
    private static final String KEY_REFER_URL = "referLink";
    // 文章内容
    private static final String KEY_TAGS = "tags";
    // 文本信息
    private static final String KEY_TEXT = "text";


    /**
     * 根据文章链接抓取文章内容
     * @param url 文章链接
     * @return 文章内容
     */
    public static Resp<JSONObject> getActicle(String url) {
        // 请求与响应
        String resp = HttpTool.get(url, getWxHeaderMap());
        if (resp == null || resp.trim().length() == 0) {
            return Resp.error("文章获取失败，请检查链接是否正确");
        }
        // 解析
        Resp<JSONObject> acticleResp = getWxActicleContent(resp, url);
        if (acticleResp.isError()) {
            return Resp.error(acticleResp.getMsg());
        }
        return acticleResp;
    }

    /**
     * 微信公众号请求头设置
     */
    public static Map<String, String> getWxHeaderMap() {
        Map<String, String> map = new HashMap<>(new LinkedHashMap<>());
        map.put("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Language", "zh-Hans-CN, zh-Hans; q=0.8, en-US; q=0.5, en; q=0.3");
        map.put("Host", "mp.weixin.qq.com");
        map.put("If-Modified-Since", "Sat, 04 Jan 2020 12:23:43 GMT");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        return map;
    }


    /**
     * 解析微信公众号文章
     *
     * @param resp 请求文章响应
     * @param url 文章链接
     * @return 文章信息
     */
    public static Resp<JSONObject> getWxActicleContent(String resp, String url) {
        try {
            Document document = Jsoup.parse(resp);
            // 文章出处（作者）
            String referName = document.getElementsByClass("profile_nickname").get(0).text();
            // 文章封面图链接
            String coverUrl = document.select("meta[property=\"og:image\"]").get(0).attr("content");
            // 文章标题
            String title = document.getElementById("activity-name").text();
            String js_content = document.getElementById("js_content").text();

            // 文章内容
            StringBuilder sb=new StringBuilder();
            Elements p = document.getElementsByTag("p");
            for (Element element : p) {
                Elements img = element.getElementsByTag("img");
                String attr = img.attr("data-src");
                img.attr("src",attr);

            }
            for (Element element: p) {
                String html = element.html();
                sb.append(html);
            }

            JSONObject json = new JSONObject(new LinkedHashMap<>());
            json.put(KEY_TITLE, title);
            json.put(KEY_COVER_URL, coverUrl);
            json.put(KEY_REFER_NAME, referName);
            json.put(KEY_REFER_URL, url);
            json.put(KEY_TEXT,js_content);
            json.put(KEY_TAGS, sb.toString());
            return Resp.success(json);
        } catch (Exception e) {
            e.printStackTrace();
            return Resp.error("文章解析失败");
        }
    }
}