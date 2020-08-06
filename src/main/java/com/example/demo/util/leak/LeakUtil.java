package com.example.demo.util.leak;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qp
 */
public class LeakUtil {
    public static void main(String[] args) throws Exception {
        List<String> test = test(1);
        for (String list: test) {
            System.out.println(list);
        }
    }

    public static List<String> test(int i) throws Exception {
        List<String> list = new ArrayList<>();
        String url = "http://www.cnnvd.org.cn/web/vulnerability/querylist.tag?pageno=" + i + "&repairLd=";
        Document document = Jsoup.connect(url).get();
        Elements element = document.getElementsByClass("list_list");
        Elements ul = element.get(0).getElementsByTag("ul");
        Elements li = ul.get(0).getElementsByTag("li");
         for (int j = 0; j < li.size(); j++) {
            Element f1 = li.get(j).getElementsByTag("div").get(0);
            Element element1 = f1.getElementsByTag("a").get(0);
            String ip = element1.attr("abs:href");
            list.add(ip);
        }
        return list;
     }



}
