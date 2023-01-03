/**
 * Creates a player
 */
public class Player {

    /** player's name */
    private String name;
    /** player's score */
    private int score;


    /**
     * Constructs a player
     * @param name      name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns the player's name
     * @return      player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's score
     * @return      player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds to the overall score for player
     * @param pointsToAdd       points added for the player's score
     */
    public void addToScore(int pointsToAdd) {
        score += pointsToAdd;
    }

}
