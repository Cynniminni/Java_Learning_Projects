import java.util.Scanner;

/*
 * Pig Latin is a game of alterations played on the English language game. 
 * To create the Pig Latin form of an English word the initial consonant sound 
 * is transposed to the end of the word and an ay is affixed 
 * (Ex.: "banana" would yield anana-bay). 
 * Read Wikipedia for more information on rules.
 */

public class PigLatin {
	public static final String VOWELS = "aeiouAEIOU";
	
	public static void main(String[] args) {
		//initialize
		String input;
		char firstChar;
		Scanner scan = new Scanner(System.in);
		
		//prompt user for input
		System.out.println("Please enter a word:");
		input = scan.next();
		firstChar = input.charAt(0);
		
		System.out.println("Pig Latin of \'" + input + "\' is:");
		
		//check if it's a consonant
		if (isConsonant(firstChar)) {			
			System.out.println(applyConsonantRule(input, firstChar));
			
		} else {
			System.out.println(applyVowelRule(input, firstChar));
		}
	}
	
	public static boolean isConsonant(char c) {
		if (VOWELS.contains(Character.toString(c))) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String applyConsonantRule(String word, char firstChar) {
		//move first char to the end
		word = word.substring(1) + firstChar;
		
		//add "ay" to the end
		word = word + "ay";
		
		return word;
	}
	
	public static String applyVowelRule(String word, char firstChar) {
		//add "way" to the end
		word = word + "way";
		
		return word;
	}
}
