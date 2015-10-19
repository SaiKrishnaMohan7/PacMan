package pacmanPro;

import java.awt.event.*;

import processing.core.*;

public class Pacman extends PApplet implements KeyListener {

	// Pac-man variables
	Position pacPos, pacPosPrev;
	PImage pacman_open, pacman_closed, backgrounds, pellet, wallMiddle, black_space, dummy_obstruct, lose, win, lose_final, win_final, back;
	PImage pacman_open_down, pacman_open_up, pacman_open_right, pacman_open_left;
	PImage pacman_closed_down, pacman_closed_up, pacman_closed_right, pacman_closed_left;

	// ghost variables
	Position ghost1Pos, ghost2Pos, ghost3Pos, ghost4Pos;
	PImage ghost1, ghost2, ghost3, ghost4;
	boolean mouthClosed;
	Maze maze;
	int x ,y = 0;
	Command cmd = Command.NONE;
	int prevPacx , prevPacy, prevGhost1x, prevGhost1y, prevGhost2x, prevGhost2y, prevGhost3x, prevGhost3y, prevGhost4x, prevGhost4y = 0;
	int totalPellet = 0;
	String path = "E:/Workbench/pacman/Images/";

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "pacmanPro.Pacman" });
	}

	public void setup() {

		maze = new Maze();

		maze.readFile(path + "maze.txt");
		cmd = Command.MOVE;
		
		size(600,270);
		background(0);
		
		loadAllImages();
		image(back, 45, 45);
		drawMaze(maze);
	
		pacPos = new Position( 24*15, 12*15, Direction.UP);

		image(lose, 28*15 , 14*15);
		image(win, 4* 15, 14*15);
		
		mouthClosed = true;
		
		pacman_open = pacman_open_up;
		pacman_closed = pacman_closed_up;
		
		ghost1Pos = new Position(23*15, 8*15, Direction.UP);
		ghost2Pos = new Position(23*15, 8*15, Direction.UP);
		ghost3Pos = new Position(23*15, 8*15, Direction.UP);
		ghost4Pos = new Position(23*15, 8*15, Direction.UP);
	}

	public void draw() {
		if(!checkCollision(ghost1Pos, pacPos) && !checkCollision(ghost2Pos, pacPos) && !checkCollision(ghost3Pos, pacPos) && !checkCollision(ghost4Pos, pacPos) && (totalPellet != 0))
		{
			if(cmd == Command.MOVE)
			{
				movePac();
				updatePac(pacPos);
			}
			drawAndCalculateGhost();
			CheckCollisionWithPellet();

			delay(58); // 33 nice value
		}
		else
		{
			if(totalPellet == 0)
			{
				image(win_final, 4* 15, 14*15);
			}
			else
			{
				image(lose_final, 28*15 , 14*15);
				
			}
		}
	}

	public void CheckCollisionWithPellet()
	{
		if(((pacPos.Pos_x % 15 == 0) && (pacPos.Pos_y % 15 == 0)))
		{
			if(maze.layout[pacPos.Pos_x/15][pacPos.Pos_y/15] == "p")
			{
				maze.layout[pacPos.Pos_x/15][pacPos.Pos_y/15] = "b";
				totalPellet--;
			}
		}
	}

	public void drawAndCalculateGhost() {

		moveGhost();	
		drawGhost();
	}

	public void moveGhost() {

		if((ghost1Pos.Pos_x % 15 == 0) && (ghost1Pos.Pos_y % 15 == 0))
		{
			prevGhost1x = ghost1Pos.Pos_x ;
			prevGhost1y = ghost1Pos.Pos_y ;
		}
		ghost1Pos = Ghost.moveGhosts(ghost1Pos, maze);

		if((ghost2Pos.Pos_x % 15 == 0) && (ghost2Pos.Pos_y % 15 == 0))
		{
			prevGhost2x = ghost2Pos.Pos_x ;
			prevGhost2y = ghost2Pos.Pos_y ;
		}
		ghost2Pos = Ghost.moveGhosts(ghost2Pos, maze);

		if((ghost3Pos.Pos_x % 15 == 0) && (ghost3Pos.Pos_y % 15 == 0))
		{
			prevGhost3x = ghost3Pos.Pos_x ;
			prevGhost3y = ghost3Pos.Pos_y ;
		}
		ghost3Pos = Ghost.moveGhosts(ghost3Pos, maze);

		if((ghost4Pos.Pos_x % 15 == 0) && (ghost4Pos.Pos_y % 15 == 0))
		{
			prevGhost4x = ghost4Pos.Pos_x ;
			prevGhost4y = ghost4Pos.Pos_y ;
		}
		ghost4Pos = Ghost.moveGhosts(ghost4Pos, maze);
	}

	public Boolean checkCollision(Position x, Position y)
	{
		if((x.Pos_x == y.Pos_x) && (x.Pos_y == y.Pos_y))
			return true;
		else
			return false;
	}

	public void drawGhost() {

		drawMazeOnPoint(maze, prevGhost1x / 15, prevGhost1y /15);
		drawMazeOnPoint(maze, prevGhost2x / 15, prevGhost2y /15);
		drawMazeOnPoint(maze, prevGhost3x / 15, prevGhost3y /15);
		drawMazeOnPoint(maze, prevGhost4x / 15, prevGhost4y /15);

		image(ghost1, ghost1Pos.Pos_x, ghost1Pos.Pos_y);
		image(ghost2, ghost2Pos.Pos_x, ghost2Pos.Pos_y);
		image(ghost3, ghost3Pos.Pos_x, ghost3Pos.Pos_y);
		image(ghost4, ghost4Pos.Pos_x, ghost4Pos.Pos_y);
	}

	public void drawMazeOnPoint(Maze mazez, int x, int y)
	{
		if(mazez.layout[x][y] == "p")
		{
			image(pellet, x*15, y*15);
		}
		else
		{
			image(black_space, x*15, y*15);
		}
	}


	public void movePac() {
		prevPacx = pacPos.Pos_x;
		prevPacy = pacPos.Pos_y;

		switch(pacPos.direction)
		{
		case LEFT:
			pacPos.moveLeft();
			break;
		case RIGHT:
			pacPos.moveRight();
			break;
		case UP:
			pacPos.moveUp();
			break;
		case DOWN:
			pacPos.moveDown();
			break;
		default:
			break;
		}
		//	for viewing pos of Pacman
		//	System.out.println(prevPacx+" prev " + prevPacy);
		//	System.out.println(pacPos.Pos_x+" new "+pacPos.Pos_y);
		cmd = maze.checkObstruct(pacPos);

	}


	public void updatePac(Position pacPosit) {
		image(black_space, prevPacx, prevPacy);
		if(mouthClosed)
		{
			mouthClosed = false;
			image(pacman_open, pacPosit.Pos_x, pacPosit.Pos_y);	
		}
		else
		{
			mouthClosed = true;
			image(pacman_closed, pacPosit.Pos_x, pacPosit.Pos_y);
		}
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case 37:
			if(maze.allowDirectionChange(Direction.LEFT, pacPos))
			{
				image(black_space, pacPos.Pos_x, pacPos.Pos_y);

				pacPos.direction = Direction.LEFT;
				pacman_open = pacman_open_left;
				pacman_closed = pacman_closed_left;
				pacPos.Pos_y = pacPos.Pos_y - (pacPos.Pos_y % 15);
			}
			break;
		case 38:
			if(maze.allowDirectionChange(Direction.UP, pacPos))
			{
				image(black_space, pacPos.Pos_x, pacPos.Pos_y);

				pacPos.direction = Direction.UP;
				pacman_open = pacman_open_up;
				pacman_closed = pacman_closed_up;
				pacPos.Pos_x = pacPos.Pos_x - (pacPos.Pos_x % 15);
			}
			break;
		case 39:
			if(maze.allowDirectionChange(Direction.RIGHT, pacPos))
			{	
				image(black_space, pacPos.Pos_x, pacPos.Pos_y);

				pacPos.direction = Direction.RIGHT;
				pacman_open = pacman_open_right;
				pacman_closed = pacman_closed_right;
				pacPos.Pos_y = pacPos.Pos_y - (pacPos.Pos_y % 15);
			}
			break;
		case 40:
			if(maze.allowDirectionChange(Direction.DOWN, pacPos))
			{
				image(black_space, pacPos.Pos_x, pacPos.Pos_y);

				pacPos.direction = Direction.DOWN;
				pacman_open = pacman_open_down;
				pacman_closed = pacman_closed_down;
				pacPos.Pos_x = pacPos.Pos_x - (pacPos.Pos_x % 15);
			}
			break;
		default:
			break;
		}
		cmd = maze.checkObstruct(pacPos);
	}

	public void drawMaze(Maze ma) {
		for(int y = 0; y < ma.HEIGHT; y++ )
			for(int x = 0; x < ma.WIDTH; x++)
			{
				if(ma.layout[x][y] == "b")
				{
					continue;
				}
				else if(ma.layout[x][y] == "w")
				{
					// used before for testing purposes fake walls
					//image(dummy_obstruct, x * 15, y * 15);
				}
				else if(ma.layout[x][y] == "p")
				{
					image(pellet, x*15, y*15);
					totalPellet++;
				}
			}
	}

	public void loadAllImages() {
		dummy_obstruct = loadImage(path + "dummy_obstruct.bmp");
		pacman_open_right = loadImage(path + "pacman_open_right.bmp");
		pacman_open_left = loadImage(path + "pacman_open_left.bmp");
		pacman_open_up = loadImage(path + "pacman_open_up.bmp");
		pacman_open_down = loadImage(path + "pacman_open_down.bmp");
		pacman_closed_right = loadImage(path + "pacman_closed_right.bmp");
		pacman_closed_left = loadImage(path + "pacman_closed_left.bmp");
		pacman_closed_up = loadImage(path + "pacman_closed_up.bmp");
		pacman_closed_down = loadImage(path + "pacman_closed_down.bmp");
		black_space = loadImage(path + "black_space.bmp");
		backgrounds = loadImage(path + "maze_pacman.bmp");
		// ghost
		ghost1 = loadImage(path + "Ghost1.bmp");
		ghost2 = loadImage(path + "Ghost2.bmp");
		ghost3 = loadImage(path + "Ghost3.bmp");
		ghost4 = loadImage(path + "Ghost4.bmp");
		// pellet
		pellet = loadImage(path + "pellet.bmp");
		lose = loadImage(path + "lose.bmp");
		win = loadImage(path + "win.bmp");
		lose_final = loadImage(path + "lose_final.bmp");
		win_final = loadImage(path + "win_final.bmp");
		back = loadImage(path + "back.bmp");
	}
}
