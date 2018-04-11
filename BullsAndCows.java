package project2;

import java.util.*; //imports all of the java utilities including the Scanner class

public class BullsAndCows {
	
	/*
	 * Method provided for generating a random code with no duplicate digits
	 */
	
	public static String generateRandomCode(int codeLength) {
		List<Integer> numbers = new ArrayList<>();
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }

	    Collections.shuffle(numbers);

	    String code = "";
	    for(int i = 0; i < codeLength; i++){
	        code += numbers.get(i).toString();
	    } 
	    return code;
	}
	
	/*
	 * Method for checking how many bulls are in the guess when compared to the code
	 * Returns the int indicating how many bulls
	 */
	
	public static int checkBulls(String guess, String code) {
		int bulls = 0; //creates a variable for bulls and initializes the number to 0
		for (int i = 0; i<=(code.length() - 1); i++) { //for loops iterating from zero to the last index for the code (which happens to be the same number as the guess)
			if (guess.charAt(i) == code.charAt(i)) //if the char (which is a digit) is the exact same at the exact index,
				++bulls; //increments the bulls variable
		}
		return bulls; //returns the total number of bulls after the loop is done executing
	}
	
	/*
	 * Method for checking how many cows are in the guess compared to the code
	 * Returns int
	 */
	
	public static int checkCows(String guess, String code) {
		int cows = 0; //creates a variable for cows and initializes the value to be 0
		char letter; //creates a char variable called letter
		for (int index = 0; index<=(code.length() - 1); index++) { //loop that loops through however many digits there are in the code/guess
			letter = guess.charAt(index); //stores the value of the char of where the iterator is into the letter variable. will be used for the next loop
			for (int iterator = 1; iterator<=(code.length() - 1); iterator++) { //for loop that checks if the letter is in the code at all (in any index except its own)
				if (letter == code.charAt((index + iterator) % code.length())) //uses the remainder operator so that the index parameter in the charAt method never goes out of bounds (if it reaches the end of the code it loops back to the beginning)
					++cows; //increments the cow variable
			}
		}
		return cows; //returns the total value of the cows variable
	}
	
	/*
	 * Method for checking if a digit occurs more than once within the user's guess
	 */
	
	public static Boolean checkRepetition(String guess) {
		Boolean repetition = false; //creates the boolean variable for repetition and initializes it to false (no repetition)
		char initial; //creates a char variable 
		for (int i = 0; i<=(guess.length() - 1); i++) { //for loop iterating through the length of the guess 
			initial = guess.charAt(i); //assigns the char at the specified index in guess as the value for the initial variable
			for (int index = 1; index<=(guess.length() - 1); index++) { //for loop for iterating through each digit in guess
				if (initial == guess.charAt((i + index) % guess.length())) { //checks if that digit occurs in any other spot in guess, and if it does
					repetition = true; //assigns the repetition value as true
				}
			}
		}
		return repetition; //returns the value of the boolean repetition
	}
	
	public static void main(String[] args) {
		int codeLength; //creates an int variable codeLength
		String guess = "", code = "", play = "y"; //creates String objects guess, code and play. Guess and code are initialized to empty strings and play is initialized to "y"
		String xDigit = "0"; //creates String object called xDigit and initialized as "0"
		
		Scanner scan = new Scanner(System.in); //creates Scanner object for user input
		
		System.out.println("Welcome to \"Bulls and Cows\""); //prints out intro line
		
		do { //executes this as long as the value of play is "y"
			if (play != "y") { //double checks if play is "y" and if it isn't then it breaks out of the do-while loop
				break; 
			} else if (guess.equals("quit")) { //double checks if the user input "quit" as a guess and breaks out of the loop if it is
				break; 
			} else {
				System.out.println("Which option do you want to play? 3 digits, 4 digits or 5 digits? "); //prompts the user
				
				codeLength = scan.nextInt(); //stores the user's answer into the variable codeLength
			
				code = generateRandomCode(codeLength); //the value of the String code is assigned to be a randomly generated code with the specified length using the method generateRandomCode
				
				if (codeLength == 3) { //prints the following line if the code has a length of 3
					System.out.println("Number to guess: ???");
				} else if (codeLength == 4) { //prints the following line if the code has a length of 4
					System.out.println("Number to guess: ????");
				} else { //prints the following line if the code has a length of 5
					System.out.println("Number to guess: ?????"); 
				}
				
				while (guess != "quit") { //loops as long as the value the user input into guess is not "quit"
					System.out.println("Enter guess: "); //prompts the user asking for a guess
					guess = scan.next(); //stores that number into guess
					if(guess.equals("quit")) { //checks if the value of guess is "quit", and breaks out of the loop if it is
						break;
					} else {
						if (guess.length() > code.length()) { //checks to see if the guess is longer than the code length
							System.out.println("You can only enter " + code.length() + " digits!"); //notifies the user if it is
						} else if (guess.length() < code.length()) { //checks if the code is longer than the digit
							if ((code.length() - guess.length()) > 1) { //if the difference in length is more than one, than notify the user that each digit must be different
								System.out.println("Each digit must be different!"); //because adding more than one zero creates a duplicate digit
							} else if (code.length() - guess.length() == 1){ //if the difference is only 1, then just concatenate a zero to the beginning of the guess
								guess = xDigit.concat(guess); //uses the String object xDigit to provide an extra zero
							}				
						}
						if (guess.length() == code.length()) { //if the length of guess is the same as the length of the code
							if (checkRepetition(guess)) { //check for duplicates using the method checkRepetition
								System.out.println("Each digit must be different!"); //notify the user if the method returns true
							} else {
								int bulls = checkBulls(guess, code); //calculates the number of bulls using the checkBulls method and stores the value into the int variable bulls
								if (bulls == code.length()) { //if all of the digits in the guess are bulls,
									System.out.println(codeLength + " bulls - " + guess + " is correct!"); //notify the user that the guess is correct
									System.out.println("Enter \'y\' to play again: "); //ask the user if they want to play again
									play = scan.next(); //store their answer into the String object play
									if (play != "y") { //if the String isn't "y" then break out of the loop
										break; 
									}
								} else { //if not all of the digits in the guess are bulls,
									System.out.println(checkBulls(guess, code) + " bulls");	//print out how many bulls there are using checkBulls
									System.out.println(checkCows(guess, code) + " cows"); //print out how many cow there are using checkCows
								}
							}
						}	 
					}
				}
			}
		} while (play == "y");
		
		if (play != "y") { //if the reason why the program exited out of both the do-while and the while loop was because play is no longer "y",
			System.out.println("Thanks for playing!"); //print message because they have completed the game successfully
			scan.close(); //close the scanner
		} else { //else must mean the user input "quit" meaning they didn't successfully find the right answer and want to leave
			System.out.println("Number to guess: " + code); //print out the answer code
			System.out.println("Thanks for playing!"); //print message
			scan.close(); //close the scanner
		}
	}
}
