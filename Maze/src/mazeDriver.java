	//Linear Data Structure Implementation
	//Bradley Agyei
	//March 10, 2020
	
	import java.io.*;
	import java.util.*;
	 
	
	public class mazeDriver 
	{
		//Sets boundaries of maze
		private static final int A = 20;
		private static final int B = 20;  
		
		private static boolean[][] pathway = new boolean[A][B];
	
		public static void main(String[] args) 
				throws FileNotFoundException
		{	
			
			//Getting maze text file
			setTour(true); 
			boolean open;
			String [][] Maze = new String [A][B];
			Scanner inputFile = null;
			File MazeFile = new File("maze.txt");
			int col = 0;
			int row = 0;
			
			//Printing out maze with rows and columns numbered
			System.out.println("Welcome to the Maze Game");
			System.out.println("------------------------");
			System.out.println();
			
			//Try-Catch to get file
			try 
			{
				inputFile = new Scanner(MazeFile);
				open = true;
			}
			catch (FileNotFoundException e)
			{
				System.out.println("The file you entered could not be found");
				open = false; 
			}
			
			if(open && inputFile.hasNext())
			{
				while(inputFile.hasNext())
				{
					Maze[row][col] = inputFile.next(); 
					col++; 
					if(col == 20)
					{
						row++;
						col = 0; 
					}
				}
				
			}
			
			//Print maze after user input and determines if path has been found or not.
			print(Maze);
			System.out.println();
			
			if(pathWay(Maze))
			{
				System.out.println();
				System.out.println("    I am free");
			}
			else 
			{
				
				System.out.println("    Help, I am trapped");
			}
			System.out.println("");		
			
			print(Maze);
		}
		
		//Prints out file and formats to include rows and column
		public static void print(String [][]Maze)
		{
			for(int  i = 0; i < 20; i++)
			{
				System.out.printf("%2d   ", i);
				for(int j = 0; j < 20; j++)
				{
					System.out.print(Maze[i][j]+"  ");
				}
				System.out.println();
			}
			System.out.print("\n   ");
			for(int a = 0; a < 20; a++)
			{
				System.out.printf("%3d", a );
			}
		}
		
		//Function to determine if coordinates have already been visited 
		private static void setTour(boolean x)
		{
			for(int i = 0; i < pathway.length; i++)
				for(int k = 0; k < pathway[i].length; k++)
				{
					pathway[i][k] = x; 
				}
		}
		
		//Function to find a path in maze
		private static boolean pathWay(String Maze[][])
		{
			
			//Asking user for starting position 
			Scanner console = new Scanner(System.in);
			System.out.println();
			System.out.println("Please type row number for starting position");
			int r = console.nextInt();
			System.out.println("Please type column number for starting position");
			int c = console.nextInt();
			
			//Using a stack to pop and push coordinates while on path
			Stack<Node> n = new Stack<Node>();
			Node temp = new Node(r, c);
			Maze[r][c] ="S";
			
			n.push(temp);
			
			while(!n.empty())
			{
				temp = n.peek();
				int a = temp.getSteps();
				r = temp.getX();
				c = temp.getY();
				
				temp.setSteps(temp.getSteps() + 1);
				n.pop();
				n.push(temp);
				Maze[r][c]="+";
				
				//If at "E", a proper pathway has been found
				if(r == 0 && c == 0)
				{
					return true;
				}
				
				//Checking to see if going North is possible
				if(a == 0)
				{
					if(r - 1 >= 0 && Maze[r - 1][c].equals("0") && pathway[r - 1][c]||
					   r - 1 >= 0 && Maze[r - 1][c].equals("E") && pathway[r - 1][c]||
					   r - 1 >= 0 && Maze[r - 1][c].equals("S") && pathway[r - 1][c])
					{
						Node temp1 = new Node(r - 1, c);
						pathway[r - 1][c] = false;
						n.push(temp1);
					}
				}
				
				//Checking to see if going West is possible
				else if(a == 1)
				{
					if(c - 1 >= 0 && Maze[r][c - 1].equals("0") && pathway[r][c - 1]||
					   c - 1 >= 0 && Maze[r][c - 1].equals("E") && pathway[r][c - 1]|| 
					   c - 1 >= 0 && Maze[r][c - 1].equals("S") && pathway[r][c - 1]) 
					{
						Node temp1 = new Node(r, c - 1);
						pathway[r][c - 1] = false;
						n.push(temp1);
					}
				}
				
				//Checking to see if going East is possible
				else if(a == 2)
				{
					if(c + 1 < B && Maze[r][c + 1].equals("0") && pathway[r][c + 1]||
					   c + 1 < B && Maze[r][c + 1].equals("E") && pathway[r][c + 1]||
					   c + 1 < B && Maze[r][c + 1].equals("S") && pathway[r][c + 1])
					{
						Node temp1 = new Node(r, c + 1);
						pathway[r][c + 1] = false;
						n.push(temp1);
					}
				}
				
				//Checking to see if going South is possible
				else if(a == 3)
				{
					if(r + 1 < A && Maze[r + 1][c].equals("0") && pathway[r + 1][c]||
					   r + 1 < A && Maze[r + 1][c].equals("E") && pathway[r + 1][c]||
					   r + 1 < A && Maze[r + 1][c].equals("S") && pathway[r + 1][c])
					{
						Node temp1 = new Node(r + 1, c);
						pathway[r + 1][c] = false;
						n.push(temp1);
					}
				}
				
				//Returning to parent if road block has been hit
				else
				{
					pathway[temp.getX()][temp.getY()] = true;
					Maze[temp.getX()][temp.getY()]="1";
					n.pop();
				}
			}
			
			return false; 
		}
		
	}
