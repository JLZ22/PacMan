import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import java.awt.Color;
/**
 * A fruit which gives the player extra points if eaten. 
 * It expires after a certain amount of time.
 * 
 * @author John Zeng
 * @version 3/25/2020
 */
public class Fruit extends Collectible
{
    /**
     * Constructor for class Fruit.
     */
    public Fruit()
    {
        setColor(Color.GREEN);
    }
}