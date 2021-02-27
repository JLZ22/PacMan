import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import java.awt.Color;
/**
 * The dots that the avatar must eat to acumulate points.
 * 
 * @author John Zeng
 * @version 3/25/2020
 */
public class PacDot extends Collectible
{
    /**
     * Constructor for class PacDot.
     */
    public PacDot()
    {
        setColor(Color.RED);
    }
}
