package io.github.tyb.consumer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
//public class ConsumeUtils<T, U extends Map<?, ?>> {
public class ConsumeUtils<T, U, V> {
    private Iterator<T> getPagingIterator(//U query,
                                          Map<U,V> query,
                                          //Function<U, List<T>> requestFn,
                                          Function<Map<U,V>, List<T>> requestFn,
                                          //Function<U, V> requestFn,
                                          //Function<V, List<T>> getResultList,
                                          //Function<V, Object> getLastObjectID,
                                          Function<List<T>, T> getLastObjectID,
                                          //Consumer<Integer> setLastObjectID) {
                                          Consumer<T> setLastObjectID) {

        Iterator<T> myIterator = new Iterator<T>() {
            private AtomicInteger currentIndex = new AtomicInteger(0);
            private List<T> buffer = new ArrayList<T>();
            private T lastObject;
            private AtomicInteger totalResult = new AtomicInteger(0);

            @Override
            public boolean hasNext() {
                if (currentIndex.get() >= buffer.size()) {
                    try {
                        log.info("Request rest objects starting from {}", totalResult.get());
                        buffer = requestFn.apply(query);
                        //log.info("offset {} ", query.get("offset"));
                        log.info("Fetched {} objects.", buffer.size());

                        lastObject = getLastObjectID.apply(buffer);

                        //offset li kullanimda boyleydi ve lastObject'in bir anlami yoktu ama before_id de ikisi beraber kullanilir.
                        //setLastObjectID.accept(totalResult.addAndGet(buffer.size()));
                        setLastObjectID.accept(lastObject);

                        currentIndex.set(0);
                        if (buffer.isEmpty()) { //en son 20 den az gelse bile yeni sorgu atilacak o sorgu sonucu bitecek.
                            //none records found
                            return false;
                        }
                    } catch (Exception ex) {
                        log.warn("Cannot fetch results from tumblr...");
                    }
                }
                return true;
            }

            @Override
            public T next() {
                return buffer.get(currentIndex.getAndIncrement());
            }
        };
        return myIterator;
    }

    public Stream<T> getPagingQueryStream(Map<U,V> query,
                                          //Function<U, List<T>> requestFn,
                                          Function<Map<U,V>, List<T>> requestFn,
                                          //Function<U, V> requestFn,
                                          //Function<V, List<T>> getResultList,
                                          //Function<V, Object> getLastObjectID,
                                          Function<List<T>, T> getLastObjectID,
                                          Consumer<T> setLastObjectID) {
                                          //Consumer<Integer> setLastObjectID) {
        //Iterator<T> it = getPagingIterator(query, requestFn, getResultList, getLastObjectID, setLastObjectID);
        Iterator<T> it = getPagingIterator(query, requestFn, getLastObjectID, setLastObjectID);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.DISTINCT), false);
    }
}
