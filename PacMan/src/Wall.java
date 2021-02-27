import info.gridworld.actor.Bug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;
/**
 * An obstacle that the Pacman cannot go through.
 * 
 * @author John Zeng
 * @version 3/25/2020
 */
public class Wall extends Block
{
    /**
     * Constructor for class Wall.
     */
    public Wall()
    {
        setColor(Color.BLUE);
    }

}
