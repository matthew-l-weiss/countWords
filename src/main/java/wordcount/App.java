package wordcount;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;


public class App 
{
   public void countWords(String sentence) {

      Map<Long, List<String>> groupedOccurrences = Arrays.asList(sentence.split(" ")).stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
            .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

      for (Long key : groupedOccurrences.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
         Collection<String> collection = groupedOccurrences.get(key);
         for (String occurrence : collection) {
            System.out.println(key + " " + occurrence);
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
