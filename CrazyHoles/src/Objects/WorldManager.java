package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WorldManager {
	
	private WorldImpl  world;
	private ArrayList<Hole> holes = new ArrayList<>();
	private List<Ball> balls = new ArrayList<>();
	ArrayList<String> color = new ArrayList<>();
	private int livello;
	
	
	public WorldManager()
	{
		world = new WorldImpl();
	}
	
	private World loadWorld(int level) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new FileReader("images/defaultWorld.txt"));
		
		String buffer;
		buffer = br.readLine();
		while(buffer!=null)
		{
			String[] az = buffer.split(" ");
			if(az[0].equals("buca"))
			{	
				Hole h = new Hole(Integer.parseInt(az[4]), Integer.parseInt(az[2]),Integer.parseInt(az[3]), 5, world, az[1]);
				holes.add(h);
				color.add(az[1]);
			}
			if(az[0].equals("numeroPalle"))
			{
				world.setHoles(holes);
				for(int i=0;i<Integer.parseInt(az[1]);i++)
				{
					Ball b = new Ball(10,color,world);
					balls.add(b);
				}
			}
			buffer=br.readLine();
		}
		
		
		br.close();
		
		world.setBalls(balls);
		return world;
	}
	
	public World getworld(int level) throws IOException
	{
		return loadWorld(level);
	}

 	public boolean areThereBalls()
 	{
 		return world.getBalls().isEmpty();		
 	}
}
