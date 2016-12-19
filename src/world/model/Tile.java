package world.model;

import java.awt.Color;

public abstract class Tile
{

	private boolean isCrossable;
	private Color tileColor;
	private double monsterSpawnChance;
	private boolean isExit;
	private boolean isInhabited = false;
	
	public Tile(boolean isCrossable, Color color, double spawnChance)
	{
		this.isCrossable = isCrossable;
		this.tileColor = color;
		this.monsterSpawnChance = spawnChance;
		this.isExit = false;
	}
	public Tile(boolean isCrossable, Color color, double spawnChance, boolean isExit)
	{
		this.isCrossable = isCrossable;
		this.tileColor = color;
		this.monsterSpawnChance = spawnChance;
		this.isExit = isExit;
	}
	
	
	public boolean canCross()
	{
		return isCrossable;
	}
	public Color getColor()
	{
		return tileColor;
	}
	public double getSpawnChance()
	{
		return monsterSpawnChance;
	}
	public int[] getDoorDirection()
	{
		return null;
	}
	public boolean getIsExit()
	{
		return isExit;
	}
	public static int getMinimumSize()
	{
		return 50;
	}
	public boolean isInhabited()
	{
		return isInhabited;
	}
	public void setInhabited(boolean isInhabited)
	{
		this.isInhabited = isInhabited;
	}

}
