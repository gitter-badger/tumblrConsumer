package io.github.tyb.consumer.test;

import io.github.tyb.consumer.service.Consumer;
import io.github.tyb.consumer.service.Scraper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ConsumerTest {
    @Autowired
    private Consumer consumer;

    @Autowired
    private Scraper scraper;

    @Test
    public void consumeRaw() {
        consumer.consumeRaw();
    }

    @Test
    public void consume() {
        consumer.consume(); //EN BUYUK HATA: BU SEKILDE TEST ETMEYE CALISIRSAN YANILIRSIN
        //CUNKU BASKA BIR PORTTAN BASKA BIR SPRING UYGULAMASI AYAGA KALDIRIYOR. VUE CLIENT I ISE ONU BILMIYOR.
    }

    @Test
    public void scrapeEksi() {
        scraper.getEksiGundem();
    }
}
