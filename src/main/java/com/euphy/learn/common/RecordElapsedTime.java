package com.euphy.learn.common;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public final class RecordElapsedTime {
    private static final Map<String, Instant> methodMap = new LinkedHashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").withLocale(Locale.TAIWAN).withZone(ZoneId.systemDefault());

    public static void start(String methodName) {
        Instant start = Instant.now();
        methodMap.put(methodName, start);
        System.out.println("時間: " + formatter.format(start) + "開始執行" + methodName + "方法...\n");
    }

    public static void end(String methodName) {
        Instant finish = Instant.now();
        Instant start = methodMap.get(methodName);
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("時間: " + formatter.format(finish) + "完成執行" + methodName + "方法, 共花費時間: " + timeElapsed / 1000.0 + " sec\n");
    }
}
