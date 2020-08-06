package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.util.JsonUtils;
import com.example.demo.util.Resp;
import com.example.demo.util.entity.Leak;
import com.example.demo.util.entity.Soil;
import com.example.demo.util.leak.LeakUtil;
import com.example.demo.util.mapper.LeakMapper;
import com.example.demo.util.mapper.SoilMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.demo.util.SpiderUtil.getActicle;


@SpringBootTest
public class DemoApplicationTests {


    @Autowired
    private SoilMapper soilMapper;

    @Autowired
    private LeakMapper leakMapper;


    @Test
    public void test() throws Exception {
        QueryWrapper<Soil> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", "1");
        List<Soil> soils = soilMapper.selectList(queryWrapper);
        String[] tem = new String[]{"http://mp.weixin.qq.com/mp/homepage?__biz=MzU2Nzk5Njg4Nw==&hid=9&sn=c964c855e07ba8e971a62b06c815b29b&scene=18#wechat_redirect",
                "http://mp.weixin.qq.com/mp/homepage?__biz=MzU2Nzk5Njg4Nw==&hid=8&sn=58767dbd02b857b52bec6fd338c300ab&scene=18#wechat_redirect",
                "https://mp.weixin.qq.com/mp/homepage?__biz=MzIwMjQzMzUxMg==&hid=1&sn=b5518a7a24d20f20266cd8d4a149bb7b&scene=18", "https://mp.weixin.qq.com/mp/homepage?__biz=MzIwMjQzMzUxMg==&hid=4&sn=530e5e1ec8935b4d85b6e10105855945&scene=18",
                "https://mp.weixin.qq.com/mp/homepage?__biz=MzIwMjQzMzUxMg==&hid=2&sn=662bd2f6cb98c93d93bed0c5ecf3f971&scene=18"
        };
        for (String url2 : tem) {
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
                soil.setDelated(1);
                boolean flag = true;
                for (Soil s : soils) {
                    if (s.getUrl().equals(url)) {
                        flag = false;
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
        String[] tem = new String[]{"https://mp.weixin.qq.com/s/vERQ0VMPdqLDKJ9z79dmnQ",
                "https://mp.weixin.qq.com/s/TKewBeh-9Q5OB8GhqLE8OA",
                "https://mp.weixin.qq.com/s/wz5zsgbHL6_46X-jd5N76Q",
                "https://mp.weixin.qq.com/s/Qzm93YAy3tVT-cQdOetWgg",
                "https://mp.weixin.qq.com/s/d5idbgKfpV0qcCUr5piTDg",
                "https://mp.weixin.qq.com/s/d5idbgKfpV0qcCUr5piTDg",
                "https://mp.weixin.qq.com/s/vC0mwv96VaxX4pbYO3IThw"
        };
        for (String url : tem) {
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
            soil.setDelated(1);
            boolean flag = true;
            for (Soil s : soils) {
                if (s.getUrl().equals(url)) {
                    flag = false;
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

    /*@Test
    public void leak()  {
        QueryWrapper<Leak> wrapper = new QueryWrapper<>();
        wrapper.gt("newsTime", "2020-08-01");
        List<Leak> leaks = leakMapper.selectList(wrapper);
        for (int j = 4481; j <= 14882; j++) {
            try {
                List<String> list1 = LeakUtil.test(j);
                for (String test : list1) {
                    Document document = Jsoup.connect(test).get();
                    Elements elements = document.getElementsByClass("detail_xq w770");
                    Element element = elements.get(0).getElementsByTag("ul").get(0);
                    Elements li = element.getElementsByTag("li");
                    String cnnvdTitle = li.get(0).getElementsByTag("span").get(0).text();
                    cnnvdTitle = cnnvdTitle.substring(8);
                    String cveTitle = li.get(2).getElementsByTag("a").text();
                    String hazardLevel = li.get(1).getElementsByTag("a").text();
                    String a = li.get(4).getElementsByTag("a").text();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date newsTime = sdf.parse(a);
                    String a1 = li.get(6).getElementsByTag("a").text();
                    Date updateTime = sdf.parse(a1);
                    String leadSources = li.get(9).getElementsByTag("span").text();
                    String leakType = li.get(3).getElementsByTag("a").text();
                    String threatType = li.get(5).getElementsByTag("a").text();
                    String firm = li.get(7).getElementsByTag("span").text();
                    firm = firm.substring(4);
                    Elements elements1 = document.getElementsByClass("d_ldjj");
                    Elements p = elements1.get(0).getElementsByTag("p");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < p.size(); i++) {
                        sb.append(p.get(i).text());
                    }
                    String leakProfile = sb.toString();
                    Elements e = document.getElementsByClass("d_ldjj m_t_20");
                    Elements p1 = e.get(0).getElementsByTag("p");
                    StringBuilder s2 = new StringBuilder();
                    for (int i = 0; i < p1.size(); i++) {
                        s2.append(p1.get(i).text());
                    }
                    String leakKnow = s2.toString();
                    Elements p2 = e.get(1).getElementsByTag("p");
                    StringBuilder s3 = new StringBuilder();
                    for (int i = 0; i < p2.size(); i++) {
                        s3.append(p2.get(i).text() + "  ");
                    }
                    String referenceWebsite = s3.toString();
                    Elements ul = e.get(2).getElementsByTag("ul");
                    String affectedEntity = ul.get(0).getElementsByTag("p").text();
                    Element div = e.get(3).getElementsByTag("div").get(3);
                    Elements ul1 = div.getElementsByTag("ul");
                    Elements a3 = ul1.get(0).getElementsByTag("a");
                    String attr = a3.attr("abs:href");
                    String patch = attr;
                    Leak leak = new Leak();
                    leak.setCnnvdTitle(cnnvdTitle);
                    leak.setCveTitle(cveTitle);
                    leak.setNewsTime(newsTime);
                    leak.setUpdateTime(updateTime);
                    leak.setLeadSources(leadSources);
                    leak.setHazardLevel(hazardLevel);
                    leak.setLeakType(leakType);
                    leak.setThreatType(threatType);
                    leak.setFirm(firm);
                    leak.setLeakProfile(leakProfile);
                    leak.setLeakKnow(leakKnow);
                    leak.setReferenceWebsite(referenceWebsite);
                    leak.setAffectedEntity(affectedEntity);
                    leak.setPatch(patch);
                    leak.setUrl(test);
                    leak.setCreateTime(new Date());
                    boolean flag = true;
                    for (Leak s : leaks) {
                        if (s.getUrl().equals(test)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        leakMapper.insert(leak);
                    }
                }
            } catch (Exception e) {
                j++;
            }finally {
                System.out.println(j);
            }

            };
        }
    }*/



