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
    int[] liberatedFlagsUsed;
    int fascistTokenCount;
    int diceRollCount;

    //Scanner playerInput = new Scanner(System.in);

    //Initializes a new GameState, to be edited at the start of the game.
    public GameState()
    {

        planetLayout = setPlanetLayout();

        resistDeck = new Deck();
        
        diceRollCount = STARTING_DICE;

        //edit below for when it is not restricted
        fascismScale = DEFAULT_DIFFICULTY;
        players = DEFAULT_PLAYERS;
        //edit above for when it is not restricted

        /*for(int i = 1; i < players + 1; i++)
        {
            System.out.println("Player " + i + ", input your desired cat from the list below:");
            System.out.println("1: Alias:SC, 2: Jasper, 3: Ophelia, 4: Pip");
            
        }*/

        
        galaxyNewsDeck = new Deck();
        resistUsedDeck = new Deck();
        galaxyNewsUsedDeck = new Deck();

        

        liberatedFlagsUsed = new int[];

        FascistTokenCount = players.size + STARTING_TOKENS;
        

        playerTurn = 1;

    }

    //places a token on the inputted planet id, increments amount of tokens
    public void placeToken(int id)
    {

        FascistTokenCount++;

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