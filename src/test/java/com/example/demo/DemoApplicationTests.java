package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.util.JsonUtils;
import com.example.demo.util.Resp;
import com.example.demo.util.entity.Soil;
import com.example.demo.util.mapper.SoilMapper3;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.util.SpiderUtil.getActicle;


@SpringBootTest
class DemoApplicationTests {


    @Autowired
    private SoilMapper3 soilMapper;

    @Test
    public void test() throws Exception{
        List<Soil> soils = soilMapper.selectList(new QueryWrapper<>(null));
        String[] tem=new String[]{"http://mp.weixin.qq.com/mp/homepage?__biz=MzU2Nzk5Njg4Nw==&hid=9&sn=c964c855e07ba8e971a62b06c815b29b&scene=18#wechat_redirect",
        "http://mp.weixin.qq.com/mp/homepage?__biz=MzU2Nzk5Njg4Nw==&hid=8&sn=58767dbd02b857b52bec6fd338c300ab&scene=18#wechat_redirect",
        "https://mp.weixin.qq.com/mp/homepage?__biz=MzIwMjQzMzUxMg==&hid=1&sn=b5518a7a24d20f20266cd8d4a149bb7b&scene=18", "https://mp.weixin.qq.com/mp/homepage?__biz=MzIwMjQzMzUxMg==&hid=4&sn=530e5e1ec8935b4d85b6e10105855945&scene=18",
                "https://mp.weixin.qq.com/mp/homepage?__biz=MzIwMjQzMzUxMg==&hid=2&sn=662bd2f6cb98c93d93bed0c5ecf3f971&scene=18"
        };
        for (String url2 :tem) {
            List<String> res = JsonUtils.res(url2);
            for (String url : res) {
                Connection connect = Jsoup.connect(url);
                Document document = connect.get();
                Elements script = document.getElementsByTag("script");
                Map<String, Object> map = new HashMap<>();
                String[] data = script.get(13).data().toString().split("var");
                String[] split = data[1].split(";")[0].split("=");
                String substring = split[3].substring(0, split[3].length() - 1);
                String time = substring.substring(1, substring.length());
                Resp<JSONObject> resp = getActicle(url);
                JSONObject body = resp.getBody();
                Soil soil = new Soil();
                String title = (String) body.get("title");
                String text = (String) body.get("tags");
                String referName = (String) body.get("referName");
              /*  System.out.println("文章标题   " + title);
                System.out.println("文章内容   " + text);
                System.out.println("文章来源   " + referName);
                System.out.println("文章url   " + url);
                System.out.println("发布时间   " + time);
                System.out.println("摘要       " + null);
                System.out.println("作者      " + referName);
                System.out.println("抓取来源   " + referName);
                System.out.println("逻辑删除  " + "1");
                System.out.println("抓取时间  " + new Date());*/
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date parse = sdf.parse(time);
                soil.setAuthor(referName);
                soil.setCategory(referName);
                soil.setCreateTime(new Date());
                soil.setOrigin(referName);
                soil.setText(text);
                soil.setSummary(null);
                soil.setTitle(title);
                soil.setOrigin(referName);
                soil.setUrl(url);
                soil.setReleaseDate(parse);
                soil.setGetTime(new Date());
                boolean flag=true;
                 for (Soil s: soils) {
                     if (s.getUrl().equals(url)) {
                         flag=false;
                         break;
                     }
                 }
                System.out.println(flag);
                if (flag) {
                     soilMapper.insert(soil);
                }
            }
        }
    }

    @Test
    public void test2() throws Exception {

        List<Soil> soils = soilMapper.selectList(new QueryWrapper<>(null));
              String[] tem=new String[]{"https://mp.weixin.qq.com/s/vERQ0VMPdqLDKJ9z79dmnQ",
                      "https://mp.weixin.qq.com/s/TKewBeh-9Q5OB8GhqLE8OA",
                      "https://mp.weixin.qq.com/s/wz5zsgbHL6_46X-jd5N76Q",
                      "https://mp.weixin.qq.com/s/Qzm93YAy3tVT-cQdOetWgg",
                      "https://mp.weixin.qq.com/s/d5idbgKfpV0qcCUr5piTDg",
                      "https://mp.weixin.qq.com/s/d5idbgKfpV0qcCUr5piTDg",
                      "https://mp.weixin.qq.com/s/vC0mwv96VaxX4pbYO3IThw"
              };
                for (String url: tem) {
                    Connection connect = Jsoup.connect(url);
                    Document document = connect.get();
                    Elements script = document.getElementsByTag("script");
                    String[] data = script.get(13).data().toString().split("var");
                    String[] split = data[1].split(";")[0].split("=");
                    String substring = split[3].substring(0, split[3].length() - 1);
                    String time = substring.substring(1, substring.length());
                    Resp<JSONObject> resp = getActicle(url);
                    JSONObject body = resp.getBody();
                    Soil soil = new Soil();
                    String title = (String) body.get("title");
                    String text = (String) body.get("tags");
                    String referName = (String) body.get("referName");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse = sdf.parse(time);
                    soil.setAuthor(referName);
                    soil.setCategory(referName);
                    soil.setCreateTime(new Date());
                    soil.setOrigin(referName);
                    soil.setText(text);
                    soil.setSummary(null);
                    soil.setTitle(title);
                    soil.setOrigin(referName);
                    soil.setUrl(url);
                    soil.setReleaseDate(parse);
                    soil.setGetTime(new Date());
                    boolean flag=true;
                    for (Soil s: soils) {
                        if (s.getUrl().equals(url)) {
                            flag=false;
                            break;
                        }
                    }
                    System.out.println(flag);
                    if (flag) {
                        soilMapper.insert(soil);
                    }
                }
            }
}
