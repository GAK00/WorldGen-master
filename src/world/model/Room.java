package world.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import world.controls.WorldControl;

public class Room
{
	private ArrayList<Tile> interior;
	private Dimension roomDimension;
	private WorldControl control;
	private double ambushChance;
	private int restAmount;
	private Random rand;

	public Room(ArrayList<Tile> interior, Dimension roomDimension,WorldControl control)
	{
		this.interior = interior;
		this.roomDimension = roomDimension;
		this.control = control;
		ambushChance = 0.1;
		restAmount =1;
		rand = new Random();
	}
	
	public void resetRest()
	{
		restAmount =1;
	}
	private void increaseAmbushChance()
	{
		if(Double.compare(ambushChance,2.0)<0)
		{
			ambushChance += 0.1;
		}
	}
	private void increaseRestAmount()
	{
		restAmount += 1;
	}
	public boolean isAmbush()
	{
		boolean isAmbush = false;
		int modifier = 10;
		if(Double.compare(ambushChance, 1.0)>=0)
		{
			modifier += restAmount;
		}
		double fightChance = rand.nextInt(modifier)+(ambushChance*restAmount);
		if(restAmount<3){
		if(Double.compare(fightChance-modifier, 3.75)>0)
		{
			isAmbush = true;
		}}
		else
		{
			if(Double.compare(fightChance, (restAmount+modifier))>0)
			{
				isAmbush = true;
			}
		}
		increaseRestAmount();
		increaseAmbushChance();
		return isAmbush;
	}

	public Tile getTile(Dimension retriveDimension)
	{
		int index = (int) (((retriveDimension.getWidth() - 1) * (roomDimension.getHeight())) + (retriveDimension.getHeight() - 1));
		// System.out.println(index);
		if (index <= interior.size() && index >= 0)
		{
			return interior.get(index);
		} else
		{
			return null;
		}
	}

	public Dimension getSize()
	{
		return roomDimension;
	}
	
	
	
	

	public void move(int[] direction)
	{
		

		Dimension currentPos = getOccupyiedTile();
		if (currentPos != null)
		{
			Dimension nextPosition = new Dimension(currentPos.width + direction[0], currentPos.height + direction[1]);
			if (getTile(currentPos).getIsExit()&&getTile(currentPos).getDoorDirection()[0]==direction[0]&&getTile(currentPos).getDoorDirection()[1]==direction[1])
			{
				control.updateMap(getTile(currentPos).getDoorDirection(), currentPos);
			} else
			{
				if(getTile(nextPosition).canCross())
				{
					getTile(currentPos).setInhabited(false);
					getTile(nextPosition).setInhabited(true);
					if(getTile(nextPosition).gethasMonster())
					{
						System.out.println("fight");
						getTile(nextPosition).setHasMonster(false);
					}
				}
			}
		}
	}

	public Dimension getOccupyiedTile()
	{
		for (int getX = 1; getX <= ((int) roomDimension.getWidth()); getX++)
		{
			for (int getY = 1; getY <= ((int) roomDimension.getHeight()); getY++)
			{
				if (getTile(new Dimension(getX, getY)).isInhabited())
				{
					return new Dimension(getX, getY);
				}
			}
		}
		return null;
	}

	public void clean()
	{
		for (int getX = 1; getX <= ((int) roomDimension.getWidth()); getX++)
		{
			for (int getY = 1; getY <= ((int) roomDimension.getHeight()); getY++)
			{
				if (getTile(new Dimension(getX, getY)).isInhabited())
				{
					getTile(new Dimension(getX, getY)).setInhabited(false);
				}
			}
		}
	}
		
	
}
