// Name: Pranav Senthil Vadivel
// Date: 06/02/2026
// Program Description: AI Quiz game
// Enhancements: Done from lines 71 - 91 (Added a percentage calulator that will determine how well the user did the quiz)


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Creating the main class to serve the program's entry point
public class Main {

    // Creating constants so the number of questions and answer choices can be changed easily
    public static final int NUMBER_OF_QUESTIONS = 10;
    public static final int NUMBER_OF_CHOICES = 4;

    public static void main(String[] args) {

         // These are the data structures for the program's data where it stores my quetions and answers
        String[] questions = new String[NUMBER_OF_QUESTIONS];
        //Each question is stored in the question array
        String[][] answers = new String[NUMBER_OF_QUESTIONS][NUMBER_OF_CHOICES];
        // Each set of ansqers is stored in the dual dimensional answer array
        int[] correctAnswers = new int[NUMBER_OF_QUESTIONS];

        // Reading the CSV file for the questions, answers from user and comparing with the correct answers
        readQuizFile(questions, answers, correctAnswers);

        // Initializing scanner to be able to get input from user
        Scanner input = new Scanner(System.in);
        // Setting the intial score to start of from 0
        int score = 0;

        // These print statements will first welcome the user to the game, and give them information on how to play
        System.out.println("Welcome to the AI Quiz Game!");
        System.out.println("Choose the correct answer by entering 1, 2, 3, or 4.\n");

        // This for loop will be used to go through eash question one at a time
        for (int i = 0; i < questions.length; i++) {
            // Used for displaying the current question to user for them to answer
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            // Used for displaying all answer choices for the current question
            for (int j = 0; j < answers[i].length; j++) {
                System.out.println((j + 1) + ". " + answers[i][j]);
            }
            // Asking the user to enter their answer choice
            System.out.print("Your answer: ");
            // This will subtract 1 because the array index starts at 0 while the user's choice starts at 1
            int userAnswer = input.nextInt() - 1;

            // Checkeing to see if user's answer matches what the actual correct answer
            if (userAnswer == correctAnswers[i]) {
                System.out.println("Correct!\n");
                // This will increase the score by 1
                score++;
            } else {
                System.out.println("Incorrect.");
                // Showing the correct answer if only if the user gets the question wrong
                System.out.println("The correct answer was: " + answers[i][correctAnswers[i]] + "\n");
            }
        }

        System.out.println("Quiz complete!");
        // Displaying the user's final score after all question are answered
        System.out.println("Your final score is: " + score + " out of " + questions.length);



        // ENHANCEMENT: This section calculates the percentage score and gives the user feedback based on how well they did on the quiz
        // Creating a percentage value using a double because it can be in decimals
        double percentage = (double) score / questions.length * 100;
        // Displaying the percentage along with additonal information
        System.out.println("Percentage: " + percentage +  "%");

        // If the percentage is 90 and above then, they are condiered an expert
        if (percentage >= 90)
        {
            System.out.println("Excellent job! You are an AI expert!");
        }
        // If the percentage is less than 90 but greater than or equal to 70 then they are have learned a lot
        else if (percentage < 90 && percentage >= 70)
        {
            System.out.println("Good work! You know a ot about AI.");
        }
        // If its anything below the 70 then they need to work harder and practice next time
        else 
        {
            System.out.println("Keep practicing and learning about AI!");
        }



        input.close();
    }

    public static void readQuizFile(String[] questions, String[][] answers, int[] correctAnswers) {
        try {
            // Openin the CSV file that contains all the quiz questions and answers
            File file = new File("ai_quiz_questions.csv");
            Scanner fileReader = new Scanner(file);

            // Skipping the header row of the CSV file
            fileReader.nextLine();

            int index = 0;

            // Reading each line of the file until there are no more questions left
            while (fileReader.hasNextLine() && index < questions.length) {
                String line = fileReader.nextLine();
                // Spliting the CSV line into seperate pieces of data
                String[] data = line.split(",");

                // Storing the question text into the question array
                questions[index] = data[0];

                // Storing all 4 answer choices for the current question
                for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
                    answers[index][i] = data[i + 1];
                }

                // Setting the first answer choice as the correct answer
                correctAnswers[index] = 0;
                // Moving to the next question in the arrays
                index++;
            }

            fileReader.close();

            // Displaying an error message if the file isn't found
        } catch (FileNotFoundException e) {
            System.out.println("The quiz file could not be found.");
        }
    }
}