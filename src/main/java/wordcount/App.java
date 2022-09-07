package wordcount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class App 
{

   public void countWords(String sentence) {
      SortedMap<Long, Collection<String>> groupedOccurrences = new TreeMap<>(Collections.reverseOrder());

      Arrays.asList(sentence.split(" ")).stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
            .forEach((entry) -> {
               Collection<String> stringList = groupedOccurrences.getOrDefault(entry.getValue(), new ArrayList<>());
               stringList.add(entry.getKey());
               groupedOccurrences.put(entry.getValue(), stringList);
            });

      for (Long wordCount : groupedOccurrences.keySet()) {
         Collection<String> words = groupedOccurrences.get(wordCount);
         for (String word : words) {
            System.out.println(wordCount + " " + word);
         }
      }
   }

   public static void main(String[] args) {
      System.out.println("Enter a string and press enter");
      Scanner in = new Scanner(System.in);
      App instance = new App();
      instance.countWords(in.nextLine());
   }
}
