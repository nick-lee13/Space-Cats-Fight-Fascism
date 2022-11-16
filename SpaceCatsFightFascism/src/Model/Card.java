import java.util.LinkedList;

public abstract class Card {
    
    String name;
    
    public String GetName() {
        return name;
    }

    protected abstract LinkedList<CardAction> GetActions();

}
