package com.stringsearch.stringsearch.controller;

import com.stringsearch.stringsearch.service.StringSearchService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/stringSearch")
public class StringSearchController {

    @NonNull
    private StringSearchService stringSearchService;

    @GetMapping(path = "/getLongestWords")
    @CrossOrigin
    public List<String> getLongestWords() throws IOException {
        String filePath = "classpath:static/raamat.txt";
        String start = "*** START OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";
        String end = "*** END OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";

        return stringSearchService.getLongestWords(filePath, start, end);
    }

    @GetMapping(path = "/getWordsWithHighestFrequency")
    @CrossOrigin
    public Map.Entry<String, Long> getWordsWithHighestFrequency() throws IOException {
        String filePath = "classpath:static/raamat.txt";
        String start = "*** START OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";
        String end = "*** END OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";

        return stringSearchService.getWordsWithHighestFrequency(filePath, start, end);
    }
}
