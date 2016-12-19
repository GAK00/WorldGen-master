package world.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class HealthBar
{
	private int width;
	private int height;
	private int currentHealth;
	private Color color;
	public HealthBar(int width, int height,int maxHealth,int  health)
	{
		this.width = (width/50)*maxHealth;
		this.height = height/25;
		this.currentHealth = health;
		double healthPercent = ((double)health/(double) maxHealth);
		this.color = Color.RED;
		if(Double.compare(healthPercent,.8)>0)
		{
			this.color = Color.GREEN;
		}
		else if(Double.compare(healthPercent,.4)>0)
		{
			this.color = Color.YELLOW;
		}
		
	}
	
	public BufferedImage render()
	{
		BufferedImage healthBar = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x<width;x++)
		{
			for(int y = 0; y<height;y++)
			{
				
			}
		}
	}

}
