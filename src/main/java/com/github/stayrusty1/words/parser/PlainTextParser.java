package com.github.stayrusty1.words.parser;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlainTextParser implements WordsParser {


    @Override
    public Set<String> parse(String data) {
        return Arrays.stream(data.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toSet());
    }
}
