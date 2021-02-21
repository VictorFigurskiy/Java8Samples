package com.sample.patterns.behavioral.state;

import java.util.stream.IntStream;

public class DeveloperDay {

    public static void main(String[] args) {
        Activity activity = new Sleeping();
        Developer developer = new Developer();

        developer.setActivity(activity);

        IntStream.range(0,10).forEach(value -> {
            developer.justDoIt();
            developer.changeActivity();
        });
    }
}
