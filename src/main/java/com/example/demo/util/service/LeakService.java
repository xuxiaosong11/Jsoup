package com.example.demo.util.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.util.entity.Leak;
import com.example.demo.util.leak.LeakUtil;
import com.example.demo.util.mapper.LeakMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author qp
 */
public class LeakService {
    @Autowired
    LeakMapper leakMapper;

    public void leak()  {
        QueryWrapper<Leak> wrapper = new QueryWrapper<>();
        wrapper.gt("newsTime", "2020-08-01");
        List<Leak> leaks = leakMapper.selectList(wrapper);
        for (int j = 1; j <= 20; j++) {
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
            }
        }
    }
}
