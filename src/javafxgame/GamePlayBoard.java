/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mikec
 */
public class GamePlayBoard extends BorderPane
{
    private GridPane infoGrid;
    
    private Label TeamOneScore;
    private Label TeamTwoScore;
    
    private Label TeamOneTrickCount;
    private Label TeamTwoTrickCount;
    
    private TextField T1Score;
    private TextField T2Score;
    private TextField T1TrickCount;
    private TextField T2TrickCount;
    
    private int scoreOne;
    private int scoreTwo;
    private int trickOne;
    private int trickTwo;
    
    GamePlayBoard() { init(); }
    
    private void init()
    {
        scoreOne = 0;
        scoreTwo = 0;
        trickOne = 0;
        trickTwo = 0;
        
        TeamOneScore = new Label("Team One Score");
        TeamTwoScore = new Label("Team Two Score");
        TeamOneTrickCount = new Label("Trick Count");
        TeamTwoTrickCount = new Label("Trick Count");
        
        T1Score = new TextField();
        T2Score = new TextField();
        T1TrickCount = new TextField();
        T2TrickCount = new TextField();
        
        infoGrid = new GridPane();
        infoGrid.setPadding(new Insets(5, 5, 5, 5));
        infoGrid.setVgap(5);
        infoGrid.setHgap(5);
        infoGrid.setAlignment(Pos.CENTER);
        infoGrid.setGridLinesVisible(true);
        
        infoGrid.add(TeamOneScore, 0, 0);       infoGrid.add(T1Score, 1, 0);
        infoGrid.add(TeamOneTrickCount, 0, 1);  infoGrid.add(T1TrickCount, 1, 1);
        infoGrid.add(new Label(""), 0, 2);
        infoGrid.add(TeamTwoScore, 0, 3);       infoGrid.add(T2Score, 1, 3);
        infoGrid.add(TeamTwoTrickCount, 0, 4);  infoGrid.add(T2TrickCount, 1, 4);
        
        updateScores();
        updateTricks();
        
        setCenter(infoGrid);
    }
    
    public void addToScore(int team)
    {
        switch(team)
        {
            case 1: scoreOne++; break;
            case 2: scoreTwo++; break;
        }
        updateScores();
    }
    
    public void addToTricks(int team)
    {
        switch(team)
        {
            case 1: trickOne++; break;
            case 2: trickTwo++; break;
        }
        
        if ( (trickOne + trickTwo) == 5 ) 
        {
            if (trickOne == 5) { addToScore(1); }
            if (trickTwo == 5) { addToScore(2); }
            
            if (trickOne > trickTwo) { addToScore(1); }
            else { addToScore(2); }
        }
        updateTricks();
    }
    
    public void resetTricks()
    {
        trickOne = 0;
        trickTwo = 0;
        updateTricks();
    }
    
    public void updateScores()
    {
        T1Score.setText(String.valueOf(scoreOne));
        T2Score.setText(String.valueOf(scoreTwo));
    }
    
    public void updateTricks()
    {
        T1TrickCount.setText(String.valueOf(trickOne));
        T2TrickCount.setText(String.valueOf(trickTwo));
    }
}
