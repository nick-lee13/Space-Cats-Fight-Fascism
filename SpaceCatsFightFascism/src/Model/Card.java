//Card class to be extended by types of cards
public abstract class Card {
    protected Player owner;
    public CardAction playAction;
    private String name;

    //Return the card's name
    public String getName() {
        return name;
    }

    //Return the card's owner, for relevant actions
    public Player getOwner() {
        return owner;
    }

    //Set the owner of the card, to be used upon drawing or playing
    public void setOwner(Player inOwner) {
        owner = inOwner
    }

    //Method that will enact the card's action
    public void play()
    {
        if(playAction != null)
        {
            playAction.action();
        }
        else
        {
            System.out.println("Hey buddy, you forgot to put in an action for this card.");
        }
    }

}
