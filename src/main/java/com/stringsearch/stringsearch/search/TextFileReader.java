package com.stringsearch.stringsearch.search;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TextFileReader {

    String getTextFromFileAsString(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public List<String> getTextAsWordList(String filePath, String start, String end) throws IOException {
        String rawText = getTextFromFileAsString(filePath);

        String newText1 = rawText.substring(rawText.indexOf(start) + start.length());
        String newText2 = newText1.substring(0, newText1.lastIndexOf(end));
        String newText3 = newText2.toLowerCase().replaceAll("[^a-zA-Z\\s+]", " ");

        List<String> wordsList = new ArrayList<>(Arrays.asList(newText3.split("\\s+")));
        wordsList.removeAll(Collections.singleton(""));

        return wordsList;
    }
}
