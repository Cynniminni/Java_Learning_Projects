import java.util.Scanner;
import java.util.Stack;

/*
 * Enter a string and the program will reverse it and print it out.
 */
public class ReverseAString {
	public static void main(String[] args) {
		//initialize
		String input = "";
		Stack stack = new Stack<Character>();
		Scanner scan = new Scanner(System.in);
		
		//prompt for string input
		System.out.println("Please enter a string:");
		input = scan.nextLine();
		
		//push input onto a stack
		for (int i = 0; i < input.length(); i++) {
			stack.push(input.charAt(i));
		}
		
		System.out.println("The reversed string is:\n");
		
		//print reversed string
		for (int i = 0; i < input.length(); i++) {
			System.out.print(stack.pop());
		}
	}
}
