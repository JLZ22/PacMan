import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;
/**
 * Runs the game PacMan. 
 * 
 * @author John Zeng
 * @version 4/5/2020
 */
public class Game implements ArrowListener
{
    private PacMan pac;
    private Ghost inky;
    private Ghost blinky;
    private Ghost clyde;
    private Ghost pinky;
    private BlockDisplay display;
    private MyBoundedGrid<Block> world;
    private Fruit fruit;

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        world = new MyBoundedGrid<Block>(32,27);
        display = new BlockDisplay(world);
        display.setArrowListener(this);
        pac = new PacMan(world, 3);
        inky = new Ghost(world);
        blinky = new Ghost(world);
        pinky = new Ghost(world);
        clyde = new Ghost(world);
        fruit = new Fruit();
    }

    /**
     * Plays a game of Pacman.
     * 
     * @param args information passed by the user via the command line
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
        game.end();
    }

    /**
     * Fills a grid with red blocks.
     */
    public void end()
    {
        for (int r = 0 ; r < world.getNumRows() ; r++)
        {
            try
            {              
                Thread.sleep(50);

            }
            catch(InterruptedException e)
            {
                //empty on purpose
            }
            for (int c = 0 ; c < world.getNumCols() ; c++)
            {
                Location endScreenLoc = new Location(r,c);
                new Block().putSelfInGrid(world,endScreenLoc);
            }
            display.showBlocks();
        }
    }

    /**
     * Puts PacMan and 4 ghosts in the world.
     */
    public void placeCharacters()
    {
        pac.putSelfInGrid(world,new Location(19,13));
        putDots();    
        inky.setCurr(inky.putSelfInGrid(world, new Location(2,2)));
        blinky.setCurr(blinky.putSelfInGrid(world, new Location(2,24)));
        pinky.setCurr(pinky.putSelfInGrid(world, new Location(30,2)));
        clyde.setCurr(clyde.putSelfInGrid(world, new Location(30,24)));
        fruit.putSelfInGrid(world, new Location(6,13));
    }

    /**
     * Moves all the ghosts around randomly until the Pacman is hit. 
     */
    public void play()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Purple: lives \nRed: pac-dots \nYellow: PacMan \nCyan: Ghosts \nGreen: Fruit");
        buildWorld();
        display.setTitle("Pacman");
        placeCharacters();
        int l = pac.getLives();
        showLives(l);
        while(l>0)
        {
            showLives(l);
            if(pac.nextToGhost())
                pac.loseLife();
            if(pac.getLives()!=l)
            {
                l=pac.getLives();
                showLives(l);
                display.showBlocks();
            }
            try
            {
                Thread.sleep(200);

                display.showBlocks();
            }
            catch(InterruptedException e)
            {
                //empty on purpose
            }
            inky.chase();
            clyde.chase();
            blinky.chase();
            pinky.chase();
            display.setTitle("Pacman: " + pac.getPoints());
        }
        System.out.println("Oh no! You Lost");
        System.out.println("Total Points: " + pac.getPoints());
        display.showBlocks();
    }

    /**
     * Places dots on all the empty squares.
     */
    public void putDots()
    {
        for(int r = 2 ; r < 31 ; r++)
            for(int c = 2 ; c < 25 ; c++)
            {
                Location loc = new Location(r,c);
                if(world.get(loc) == null)
                {
                    PacDot dot = new PacDot();
                    dot.putSelfInGrid(world,loc);
                }
            }
    }

    /**
     * Clears the first row and displays the given number of lives. 
     * 
     * @param l the number of lives to show
     */
    public void showLives(int l)
    {
        for(int i = 0 ; i < world.getNumCols() ; i++)
        {
            world.remove(new Location(0,i));
        }
        for(int i = 0 ; i < l ; i++)
            new Heart().putSelfInGrid(world,new Location(0,i+1));
    }

    /**
     * Creates the world for the game. 
     */
    public void buildWorld()
    {
        putHorizontal(new Location(1,1), 25);
        putHorizontal(new Location(31,1),25);
        putVertical(new Location(2,1), 9);
        putVertical(new Location(2,25), 9);
        putVertical(new Location(22,1), 9);
        putVertical(new Location(22,25), 9);
        buildBlock(new Location(11,1), 6, 5);
        buildBlock(new Location(11,20), 6, 5);
        buildBlock(new Location(17,1), 6, 5);
        buildBlock(new Location(17,20), 6, 5);

        //top left corner
        buildBlock(new Location(3,3), 4, 3);
        buildBlock(new Location(7,3), 4, 3);
        buildBlock(new Location(3,8), 4, 3);
        buildBlock(new Location(7,8), 2, 9);
        putHorizontal(new Location(10,10), 2);

        // top right corner
        buildBlock(new Location(3,15), 4, 3);
        buildBlock(new Location(7,20), 4, 3);
        buildBlock(new Location(3,20), 4, 3);
        buildBlock(new Location(7,17), 2, 9);
        putHorizontal(new Location(10,15), 2);

        // top middle
        putVertical(new Location(2, 13), 4);
        buildBlock(new Location(7, 11), 5, 2);
        putVertical(new Location(8,13), 3);
        putHorizontal(new Location(12,11), 5);

        // directly middle 
        putVertical(new Location(14,11), 5);
        putVertical(new Location(14,15), 5);
        putHorizontal(new Location(18,12), 3);

        // bottom middle
        buildBlock(new Location(17,8), 2, 5);
        buildBlock(new Location(17,17), 2, 5);
        buildBlock(new Location(20, 11), 5, 2);
        putVertical(new Location(22,13), 2);
        buildBlock(new Location(25, 11), 5, 3);
        putVertical(new Location(27,13), 3);

        //bottom right corner
        putHorizontal(new Location(26,23),2);
        putHorizontal(new Location(23,15),4);
        buildBlock(new Location(23,20),2,5);
        buildBlock(new Location(23,22),2,2);
        putHorizontal(new Location(29,15),9);
        buildBlock(new Location(25,17),2,4);

        //bottom left corner
        putHorizontal(new Location(23,8),4);
        putHorizontal(new Location(26,2),2);
        buildBlock(new Location(23,5),2,5);
        buildBlock(new Location(23,3),2,2);
        putHorizontal(new Location(29,3),9);
        buildBlock(new Location(25,8),2,4);
    }

    /**
     * Places a certain number of walls horizontally. 
     * 
     * @param world the world the blocks will be put in
     * @param start where the blocks will begin to be placed
     * @param number how many blocks will be placed
     */
    public void putHorizontal(Location start, int number)
    {
        if (world==null)
            return;
        int row = start.getRow();
        int startColumn = start.getCol();
        int column;
        if (!world.isValid(new Location(row,startColumn+number)))
            return;
        for (int i = 0 ; i < number ; i++)
        {
            column = startColumn+i;
            Wall wall = new Wall();
            wall.putSelfInGrid(world,new Location(row,column));
        }
    }

    /**
     * Builds the blocks that are above and below the portal.
     * 
     * @param world  the world the blocks will be put in
     * @param start where the top left block will be placed
     * @param len the length of the block(horizontal)
     * @param w the width of the block(vertical)
     * @precondition the block being built will fit inside the grid
     */
    public void buildBlock(Location start, int len, int w)
    {
        if(world == null)
            return;
        int col = start.getCol();    
        int row = start.getRow();
        for(int i = 0 ; i < w ; i++)
        {
            putHorizontal(new Location(row,col), len);
            row++;
        }
    }

    /**
     * Places a certain number of walls horizontally. 
     * 
     * @param world the world the blocks will be put in
     * @param start where the blocks will begin to be placed
     * @param number how many blocks will be placed
     */
    public void putVertical(Location start, int number)
    {
        if (world==null)
            return;
        int col = start.getCol();
        int startRow = start.getRow();
        int row;
        if (!world.isValid(new Location(startRow+number,col)))
            return;
        for (int i = 0 ; i < number ; i++)
        {
            row = startRow+i;
            Wall wall = new Wall();
            wall.putSelfInGrid(world,new Location(row,col));
        }
    }

    /**
     * Responses to the up arrow.
     *
     */
    @Override
    public void upPressed()
    {
        pac.translate(-1,0);
        display.showBlocks();
    }

    /**
     * 
     * Responses to the down arrow.
     *
     */
    @Override
    public void downPressed()
    {
        pac.translate(1,0);
        display.showBlocks();
    }

    /**
     *Responses to the left arrow.
     */
    @Override
    public void leftPressed()
    {
        pac.translate(0,-1);
        display.showBlocks();
    }

    /**
     * Responses to the right arrow.
     */
    @Override
    public void rightPressed()
    {
        pac.translate(0,1);
        display.showBlocks();
    }
}
