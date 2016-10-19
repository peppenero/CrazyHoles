package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorldManager {

	private WorldImpl world;
	ArrayList<String> color = new ArrayList<>();

	public WorldManager() {
		world = new WorldImpl();
	}

	private World loadWorld(int level) throws IOException {
		world.reset();

		String filename = new String("data/levels/world"+level+".txt");
		



		BufferedReader br = new BufferedReader(new FileReader(filename));

		String buffer;
		buffer = br.readLine();
		while (buffer != null) {
			String[] az = buffer.split(" ");
			if (az[0].equals("buca")) {
				Hole h = new Hole(Integer.parseInt(az[4]),
						Integer.parseInt(az[2]), Integer.parseInt(az[3]), 5,
						world, az[1], Integer.parseInt(az[5]));
				world.getHoles().add(h);
				color.add(az[1]);
			}
			if (az[0].equals("numeroPalle")) {
				for (int i = 0; i < Integer.parseInt(az[1]); i++) {
					Ball b = new Ball(color, world);
					world.getBalls().add(b);
				}
			}
			if (buffer.equals("ultimo")) {
				world.setLastLevel(true);
			}
			buffer = br.readLine();

		}

		br.close();
		return world;
	}
	private World loadWorld(File file) throws IOException {
		world.reset();

		BufferedReader br = new BufferedReader(new FileReader(file));

		String buffer;
		buffer = br.readLine();
		while (buffer != null) {
			String[] az = buffer.split(" ");
			if (az[0].equals("buca")) {
				Hole h = new Hole(Integer.parseInt(az[4]),
						Integer.parseInt(az[2]), Integer.parseInt(az[3]), 5,
						world, az[1], Integer.parseInt(az[5]));
				world.getHoles().add(h);
				color.add(az[1]);
			}
			if (az[0].equals("numeroPalle")) {
				for (int i = 0; i < Integer.parseInt(az[1]); i++) {
					Ball b = new Ball(color, world);
					world.getBalls().add(b);
				}
			}
			if (buffer.equals("ultimo")) {
				world.setLastLevel(true);
			}
			buffer = br.readLine();

		}

		br.close();
		return world;
	}

	public World getworld(int level) throws IOException {
		return loadWorld(level);
	}
	public World getWorld(File file) throws IOException
	{		
			return loadWorld(file);
	}

	public boolean areThereBalls() {
		return world.getBalls().isEmpty();
	}
}
