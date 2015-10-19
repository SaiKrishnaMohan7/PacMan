package pacmanPro;

public class Ghost {

	public static Position moveGhosts(Position posit, Maze maze) {	
		switch(posit.direction)
		{
		case LEFT:
			posit.moveLeft();
			break;
		case RIGHT:
			posit.moveRight();
			break;
		case UP:
			posit.moveUp();
			break;
		case DOWN:
			posit.moveDown();
			break;
		default:
			break;
		}

		if((posit.Pos_x % 15 == 0) && (posit.Pos_y % 15 == 0)) {
			if(!posit.checkWallLeft(maze) && !posit.checkWallRight(maze))
			{
				// wall open on both sides
				if(posit.checkWallFront(maze) == Command.STOP)
				{
					// wall open on both sides closed on front
					if(Helper.gnerateRandomNumber())
					{
						posit.turnLeft();
						return posit;
					}
					else
					{
						posit.turnRight();
						return posit;
					}
				}
				else
				{
					// wall open on both sides and the front too
					switch(Helper.generateRandomNumber(2))
					{
					case 0:
						posit.turnLeft();
						return posit;
					case 1:
						posit.turnRight();
						return posit;
					case 2:
						// keep going on
						return posit;
					default:
						return posit;				
					}	
				}
			}
			
			if(!posit.checkWallLeft(maze))
			{
				// left is open
				if(posit.checkWallFront(maze) == Command.STOP)
				{
					// only left is open
					posit.turnLeft();
					return posit;
				}
				else
				{
					// front and left is open
					if(Helper.gnerateRandomNumber())
					{
						// turn left
						posit.turnLeft();
						return posit;
					}
					else
					{
						// keep going on
						return posit;
					}
				}
			}
			
			if(!posit.checkWallRight(maze))
			{
				// Right is open
				if(posit.checkWallFront(maze) == Command.STOP)
				{
					// only Right is open
					posit.turnRight();
					return posit;
				}
				else
				{
					// front and Right is open
					if(Helper.gnerateRandomNumber())
					{
						// turn Right
						posit.turnRight();
						return posit;
					}
					else
					{
						// keep going on
						return posit;
					}
				}
			}
			
			if(posit.checkWallLeft(maze) && posit.checkWallRight(maze) && (posit.checkWallFront(maze) == Command.STOP) )
			{
				// all front three directions are closed
				posit.turnAround();
				return posit;
			}
		}


		return posit;
	}


}
