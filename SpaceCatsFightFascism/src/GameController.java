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

        Card card = resistCards.getFirst();
        gameState.getResistDeck().removeCard(card);

        return card;
    }

    // Draws a galaxy news card, then plays it accordingly.
    public void drawGalaxyNewsCard() {
        LinkedList<Card> galaxyNewsCards = gameState.getGalaxyNewsDeck().getCards();

        if (galaxyNewsCards.size() == 0) {
            gameState.replaceGalaxyNewsDeck();
            galaxyNewsCards = gameState.getGalaxyNewsDeck().getCards();
        }

        Card card = galaxyNewsCards.getFirst();
        gameState.getGalaxyNewsDeck().removeCard(card);
        gameState.getGalaxyNewsUsedDeck().addCard(card);
        card.play();
    }

    // Shuffles the decks using their discard piles
    public void shuffleDecks() {
        gameState.replaceResistDeck();
        gameState.replaceGalaxyNewsDeck();
    }

    // Add a scratch to the current player
    public void addScratch() {
        Cat cat = gameState.getPlayers()[gameState.getPlayerTurn()].getCat();
        cat.setScratchCount(cat.getScratchCount() + 1);
    }

    // Travel action for the current player
    public void travel(Planet planet) {
        Cat cat = gameState.getPlayers()[gameState.getPlayerTurn()].getCat();
        cat.setPlanet(planet);
    }

    // Fight action for the current player
    public void fight() {
        Planet planet = gameState.getPlayers()[gameState.getPlayerTurn()].getCat().getPlanet();
        
        if (planet.getTokens() < 0) {
            planet.setTokenCount(planet.getTokens() + 1);
        }
    }

    // Restock action for the current player
    public void restock() {
        Deck deck = gameState.getPlayers()[gameState.getPlayerTurn()].getDeck();

        while (deck.getCards().size() < 4) {
            deck.addCard(drawResistCard());
        }
    }

    // Play card action for the current player
    public void playCard(Card card) {
        card.play();
        if(card instanceof ResistCard)
        { 
            gameState.getResistUsedDeck().addCard(card);
        }
        //add code here that removes the card from the players hand somehow
        //or add the code in the action that leads to this action, whatever works
    }

    public void startGame() {
        
    }

    public GameState getGameState() {
        return gameState;
    }
}