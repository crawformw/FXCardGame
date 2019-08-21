package javafxgame;

import javafx.scene.Scene;
import javafx.stage.Stage;

/** GameStage Class - Stage and Scene class for the game table
 * @author mikec
 */
public class GameStage extends Stage
{ 
    public GameStage(GameTable theTable)
    {
        setTitle("Game Board");
        
        Scene scene = new Scene(theTable, 950,800);
        setScene(scene);
        show();
    }
    
}
