import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/*
 * Counts the number of individual words in a string. 
 * For added complexity read these strings in from a text file 
 * and generate a summary.
 */
public class CountWordsInAString {

	public static void main(String[] args) {
		String input;
		Scanner scan = new Scanner(System.in);		
		
		System.out.println("Enter a string:");
		input = scan.nextLine();
		
		int totalWords = countTotalWords(input);
		HashMap<String, Integer> hm = wordSummary(input);
		
		System.out.println("Total number of words: " + totalWords);		
		printWordSummary(hm);
		
	}//end main
	
	//counts total number of words by tracking whitespaces
	public static int countTotalWords(String input) {
		//counts the total number of words
		int whitespace = 0;
		int endOfLine = input.length() - 1;
		
		for (int i = 0; i < input.length(); i++) {			
			char c = input.charAt(i);
			
			if (Character.isWhitespace(c) && i != endOfLine) {
				whitespace++;
			}
		}//end for
		
		return whitespace + 1;
	}
	
	//counts the occurrences of each individual word and tracks them in a hashmap
	//*does not account for punctuation. "hey!" and "hey!!" will be two separate words
	public static HashMap<String, Integer> wordSummary(String input) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		boolean wordStart = true;
		int startIndex = 0;
		String word;
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
	
			//check if char is the last one
			if (i == input.length() - 1) {
				word = input.substring(startIndex, input.length());
				//check if it exists in hashmap
				if (hm.containsKey(word)) {
					//update count on that word
					hm.put(word, hm.get(word) + 1);
				} else {
					//put it into hashmap and initialize count to 1
					hm.put(word, 1);
				}	
			} else if (Character.isLetter(c) && wordStart) {//check if a word is staring
				startIndex = i;
				wordStart = false;
			} else if (Character.isWhitespace(c)) {//check if a word is ending
				wordStart = true;//next word is starting
				word = input.substring(startIndex, i);//extract first word
				
				//check if it exists in hashmap
				if (hm.containsKey(word)) {
					//update count on that word
					hm.put(word, hm.get(word) + 1);
				} else {
					//put it into hashmap and initialize count to 1
					hm.put(word, 1);
				}				
			}//end if-else
		}//end for
				
		return hm;
	}//end method
	
	public static void printWordSummary(HashMap<String, Integer> hm) {
		Iterator i = hm.entrySet().iterator();
		
		System.out.println("Summary:");
		
		while (i.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) i.next();
			System.out.println(entry.getKey() + "\t=\t" + entry.getValue());
		}//end while
	}//end method
}//end class
