package com.gialongchuai.post.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeFormatter {
    Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>();

    public DateTimeFormatter() {
        strategyMap.put(60L, this::formatterInSeconds);
        strategyMap.put(3600L, this::formatterInMinutes);
        strategyMap.put(86400L, this::formatterInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDate);
    }

    public String format(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        // goi toi linkedhashmap de xet tu tren xuong duoi do co thu tu
        // sau do return giay phut gio va date thich hop
        return strategyMap.entrySet()
                .stream()
                .filter(longFunctionEntry -> elapseSeconds < longFunctionEntry.getKey())
                .findFirst()
                .map(longFunctionEntry -> longFunctionEntry.getValue().apply(instant))
                .orElse(formatInDate(instant));
    }

    private String formatterInSeconds(Instant instant) {
        var elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return String.format("%s seconds ago", elapseSeconds);
    }

    private String formatterInMinutes(Instant instant) {
        var elapseMinutes = ChronoUnit.MINUTES.between(instant, Instant.now());
        return String.format("%s minutes ago", elapseMinutes);
    }

    private String formatterInHours(Instant instant) {
        var elapseHours = ChronoUnit.HOURS.between(instant, Instant.now());
        return String.format("%s hours ago", elapseHours);
    }

    private String formatInDate(Instant instant) {
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;

        return localDateTime.format(dateTimeFormatter);
    }
}
