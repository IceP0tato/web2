package example.d04._2WebCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {
    public List<String> task1() {
        String url = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
        List<String> list = new ArrayList<>();
        try {
            // Jsoup 으로 HTML 가져오기
            Document document = Jsoup.connect(url).get();
            // 가져올 HTML 식별자 지정
            Elements aList = document.select(".titles > a");
            System.out.println("aList = " + aList);

            // 가져온 마크업들을 반복하여 텍스트만 추출
            for (Element a : aList) {
                String title = a.text();
                if (title.isBlank()) continue;
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, String>> task2() {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            for (int page=1; page<=3; page++) {
                String url = "https://www.yes24.com/product/category/daybestseller?categoryNumber=001&pageNumber="+page+"&pageSize=24&type=day";
                Document document = Jsoup.connect(url).get();
                Elements nameList = document.select(".info_name > .gd_name");
                Elements priceList = document.select(".info_price > .txt_num > .yes_b");
                Elements imgList = document.select(".img_bdr .lazy");

                for (int i=0; i<nameList.size(); i++) {
                    String name = nameList.get(i).text();
                    String price = priceList.get(i).text();
                    String img = imgList.get(i).attr("data-original"); // 이미지(링크) 속성값 가져오기
                    Map<String, String> map = new HashMap<>();
                    map.put("name", name);
                    map.put("price", price);
                    map.put("img", img);
                    list.add(map);
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 동적 페이지는 Jsoup 사용 불가
    public Map<String, String> task3() {
        Map<String, String> map = new HashMap<>();
        try {
            String url = "https://weather.daum.net";
            Document document = Jsoup.connect(url).get();
            Elements element = document.select(".info_weather .num_deg");
            System.out.println("element = " + element);
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
