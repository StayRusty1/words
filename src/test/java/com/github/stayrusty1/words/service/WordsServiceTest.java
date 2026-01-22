package com.github.stayrusty1.words.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WordsServiceTest {

    private WordsService wordsService;

    @BeforeEach
    void setup() {
        wordsService = new WordsService();
    }

    @Test
    void start() {
        Set<String> input = Set.of("foobar", "foo", "fe", "f", "oo", "ar", "obar", "b", "o", "weasel", "we", "sel", "ase", "w");

        HashMap<String, List<List<String>>> results = wordsService.start(input);

        assertTrue(results.containsKey("foobar"));
        assertFalse(results.containsKey("weasel"));

        List<List<String>> combinations = results.get("foobar");
        assertEquals(4, combinations.size());
        assertEquals(List.of("f", "o", "o", "b", "ar"), combinations.get(0));
    }
}
