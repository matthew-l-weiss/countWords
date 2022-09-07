package wordcount;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

   public List<Map.Entry<Long, List<String>>> countWords(String sentence) {
      return Arrays.asList(sentence.split(" ")).stream()
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
         .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
         .entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).collect(Collectors.toList());
   }

   public static void main(String[] args) {
      System.out.println("Enter a string and press enter");
      Scanner in = new Scanner(System.in);
      App instance = new App();
      List<Map.Entry<Long, List<String>>> wordCount = instance.countWords(in.nextLine());
      for (Map.Entry<Long, List<String>> e : wordCount) {
         for (String word : e.getValue()) {
            System.out.println(e.getKey() + " " + word);
         }
      }
   }
}
