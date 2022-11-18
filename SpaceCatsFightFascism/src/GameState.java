import java.util.*;

//Class GameState, reflecting the current state of the game at the current turn in the model.
public class GameState {

    private int DEFAULT_PLAYERS = 2;
    private int DEFAULT_DIFFICULTY = 1;
    private int STARTING_DICE = 2;
    
    private Player[] players;
    private Cat[] catRoster;
    private Planet[][] planetLayout;

    private Deck resistDeck;
    private Deck resistUsedDeck;
    private Deck galaxyNewsDeck;
    private Deck galaxyNewsUsedDeck;

    private int fascismScale;
    private int playerTurn;
    private int liberatedFlagsUsed;
    private int occupiedFlagsUsed;
    private int fascistTokenCount;
    private int diceRollCount;

    ResistCard liberation, heal1, heal2, fascist2, teleport, whiskersymbol, earsymbol, tailsymbol, pawsymbol;
    GalacticNewsCard discard, scratchlocal, scratchscale, scratch1, scratch2;

    //Initializes a new GameState, to be edited at the start of the game.
    public GameState()
    {

        setPlanetLayout();

        //honestly i made the below method because i didnt want a bunch of if statements in the setplanetlayout method
        setPlanetSymbols();

        diceRollCount = STARTING_DICE;

        //edit below for when it is not restricted
        fascismScale = DEFAULT_DIFFICULTY;
        players = new Player[DEFAULT_PLAYERS];
        catRoster = initCatRoster();
        for(int i = 0; i < players.length; i++)
        {
            players[i] = new Player(catRoster[i])
        }
        //edit above for when it is not restricted

        

        resistDeck = initResistDeck();
        galaxyNewsDeck = initGalaxyNewsDeck();
        resistUsedDeck = new Deck(null);
        galaxyNewsUsedDeck = new Deck(null);

        liberatedFlagsUsed = 0;
        occupiedFlagsUsed = 0;

        FascistTokenCount = 0;
        initTokens();
        

        playerTurn = 1;

    }

    //places a token on the inputted planet id, increments amount of tokens
    public void placeLibToken(Planet planet)
    {
        int current_token = planet.getTokenCount();
        planet.setTokenCount(current_token + 1);
        fascistTokenCount--;
    }

    //Places a fascist token at the given planet.
    public void placeFascistToken(Planet planet)
    {
        int current_token = planet.getTokenCount();
        planet.setTokenCount(current_token - 1);
        fascistTokenCount++;
    }

    //initializes the roster of cats available for the game
    private Cat[] initCatRoster()
    {
        roster = new Cat[4];

        Cat aliasSC, pip, jasper, ophelia;

        AbilityCard alias = new Card("Hacker");
        AbilityCard pip = new Card("Freedom Fighter");
        AbilityCard jasper = new Card("Laser Eyes");
        AbilityCard ophelia = new Card("Escape Artist");


        //could these be cards that are implicit in a separate cat class for each individual cat?
        //yeah probably, and will likely be in the next milestone if this text is still here
        alias.playAction = new CardAction(){
            public void action(){
                //when fightfascism is called by a player with this cat as their cat, have an option that plays their abilitycard
                
            }
        };
        pip.playAction = new CardAction(){
            public void action(){
                //when restock is called by a player with this cat as their cat, have an option that plays this abilitycard
            }
        };
        jasper.playAction = new CardAction(){
            public void action(){
                //when fightfascism is called by a player with this cat as their cat, have an option that plays this abilitycard
            }
        };
        ophelia.playAction = new CardAction(){
            public void action(){
                //when 3 tokens are removed in a single turn (tracked by a constant that will be here or in gamecontroller) give an option for this card
            }
        };

        aliasSC = new Cat("Alias:SC", 3, alias);
        pip = new Cat("Pip", 8, pip);
        jasper = new Cat("Jasper", 6, jasper);
        ophelia = new Cat("Ophelia", 4, ophelia);

        aliasSC.setPlanet(findPlanet(3));
        pip.setPlanet(findPlanet(8));
        jasper.setPlanet(findPlanet(6));
        ophelia.setPlanet(findPlanet(4));

        roster[0] = aliasSC;
        roster[1] = pip;
        roster[2] = jasper;
        roster[3] = ophelia;

        return roster;
    }

    //initializes the resist card deck, these deck initializations can hopefully be done better or moved in the future
    //not sure how to work better with functionality though
    private Deck initResistDeck()
    {
        //INITIALIZE ALL CARDS HERE, MAKE SURE TO GIVE EACH ONE AN ACTION IN THEIR cardAction VARIABLE
        //DONT FORGET THIS I STG
        //make sure to check the counts of each cards in the doc
        
        /* example below for future perusal:
        ResistCard card = new Card();
        card.playAction = new CardAction(){
            public void action(){
                //action here
            }
        }*/
        Deck outDeck = new Deck(null);

        ResistCard liberation = new Card("+1 Liberation");
        ResistCard heal1 = new Card("Heal 1");
        ResistCard heal2 = new Card("Heal 2");
        ResistCard fascist2 = new Card("-2 Fascists");
        ResistCard teleport = new Card("Teleport");
        //consider making a SymbolCard extending ResistCard as these below ones have duplicate code
        ResistCard whiskersymbol = new Card("Whisker Liberation");
        ResistCard earsymbol = new Card("Ear Liberation");
        ResistCard tailsymbol = new Card("Tail Liberation");
        ResistCard pawsymbol = new Card("Paw Liberation");
        
        //A lot of the else statements here are creating duplicate cards due to the playcard method, so I will have to figure out a cleaner way to do the used piles
        liberation.playAction = new CardAction(){
            public void action(){
            Planet currPlanet = liberation.owner.getCat().getPlanet();
            if( currPlanet.getTokens() < 0)
            {
                //ADD ERROR MESSAGE HERE
                liberation.owner.getDeck().addCard(liberation);
            }
            else
            {
                liberation.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 1);
            }
             
        }
        };

        heal1.playAction = new CardAction(){
            public void action(){
                int currScratches = heal1.owner.getCat().getScratchCount();
                if(currScratches > 0)
                {
                    heal1.owner.getCat().setScratchCount(currScratches - 1);
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    heal1.owner.getDeck().addCard(heal1);
                }
            }
        };

        heal2.playAction = new CardAction(){
            public void action(){
                int currScratches = heal1.owner.getCat().getScratchCount();
                if(currScratches > 0)
                {
                    //quality if statement
                    if(currScratches - 2 < 0)
                    {
                        heal2.owner.getCat().setScratchCount(currScratches - 1);
                    }
                    else
                    {
                        heal1.owner.getCat().setScratchCount(currScratches - 2);
                    }
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    heal1.owner.getDeck().addCard(heal1);
                }
            }
        };

        fascist2.playAction = new CardAction(){
            public void action(){
                Planet currPlanet = fascist2.owner.getCat().getPlanet();
                if(currPlanet.getTokenCount() < 0)
                {
                    if(currPlanet.getTokenCount() + 2 > 0)
                    {
                        fascist2.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 1);
                    }
                    else
                    {
                        fascist2.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 2);
                    }
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    fascist2.owner.getDeck().addCard(fascist2);
                }
            }
        };

        teleport.playAction = new CardAction(){
            public void action(){
                //Add message prompting selection of desired planet
                Planet inputPlanet = teleport.owner.getCat().getPlanet();
                //REMOVE THE ABOVE ONCE IT ACTUALLY NEEDS TO BE FUNCTIONAL
                teleport.owner.getCat().setPlanet(inputPlanet);
            }
        };

        whiskersymbol.playAction = new CardAction(){
            public void action(){
                currPlanet = whiskersymbol.owner.getCat().getPlanet();
                if(currPlanet.getSymbol() = "Whiskers")
                {
                    if(currPlanet.getTokenCount() >= 0)
                    {
                        whiskersymbol.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 2);
                    }
                    else
                    {
                        //ADD ERROR MESSAGE HERE
                        whiskersymbol.owner.addCard(whiskersymbol);
                    }
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    whiskersymbol.owner.addCard(whiskersymbol);
                }
            }
        };

        earsymbol.playAction = new CardAction(){
            public void action(){
                currPlanet = earsymbol.owner.getCat().getPlanet();
                if(currPlanet.getSymbol() = "Ears")
                {
                    if(currPlanet.getTokenCount() >= 0)
                    {
                        earsymbol.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 2);
                    }
                    else
                    {
                        //ADD ERROR MESSAGE HERE
                        earsymbol.owner.addCard(earsymbol);
                    }
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    earsymbol.owner.addCard(earsymbol);
                }
            }
        };

        tailsymbol.playAction = new CardAction(){
            public void action(){
                currPlanet = tailsymbol.owner.getCat().getPlanet();
                if(currPlanet.getSymbol() = "Tail")
                {
                    if(currPlanet.getTokenCount() >= 0)
                    {
                        tailsymbol.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 2);
                    }
                    else
                    {
                        //ADD ERROR MESSAGE HERE
                        tailsymbol.owner.addCard(tailsymbol);
                    }
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    tailsymbol.owner.addCard(tailsymbol);
                }
            }
        };

        pawsymbol.playAction = new CardAction(){
            public void action(){
                currPlanet = pawsymbol.owner.getCat().getPlanet();
                if(currPlanet.getSymbol() = "Paw")
                {
                    if(currPlanet.getTokenCount() >= 0)
                    {
                        pawsymbol.owner.getCat().getPlanet().setTokenCount(currPlanet.getTokenCount() + 2);
                    }
                    else
                    {
                        //ADD ERROR MESSAGE HERE
                        pawsymbol.owner.addCard(pawsymbol);
                    }
                }
                else
                {
                    //ADD ERROR MESSAGE HERE
                    pawsymbol.owner.addCard(pawsymbol);
                }
            }
        };
        
        //honestly i should come up with better ways to add cards
        int n = 8;
        while(n > 0)
        {
            outDeck.addCard(liberation);
        }
        n = 6;
        while(n > 0)
        {
            outDeck.addCard(heal1);
        }
        n = 4;
        while(n > 0)
        {
            outDeck.addCard(heal2);
        }
        n = 5;
        while(n > 0)
        {
            outDeck.addCard(fascist2);
        }
        n = 3;
        while(n > 0)
        {
            outDeck.addCard(teleport);
        }
        n = 5;
        while(n > 0)
        {
            outDeck.addCard(whiskersymbol);
            outDeck.addCard(pawsymbol);
            outDeck.addCard(tailsymbol);
            outDeck.addCard(earsymbol);
        }
        outDeck.shuffle();

        return outDeck;
    }
    

    //initializes the galaxy news deck
    private Deck initGalaxyNewsDeck()
    {
        /* example below for future perusal:
        GalaxyNewsCard card = new Card();
        card.playAction = new CardAction(){
            public void action(){
                card.owner.getCat().setScratchCount(card.owner.getCat().getScratchCount() - 2)
                //(With appropriate cases if scratches < 2... etc...)
            }
        }*/

        Deck outDeck = new Deck(null);

        GalaxyNewsCard discard = new Card("Catnip is Criminalized");
        GalaxyNewsCard scratchlocal = new Card("Cat Arrests on All Planets");
        GalaxyNewsCard scratchscale =  new Card("Cat Power in Decline");
        GalaxyNewsCard scratch2 = new Card("Corporations Fund the Anti-Cat Campaign");
        GalaxyNewsCard scratch1 = new Card("Martial Law is Declared");

        discard.playAction = new CardAction(){
            public void action(){
                setFascismScale(getFascismScale() + 1);
                for(int i = 0; i < discard.getOwner().getDeck().getCards().size(); i++)
                {
                    Card card = players[playerTurn - 1].getDeck().getCards().remove();
                    resistUsedDeck.addCard(card);
                }
                galaxyNewsUsedDeck.addCard(discard);
            }
        };

        scratchlocal.playAction = new CardAction(){
            public void action(){
                Planet currplanet = players[playerTurn - 1].getCat().getPlanet();

                players[playerTurn - 1].getCat().getPlanet().setTokenCount(currplanet.getTokenCount() - 1);

                for(int i = 0; i < players.length; i++)
                {
                    if(players[i].getCat().getPlanet() == currPlanet())
                    {
                        players[i].getCat().setScratchCount(players[i].getCat().getScratchCount() + 1);
                    }
                }

                galaxyNewsUsedDeck.addCard(scratchlocal);
            }
        };

        scratchscale.playAction = new CardAction(){
            public void action(){
                Planet currplanet = players[playerTurn - 1].getCat().getPlanet();

                players[playerTurn - 1].getCat().getPlanet().setTokenCount(currplanet.getTokenCount() - 1);

                for(int i = 0; i < players.length; i++)
                {
                    if(players[i].getCat().getPlanet() == currPlanet())
                    {
                        players[i].getCat().setScratchCount(players[i].getCat().getScratchCount() + 1);
                    }
                }

                setFascismScale(getFascismScale() + 1);
                
                galaxyNewsUsedDeck.addCard(scratchscale);
            }
        };

        scratch2.playAction = new CardAction(){
            public void action(){
                players[playerTurn - 1].getCat().setScratchCount(players[playerTurn - 1].getCat().getScratchCount() + 2);
                players[playerTurn - 1].getCat().setPlanet(findPlanet(players[playerTurn - 1].getCat().getHomePlanet()));

                galaxyNewsUsedDeck.addCard(scratch2);
            }
        };

        scratch1.playAction = new CardAction(){
            public void action(){
                setFascismScale(getFascismScale() + 1);

                players[playerTurn - 1].getCat().setScratchCount(players[playerTurn - 1].getCat().getScratchCount() + 1);

                galaxyNewsUsedDeck.addCard(scratch1);
            }
        };

        int n = 10;
        while(n > 0)
        {
            outNewsDeck.addCard(scratch1);
        }
        n = 2;
        while(n > 0)
        {
            outNewsDeck.addCard(scratchlocal);
            outNewsDeck.addCard(discard);
        }
        n = 1;
        while(n > 0)
        {
            outNewsDeck.addCard(scratchscale);
            outNewsDeck.addCard(scratch2);
        }
        outDeck.shuffle();

        return outDeck;
    }

    //Initialize the starting tokens at the start of the game
    private void initTokens()
    {
        Player[] roster = this.getPlayers();
        
        for(int i = 0; i < roster.length; i++)
        {
            int id = roster[i].getCat().getHomePlanet();
            Planet homePlanet = findPlanet(id);
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
            for(int j = 0; j < planetLayout.length; j++)
            {
                Planet currPlanet = planetLayout[i][j];
                if(currPlanet.getId() == id)
                {
                    return currPlanet;
                }
            }
        }
        return null;
    }

    public void replaceGalaxyNewsDeck()
    {
        galaxyNewsUsedDeck.shuffle();
        galaxyNewsDeck = galaxyNewsUsedDeck;
        galaxyNewsUsedDeck = new Deck(null); // Was Missing an Owner so I passed in a null value @Alphabetical1
    }

    public void replaceResistDeck()
    {
        resistUsedDeck.shuffle();
        resistDeck = resistUsedDeck;
        resistUsedDeck = new Deck(null); // Was Missing an Owner so I passed in a null value @Alphabetical1
    }

    //You may ask: "Cameron, whats the point of making the local variables private if the getters return the variable anyways?"
    //and to that I say: i guess i like creating overhead for myself once functionality relies on this quirk

    public Player[] getPlayers()
    {
        return players;
    }

    public Cat[] getCatRoster()
    {
        return catRoster;
    }

    public int getFascismScale()
    {
        return fascismScale;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public Planet[][] getPlanetLayout()
    {
        return planetLayout;
    }

    public Deck getResistDeck()
    {
        return resistDeck;
    }

    public Deck getResistUsedDeck()
    {
        return resistUsedDeck;
    }

    public Deck getGalaxyNewsDeck()
    {
        return galaxyNewsDeck;
    }

    public Deck getGalaxyNewsUsedDeck()
    {
        return galaxyNewsUsedDeck;
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

    //I just know everyone will love this method as much as I do
    private void setPlanetSymbols()
    {
        findPlanet(1).setSymbol("Ears");
        findPlanet(5).setSymbol("Ears");
        findPlanet(9).setSymbol("Ears");
        findPlanet(2).setSymbol("Whiskers");
        findPlanet(6).setSymbol("Whiskers");
        findPlanet(10).setSymbol("Whiskers");
        findPlanet(3).setSymbol("Paw");
        findPlanet(7).setSymbol("Paw");
        findPlanet(11).setSymbol("Paw");
        findPlanet(4).setSymbol("Tail");
        findPlanet(8).setSymbol("Tail");
        findPlanet(12).setSymbol("Tail");
    }

    //i HATE how i made this method
    //creates a new planet layout
    private void setPlanetLayout()
    {
        planetLayout = new Planet[3][4];
        Random rand = new Random();
        Set<Integer> visited = new TreeSet<Integer>(); // Set is only an interface so it needed a "Set" implementation, chose treeset at random @Alphabetical1
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                //roll random number
                int n = rand.nextInt(12) + 1;
                //while the random number has been rolled already
                while(visited.contains(n))
                {
                    n = rand.nextInt(12) + 1;
                }
                //add new planet with the numbered ID
                planetLayout[i][j] = new Planet(n, i, j);
                //add the planet to the list of planets already added
                visited.add(n);

            }
        }
    }

}