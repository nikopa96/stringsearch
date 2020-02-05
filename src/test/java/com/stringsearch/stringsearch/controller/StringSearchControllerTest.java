package com.stringsearch.stringsearch.controller;

import com.stringsearch.stringsearch.search.StringSearch;
import com.stringsearch.stringsearch.search.TextFileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StringSearchControllerTest {

    @Autowired
    private StringSearchController stringSearchController;

    @MockBean
    private TextFileReader textFileReader;

    @MockBean
    private StringSearch stringSearch;

    @Test
    @DisplayName("should run all methods in the Service class that are looking for the longest word")
    void getLongestWordsTest() throws IOException {
        when(textFileReader.getTextAsWordList(anyString(), anyString(), anyString())).thenReturn(new ArrayList<>());
        stringSearchController.getLongestWords();

        verify(textFileReader, times(1)).getTextAsWordList(anyString(), anyString(),
                anyString());
        verify(stringSearch, times(1)).getLongestWords(anyList());
    }

    @Test
    @DisplayName("should run all methods in the Service class that are looking for the word with the highest frequency")
    void getWordsWithHighestFrequencyTest() throws IOException {
        when(textFileReader.getTextAsWordList(anyString(), anyString(), anyString())).thenReturn(new ArrayList<>());
        stringSearchController.getWordsWithHighestFrequency();

        verify(textFileReader, times(1)).getTextAsWordList(anyString(), anyString(),
                anyString());
        verify(stringSearch, times(1)).getWordsWithHighestFrequency(anyList());
    }
}
