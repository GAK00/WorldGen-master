package world.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import world.controls.WorldControl;
import world.model.Map;
import world.model.Room;

public class WorldPanel extends JPanel
{
	private JLabel worldView;
	WorldControl controller;
	private SpringLayout layout;
	private int height;
	private int width;
	private int blockSize;
	private int xOffset;
	private int yOffset;

	public WorldPanel(WorldControl controller,int width, int heigth)
	{
		super();
		sized(width,heigth);
		layout = new SpringLayout();

		this.controller = controller;
		this.worldView = new JLabel(roomToPicture(controller.getMap().getCurrentRoom()));
		worldView.setText("Health: 20");
		setup();
		setupListeners();
	}

	private void setup()
	{

		this.setLayout(layout);
		this.add(worldView);
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private void setupListeners()
	{

		this.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent pressed)
			{
				

				if (pressed.getKeyCode() == KeyEvent.VK_W)
				{
					int[] direction = { 0, -1 };
					controller.getMap().getCurrentRoom().move(direction);
				}
				if (pressed.getKeyCode() == KeyEvent.VK_D)
				{
					int[] direction = { 1, 0 };
					controller.getMap().getCurrentRoom().move(direction);

				}
				if (pressed.getKeyCode() == KeyEvent.VK_A)
				{
					int[] direction = { -1, 0 };
					controller.getMap().getCurrentRoom().move(direction);
				}
				if (pressed.getKeyCode() == KeyEvent.VK_S)
				{
					int[] direction = { 0, 1 };
					controller.getMap().getCurrentRoom().move(direction);
				}
				redraw(controller.getMap().getCurrentRoom());

			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

		});
	}

	private ImageIcon roomToPicture(Room room)
	{
		int block = this.blockSize;
		BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int getX = 1; getX <= ((int) room.getSize().getWidth()); getX++)
		{
			for (int getY = 1; getY <= ((int) room.getSize().getHeight()); getY++)
			{
				Color currentColor = room.getTile(new Dimension(getX, getY)).getColor();
				// System.out.println(room.getTile(new
				// Dimension(getX,getY)).getClass().getSimpleName()+getX+getY);
				if (room.getTile(new Dimension(getX, getY)).isInhabited())
				{
					for (int x = 0; x < block; x++)
					{
						for (int y = 0; y < block; y++)
						{

							if ((x > block / 4 && x < (block * 3) / 4) && (y > block / 4 && y < (block * 3) / 4))
							{
								map.setRGB(((getX - 1) * block) + x+xOffset, ((getY - 1) * block) + y+yOffset, Color.red.getRGB());
							} else
							{
								map.setRGB(((getX - 1) * block) + x+xOffset, ((getY - 1) * block) + y+yOffset, currentColor.getRGB());
							}
						}
					}
				} else
				{
					for (int x = 0; x < block; x++)
					{
						for (int y = 0; y < block; y++)
						{
							map.setRGB(((getX - 1) * block) + x+xOffset, ((getY - 1) * block) + y+yOffset, currentColor.getRGB());
						}
					}
				}
			}
		}
		ImageIcon mapImage = new ImageIcon(map);
		return mapImage;
	}
	public void redraw(Room room)
	{
		this.remove(worldView);
		worldView = new JLabel(roomToPicture(room));
		this.add(worldView);
		this.repaint();
		this.validate();
	}
	public void resized(int width, int height)
	{
		this.width = width;
		this.height = height;
		if(width>height)
		{
			this.blockSize = height/7;
		}
		else
		{
			this.blockSize = width/7;
		}
		this.xOffset = (this.width-(this.blockSize*7))/2;
		this.yOffset = (this.height-(this.blockSize*7))/2;
		redraw(this.controller.getMap().getCurrentRoom());
		
	}
	public void sized(int width, int height)
	{
		this.width = width;
		this.height = height;
		if(width>height)
		{
			this.blockSize = height/7;
		}
		else
		{
			this.blockSize = width/7;
		}
		this.xOffset = ((this.width-(this.blockSize*7))/2);
		this.yOffset = ((this.height-(this.blockSize*7))/2);
		
	}
}
