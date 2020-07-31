package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static List<String> test(String url) {
        List<String> list = new ArrayList<>();

        try {
            Connection connect = Jsoup.connect(url);
            Document document = connect.get();
            Elements scripts = document.getElementsByTag("script");
            String data = scripts.get(13).data();

            String[] split = data.split("\\n|\\r");
            for (int i = 0; i < split.length; i++) {
                if (i == 29) {
                    String s = split[i].substring(9);
                    String s2 = s.substring(0, s.length() - 1);
                    list.add(s2);
                }
                if (i == 30) {
                    String s = split[i].substring(21);
                    String s2 = s.substring(0, s.lastIndexOf("."));
                    list.add(s2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 传入列表判断是否包含关键字 是 就true 否急速false
     * @param key
     * @return
     */
    public static Boolean isContainsKey(String key) {
        if (key.contains("土壤污染") || key.contains("土壤修复") || key.contains("土壤") || key.contains("污染地块")) {
            return true;
        }
        return false;
    }

    /**
     * 传入url 获取包含关键字的列表
     * @param url
     * @return
     */
    public static List<String> res(String url) {
        List<String> list = test(url);
        ArrayList<String> res=new ArrayList<>();
        JSONObject json = JSONObject.parseObject(list.get(0));
        JSONArray array = json.getJSONArray("appmsg_list");
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            String title = o.getString("title");
            String link = o.getString("link");
            Boolean containsKey = isContainsKey(title);
            if (containsKey) {
                res.add(link);
            }
        }
      return res;
    }
}
