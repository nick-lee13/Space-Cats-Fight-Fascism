import java.util.LinkedList;

public class AbilityCard extends Card {

    String name;

    public AbilityCard(String inName) { // Constructor
        name = inName;

    }

    public LinkedList<CardAction> GetActions() { // Needs to be filled in, return statement is just there to allow compialtion
        return new LinkedList<CardAction>();
    }
    
}
