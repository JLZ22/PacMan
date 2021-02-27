import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import java.awt.Color;
/**
 * A powerup that gives the avatar the ability to eat ghosts. 
 * 
 * @author John Zeng
 * @version 3/25/2020
 */
public class PowerUp extends Collectible
{
    /**
     * Constructor for class PowerUp.
     */
    public PowerUp()
    {
        setColor(Color.YELLOW);
    }
}
