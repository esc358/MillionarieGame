/**
 * Final Project
 * Application Purpose: This Class contains all methods necessary to run the game
 * Author: Emilio Conde
 * Date: March 25, 2021
 * Time: 2:00 pm EST
 */

//import packages
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//Class declaration
public class MillionaireMethods
{
	/*
	Static void method that prints the rules and a welcome message
	 */
	public static void sayWelcome()
	{
		String welcome = "\n\n\nWelcome to Who Wants to be Millionarie! \n\n\n";
		String rules = new StringBuilder().append("The rules of the game are as follow:\n\n").append("1. You must select an User Name that contains a maximum of 10 characters. ").append("A combination of letters, numbers and special characters is allowed.\n").append("2. You must be of age 10 to 100 years old \n").append("3. You must type the letter you think is the answer. Options are A,B,C,D\n").append("4. You can use a 50/50 which will show the right answer and another option. Only one time.\n").append("5. You can decide to exit the game if you cannot answer a question. This lets you exit the game giving you the point that you have acumulated so far. \n").append("6. If you answer wrong a question you loose all the point that you have accumulated.\n").append("\n ").toString();
		String message = welcome + rules;
		System.out.println(message);
	}

	/*
	Static void method that prints the message to start the game
	 */
	public static void sayStartMessage()
	{
		String message = "\nNow we can begin with the questions \n";
		System.out.println(message);
	}

	/*This method is to verify that the username is less than
	 10 characters and it returns a String.	The method validates through the scanner class that the
	user input is equal to or less than 10 characters
	 */
	public static String verifyUsername()
	{
		boolean isUser = true;
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		do
		{
			System.out.println("\nType a User Name\n");
			userInput = scanner.nextLine().trim();
			if (userInput.length() <= 10)
			{
				isUser = false;
			} else if (userInput.length() > 10)
			{
				System.out.println("Username has more than 10 character. Please review rules and select a valid Username.\n");
				scanner = new Scanner(System.in);
			}

		} while (isUser);
		return userInput;
	}

	/*This method is to verify that the user ages is valid and
	 that is between 10 to 100. The method uses a try catch exception if the user
	 types any character that is not a number. The method returns the age input.
	 */
	public static int verifyUserAge()
	{
		boolean isAge = true;
		int ageInput = 0;
		Scanner scanner = new Scanner(System.in);
		while (isAge)
		{
			try
			{
				System.out.println("\nType your Age\n");
				ageInput = scanner.nextInt();
				if (ageInput >= 10 && ageInput <= 100)
				{
					isAge = false;
				}
				else if (ageInput < 10)
				{
					System.out.println("\nYou are below 10 years old, please review rules.");
				}
				else if (ageInput > 100)
				{
					System.out.println("\nYou are above 100 years old, please review rules.");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("\nSeems like you type a letter, be sure to type a valid number \n");
			}
			scanner = new Scanner(System.in);
		}
		return ageInput;
	}

	/*
	This static void method runs the application. Prints a welcome message and ask the user
	through the use of scanner if the user wants to play type yes, if no type no.
	It validates only those two answers. If the user types yes the method game starts is executed.
	 */
	public static void runApplication()
	{
		Scanner scanner = new Scanner(System.in);
		boolean isPlay = true;
		String playerInput = "";
		sayWelcome();
		System.out.println("\nAre you ready to start the game?\nType yes the game will begin. Type no the game will end.\n");
		while (isPlay)
		{
			playerInput = scanner.nextLine().trim();
			if (playerInput.equalsIgnoreCase("yes") )
			{
				startGame();
				System.out.println("\n\nDo you want to play Again? Type yes or no.\n");
			}
			else if(playerInput.equalsIgnoreCase("no"))
			{
				isPlay=false;
				System.out.println("\nThe game is finished.\n");
			}
			else if(!playerInput.equalsIgnoreCase("yes") || !playerInput.equalsIgnoreCase("no"))
			{
				System.out.println("\nPlease type yes or no only.\n");
			}
			scanner = new Scanner(System.in);
		}
	}

	/*
	This static int method has the logic of the game. It starts by instantiating the Millionaire person and setting
	the age and username by using the verify methods. Then the method does a do while where it creates a single
	dimensional array to strore the options.
	there are all the conditions
	to validate the input of the user. T
	 */
	public static int startGame()
	{
		//variables for this method
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		boolean used5050 = false;
		int counter = 0;

		//instantiate a player and set age and username by verifying username length and age
		MillionairePerson player = new MillionairePerson();
		player.setUserName(verifyUsername());
		player.setAge(verifyUserAge());
		System.out.println("\nWelcome to the game User: " + player.getUserName() + "\n" + "Age verified: " + player.getAge() + " years old");
		sayStartMessage();

		do
		{
			//create a single dimensional array to store options
			String[] options = new String[4];
			int counterOptions = 0;

			//iterate through all the questions in the millionaire data class
			for (int i = 0; i < MillionaireData.getQuestions().length; i++)
			{
				//create a string for each question column
				String identifier = MillionaireData.getQuestions()[i][0];
				String question = MillionaireData.getQuestions()[i][1];
				String correctAnswer = MillionaireData.getQuestions()[i][2];

				//iterate through all the options in the millionaire data class
				for (int j = 0; j < MillionaireData.getOptions().length; j++)
				{
					//condition to compare options to string identifier
					if (MillionaireData.getOptions()[j][0].equals(identifier) && counterOptions < 4)
					{
						//store options in single dimensional array and increase counter options
						options[counterOptions] = MillionaireData.getOptions()[j][1];
						counterOptions++;
					}

					//condition to break counter options and reset counter
					if (counterOptions == 4)
					{
						counterOptions = 0;
						break;
					}
				}

				//static method print each questions, with options
				printQuestion(question, options);

				//reset counter options to 0
				counterOptions = 0;

				//conditions to validate if 50/50 options has been used
				if (!used5050)
				{
					System.out.print("\nPlease type the correct answer, or use your 50/50, or if you want to exit with the answers you have type exit.\n");
				}
				else
				{
					System.out.print("\nPlease type the correct answer. 50/50 is not valid anymore.\n");
				}

				//take input from the scanner
				String userInput = scanner.nextLine().trim();

				//condition is user has used 50/50 to repeat the following message
				if (used5050 && userInput.equalsIgnoreCase("50/50"))
				{
					//while user types 50/50 print the following and scan input again
					while (userInput.trim().equalsIgnoreCase("50/50"))
					{
						System.out.print("\nSorry! You cannot use 50/50 more than once.\n");
						userInput = scanner.nextLine().trim();
					}
				}

				//condition letters that are not valid, and validate with other conditions the letters that are valid
				if (!userInput.equalsIgnoreCase("a") && !userInput.equalsIgnoreCase("b") && !userInput.equalsIgnoreCase("c") && !userInput.equalsIgnoreCase("d") && !userInput.equalsIgnoreCase("50/50") && !userInput.equalsIgnoreCase("exit"))
				{
					boolean character = true;
					do
					{
						System.out.println("\nThat is not a valid input. Please type only one valid letter a,b,c or d.\n");
						userInput = scanner.nextLine().trim();
						if (userInput.equalsIgnoreCase("a") || userInput.equalsIgnoreCase("b") || userInput.equalsIgnoreCase("c") || userInput.equalsIgnoreCase("d"))
						{
							character = false;
						}
					} while (character);
				}

				//condition check if the answer is correct
				if (!userInput.equalsIgnoreCase("50/50") && !userInput.equalsIgnoreCase("exit"))
				{
					boolean result = evaluateAnswer(userInput, correctAnswer);
					if (!result)
					{
						System.out.print("\nGame Over... " + "Your correct answers where: " + counter +"/10");
						return counter;
					}
					counter++;
					System.out.println("\nYou have the right answer: " + userInput + " " + counter +"/10\n");
				}

				//condition if user types 50/50
				if (userInput.equalsIgnoreCase("50/50"))
				{
					used5050 = true;
					String[] arrayWithWrongAnswers = new String[3];//create single dimensional array
					int counterWrongOptions = 0;
					for (int j = 0; j < options.length; j++)//iterate through array
					{
						//condition as long is not the correct answer
						if (!correctAnswer.equalsIgnoreCase(getOptionLetter(j)))
						{
							arrayWithWrongAnswers[counterWrongOptions] = getOptionLetter(j);//store in array
							counterWrongOptions++;
						}
					}

					//create a string and generate a random and store it string
					String randomWrongAnswer = arrayWithWrongAnswers[generateRan(0, 2)];

					//print questions static method
					printQuestion(question, correctAnswer, randomWrongAnswer, options);

					System.out.print("\n Please type the correct answer a,b,c,d. You cannot use 50/50 anymore.\n");

					userInput = scanner.nextLine().trim();

					//conditions if user types the follow
					if (userInput.equalsIgnoreCase("50/50") || !userInput.equalsIgnoreCase("a") && !userInput.equalsIgnoreCase("b") && !userInput.equalsIgnoreCase("c") && !userInput.equalsIgnoreCase("d") && !userInput.equalsIgnoreCase("50/50") && !userInput.equalsIgnoreCase("exit"))
					{
						boolean isTrue=true;
						do
						{
							//condition if user types the follow
							if(userInput.equalsIgnoreCase("50/50"))
							{
								System.out.print("\nSorry! You cannot use 50/50 anymore\nr");
								isTrue=true;
								userInput = scanner.nextLine().trim();
							}

							//condition if user types not valid letters
							if (!userInput.equalsIgnoreCase("a") && !userInput.equalsIgnoreCase("b") && !userInput.equalsIgnoreCase("c") && !userInput.equalsIgnoreCase("d") && !userInput.equalsIgnoreCase("50/50") && !userInput.equalsIgnoreCase("exit"))
							{
								System.out.print("Not valid input. Please type a,b,c or d\n");
								isTrue=true;
								userInput = scanner.nextLine().trim();
							}
						}while (isTrue);
					}
					//condition if user types follow
					if(userInput.equalsIgnoreCase("a") || userInput.equalsIgnoreCase("b") || userInput.equalsIgnoreCase("c") || userInput.equalsIgnoreCase("d"))
					{
						counter++;
						System.out.print("You were saved! You have correct answer: " + counter +"/10");
					}
				}

				//condition if evaluate answer is not true
				if (!evaluateAnswer(userInput, correctAnswer))
				{
					System.out.print("Game Over..." + "Respuestas Correctas: " + counter);
					return counter;
				}

				//condition if user types exit
				if (userInput.equalsIgnoreCase("exit"))
				{
					System.out.print("Game Over..." + "Respuestas Correctas: " + counter);
					return counter;
				}
			}
			System.out.print("You won the game! You have all the answers correct: " + counter +"/10");
			return counter;
		} while (!exit);

	}

	/*
	Private static int method that generates a random number by taking into two parameters.
	The method returns a number that is random and it is between the given parameters.
	 */
	private static int generateRan(int minimum, int maximum)
	{
		Random rand = new Random();
		int min = minimum;
		int max = maximum;
		int num = rand.nextInt((max - min) + 1) + min;
		return num;
	}

	/*
	Private static String method with int parameter
	The method returns a string for the following switch cases
	 */
	private static String getOptionLetter(int index)
	{
		switch (index)
		{
			case 0:
				return "a";
			case 1:
				return "b";
			case 2:
				return "c";
			case 3:
				return "d";
		}
		return null;
	}

	/*
	Private static void method to print questions and options by taking into parameters
	a string and a single dimensional array. The method prints the string parameter for the question
	and prints the single dimesional array of options by using a while and a switch. The switch
	analyzes the location of the options in the single dimensional array
	 */
	private static void printQuestion(String question, String[] options)
	{
		int counterOptions = 0;
		System.out.println(question);
		while (counterOptions < 4)
		{
			switch (counterOptions)
			{
				case 0:
					System.out.printf("\ta. %s\n", options[counterOptions]);
					break;
				case 1:
					System.out.printf("\tb. %s\n", options[counterOptions]);
					break;
				case 2:
					System.out.printf("\tc. %s\n", options[counterOptions]);
					break;
				case 3:
					System.out.printf("\td. %s\n\n", options[counterOptions]);
					break;
			}
			counterOptions++;
		}
	}

	/*
	Private static void method that takes 4 parameters.
	The method prints the question and the available options.
	 */
	private static void printQuestion(String question, String correctAnswer, String randomWrongAnswer, String[] options)
	{
		int counterOptions = 0;
		System.out.println(question);
		while (counterOptions < 4)
		{
			switch (counterOptions)
			{
				case 0:
					//condition if
					if (getOptionLetter(counterOptions).equalsIgnoreCase(correctAnswer) || getOptionLetter(counterOptions).equalsIgnoreCase(randomWrongAnswer))
					{
						System.out.printf("\ta. %s\n", options[counterOptions]);
					}
					else
					{
						System.out.printf("\ta.\n");
					}
					break;
				case 1:
					if (getOptionLetter(counterOptions).equalsIgnoreCase(correctAnswer) || getOptionLetter(counterOptions).equalsIgnoreCase(randomWrongAnswer))
					{
						System.out.printf("\tb. %s\n", options[counterOptions]);
					}
					else
					{
						System.out.printf("\tb.\n");
					}
					break;
				case 2:
					if (getOptionLetter(counterOptions).equalsIgnoreCase(correctAnswer)	|| getOptionLetter(counterOptions).equalsIgnoreCase(randomWrongAnswer))
					{
						System.out.printf("\tc. %s\n", options[counterOptions]);
					}
					else
					{
						System.out.printf("\tc.\n");
					}
					break;
				case 3:
					if (getOptionLetter(counterOptions).equalsIgnoreCase(correctAnswer)
							|| getOptionLetter(counterOptions).equalsIgnoreCase(randomWrongAnswer))
					{
						System.out.printf("\td. %s\n\n", options[counterOptions]);
					}
					else
					{
						System.out.printf("\td.\n");
					}
					break;
			}
			counterOptions++;
		}
	}

	/*
	Private static boolean method. Takes into parameters two strings.
	By conditions it checks if length is equal to 1, if so check evaluate answer method to see if it is true,
	if not returns false.
	 */
	private static boolean evaluateAnswer(String nextLine, String correctAnswer)
	{
		if (nextLine.length() == 1)
		{
			if (evaluateAnswer(nextLine.charAt(0)))
			{
				if (nextLine.equalsIgnoreCase(correctAnswer))
				{
					return true;
				}
			}
			else
			{
				System.out.printf("\nSorry the answer you typed (%s) is not correct.\n", nextLine);
			}
		}
		return false;
	}

	/*
	Private static boolean method takes one char parameter.
	The method returns true if char is equal to a,b,c,d.
	 */
	private static boolean evaluateAnswer(char character)
	{
		if (String.valueOf(character).equalsIgnoreCase("a") || String.valueOf(character).equalsIgnoreCase("b")
				|| String.valueOf(character).equalsIgnoreCase("c") || String.valueOf(character).equalsIgnoreCase("d"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
