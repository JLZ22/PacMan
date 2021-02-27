import java.util.ArrayList;
import info.gridworld.grid.Grid;
import java.awt.Color;
/**
 * The avatar for the player. 
 * 
 * @author John Zeng
 * @version 3/25/2020
 */
public class PacMan extends Character
{
    private MyBoundedGrid<Block> gr;
    private int points;
    private int lives;

    /**
     * Constructor for class PacMan
     * 
     * @param grid the grid that the PacMan is in
     * @param l the number of lives PacMan has 
     */
    public PacMan(MyBoundedGrid<Block> grid, int l)
    {
        setColor(Color.YELLOW);
        gr = grid;
        lives = l;
    }

    /**
     * Determines whether or not the pacman is next to a ghost.
     * 
     * @return true if the pacman is next to a ghost; otherwise
     *         false
     */
    public boolean nextToGhost()
    {
        Block temp = new Block();
        Location loc = getLocation();
        int r = loc.getRow();
        int c = loc.getCol();
        temp = gr.get(new Location(r+1,c));
        if(temp instanceof Ghost)
            return true;
        temp = gr.get(new Location(r-1,c));
        if(temp instanceof Ghost)
            return true;
        temp = gr.get(new Location(r,c+1));
        if(temp instanceof Ghost)
            return true;
        temp = gr.get(new Location(r,c-1));
        if(temp instanceof Ghost)
            return true;
        return false;
    }

    /**
     * Returns the number of points. 
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Returns the number of lives. 
     */
    public int getLives()
    {
        return lives;
    }

    /**
     * Decreases the number of lives by 1.
     */
    public void loseLife()
    {
        lives--;
    }

    /**
     * Moves the block up, down, left, or right based on the parameters.
     * 
     * @param r the distance moved to the right
     * @param c the distance moved to the down
     * @return true if the translate occurs successfully, otherwise 
     *         false
     */
    public boolean translate(int r, int c)
    {
        Location old = getLocation();
        int oldRow = old.getRow();
        int oldCol = old.getCol();
        Location newLoc = new Location(oldRow+r, oldCol+c);
        Block temp = gr.get(newLoc);
        Location farLeft = new Location(16,1);
        Location farRight = new Location(16,25);
        if(gr.isValid(newLoc) && (temp == null || temp instanceof Collectible))
        {
            removeSelfFromGrid();
            putSelfInGrid(gr, newLoc);
            if(temp instanceof PacDot)
                points+=10;
            if(temp instanceof Fruit)
                points+=200;
            if(newLoc.equals(farLeft))
                moveTo(farRight);
            else if(newLoc.equals(farRight))
                moveTo(farLeft);
            return true;
        }
        return false;
    }
}
