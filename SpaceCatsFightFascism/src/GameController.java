import java.util.LinkedList;
import java.util.Random;

// GameController manages the main game logic and player behaviour functions

class GameController {
    private GameState gameState;
    private Random randNumGenerator;

    // Runs the main logic for a single player's turn
    public void runTurn() {

    }

    // Increases the facism scale by 1
    public void increaseFacism() {
        gameState.setFascismScale(gameState.getFascismScale() + 1);
    }

    // Rolls a single dice and returns the resulting value
    public int rollDice() {
        return 1 + randNumGenerator.nextInt(12);
    }

    // Draws a resist card
    public Card drawResistCard() {
        LinkedList<Card> resistCards = gameState.getResistDeck().getCards();

        if (resistCards.size() == 0) {
            gameState.replaceResistDeck();
            resistCards = gameState.getResistDeck().getCards();
        }

        Card card = resistCards.get(0);
        gameState.getResistDeck().removeCard(card);

        return card;
    }

    // Draws a galaxy news card
    public Card drawGalaxyNewsCard() {
        LinkedList<Card> galaxyNewsCards = gameState.getGalaxyNewsDeck().getCards();

        if (galaxyNewsCards.size() == 0) {
            gameState.replaceGalaxyNewsDeck();
            galaxyNewsCards = gameState.getGalaxyNewsDeck().getCards();
        }

        Card card = galaxyNewsCards.get(0);
        gameState.getGalaxyNewsDeck().removeCard(card);

        return card;
    }

    // Shuffles the decks using their discard piles
    public void shuffleDecks() {

    }

    // Shuffles the planet layout
    public void shufflePlanets() {

    }

    // Add a scratch to the current player
    public void addScratch() {

    }

    // Travel action for the current player
    public void travel(Planet planet) {

    }

    // Fight action for the current player
    public void fight() {

    }

    // Restock action for the current player
    public void restock() {

    }

    // Play card action for the current player
    public void playCard() {

    }

    public GameState getGameState() {
        return gameState;
    }

    public void startGame() {
        
    }
}