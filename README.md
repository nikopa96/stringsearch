# String search

The solution consists of two parts:
1. **TextFileReader class** that reads a text file and parses it. 
Parser removes newlines and punctuation marks, divides words into two parts if they contain
an apostrophe or hyphen.

2. **StringSearch class** that searches for the longest word and the word with the highest frequency.

## Before the start
Before starting, we must determine the path to the text file, the beginning of the book,
the end of the book.

For Example, raamat.txt file contains the book itself, the license and references at the 
end of the file. We should work only with a book.

We can ignore the beginning and end of the file using an empty string 
as a parameter of the method. In this case, the program may give a different result.
```java
String filePath = "classpath:static/raamat.txt";
String start = "*** START OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";
String end = "*** END OF THIS PROJECT GUTENBERG EBOOK THE HOUND OF THE BASKERVILLES ***";

TextFileReader textFileReader = new TextFileReader();
List<String> wordList = textFileReader.getTextAsWordList(filePath, start, end);
```

## TextFileReader
We read the file and convert it to a string.
```java
String getTextFromFileAsString(String filePath) throws IOException {
    File file = ResourceUtils.getFile(filePath);
    return new String(Files.readAllBytes(file.toPath()));
}
```
After that, we crop the license text and references. Using regex we remove newlines 
and punctuation marks, divide words into two parts if they contain an apostrophe or hyphen.
Finally, we convert the string to a list and remove empty strings (if they are).
```java
public List<String> getTextAsWordList(String filePath, String start, String end) throws IOException {
    String rawText = getTextFromFileAsString(filePath);

    String newText1 = rawText.substring(rawText.indexOf(start) + start.length());
    String newText2 = newText1.substring(0, newText1.lastIndexOf(end));
    String newText3 = newText2.toLowerCase().replaceAll("[^a-zA-Z\\s+]", " ");

    List<String> wordsList = new ArrayList<>(Arrays.asList(newText3.split("\\s+")));
    wordsList.removeAll(Collections.singleton(""));

    return wordsList;
}
```

## String Search
In order to find the longest word, we sort the words in the list by their length.
After that, we reverse the list: the first word is the longest.

Then, we look for all words with the same length and remove duplicates using **distinct()** method.
We do it because the condition of the task says that several words can be returned 
if the have the same length and this length is the highest.
```java
public List<String> getLongestWords(List<String> wordList) {
    wordList.sort(Comparator.comparing(String::length));
    Collections.reverse(wordList);

    return wordList.stream().filter(word -> word.length() == wordList.get(0).length())
            .distinct()
            .collect(Collectors.toList());
}
```
In order to find the word with the highest frequency, we must group them by frequency using
**Collectors.groupingBy()** method. However, before that we must filter out words whose length
is less than 8 characters using **stream().filter()** method.

Then we return HashMap element with the highest value using **stream().max()** method.
```java
public Map.Entry<String, Long> getWordsWithHighestFrequency(List<String> wordList) {
    Map<String, Long> wordFrequencyMap = wordList.stream()
            .filter(word -> word.length() >= 8)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    Optional<Map.Entry<String, Long>> wordWithHighestFrequency = wordFrequencyMap.entrySet().stream()
            .max(Map.Entry.comparingByValue());

    return wordWithHighestFrequency.orElse(null);
}
```

## Program launch
1. We can run the program using the traditional **main()** method in the **Main class**
2. We can run the methods in the REST Controller.

    Get the longest words
    ```
    http://localhost:8080/stringSearch/getLongestWords
    ```
    
    Get the word with the highest frequency
    ```
    http://localhost:8080/stringSearch/getWordsWithHighestFrequency
    ```

## Unit Tests
Unit tests are in the test folder

## Results
* The longest word (without license text etc): **"supernaturalists"**
* The longest words (whole file with license etc): **"unenforceability"**, **"supernaturalists"**
* Word with the highest frequency (both variants): **"baskerville"** (frequency: 114)
