import sys
from omniORB import CORBA
import unscrambler
import CosNaming

orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)
obj = orb.resolve_initial_references("NameService")

rootContext = obj._narrow(CosNaming.NamingContext)

if rootContext is None:
    print("Failed to narrow the root naming context")
    sys.exit(1)

name = [CosNaming.NameComponent("unscramble","")]
try:
    obj = rootContext.resolve(name)

except CosNaming.NamingContext.NotFound as ex:
    print("Name not found")
    sys.exit(1)

eo = obj._narrow(unscrambler.unscrambleridl)

if eo is None:
    print("Object reference is not an unscrambler::unscrambler")
    sys.exit(1)


def verifyUser(name):
    nameTaken = eo.addToUsersArray(name)

    if nameTaken:
        print("Username is already taken!")
    else:
        username = name

        print("\nGood Day Player " + username + "!")
        print("Please Select. ")
        print("1. Start")
        print("2. Exit")

        choiceSwitch(username)


def choiceSwitch(username):
    choice = input("Choice: ")

    if choice == '1':
        startGame(username)
    if choice == '2':
        print("Byebye!")
        eo.exitUser(username)
        sys.exit(1)


def startGame(username):
    print("Can you guess this?! \nNote: Input 'S' to reshuffle the word.")
    lose = 1;

    try:
        scrambledWord = eo.ScrambledWord(username)
        foreverlooper = 5

        while(foreverlooper < 15):
            print(scrambledWord)
            userGuess = input("")

            while userGuess in ['S']:
                scrambledWord = eo.reScramble(username)
                print(scrambledWord)

                userGuess = input("")

                if not userGuess in ['S']:
                    break

            result = eo.answerIsCorrect(username, userGuess)

            if result:
                print("\nCorrect!")
                eo.exitUser(username)

                playAgain = input("Play Again? (1=yes, 2=no): ")

                if playAgain == '1':
                    startGame(username)
                else:
                    eo.exitUser(username)
                    sys.exit(1)

            print("\nIncorrect!")

            lives = eo.returnAttemptsLeft(username)

            if lives == lose:
                print("Game Over!")
                eo.exitUser(username)
                break


    except Exception as ex:
        print("Error has occured: " + ex)
        sys.exit(1)


print("\n-====================================-")
print("-=         UNSCRAMBLER GAME         =-")
print("-====================================-")

name = input("Input Username: ")
verifyUser(name)
