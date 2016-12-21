package world.controls;

import java.awt.Dimension;

import javax.swing.JFrame;

import world.model.HealthBar;
import world.model.Map;
import world.view.WorldPanel;
import world.view.gameFrame;

public class WorldControl
{
	private Map map;
	private gameFrame frame;
	private int health;
	private int maxHealth;
	
	public WorldControl()
	{
		
		map = new Map(this);
		map.getCurrentRoom().getTile(new Dimension(4, 4)).setInhabited(true);
		health = 8;
		maxHealth = 100;
		
	}
	public void start()
	{
		frame = new gameFrame(this);
		WorldPanel panel = (WorldPanel)frame.getPanel();
		panel.Render();
		
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
	public HealthBar getHealthBar()
	{
		WorldPanel panel = 
				(WorldPanel)
				frame.getPanel();
		return new HealthBar(panel.getRenderSize()[0],panel.getRenderSize()[1], maxHealth, health);
	}
	public void increaseHealth()
	{
		if(health+3 < maxHealth)
		{
			health +=3;
		}
		else
		{
			health = maxHealth;
		}
	}
	}

