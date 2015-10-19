package pacmanPro;

public class Position {
	int Pos_x ,Pos_y;
	int step = 5;
	Direction direction;

	int X_MAX = 600;
	int Y_MAX = 270;
	int X_MIN = 0;
	int Y_MIN = 0;

	public Position(int x, int y, Direction d)
	{
		if(x <= X_MAX && x >= X_MIN && y <= Y_MAX && y >= Y_MIN)
		{
			this.Pos_x = x;
			this.Pos_y = y;
			this.direction = d;
		}
		else
		{
			this.Pos_x = 0; 
			this.Pos_y = 0;
			this.direction = Direction.RIGHT;
		}
	}
	public void moveRight()
	{
		if(this.Pos_x <= X_MAX && this.Pos_x >= X_MIN )
		{
			this.Pos_x = this.Pos_x + step;
			this.direction = Direction.RIGHT;
		}	
	}

	public void moveLeft()
	{
		if(this.Pos_x <= X_MAX && this.Pos_x >= X_MIN )
		{
			this.Pos_x = this.Pos_x - step;
			this.direction = Direction.LEFT;
		}
	}

	public void moveUp()
	{
		if(this.Pos_y <= Y_MAX && this.Pos_y >= Y_MIN )
		{
			this.Pos_y = this.Pos_y - step;
			this.direction = Direction.UP;
		}
	}	

	public void moveDown()
	{
		if(this.Pos_y <= Y_MAX && this.Pos_y >= Y_MIN )
		{
			this.Pos_y = this.Pos_y + step;
			this.direction = Direction.DOWN;
		}
	}
	public void turnAround()
	{
		switch(this.direction)
		{
		case LEFT:
			this.direction = Direction.RIGHT;
			break;
		case RIGHT:
			this.direction = Direction.LEFT;
			break;
		case UP:
			this.direction = Direction.DOWN;
			break;
		case DOWN:
			this.direction =  Direction.UP;
			break;
		}
	}
	
	public void turnLeft()
	{
		switch(this.direction)
		{
		case LEFT:
			this.direction = Direction.DOWN;
			break;
		case RIGHT:
			this.direction = Direction.UP;
			break;
		case UP:
			this.direction = Direction.LEFT;
			break;
		case DOWN:
			this.direction =  Direction.RIGHT;
			break;
		}
	}
	public void turnRight()
	{
		switch(this.direction)
		{
		case LEFT:
			this.direction = Direction.UP;
			break;
		case RIGHT:
			this.direction = Direction.DOWN;
			break;
		case UP:
			this.direction = Direction.RIGHT;
			break;
		case DOWN:
			this.direction =  Direction.LEFT;
			break;
		}
	}

	
	public Boolean checkWallLeft(Maze maze)
	{
		this.turnLeft();
		if(checkWallFront(maze) == Command.STOP)
		{
			this.turnRight();
			return true;
		}
		else
		{
			this.turnRight();
			return false;
		}
		
	}
	
	public Boolean checkWallRight(Maze maze)
	{
		this.turnRight();
		if(checkWallFront(maze) == Command.STOP)
		{
			this.turnLeft();
			return true;
		}
		else
		{
			this.turnLeft();
			return false;
		}
		
	}
	
	public Command checkWallFront(Maze maze)
	{
		if((this.direction == Direction.RIGHT) && (this.Pos_x % 15 == 0) && checkWall(maze, (this.Pos_x /15) + 1, this.Pos_y /15))
		{
			return Command.STOP;
		}
		if((this.direction == Direction.LEFT) && (this.Pos_x % 15 == 0) && checkWall(maze, (this.Pos_x /15) - 1, this.Pos_y /15))
		{
			return Command.STOP;
		}
		if((this.direction == Direction.UP) && (this.Pos_y % 15 == 0) && checkWall(maze, this.Pos_x /15, (this.Pos_y /15) - 1))
		{
			return Command.STOP;
		}
		if((this.direction == Direction.DOWN) && (this.Pos_y % 15 == 0) && checkWall(maze, this.Pos_x /15, (this.Pos_y /15) + 1))
		{
			return Command.STOP;
		}

		return Command.MOVE;
	}
	
	public boolean checkWall(Maze lay ,int x, int y)
	{
		if(lay.layout[x][y] == "w")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
