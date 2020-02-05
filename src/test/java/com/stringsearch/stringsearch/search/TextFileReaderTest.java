package com.stringsearch.stringsearch.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextFileReaderTest {

    @Test
    @DisplayName("should return a list of words without punctuation signs, apostrophes and hyphens")
    void getTextAsWordListTest() throws IOException {
        TextFileReader textFileReader = mock(TextFileReader.class);

        String filePath = "test.txt";

        String text = "Mauris pharetra lectus lorem, ac sodales magna faucibus at. Pellentesque mattis ipsum at " +
                "aliquet ornare.";
        String start = "Mauris pharetra lectus";
        String end = "ornare.";

        when(textFileReader.getTextFromFileAsString(filePath)).thenReturn(text);
        when(textFileReader.getTextAsWordList(filePath, start, end)).thenCallRealMethod();

        List<String> expectedList = Arrays.asList("lorem", "ac", "sodales", "magna", "faucibus", "at", "pellentesque",
                "mattis", "ipsum", "at", "aliquet");
        assertEquals(expectedList, textFileReader.getTextAsWordList(filePath, start, end));

        text = "Praesent, non (mol-lis) mi?! Dui's et Dui elit!!!&";
        start = "";
        end = "";

        when(textFileReader.getTextFromFileAsString(filePath)).thenReturn(text);
        when(textFileReader.getTextAsWordList(filePath, start, end)).thenCallRealMethod();

        expectedList = Arrays.asList("praesent", "non", "mol", "lis", "mi", "dui", "s", "et", "dui", "elit");
        assertEquals(expectedList, textFileReader.getTextAsWordList(filePath, start, end));
    }
}
