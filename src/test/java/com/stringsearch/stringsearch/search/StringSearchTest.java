package com.stringsearch.stringsearch.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StringSearchTest {

    private TextFileReader textFileReader;
    private StringSearch stringSearch;

    private String text;

    @BeforeEach
    void setUp() {
        this.textFileReader = mock(TextFileReader.class);
        this.stringSearch = new StringSearch();

        this.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse porttitor ligulaut " +
                "condimentum suscipit! Suspendisse commodo nec nibh veleleifend. Morbi nec sodales nisl. " +
                "Donec hendrerit leo ut egestas maximus. Nullam vehicula consectetur ante nec ultrices";
    }

    @Test
    @DisplayName("should return the longest word (or words) in the text")
    void getLongestWordsTest() throws IOException {
        String filePath = "test.txt";
        String start = "Lorem ipsum dolor sit amet";
        String end = "nibh veleleifend";

        when(textFileReader.getTextFromFileAsString(filePath)).thenReturn(text);
        when(textFileReader.getTextAsWordList(filePath, start, end)).thenCallRealMethod();

        assertEquals(Arrays.asList("suspendisse", "condimentum", "consectetur"),
                stringSearch.getLongestWords(textFileReader.getTextAsWordList(filePath, start, end)));

        start = "Morbi nec sodales nisl";
        end = "";
        when(textFileReader.getTextAsWordList(filePath, start, end)).thenCallRealMethod();
        assertEquals(Collections.singletonList("consectetur"),
                stringSearch.getLongestWords(textFileReader.getTextAsWordList(filePath, start, end)));
    }

    @Test
    @DisplayName("should return the word with the highest frequency")
    void getWordsWithHighestFrequencyTest() throws IOException {
        String filePath = "test.txt";
        String start = "";
        String end = "";

        when(textFileReader.getTextFromFileAsString(filePath)).thenReturn(text);
        when(textFileReader.getTextAsWordList(filePath, start, end)).thenCallRealMethod();

        Map.Entry<String, Long> actualAnswer = stringSearch
                .getWordsWithHighestFrequency(textFileReader.getTextAsWordList(filePath, start, end));

        assertEquals("suspendisse", actualAnswer.getKey());
        assertEquals(2L, actualAnswer.getValue());
    }
}
