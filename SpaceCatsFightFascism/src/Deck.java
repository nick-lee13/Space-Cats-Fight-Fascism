import java.util.LinkedList;

public class Deck {

    LinkedList<Card> cards = new LinkedList<Card>();

    public void AddCard(Card inCard){
        cards.add(inCard);
    }

    public void RemoveCard(Card inCard){
        cards.remove(inCard);
    }
}
