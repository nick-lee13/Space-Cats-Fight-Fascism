import java.util.LinkedList;
import java.util.Random;
import java.lang.Math;

// GameController manages the main game logic and player behaviour functions

class GameController {
    private GameState gameState;
    private Random randNumGenerator;
    private int actionCount;
    DisplayUpdater view;

    // Runs the main logic for a single player's turn
    //((POTENTIALLY OBSOLETE))
    /*public void runTurn() {
        actionCount = 3;
        while(actionCount > 0)
        {
            
        }

    }*/

    public void actionTaken()
    {
        actionCount--;
        if(actionCount == 0)
        {
            if(gameState.getPlayerTurn() + 1 > gameState.getPlayers().length - 1)
            {
                gameState.setPlayerTurn(0);
            }
            else
            {
                gameState.setPlayerTurn(gameState.getPlayerTurn() + 1);
            }
            for(int i = 0; i < gameState.getDiceRollCount(); i++)
            {
                int roll = rollDice();
                Planet currPlanet = gameState.findPlanet(roll);
                currPlanet.setTokens(currPlanet.getTokens() + 1);
                //something seems smelly around here
                for(int j = 0; j < gameState.getPlayers().length; j++)
                {
                    if(gameState.getPlayers()[j].getCat().getPlanet() == currPlanet)
                    {
                        gameState.getPlayers()[j].getCat().setScratchCount(players[j].getCat().getScratchCount() + 1);
                    }
                }

            }
            gameState.setRollDiceCount(2 + (int) Math.floor(gameState.getFascismScale()/7));
        }
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
    public boolean travel(int direction) {
        Cat cat = gameState.getPlayers()[gameState.getPlayerTurn()].getCat();
        Planet currPlanet = cat.getPlanet();
        int[] currIndex = currPlanet.getIndex();
        Planet outPlanet;
        //oh boy is this ever not scalable
        if(direction == 0)
        {
            if(currIndex[0] + 1 > 2)
            {
                return false;
            }
            outPlanet = gameState.getPlanetLayout()[currIndex[0] + 1][currIndex[1]];
        }
        else if(direction == 1)
        {
            if(currIndex[0] - 1 < 0)
            {
                return false;
            }
            outPlanet = gameState.getPlanetLayout()[currIndex[0]][currIndex[1] - 1];
        }
        else if(direction == 2)
        {   
            if(currIndex[1] - 1 < 0)
            {
                return false;
            }
            outPlanet = gameState.getPlanetLayout()[currIndex[0] - 1][currIndex[1]];
        }
        else if(direction == 3)
        {
            if(currIndex[1] + 1 > 3)
            {
                return false;
            }
            outPlanet = gameState.getPlanetLayout()[currIndex[0]][currIndex[1] + 1];
        }
        else{
            outPlanet = gameState.getPlanetLayout()[currIndex[0]][currIndex[1]];
        }
        cat.setPlanet(outPlanet);
        actionTaken();
        return true;
    }

    // Fight action for the current player
    public boolean fight() {
        Planet planet = gameState.getPlayers()[gameState.getPlayerTurn()].getCat().getPlanet();
        
        if (planet.getTokens() < 0) {
            planet.setTokens(planet.getTokens() + 1);
            actionTaken();
            return true;
        }
        return false;
    }

    // Restock action for the current player
    public boolean restock() {
        Deck deck = gameState.getPlayers()[gameState.getPlayerTurn()].getDeck();

        if(deck.getSize() == 3)
        {
            return false;
        }
        else
        {
            while (deck.getCards().size() < 4) {
                deck.addCard(drawResistCard());
            }
            actionTaken();
        }
        return true;
        
    }

    // Play card action for the current player
    public void playCard(Card card) {
        card.play();
        if(card instanceof ResistCard)
        { 
            gameState.getResistUsedDeck().addCard(card);
        }
        actionTaken();
        //add code here that removes the card from the players hand somehow
        //or add the code in the action that leads to this action, whatever works
    }

    public void startGame() {
        gameState = new GameState();
        DisplayUpdater view = new DisplayUpdater();
        view.initDisplay();
        actionCount = 3;
        //runTurn();
    }

    public GameState getGameState() {
        return gameState;
    }

    public static void main(String[] args)
    {
        startGame();
    }
}