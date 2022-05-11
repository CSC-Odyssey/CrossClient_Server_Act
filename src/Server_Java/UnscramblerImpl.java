//package server;

//import unscrambler.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UnscramblerImpl extends unscrambler.unscrambleridlPOA{
    ArrayList<String> wordsArray = new ArrayList<>();

    ArrayList<String> finishedWordsArray = new ArrayList<>();
    ArrayList<String> usersArray = new ArrayList<>();

    //String username, <unscrambledWord, scrambledWord>
    HashMap<String, String> userGameData = new HashMap<>();
    //String username, lives
    HashMap<String, Integer> userGameLife = new HashMap<>();

    protected UnscramblerImpl(){
        super();

        populateWordsArrayList();
        System.out.println(wordsArray.toString());
    }

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

    /**
     * Verifies if the answer is correct and deduct lives if incorrect.
     * @param name is the username of the player.
     * @param answer is the anmswer of the player.
     * @return whether answer is true / false - correct / incorrect.
     */
    public boolean answerIsCorrect(String name, String answer){
        boolean isCorrect = false;

        if(answer.equals(fetchUnscrambledWord(name))){
            isCorrect = true;
            userGameData.remove(name);
        }
        else{
            int life = Integer.parseInt(String.valueOf(userGameLife.get(name)-1));

            if(life == 0){
                userGameData.remove(name);
                userGameLife.remove(name);
            }

            userGameLife.replace(name, life);
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

    public String ScrambledWord(String name){
        Random random_method = new Random();
        String scrambledWord = "";
        String unscrambledWord = "";
        int LIVES = 6;

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

    public String reScramble(String name){
        String unscrambledWord = fetchUnscrambledWord(name);

        String scrambledWord = scramble(unscrambledWord);

        //Check whether the scrambled word is the same as the previous one.
        while(userGameData.get(name).equals(scrambledWord)){
            scrambledWord = scramble(unscrambledWord);
        }

        return scrambledWord;
    }

    /**
     * Return the lives of the player.
     * @param name is the username.
     * @return the number of lives.
     * @throws RemoteException
     */
    public short returnAttemptsLeft(String name){
        return userGameLife.get(name).shortValue();
    }

    public void exitUser(String name){
        usersArray.remove(name);
        userGameData.remove(name);
        userGameLife.remove(name);
        modifiedPrint(name + " has closed the game.");
    }

    /**
     * Modified Printer.
     */
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("[hh:mm a]");
    public static void modifiedPrint(String logMessage){
        System.out.printf("%s %s\n", dateFormatter.format(new Date()), logMessage);
    }

    /**
     * Method to populate the wordsArrayList with words on the words.txt file.
     */
    private void populateWordsArrayList(){
        String wordFile = "words.txt";
        String line;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(wordFile))){

            while((line = bufferedReader.readLine()) != null) {
                wordsArray.add(line);
            }

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
