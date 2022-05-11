//package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import unscrambler.*;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class UnscramblerClient {
    unscrambleridl unscramblerImpl;

    Scanner input;

    String unscrambler_InterfaceString = "unscrambler_Interface";
    String username;
    short one = 1;

    public static void main(String[] args){
        try{
            EventQueue.invokeLater(() -> new UnscramblerClient(args));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private UnscramblerClient(String[] args){
        try{
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "unscramble";

            unscramblerImpl = unscrambleridlHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Connected Successfully");

            welcomeMessage();

            String nameInput = inputUserCredentials();
            verifyUserCredentials(nameInput);

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
    /**
     * Initial game name.
     */
    private void welcomeMessage(){
        System.out.println("\n-====================================-");
        System.out.println("-=         UNSCRAMBLER GAME         =-");
        System.out.println("-====================================-");
    }

    private void choiceMessage(){
        System.out.println("\nGood Day Player " + username + "!");
        System.out.println("Please Select. ");
        System.out.println("1. Start");
        System.out.println("2. Exit");
        System.out.print("Choice: ");
    }

    /**
     * Accepts a name.
     * @return the username.
     */
    private String inputUserCredentials(){
        String userInput = "";

        try{
            input = new Scanner(System.in);

            System.out.print("\nInput Username: ");
            userInput = input.nextLine();
        }catch(Exception exception){
            exception.printStackTrace();
        }

        return userInput;
    }

    /**
     * Check if the username is already taken.
     * @param name is the username.
     */
    private void verifyUserCredentials(String name){
        try {
            boolean nameTaken = unscramblerImpl.addToUsersArray(name);
            if(nameTaken){
                System.err.println("Username is already taken!");
            }
            else{
                username = name;

                int difficulty = inputChoice();
                verifyChoice(difficulty);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private int inputChoice(){
        int userInput = 0;

        try{
            input = new Scanner(System.in);

            choiceMessage();

            userInput = input.nextInt();
        }catch(Exception exception){
            exception.printStackTrace();
        }

        return userInput;
    }

    private void verifyChoice(int choice){
        switch (choice) {
            case 1 :
                System.out.println("\n-= Game Start! =-\n");
                startGame();
                break;

            case 2 :
                System.exit(0);
                break;

            default :
                System.err.println("Invalid Choice.");
                break;
        }
    }

    private void startGame(){
        System.out.println("Can you guess this?! \nNote: Input 'S' to reshuffle the word.");
        int playAgain = 0;

        try {
            String scrambledWord = unscramblerImpl.ScrambledWord(username);

            while (true) {

                System.out.print("\n" + scrambledWord + "\n");
                String userGuess = input.next();

                while (userGuess.equals("S")) {

                    scrambledWord = unscramblerImpl.reScramble(username);
                    System.out.print("\n" + scrambledWord + "\n");

                    userGuess = input.next();

                    if (!userGuess.equals("S")) {
                        break;
                    }
                }

                boolean result = unscramblerImpl.answerIsCorrect(username, userGuess);

                if (result) {
                    System.out.println("\nCorrect!\n");
                    unscramblerImpl.exitUser(username);

                    System.out.print("Play Again? (1=yes/2=no): ");
                    playAgain = input.nextInt();

                    if(playAgain==1){
                        startGame();
                    }
                    else {
                        System.exit(1);
                    }
                }

                System.err.println("\nIncorrect!");

                short lives = unscramblerImpl.returnAttemptsLeft(username);

                if (lives == 1) {
                    System.err.println("Game Over!");
                    unscramblerImpl.exitUser(username);
                    break;
                }

                System.out.println("\nLives: " + (lives - one) + "\n");

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
