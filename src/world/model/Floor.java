package world.model;

import java.awt.Color;

public class Floor extends Tile
{
	public Floor(Color color)
	{
		super(true, color, 0.35);
	}
	public Floor(Color color,double DangerLevel)
	{
		super(true, color, DangerLevel);
	}
}
