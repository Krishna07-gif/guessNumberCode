package gg.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.Scanner;

@Controller
public class GuessNumberController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/start")
    public String startGame(Model model) {
        Random random = new Random();
        boolean playAgain = true;

        System.out.println("Welcome to the Guess the Number Game!");

        while (playAgain) {
            int totalScore = 0;
            int randNum = random.nextInt(100) + 1;
            // System.out.print(randNum);
            System.out.println("\nNew Game Started!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have 10 attempts to guess the number.");

            int taken = randNum + 1;
            int attempts = 10;
            int globAttempt = attempts;

            while (randNum != taken && attempts > 0) {
                System.out.print("Enter your guess: ");
                Scanner sc = new Scanner(System.in);
                taken = sc.nextInt();
                int difference = Math.abs(randNum - taken);
                int score = 100 - difference;
                totalScore += score;
                attempts = attempts - 1;

                if (taken == randNum) {
                    System.out.println("Congratulations! You have guessed the correct number.");
                    System.out.println("Your score is " + totalScore / (globAttempt - attempts));
                } else if (taken < randNum) {
                    System.out.println("Too low! Try guessing a higher number.");
                } else {
                    System.out.println("Too high! Try guessing a lower number.");
                }

            }
            if (taken != randNum) {
                System.out.println("Your attempts are exhausted!");
                System.out.println("The correct number was: " + randNum);
                System.out.println("Your score is " + totalScore / globAttempt);
            }

            System.out.print("\nDo you want to play again? (Y/N): ");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            String playAgainChoice = sc.nextLine();
            playAgain = playAgainChoice.equalsIgnoreCase("Y");
            System.out.println("\nTotal Score: " + totalScore);
            System.out.println("Thank you for playing Guess the Number Game!");
        }

        return "game";
    }

}
