import java.util.LinkedList;
import java.util.Collections;

public class Deck {

    LinkedList<Card> cards = new LinkedList<Card>(); // "Deck of Cards" is a collection of card objects

    public Deck() { // Deck Constructor
            // Body
    }

    public void AddCard(Card inCard) { // Removes the given card from a deck
        cards.add(inCard);
    }

    public void RemoveCard(Card inCard) { // Removes the given card from the deck
        cards.remove(inCard);
    }

    public LinkedList<Card> GetCards() { // Returns a list of cards in deck
        return cards;
    }

    public void Shuffle() { // Shuffles the cards in the deck
        Collections.shuffle(cards);
    }
}
