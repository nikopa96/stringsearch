package com.stringsearch.stringsearch;

import com.stringsearch.stringsearch.search.StringSearch;
import com.stringsearch.stringsearch.search.TextFileReader;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "classpath:static/raamat.txt";
        String start = "*** START OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";
        String end = "*** END OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";

        TextFileReader textFileReader = new TextFileReader();
        List<String> wordList = textFileReader.getTextAsWordList(filePath, start, end);

        StringSearch stringSearch = new StringSearch();
        System.out.println(stringSearch.getLongestWords(wordList));
        System.out.println(stringSearch.getWordsWithHighestFrequency(wordList));
    }
}
