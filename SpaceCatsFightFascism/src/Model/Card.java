import java.util.LinkedList;

public abstract class Card {
    protected Player owner;
    public CardAction playAction;
    private String name;
    
    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player inOwner) {
        owner = inOwner
    }

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
