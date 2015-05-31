import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		int choice;
		int totalWords;		
		HashMap<String, Integer> hm;
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		
		while (running) {
			System.out.println("Enter \'1\' to read from user input, \'2\' to read from text file, \'3\' to exit:");
			choice = scan.nextInt();
			
			switch (choice) {
				case 1:
					//===============================================
					//	reading from user input
					//===============================================
					System.out.println("Please enter a string:");
					scan.nextLine();
					input = scan.nextLine();
					
					totalWords = countTotalWords(input);
					hm = wordSummary(input);
					
					System.out.println("Total number of words: " + totalWords);		
					printWordSummary(hm);
					break;
				case 2:
					//===============================================
					//	reading from text file
					//===============================================
					System.out.println("Please enter a filepath:");
					scan.nextLine();
					input = scan.nextLine();
					
					try {
						FileReader fr = new FileReader(input);
						BufferedReader br = new BufferedReader(fr);
						String text = "";
						
						while (br.ready()) {
							text += br.readLine();
						}
										
						br.close();
						
						totalWords = countTotalWords(text);
						hm = wordSummary(text);
						
						System.out.println("Total number of words: " + totalWords);		
						printWordSummary(hm);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					running = false;
					break;
				default:
					System.out.println("Invalid choice. Please choose again.");					
					break;
			}//end switch		
		}//end while		

		System.out.println("Program ending.");
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
