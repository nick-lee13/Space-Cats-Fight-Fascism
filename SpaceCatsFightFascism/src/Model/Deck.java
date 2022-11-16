import java.util.LinkedList;
import java.util.Collections;

public class Deck {

    LinkedList<Card> cards = new LinkedList<Card>(); // "Deck of Cards" is a collection of card objects

    public Deck() { // Deck Constructor
            // Body
        this.shuffle();
    }

    public void addCard(Card inCard) { // Removes the given card from a deck
        cards.add(inCard);
    }

    public void removeCard(Card inCard) { // Removes the given card from the deck
        cards.remove(inCard);
    }

    public LinkedList<Card> getCards() { // Returns a list of cards in deck
        return cards;
    }

    public void shuffle() { // Shuffles the cards in the deck
        Collections.shuffle(cards);
    }
}
