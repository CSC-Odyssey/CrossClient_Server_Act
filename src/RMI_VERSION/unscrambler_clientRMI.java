package RMI_VERSION;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class unscrambler_clientRMI {
    Scanner input;

    String unscrambler_InterfaceString = "unscrambler_Interface";
    String username;

    Registry registry;
    unscrambler_Interface unscrambler_interface;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String args[]){
        EventQueue.invokeLater(() -> new unscrambler_clientRMI());
    }

    private unscrambler_clientRMI(){
        try{
            String userInput = inputServerCredentials();
            verifyServerCredentials(userInput);

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

    private void difficultyMessage(){
        System.out.println("\nGood Day Player " + username + "!");
        System.out.println("Please Select. ");
        System.out.println("1. Start");
        System.out.println("2. Exit");
        System.out.print("Choice: ");
    }

    /**
     * Accepts user input.
     * @return user Input.
     */
    private String inputServerCredentials(){
        String userInput = "";

        try{
            input = new Scanner(System.in);

            System.out.println("Ip Address and Port (Comma-Separated): ");
            userInput = input.nextLine();
        }catch(Exception exception){
            exception.printStackTrace();
        }

        return userInput;
    }

    /**
     * Verify the user input server credentials.
     * @param userInput is the user input.
     */
    private void verifyServerCredentials(String userInput){
        String ipAddress;
        int port;

        String[] split;

        try{
            split = userInput.split(",");

            ipAddress = split[0];
            port = Integer.parseInt(split[1]);

            registry = LocateRegistry.getRegistry(ipAddress, port);
            unscrambler_interface = (unscrambler_Interface) registry.lookup(unscrambler_InterfaceString);
        }catch(Exception exception){
            exception.printStackTrace();
        }
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
            boolean nameTaken = unscrambler_interface.addToUsersArray(name);
            if(nameTaken){
                System.err.println("Username is already taken!");
            }
            else{
                username = name;

                int difficulty = inputDifficulty();
                verifyDifficulty(difficulty);

            }
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
    }

    private int inputDifficulty(){
        int userInput = 0;

        try{
            input = new Scanner(System.in);

            difficultyMessage();

            userInput = input.nextInt();
        }catch(Exception exception){
            exception.printStackTrace();
        }

        return userInput;
    }

    private void verifyDifficulty(int choice){
        switch (choice) {
            case 1 : {
                System.out.println("\n-= Game Start! =-\n");
                startGame();
            }
            break;
            case 2 :
                System.exit(0);
                break;
            default : System.err.println("Invalid Choice.");
            break;
        }
    }

    private void startGame(){
        System.out.println("Can you guess this?! \nNote: Input 'S' to reshuffle the word.");

        try {
            String scrambledWord = unscrambler_interface.easyScrambledWord(username);
            input.nextLine();
            while(true) {

                System.out.println(scrambledWord);
                String userGuess = input.nextLine();

                while(userGuess.equals("S")){
                    userGuess = input.nextLine();
                    scrambledWord = unscrambler_interface.reScramble(username);

                    if(!userGuess.equals("S")){
                        break;
                    }

                    System.out.println(scrambledWord);
                }

                boolean result = unscrambler_interface.answerIsCorrect(username, userGuess);

                if (result) {
                    System.out.println(ANSI_GREEN + "\nCorrect!" + ANSI_RESET);
                    unscrambler_interface.exitUser(username);
                    break;
                }

                System.err.println("Incorrect!");

                int lives = unscrambler_interface.returnAttemptsLeft(username);

                if(lives == 0){
                    System.err.println("Game Over!");
                    break;
                }

                System.out.println("\nLives: " + lives + "\n");


            }
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
    }
}
