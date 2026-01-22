package com.github.stayrusty1.words.formatter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class PlusFormatter implements WordResultFormatter {

    @Override
    public String format(HashMap<String, List<List<String>>> data) {
        StringBuilder sb = new StringBuilder();

        data.forEach((word, options) -> {
            for(List<String> parts : options) {
                sb.append(String.join("+", parts)).append("=").append(word).append("\n");
            }
        });

        return sb.toString();
    }
}
