package world.model;

import java.awt.Dimension;
import java.util.ArrayList;

import world.controls.WorldControl;

public class Room
{
	private ArrayList<Tile> interior;
	private Dimension roomDimension;
	private WorldControl control;

	public Room(ArrayList<Tile> interior, Dimension roomDimension,WorldControl control)
	{
		this.interior = interior;
		this.roomDimension = roomDimension;
		this.control = control;
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
