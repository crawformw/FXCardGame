package javafxgame;

import javafx.scene.image.Image;

/** Card class - represents each card in the deck
 * @author mikec
 */
public class Card implements GameConstants
{
    int suit;
    int rank;
    Image cardImage;
    Image backSide;
    boolean cardPlayed;
    
    String suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
    String faces[] = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};
    int cardValue[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
    
    public Card(int s, int f) 
    {
        suit = s;
        rank = f;
        cardPlayed = false;
        setCardImage();
        setBackSide();
    }
    
    private void setCardImage() { cardImage = new Image(getClass().getResourceAsStream("images/"+faces[rank]+abbrSuit[suit]+".gif")); }
    private void setBackSide() { backSide = new Image(getClass().getResourceAsStream("images/BackSide.gif")); }
    
    public String fullCardName() { return faces[rank] + " of " + suits[suit]; }
    public String cardName() { return faces[rank] + abbrSuit[suit]; }
    public Image getCardImage() { return cardImage; }
    public Image getBackSide() { return backSide; }
    public int getCardSuit() { return suit; }
    public int getCardValue() { return cardValue[rank]; }
    public boolean isSameSuite(int inSuit) { return inSuit == suit; }
    public void setAsPlayed() { cardPlayed = true; }
    public boolean isPlayed() { return cardPlayed; }
    
}
