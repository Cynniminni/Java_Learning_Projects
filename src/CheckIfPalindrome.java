import java.util.Scanner;

/*
 * Checks if the string entered by the user is a palindrome. 
 * That is that it reads the same forwards as backwards 
 * like “racecar”
 * 
 * Note: punctuation and capitalization are ignored to include
 * sentences that are palindromes.
 */

public class CheckIfPalindrome {
	
	public static final String PUNCTUATION = " ,\'\"!?<>{}[]()@#$%^&*:";
	
	public static void main(String[] args) {
		String original;
		String forwards;
		Scanner scan = new Scanner(System.in);		
		
		while (true) {
			//get user input
			System.out.println("Please enter a string. Enter \'exit\' to stop.");
			original = scan.nextLine();
			forwards = original;		
			
			if (original.equalsIgnoreCase("exit")) {
				break;
			}															
			
			for (int i = 0; i < original.length(); i++) {
				char letter = original.charAt(i);
				
				if (letter == '.') {
					//remove dots
					forwards = forwards.replaceAll("\\.", "");
				} else if (PUNCTUATION.contains(Character.toString(letter))) {
					//remove any punctuation from the string including white space
					forwards = forwards.replaceAll(Character.toString(letter), "");	
				} 				
			}
			//reverse the string for checking
			String backwards = new StringBuilder(forwards).reverse().toString();
		
			if (forwards.toString().equalsIgnoreCase(backwards)) {
				System.out.println(original + " is a palindrome.");
			} else {
				System.out.println(original + " is not a palindrome");
			}
		}//end while
		
		System.out.println("Program ended.");
	}//end main
}//end class
