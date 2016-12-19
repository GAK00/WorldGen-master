package world.model;

public class Exit extends Tile
{
	int[] direction;
	public Exit(int[] direction,Tile tile)
	{
		super(true, tile.getColor(), 0);
		this.direction = direction;
	}
	@Override
	public int[] getDoorDirection()
	{
		return direction;
	}
	@Override
	public boolean getIsExit()
	{
		return true;
	}

}
