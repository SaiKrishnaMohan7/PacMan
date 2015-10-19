package pacmanPro;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class Maze {

	final int WIDTH = 40;
	final int HEIGHT = 18;
	String[][] layout = new String[40][18];

	public Maze()
	{
		for(int y = 0; y < HEIGHT; y++ )
			for(int x = 0; x < WIDTH; x++)
				layout[x][y] = "b";

	}

	public Command checkObstruct(Position pos)
	{
		if((pos.direction == Direction.RIGHT) && (pos.Pos_x % 15 == 0) && checkWall((pos.Pos_x /15) + 1, pos.Pos_y /15))
		{
			return Command.STOP;
		}
		if((pos.direction == Direction.LEFT) && (pos.Pos_x % 15 == 0) && checkWall((pos.Pos_x /15) - 1, pos.Pos_y /15))
		{
			return Command.STOP;
		}
		if((pos.direction == Direction.UP) && (pos.Pos_y % 15 == 0) && checkWall(pos.Pos_x /15, (pos.Pos_y /15) - 1))
		{
			return Command.STOP;
		}
		if((pos.direction == Direction.DOWN) && (pos.Pos_y % 15 == 0) && checkWall(pos.Pos_x /15, (pos.Pos_y /15) + 1))
		{
			return Command.STOP;
		}

		return Command.MOVE;

	}

	public boolean allowDirectionChange(Direction direct, Position pos)
	{
		if(direct != pos.direction)
		{
			if(pos.direction == Direction.RIGHT)
			{
				if((direct == Direction.UP) && checkWall( pos.Pos_x /15, (pos.Pos_y /15) - 1 ))
				{
					return false;
				}
				if((direct == Direction.DOWN) && checkWall( pos.Pos_x /15, (pos.Pos_y /15) + 1 ))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else if(pos.direction == Direction.LEFT)
			{
				if((direct == Direction.UP) && checkWall( pos.Pos_x /15, (pos.Pos_y /15) - 1 ))
				{
					return false;
				}
				if((direct == Direction.DOWN) && checkWall( pos.Pos_x /15, (pos.Pos_y /15) + 1 ))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else if(pos.direction == Direction.UP)
			{
				if((direct == Direction.RIGHT) && checkWall( (pos.Pos_x /15) + 1, pos.Pos_y /15 ))
				{
					return false;
				}
				if((direct == Direction.LEFT) && checkWall( (pos.Pos_x /15) - 1 , pos.Pos_y /15))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else if(pos.direction == Direction.DOWN)
			{
				if((direct == Direction.RIGHT) && checkWall( (pos.Pos_x /15) + 1, pos.Pos_y /15 ))
				{
					return false;
				}
				if((direct == Direction.LEFT) && checkWall( (pos.Pos_x /15) - 1 , pos.Pos_y /15))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else 
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}

	public boolean checkWall(int x, int y)
	{
		if(layout[x][y] == "w")
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void readFile(String fileName)
	{
		try {
			BufferedReader rd = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			String[] pieces;

			line = rd.readLine();

			while (line != null)
			{
				pieces = line.split("[ ]+");

				if (pieces[0].equals("w"))
				{
					layout[Integer.parseInt(pieces[1]) - 1][Integer.parseInt(pieces[2]) - 1] = "w";
				}
				else if(pieces[0].equals("p"))
				{
					layout[Integer.parseInt(pieces[1]) - 1][Integer.parseInt(pieces[2]) - 1] = "p";
				}
				line = rd.readLine();
			}

			rd.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
