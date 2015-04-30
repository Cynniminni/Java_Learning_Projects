/*
 * Enter a string and the program counts the number of vowels in the text. 
 * For added complexity have it report a sum of each vowel found.
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CountVowels {

	public static final String VOWELS = "aeiouAEIOU";
	public static HashMap vowelMap = new HashMap<Character, Integer>();
	
	public static void main(String[] args) {
		//initialize
		String input;		
		Scanner scan = new Scanner(System.in);
		
		//prompt user for string
		System.out.println("Please enter a string:");
		input = scan.nextLine();			
		
		//output total number of vowels found
		System.out.println("Total number of vowels found: " + countVowels(input));
		
		//output sum of each vowel found
		System.out.println("Sum of each vowel found:");
		printVowelMap();
	}
	
	public static boolean isVowel(char c) {
		if (VOWELS.contains(Character.toString(c))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int countVowels(String input) {
		int total = 0;
		char letter;
		
		for (int i = 0; i < input.length(); i++) {
			letter = input.charAt(i);
			
			if (isVowel(letter)) {
				total++;
				
				if(!vowelMap.containsKey(letter)) {
					vowelMap.put(letter, 1);
				} else {
					int count = (int) vowelMap.get(letter);
					vowelMap.put(letter, count + 1);
				}
			}
		}
		
		return total;
	}

	public static void printVowelMap() {
		Set set = vowelMap.entrySet();
		Iterator i = set.iterator();
		
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
}
