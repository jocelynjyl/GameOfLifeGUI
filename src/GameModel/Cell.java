package GameModel;

/* Cell state is represented with boolean values.
   A Cell is alive if it is true, dead if it is false.
 */

public class Cell
{
	private boolean cell;

    // Constructor: initializes new dead Cell
	public Cell ()
	{
		cell = false;
	}

	public void setLive ()
	{
		cell = true;
	}
	
	public void setDead ()
	{
		cell = false;
	}

    public boolean getCurrentState()
    {
        return cell;
    }

    // EFFECTS: Prints "*" to the console if cell is alive, "-" if cell is dead
    protected void printCell () {
		if (cell) {
            System.out.print("*");
        }
        else {
            System.out.print("-");
        }
	}

}