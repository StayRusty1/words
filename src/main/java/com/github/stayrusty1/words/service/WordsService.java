package com.github.stayrusty1.words.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@NoArgsConstructor
public class WordsService {

    private final int WORD_LENGTH = 6;

    HashMap<Character, List<List<String>>> mappedPartials = new HashMap<>();
    Set<String> words = new HashSet<>();

    /**
     *  Find all the possible ways to create words from the given partial words
     *
     * @param wordList the input list of words & partials
     * @return a hasmap with for each word a list of possible combinations
     */
    public HashMap<String, List<List<String>>> start(Set<String> wordList) {
        initialize(wordList);

        HashMap<String, List<List<String>>> resultMap = new HashMap<>();

        for (String word : words) {
            List<List<String>> results = findForWord(word, 0);
            if(results.isEmpty()) continue;
            resultMap.put(word, results);
        }

        return resultMap;
    }

    private void initialize(Set<String> wordList) {
        for (String word : wordList) {
            int len = word.length();
            if(len == 0) continue;
            if(len > WORD_LENGTH) continue;

            if(len == WORD_LENGTH) {
                words.add(word);
                continue;
            }

            Character firstChar = word.charAt(0);

            List<List<String>> lengthLists =  mappedPartials.computeIfAbsent(firstChar, c -> {
                List<List<String>> lengthList = new ArrayList<>(WORD_LENGTH);
                for (int i = 0; i < WORD_LENGTH; i++) {
                    lengthList.add(new ArrayList<>());
                }
                return lengthList;
            });

            lengthLists.get(len-1).add(word);
        }
    }

    private List<List<String>> findForWord(String word, int pos) {
        if (pos == word.length()) {
            List<List<String>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }

        List<List<String>> found = new ArrayList<>();
        int remainingLength = word.length() - pos;

        for (int i = 0; i < remainingLength; i++) {
            List<List<String>> lengthLists = mappedPartials.get(word.charAt(pos));
            if(Objects.isNull(lengthLists)) return new ArrayList<>();
            List<String> options = lengthLists.get(i);

            for (String option : options) {
                if (word.startsWith(option, pos)) {
                    List<List<String>> paths = findForWord(word, pos + option.length());

                    for (List<String> path : paths) {
                        List<String> combined = new ArrayList<>();
                        combined.add(option);
                        combined.addAll(path);
                        found.add(combined);
                    }
                    break;
                }
            }
        }

        return found;
    }


}
