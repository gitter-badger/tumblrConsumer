package io.github.tyb.consumer.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class Scraper {

    public void getEksiGundem() {
        try {
            Document doc = Jsoup.connect("https://eksisozluk.com/basliklar/gundem").get();
            System.out.println(doc.title());
            Elements newsHeadlines = doc.select(".topic-list > li > a");
            for (Element headline : newsHeadlines) {
                System.out.printf("%s\n\t%s", headline.attr("title"), headline.absUrl("href"));
            }
        }
        catch (Exception e) { e.printStackTrace();}

    }

}
