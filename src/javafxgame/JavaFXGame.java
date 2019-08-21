
package javafxgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author mikec
 */
public class JavaFXGame extends Application 
{
    Deck theDeck;
    GameTable theTable;
    VBox root;
    
    private static Button euchreDeckBtn;
    private static Button createTableBtn;
    private static Button dealBtn;
    private static Button quitBtn;
    
    private static TextArea statusArea;
    private static ScrollPane scrollPane;
    private static GamePlayBoard playingBoard;
    
    private static Label team1;
    private static Label team2;
    
    @Override
    public void start(Stage primaryStage) 
    {
        createButtons();
        
        HBox row1 = new HBox(3);
        row1.getChildren().addAll(euchreDeckBtn, createTableBtn, dealBtn, quitBtn);
        
        statusArea = new TextArea();
        statusArea.setMaxSize(400, 200);
        
        scrollPane = new ScrollPane();
        scrollPane.setContent(statusArea);
        
        playingBoard = new GamePlayBoard();
        
        root = new VBox(3);
        root.getChildren().addAll(row1, scrollPane, playingBoard);
        
        Scene scene = new Scene(root, 550, 400);
        
        primaryStage.setTitle("Simple Deck of cards");
        primaryStage.setScene(scene);
        primaryStage.setX(100);
        primaryStage.setY(100);
        primaryStage.show();
    }
    
    public static void setStatusArea(String message) 
    { 
        statusArea.appendText(message);
        statusArea.appendText("\n");
        
        Platform.runLater(() -> { scrollPane.setVvalue(1.0); });
    }
    
    public static void addToTricks(int team) { playingBoard.addToTricks(team); }
    public static void resetTricks() { playingBoard.resetTricks(); }
    
    private void createButtons()
    {   
        euchreDeckBtn = new Button();
        euchreDeckBtn.setText("Create Euchre Deck");
        euchreDeckBtn.setOnAction(ae -> 
        {
            setStatusArea("New Euchre Deck Created");
            theDeck = new Deck();
            setStatusArea("There are " + theDeck.getTotalCards() + " cards in our deck");
        });
        
        createTableBtn = new Button();
        createTableBtn.setText("Create Table");
        createTableBtn.setOnAction(ae -> 
        {
            setStatusArea("Creating Table with 4 players");
            theTable = new GameTable();
            theTable.showGameTable();
            GameStage gameStage = new GameStage(theTable);
        });
        
        dealBtn = new Button();
        dealBtn.setText("Deal A Hand");
        dealBtn.setOnAction(ae -> 
        {
            if (theDeck == null || theTable == null) { setStatusArea("No deck/players created."); }
            else { theDeck.dealCards(theTable); }
        });
        
        quitBtn = new Button();
        quitBtn.setText("Quit");
        quitBtn.setOnAction(ae ->
        {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
