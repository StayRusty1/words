package com.github.stayrusty1.words.controller;

import com.github.stayrusty1.words.formatter.PlusFormatter;
import com.github.stayrusty1.words.parser.PlainTextParser;
import com.github.stayrusty1.words.service.WordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WordController {

    private final WordsService wordsService;
    private final PlusFormatter formatter;
    private final PlainTextParser parser;

    @PostMapping(value = "/file", consumes = "text/plain")
    public ResponseEntity<String> file(@RequestBody String words) {
        Set<String> wordsList = parser.parse(words);

        HashMap<String, List<List<String>>> results = wordsService.start(wordsList);

        return ResponseEntity.ok(formatter.format(results));
    }

}
