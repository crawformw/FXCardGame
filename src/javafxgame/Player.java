/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgame;

import java.util.ArrayList;

/**
 *
 * @author mikec
 */
public class Player 
{
    public String name;
    public ArrayList<Card> hand;
    
    /* Player() - constructor to init player class */
    public Player(String pName) { initPlayer(pName); }
    
    private void initPlayer(String playerName) { setPlayerName(playerName); }
    public void newHand() { hand = new ArrayList<>(); }
    
    public void addToHand(Card c) { hand.add(c); }
    public void discardFromHand(Card c) { hand.remove(c); }
    public void markCardPlayed(Card c) { c.setAsPlayed(); }
    public Card getCard(int cnt) { return hand.get(cnt-1); }
    
    /* showHand() - show the player hand */
    public void showHand()
    {
        StringBuilder handStr = new StringBuilder();
        
        if (hand.size() > 0)
        {
            handStr.append("Player: ").append( getPlayerName()).append(" Hand: ");
            hand.forEach((hand1) -> { handStr.append(hand1.cardName()).append(", "); });
            JavaFXGame.setStatusArea(handStr.toString());
        }
        else { JavaFXGame.setStatusArea("Player: " + getPlayerName() + " Hand: Empty " ); }
    }
    
    public void setPlayerName(String n) { name = n; }
    public String getPlayerName() { return name; }
}
