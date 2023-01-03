// IDE's forces import*
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static final int FIRST_TO_100 = 100;
    public static final int TIMER_CONTROL = 300;
    public static final int TIMER_FOR_SOUND = 2;


    public static void main(String[] args) {
        CircularLinkedList<Player> players = new CircularLinkedList<>();
        Player abe = new Player("Abe");
        Player ben = new Player("Ben");
        Player cara = new Player("Cara");
        Player deb = new Player("Deb");

        players.add(abe);
        players.add(ben);
        players.add(cara);
        players.add(deb);

        Random dice = new Random();
        int diceOne = 0;
        int diceTwo = 0;

        int highestScore = 0;
        Iterator<Player> itPlyr = players.iterator();
        Player currentPlayer = null;

        playAudio("startOfGame.wav");
        timeControl(TIMER_FOR_SOUND );
        System.out.println("LET THE GAME BEGIN!");

        while (highestScore <= FIRST_TO_100) {

            Player winner = null;
            System.out.println();
            System.out.println("New Round Starting");

            playAudio("startOfRound.wav");
            timeControl(TIMER_FOR_SOUND );

            for (int currPlyr = 0; currPlyr < players.getSize() && itPlyr.hasNext(); currPlyr++) {
                currentPlayer = itPlyr.next();
                diceOne = dice.nextInt(1, 7);
                diceTwo = dice.nextInt(1, 7);
                currentPlayer.addToScore(diceOne + diceTwo);

                if (currentPlayer.getScore() > highestScore) {
                    highestScore = currentPlayer.getScore();
                    winner = currentPlayer;
                    // determine the winner of the round and announce it, followed by the others players and their score
                    System.out.println(currentPlayer.getName() + " rolls a " + diceOne + " and a " + diceTwo + ", score now totaling " + highestScore + "...new high score!");
                } else {
                    System.out.println(currentPlayer.getName() + " rolls a " + diceOne + " and a " + diceTwo + ", score now totaling " + currentPlayer.getScore());
                }

                playAudio("diceRoll.wav");
                timeControl(TIMER_FOR_SOUND );
            }
            if (highestScore >= FIRST_TO_100) {
                // determine the winner of the game and announce it and remove all the other players
                System.out.println("\n\nThe winner is " + winner.getName() + " with a score of " + highestScore+ "!\n");
                playAudio("gameEnding.wav");
                timeControl(TIMER_FOR_SOUND );
            }

        }
    }


    public static void playAudio(String soundName) {
        File soundFileDiceRoll = new File(soundName);
        AudioInputStream audioIn = null;
        Clip clip = null;

        try {
            audioIn = AudioSystem.getAudioInputStream(soundFileDiceRoll);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            // do nothing
        }

        clip.setFramePosition(0);
        clip.start();
    }


    public static void timeControl(int seconds) {
        try {
            Thread.sleep(seconds * TIMER_CONTROL);
        } catch (InterruptedException e) {
           // do nothing
        }
    }

}
