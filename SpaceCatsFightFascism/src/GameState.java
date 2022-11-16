import java.util.*;

//Class GameState, reflecting the current state of the game at the current turn in the model.
public class GameState {

    int DEFAULT_PLAYERS = 2;
    int DEFAULT_DIFFICULTY = 1;
    int STARTING_DICE = 2;
    int STARTING_TOKENS = 6;
    
    Player[] players;
    Cat[] catRoster;
    Planet[][] planetLayout;

    Deck resistDeck;
    Deck resistUsedDeck;
    Deck galaxyNewsDeck;
    Deck galaxyNewsUsedDeck;

    int fascismScale;
    int playerTurn;
    int liberatedFlagsUsed;
    int occupiedFlagsUsed;
    int fascistTokenCount;
    int diceRollCount;

    //Initializes a new GameState, to be edited at the start of the game.
    public GameState()
    {

        planetLayout = setPlanetLayout();

        resistDeck = new Deck();
        
        diceRollCount = STARTING_DICE;

        //edit below for when it is not restricted
        fascismScale = DEFAULT_DIFFICULTY;
        players = new Player[DEFAULT_PLAYERS]
        for(int i = 0, i < players.length; i++)
        {
            players[0]
        }
        //edit above for when it is not restricted

        catRoster = new Cat[];
        initCatRoster();
        
        galaxyNewsDeck = new Deck();
        resistUsedDeck = new Deck();
        galaxyNewsUsedDeck = new Deck();

        liberatedFlagsUsed = 0;
        occupiedFlagsUsed = 0;

        FascistTokenCount = 0;
        initTokens(FascistTokenCount);
        

        playerTurn = 1;

    }

    //places a token on the inputted planet id, increments amount of tokens
    public void placeLibToken(Planet planet)
    {
        int current_token = planet.getTokenCount();
        planet.setTokenCount(current_token + 1);
        FascistTokenCount--;
    }

    //Places a fascist token at the given planet.
    public void placeFascistToken(Planet planet)
    {
        int current_token = planet.getTokenCount();
        planet.setTokenCount(current_token - 1);
        FascistTokenCount++;
    }

    //Initialize the starting tokens at the start of the game
    private void initTokens()
    {
        Player[] roster = this.getPlayers();
        
        for(int i = 0; i < roster.length; i++)
        {
            int id = roster[i].getCat().getHomePlanet();
            homePlanet = findPlanet(id);
            placeFascistToken(homePlanet);
        }
        placeFascistToken(findPlanet(2));
        placeFascistToken(findPlanet(4));
        placeFascistToken(findPlanet(6));
        placeFascistToken(findPlanet(8));
        placeFascistToken(findPlanet(10));
        placeFascistToken(findPlanet(12));
    }

    //Helper function to return a planet given a planet ID by searching through the planet layout.
    private Planet findPlanet(int id)
    {
        for(int i = 0; i < planetLayout.length; i++)
        {
            for(int j = 0; j < planetLayout[].length; j++)
            {
                currPlanet = planetLayout[i][j];
                if(currPlanet.getID() == id)
                {
                    return currPlanet;
                }
            }
        }
        return null;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public Cat[] getCatRoster()
    {
        return catRoster;
    }

    public Planet[][] getPlanetLayout()
    {
        return planetLayout;
    }

    public Deck getResistDeck()
    {
        return resistDeck;
    }

    public Deck getGalaxyNewsDeck()
    {
        return galaxyNewsDeck;
    }

    public int getDiceRollCount()
    {
        return diceRollCount;
    }

    public void setFascismScale(int scale)
    {
        fascismScale = scale;
    }
    
    public void setPlayers(int inputPlayers)
    {
        players = inputPlayers;
    }

    public void setCatRoster(Cat[] cats)
    {
        catRoster = cats;
    }


    //i HATE how i made this method
    //creates a new planet layout
    private void setPlanetLayout()
    {
        planetLayout = new Planet[3][4];
        Random rand = new Random();
        Set<int> visited = new Set<int>;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                //roll random number
                int n = rand.nextInt(12) + 1;
                //while the random number has been rolled already
                while(visited.contains(n))
                {
                    int n = rand.nextInt(12) + 1;
                }
                //add new planet with the numbered ID
                planetLayout[i][j] = new Planet(n);
                //add the planet to the list of planets already added
                visited.add(n);

            }
        }
    }

}