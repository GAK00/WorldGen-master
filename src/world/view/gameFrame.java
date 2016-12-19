package world.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import world.controls.WorldControl;
import world.model.Tile;

public class gameFrame extends JFrame
{
	private WorldControl controller;
	private WorldPanel panel;
	public gameFrame(WorldControl controller)
	{
		super();
		this.controller = controller;
		panel = new WorldPanel(controller, 350, 350);
		setup();
		setupListeners();
	}
	private void setup()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Dungeon");
		this.setSize(350,350);
		this.setContentPane(panel);
		this.setVisible(true);
	}
public JPanel getPanel()
{
return panel;
}

private void setupListeners()
{
this.addComponentListener(new ComponentListener(){

	@Override
	public void componentHidden(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0)
	{
		if(controller.getFrame().getWidth()<(int)(Tile.getMinimumSize()*controller.getMap().getCurrentRoom().getSize().getWidth())||controller.getFrame().getHeight()<(int)(Tile.getMinimumSize()*controller.getMap().getCurrentRoom().getSize().getHeight()))
		{
			System.out.println("no");
			controller.getFrame().setSize(new Dimension((int)controller.getMap().getCurrentRoom().getSize().getWidth()*Tile.getMinimumSize() , (int)controller.getMap().getCurrentRoom().getSize().getHeight()*Tile.getMinimumSize()));
		} 
		
		panel.resized(controller.getFrame().getWidth(), controller.getFrame().getHeight());
		
	}

	@Override
	public void componentShown(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}});
}
}
