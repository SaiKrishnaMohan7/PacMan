package pacmanPro;

public class Wall {
	
	Position pos = new Position(0, 0 ,Direction.NONE);
	
	public Wall(int x, int y)
	{
		this.pos.Pos_x = x;
		this.pos.Pos_y = y;
	}
	

}
