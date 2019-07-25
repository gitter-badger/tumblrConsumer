package io.github.tyb.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Component
public class Scraper {

    public void getEksiGundem() {
        try {
            Document doc = Jsoup.connect("https://eksisozluk.com/basliklar/gundem").get();
            System.out.println("ana başlık: " + doc.title());

            Elements newsHeadlines = doc.select(".topic-list > li > a");
            for (Element headline : newsHeadlines) {
                System.out.printf("%s\n\t%s", headline.absUrl("href"), headline.text());
                System.out.println("");

                Document doc2 = Jsoup.connect(headline.attr("abs:href"))
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .maxBodySize(0)
                        .timeout(1000*5)
                        .get();
                System.out.println("gündem item başlığı: " + doc2.title());

                //pretty print the object.
                //System.out.println(new Gson().toJson(doc2));

                //ObjectMapper mapper = new ObjectMapper();
                //mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc2);
                //System.out.println(new JSONObject(stats).toString(2));

                //Arrays.toString(((ArrayList<?>)doc2).entrySet().toArray())
                //Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                //gson.toJson(doc2);
                System.out.println(printObject(doc2));

                Elements entries = doc2.select("ul#entry-item-list li");
                System.out.println("entries: " + printObject(entries));

                Map<Integer, String> entryMap = new HashMap<Integer, String>();
                for (Element entry : entries) {
                    System.out.println("fav:" + entry.attr("data-favorite-count"));
                    System.out.println("entry text: " + entry.text());
                    System.out.println("entry: " + printObject(entry));

                    //We add numbers to the list. Autoboxing wraps primitive int types to the Integer objects.
                    entryMap.put(Integer.parseInt(entry.attr("data-favorite-count")), entry.text());
                    //String objStr = new Gson().toJson(entry);
                    //System.out.println("pretty printing object: " + objStr);
                }

                // let's sort this map by values first
                Map<Integer, String> sorted = entryMap
                        .entrySet()
                        .stream()
                        .sorted(comparingByKey())
                        .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                        LinkedHashMap::new));

                System.out.println("map after sorting by values: " + sorted);

                /*
                // above code can be cleaned a bit by using method reference
                sorted = entryMap
                        .entrySet()
                        .stream()
                        .sorted(comparingByValue())
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                        LinkedHashMap::new));
                */
                // now let's sort the map in decreasing order of value
                sorted = entryMap
                        .entrySet()
                        .stream()
                        .sorted(Collections.reverseOrder(comparingByKey()))
                        .collect(
                                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                        LinkedHashMap::new));

                 //traversing a list:
                 /*
                 List<String> martialArts = new ArrayList<>();
                 martialArts.forEach((e) -> System.out.printf("%s ", e));

                  */

                 //string operations
                /*
                Map<String, Integer> wordCount = new HashMap<>();

                String fileName = "src/resources/thermopylae.txt";

                List<String> lines = Files.readAllLines(Paths.get(fileName),
                        StandardCharsets.UTF_8);

                for (String line : lines) {

                    String[] words = line.split("\\s+");

                    for (String word : words) {

                        if (word.endsWith(".") || word.endsWith(",")) {
                            word = word.substring(0, word.length()-1);
                        }

                        if (wordCount.containsKey(word)) {
                            wordCount.put(word, wordCount.get(word) + 1);

                        } else {
                            wordCount.put(word, 1);
                        }
                    }
                }

                for (String key : wordCount.keySet()) {
                    System.out.println(key + ": " + wordCount.get(key));
                }
                */

                //java8 map collect
                /*
                List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000)));
                List<String> collect = staff.stream().map(x -> x.getName()).collect(Collectors.toList());

                 List<Map<String, String>> listOfMaps;
                List<String> valuesMatchingKey = listOfMaps.stream()
                    .filter(map -> map.containsKey("Key"))
                    .map(map -> map.get("Key"))
                    .collect(Collectors.toList());


                 List<Map<String, String>> tx = l.stream().map(m -> m.entrySet().stream()
                    .filter(map -> map.getKey().equals("x") || map.getKey().equals("z"))
                    .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue())))
                .collect(Collectors.toList());
                List<Map<String, String>> tx = l.stream()
                    .map(m -> Stream.of("x", "y")
                                    .filter(m::containsKey).collect(Collectors.toMap(key->key, m::get)))
                    .collect(Collectors.toList());
                 */
            }
        }
        catch (Exception e) { e.printStackTrace();}

    }

    public static <T> String printObject(T t) {
        StringBuilder sb = new StringBuilder();

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                sb.append(field.getName()).append(": ").append(field.get(t)).append('\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
