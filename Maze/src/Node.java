import java.util.*;
public class Node 
{
	private int x, y;
	private int steps; 
	
	public Node(int a, int b)
	{
		this.x = a;
		this.y = b;
		this.steps = 0;
	}
	
	//Getters and Setters
	public int getX()
	{
		return x; 
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getSteps()
	{
		return steps; 
	}
	
	public void setX(int x)
	{
		this.x = x; 
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setSteps(int steps)
	{
		this.steps = steps; 
	}
	
}
