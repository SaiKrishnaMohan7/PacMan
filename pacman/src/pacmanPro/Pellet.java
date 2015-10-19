package pacmanPro;

public class Pellet {

	Position pos = new Position(0,0, Direction.NONE);
	boolean isVisible;

	public Pellet(int x, int y)
	{
		this.pos.Pos_x = x;
		this.pos.Pos_y = y;
		isVisible = true;
	}
}
