package com.stringsearch.stringsearch.service;

import com.stringsearch.stringsearch.search.StringSearch;
import com.stringsearch.stringsearch.search.TextFileReader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StringSearchService {

    @NonNull
    private TextFileReader textFileReader;

    @NonNull
    private StringSearch stringSearch;

    public List<String> getLongestWords(String filePath, String start, String end) throws IOException {
        List<String> wordList = textFileReader.getTextAsWordList(filePath, start, end);

        return stringSearch.getLongestWords(wordList);
    }

    public Map.Entry<String, Long> getWordsWithHighestFrequency(String filePath, String start, String end)
            throws IOException {
        List<String> wordList = textFileReader.getTextAsWordList(filePath, start, end);

        return stringSearch.getWordsWithHighestFrequency(wordList);
    }
}
