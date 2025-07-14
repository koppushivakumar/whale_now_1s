package epicor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Epicormain {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder sbOut = new StringBuilder();
		final Set<String> wordsExclude = Set.of("in", "at", "he", "she", "it", "and", "or", "but", "then", "than", "on",
				"the", "a", "an", "is", "was", "of", "to", "that", "his", "i", "s", "as", "with", "for", "all", "this",
				"by", "not", "so", "from", "him", "be","you");
		List<String> listr = new ArrayList<String>(); 

		try (InputStream is = EpicorMain.class.getClassLoader().getResourceAsStream("moby.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append(" ");
			}
			// converting whole lines to lowercase
			// replacing space, \n, \t with space
			// replacing all characters except alphabets a-z with space
			// spliting the string with space
			String strArray[] = sb.toString().toLowerCase().replaceAll("[^a-z\\s]", " ").split("\\s+");
			listr = Arrays.asList(strArray);
			sbOut.append("Total Words: " + listr.size()).append("\n");
			System.out.println("Total Words: " + listr.size());
			System.out.println();
			// Using streams converting list to map, word as key and count as value
			Map<String, Long> map = listr.stream().filter(s -> !wordsExclude.contains(s))
					.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
			System.out.println();
			sbOut.append("Top 5 most frequent: ").append("\n");
			System.out.println("Top 5 most frequent: ");
			System.out.println();

			map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(5).map(s -> {
				sbOut.append("word " + s.getKey() + " - count " + s.getValue()).append("\n");
				return s;
			}).forEach(System.out::println);
			System.out.println();
			System.out.println("Alphabetically sorted list: ");
			sbOut.append("Alphabetically sorted list: ").append("\n");
			System.out.println();
			map.entrySet().stream().filter(s -> !s.getKey().isEmpty()).sorted(Map.Entry.<String, Long>comparingByKey())
					.limit(50).map(s -> {
						sbOut.append(s.getKey()).append("\n");
						return s;
					}).forEach(s -> System.out.println(s.getKey()));
			//Writing the output to output_epicor.txt
			FileWriter writer = new FileWriter("C:\\Users\\koppu\\OneDrive\\Desktop\\output_epicor.txt");
			writer.write(sbOut.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
