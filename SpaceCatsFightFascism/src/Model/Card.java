import java.util.LinkedList;

public abstract class Card {
    
    String name;
    
    public String getName() {
        return name;
    }

    protected abstract LinkedList<CardAction> getActions();

}
