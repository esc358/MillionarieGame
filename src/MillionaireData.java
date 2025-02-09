/**
 * Final Project
 * Application Purpose: This Class has two static methods that return multidimensional arrays
 * Author: Emilio Conde
 */

//Class
public class MillionaireData
{
	/*
	Static method that returns a multidimensional array of questions
	Array organized with (id,question,answer)
	 */
	public static String[][] getQuestions()
	{
		String[][] questions =
				{
						{"1", "1. How many provinces and territories are in Canada?", "d"},
						{"2", "2. Who created the show �The Simpsons�?", "a"},
						{"3", "3. What does a magnet attract?", "c"},
						{"4", "4. What is diabetes?", "a"},
						{"5", "5. How many sides on an octagon?", "d"},
						{"6", "6. What does the word �loquacious� mean?", "b"},
						{"7", "7. What is obstetrics?", "a"},
						{"8", "8. Which of these landmarks was constructed first?", "d"},
						{"9", "9. Who was the first president of the USA?", "a"},
						{"10", "10. How many miles approximately is the Earth away from the sun?", "c"},
				};
		return questions;
	}

	/*
	Static String method that returns a multidimensional array of options for the questions above
	Array organized with (id, options answer)
	 */
	public static String[][] getOptions()
	{
		String[][] options =
				{
						{"1", "11"},
						{"1", "10"},
						{"1", "12"},
						{"1", "13"},
						{"2", "Matt Groening"},
						{"2", "James Gosling"},
						{"2", "Tom Cruise"},
						{"2", "Bruno Mars"},
						{"3", "Wood"},
						{"3", "Plastic"},
						{"3", "Metal"},
						{"3", "Paper"},
						{"4", "Disease of high blood sugar"},
						{"4", "Disease of weak bones"},
						{"4", "Disease of injury in the brain"},
						{"4", "Disease of pain in muscles"},
						{"5", "5"},
						{"5", "6"},
						{"5", "7"},
						{"5", "8"},
						{"6", "Angry"},
						{"6", "Chatty"},
						{"6", "Beautiful"},
						{"6", "Shy"},
						{"7", "Childbirth"},
						{"7", "Broken bones"},
						{"7", "Heart conditions"},
						{"7", "Old age"},
						{"8", "Empire State Building"},
						{"8", "Royal Albert Halll"},
						{"8", "Eiffel Tower"},
						{"8", "Big Ben Clock Tower"},
						{"9", "George Washington"},
						{"9", "John Adams"},
						{"9", "Thomas Jefferson"},
						{"9", "James Monroe"},
						{"10", "9.3 million"},
						{"10", "39 million"},
						{"10", "93 million"},
						{"10", "193 million"},
				};
		return options;
	}
}
