package com.stringsearch.stringsearch.search;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StringSearch {

    public List<String> getLongestWords(List<String> wordList) {
        wordList.sort(Comparator.comparing(String::length));
        Collections.reverse(wordList);

        return wordList.stream().filter(word -> word.length() == wordList.get(0).length())
                .distinct()
                .collect(Collectors.toList());
    }

    public Map.Entry<String, Long> getWordsWithHighestFrequency(List<String> wordList) {
        Map<String, Long> wordFrequencyMap = wordList.stream()
                .filter(word -> word.length() >= 8)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional<Map.Entry<String, Long>> wordWithHighestFrequency = wordFrequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        return wordWithHighestFrequency.orElse(null);
    }
}
