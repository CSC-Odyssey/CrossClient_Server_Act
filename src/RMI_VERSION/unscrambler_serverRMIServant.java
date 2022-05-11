package RMI_VERSION;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class unscrambler_serverRMIServant extends UnicastRemoteObject implements unscrambler_Interface{

    ArrayList<String> wordsArray = new ArrayList<>();

    ArrayList<String> finishedWordsArray = new ArrayList<>();
    ArrayList<String> usersArray = new ArrayList<>();

    //String username, <unscrambledWord, scrambledWord>
    HashMap<String, String> userGameData = new HashMap<>();
    //String username, lives
    HashMap<String, Integer> userGameLife = new HashMap<>();
    int LIVES = 5;

    protected unscrambler_serverRMIServant() throws RemoteException {
        super();

        populateWordsArrayList();
        System.out.println(wordsArray.toString());
    }

    /**
     * Add current users in the user Array.
     * @param name is the name of the user
     * @return
     */
    @Override
    public boolean addToUsersArray(String name){
        boolean isInUsersArray = false;
        if(usersArray.contains(name)){
            isInUsersArray = true;
        }
        else{
            usersArray.add(name);
            modifiedPrint(name + " has started the game.");
        }
        return isInUsersArray;
    }

    @Override
    public String easyScrambledWord(String name){
        Random random_method = new Random();
        String scrambledWord = "";
        String unscrambledWord = "";

        for (int i = 0; i < wordsArray.size(); i++){
            int index = random_method.nextInt(wordsArray.size());

            unscrambledWord = wordsArray.get(index);
        }
        scrambledWord = scramble(unscrambledWord);

        String scramblerDuo = unscrambledWord + "," + scrambledWord;
        userGameData.put(name, scramblerDuo);
        userGameLife.put(name, LIVES);

        return scrambledWord;
    }

    /**
     * Return the lives of the player.
     * @param name is the username.
     * @return the number of lives.
     * @throws RemoteException
     */
    @Override
    public int returnAttemptsLeft(String name) throws RemoteException{
        return userGameLife.get(name);
    }

    /**
     * Verifies if the answer is correct and deduct lives if incorrect.
     * @param name is the username of the player.
     * @param answer is the anmswer of the player.
     * @return whether answer is true / false - correct / incorrect.
     */
    @Override
    public boolean answerIsCorrect(String name, String answer){
        boolean isCorrect = false;

        if(answer.equals(fetchUnscrambledWord(name))){
            isCorrect = true;
            userGameData.remove(name);
        }
        else{
            int life = Integer.parseInt(String.valueOf(userGameLife.get(name)-1));
            userGameLife.replace(name, life);

            if(life == 0){
                userGameData.remove(name);
                userGameLife.remove(name);
            }
        }
        return isCorrect;
    }

    /**
     * Get the unscrambled word provided for the player.
     * @param name is the username.
     * @return the unscrambled word.
     */
    private String fetchUnscrambledWord(String name){
        String scramblerDuo = userGameData.get(name);
        String[] split = scramblerDuo.split(",");

        return split[0];
    }

    /**
     * Get the scrambled word provided for the player.
     * @param name is the username.
     * @return the scrambled word.
     */
    private String fetchScrambledWord(String name){
        String scramblerDuo = userGameData.get(name);
        String[] split = scramblerDuo.split(",");

        return split[1];
    }

    @Override
    public String reScramble(String name){
        String unscrambledWord = fetchUnscrambledWord(name);

        String scrambledWord = scramble(unscrambledWord);

        //Check whether the scrambled word is the same as the previous one.
        while(userGameData.get(name).equals(scrambledWord)){
            scrambledWord = scramble(unscrambledWord);
        }

        return scrambledWord;
    }

    @Override
    public void exitUser(String name){
        usersArray.remove(name);
        userGameData.remove(name);
        userGameLife.remove(name);
        modifiedPrint(name + " has closed the game.");
    }

    /**
     * Shuffles the word.
     * @param unscrambledWord is the word.
     */
    private String scramble(String unscrambledWord){
        String scrambledWord = "";

        List<String> letters = Arrays.asList(unscrambledWord.split(""));
        Collections.shuffle(letters);

        for(String letter : letters) scrambledWord += letter;

        return scrambledWord;
    }

    /**
     * Method to populate the wordsArrayList with words on the words.txt file.
     */
    private void populateWordsArrayList(){
        String wordFile = "res\\words.txt";
        String line;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(wordFile))){

            while((line = bufferedReader.readLine()) != null) {
                wordsArray.add(line);
            }

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Modified Printer.
     */
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("[hh:mm a]");
    public static void modifiedPrint(String logMessage){
        System.out.printf("%s %s\n", dateFormatter.format(new Date()), logMessage);
    }

}
