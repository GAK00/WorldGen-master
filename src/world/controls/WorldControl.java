package world.controls;

import java.awt.Dimension;

import javax.swing.JFrame;

import world.model.Map;
import world.view.gameFrame;

public class WorldControl
{
	private Map map;
	gameFrame frame;
	public WorldControl()
	{
		map = new Map(this);
	}
	public void start()
	{

		map.getCurrentRoom().getTile(new Dimension(4, 4)).setInhabited(true);
		frame = new gameFrame(this);
	}
	public Map getMap()
	{
		return map;
	}
	public void updateMap(int[] direction,Dimension startPoint)
	{
		int spawnx=5;
		int spawny=5;
		if((int)startPoint.getHeight()==7)
		{
			spawny =1;
			spawnx = (int)startPoint.getWidth();
		}
		else if((int)startPoint.getHeight()==1)
		{
			spawny =7;
			spawnx = (int)startPoint.getWidth();
		}
		else if((int)startPoint.getWidth()==1)
		{
			spawnx =7;
			spawny = (int)startPoint.getHeight();
		}
		else if((int)startPoint.getWidth()==7)
		{
			spawnx =1;
			spawny = (int)startPoint.getHeight();
		}
		Dimension spawnPoint = new Dimension(spawnx,spawny);
		Dimension current = map.getCurrentPos();
		Dimension next = new Dimension((int)(current.getWidth()+direction[0]),(int)(current.getHeight()+direction[1]));
		map.setCurrentPos(next);
		map.getCurrentRoom().clean();
		System.out.println(spawnPoint);
		map.getCurrentRoom().getTile(spawnPoint).setInhabited(true);
	}
	public JFrame getFrame()
	{
		return frame;
	}
	}

