package com.margin;

import com.margin.error.ApiException;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;

import java.io.FileInputStream;
import java.util.Scanner;

@Component
public class XMLSplitter {

    public void split(String inputFilePath, String tagName, String charsetName, Subscriber<String> subscriber) {

        Observable<String> obs = Observable.unsafeCreate(subscriber1 -> {
            Scanner sc = null;
            try {
                FileInputStream inputStream = new FileInputStream(inputFilePath);
                sc = new Scanner(inputStream, charsetName);
            } catch (Exception e) {
                subscriber1.onError(e);
                e.printStackTrace();
            }
            if (sc == null) subscriber1.onError(new ApiException("File scanner cannot be null!"));

            try {
                sc.useDelimiter("</?" + tagName + ">");
            } catch (Exception e) {
                e.printStackTrace();
                subscriber1.onError(e);
            }

            while (sc.hasNext()) {
                String xmlString = sc.next().trim();
                subscriber1.onNext(xmlString);
                if (!sc.hasNext()) break;
            }
            subscriber1.onCompleted();
        });

        obs.subscribe(subscriber);
    }

    public void getOne(String inputFilePath, String tagName, String charsetName, int id, Subscriber<String> subscriber) {
        Observable<String> obs = Observable.unsafeCreate(subscriber1 -> {
            String tag = "";
            int counter = 0;
            try (
                    FileInputStream inputStream = new FileInputStream(inputFilePath);
                    Scanner sc = new Scanner(inputStream, charsetName)
            ) {
                sc.useDelimiter("<" + tagName + ">");
                if (!sc.hasNext()) return;
                sc.next();
                sc.useDelimiter("</" + tagName + ">");
                while (sc.hasNext() && counter++ < id) {
                    sc.next();
                }
                if (sc.hasNext()) {
                    tag = sc.next().trim();
                    subscriber1.onNext(tag);
                }

                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
            } catch (Exception e) {
                subscriber1.onNext(tag);
                e.printStackTrace();
            }
        });
        obs.subscribe(subscriber);
    }
}
