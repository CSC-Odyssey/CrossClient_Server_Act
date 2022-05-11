package RMI_VERSION;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface unscrambler_Interface extends Remote {

    boolean addToUsersArray(String name) throws RemoteException;
    boolean answerIsCorrect(String name, String answer) throws RemoteException;

    String easyScrambledWord(String name) throws RemoteException;
    String reScramble(String name) throws RemoteException;

    int returnAttemptsLeft(String name) throws RemoteException;

    void exitUser(String name) throws RemoteException;

}
