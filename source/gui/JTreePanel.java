/* JASON-Project-AI
 * Copyright (C) 2005, Charles Paulson, Brady Catherman, Jim Lowe
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
 
package gui;

import javax.swing.JPanel;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Displays a Tree of information. This is used to display the AI Players
 * current process.
 *
 * @author Jim Lowe, Brady Catherman
 * @version 1.0
 */
public class JTreePanel extends JPanel implements MouseListener,
                                                  MouseMotionListener
{
	/** Stores the tree that we are currently drawing. */
	private TreeElement node;
	
	/** The fixed value used to size the round nodes in the tree. */
	public final static float mult=0.80f;

	/** Stores the ranges of clickable points. */
	private int[] clickranges_x1 = new int[46];
	private int[] clickranges_x2 = new int[46];
	private int[] clickranges_y1 = new int[46];
	private int[] clickranges_y2 = new int[46];
	private TreeElement[] clickranges_te = new TreeElement[46];
	private int clickranges_max = 0;
	private int clickranges_size = 10;

	/** The range that should be highlighted with a box. */
	private int high = -1;

	/** Stores all of the ActionListeners associated with this object. */
	private LinkedList<ActionListener> alist = new LinkedList<ActionListener>();

	public JTreePanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * Sets the element that we are currently displaying. This will force
	 * a repaint to make this panel reflect the change.
	 *
	 * @param Node the node to draw.
	 * @return Nothing.
	 */
	public void setTreeElement(TreeElement Node)
	{
		node=Node;
		repaint();
	}
	
	/**
	 * This method is called to repaint the panel. It will go through and
	 * draw all of the nodes and current locations.
	 * @param g the Graphics object to draw on.
	 * @return Nothing.
	 */
	public void paint(Graphics g)
	{
		int height = getHeight();
		int width = getWidth();
		int maxWidth = 1;
		int depth = 1;
		int path[] = new int[10];

		g.setColor(Color.BLACK);
		g.fillRect(0,0,width,height);

		clickranges_max = 0;

		if (node==null)
		 	return;
		
		node.lock();
		
		TreeElement walkNode = node;
		while (walkNode.getChildren().size() > 0)
		{
			path[depth] = walkNode.getPath();
			walkNode = walkNode.getChildren().get(path[depth]);
			depth++;
		}
				
		int vertDiv = height / depth;
		int xCent = width/2;
		int yCent = vertDiv/2;
		
		walkNode = node;
		g.setColor(Color.WHITE);
		for (int i=1; i < depth; i++)
		{	
			int horzDiv;
			if (walkNode.getChildren().size() == 0)
				horzDiv = 1;
			else 
				horzDiv = width / walkNode.getChildren().size();

			if (walkNode.getChildren().size() > maxWidth)
				maxWidth = walkNode.getChildren().size();

				
			for (int j=0; j < walkNode.getChildren().size();j++)
			{
				g.drawLine(xCent,yCent,(horzDiv*j+horzDiv/2),(yCent+vertDiv));
			}
			xCent = horzDiv*path[i]+horzDiv/2;
			yCent = yCent+vertDiv;
		
			walkNode=walkNode.getChildren().get(path[i]);
		}

		int xSize = (int)((width/maxWidth)*mult);
		int ySize = (int)((vertDiv/2)*mult);
		if (ySize < xSize)
			xSize = ySize;
		else
			ySize = xSize;
		
		xCent = width/2;
		yCent = vertDiv/2;
		drawCirc(xCent,yCent,1,xSize,g, node);
	
		walkNode=node;
		for (int i=1; i < depth; i++)
		{	
			int horzDiv = width / walkNode.getChildren().size();
			
			yCent = yCent+vertDiv;

			ListIterator<TreeElement> l= walkNode.getChildren().listIterator(0);
			int j = 0;
			while (l.hasNext())
			{
				TreeElement te = l.next();
				xCent = (horzDiv*j)+horzDiv/2;
				drawCirc(xCent, yCent, te.getColor(),
				         xSize,g,te);
				j++;
			}
			
			walkNode=walkNode.getChildren().get(path[i]);
		}

		node.unlock();

		if (high != -1)
		{
			g.setColor(Color.BLUE);
			g.drawRect(clickranges_x1[high], clickranges_y1[high],
			           clickranges_size, clickranges_size);
		}
	}
	
	/**
	 * Draws a circle at the given location.
	 * @param x the center of the circle to draw.
	 * @param y the center of the circle to draw.
	 * @param color, the color of the circle to draw.
	 * @param g The graphics to draw on.
	 * @return Nothing.
	 */
	private void drawCirc(int x, int y, int color, int size,
	                      Graphics g, TreeElement e)
	{
		clickranges_x1[clickranges_max] = x - size;
		clickranges_x2[clickranges_max] = x + size;
		clickranges_y1[clickranges_max] = y - size;
		clickranges_y2[clickranges_max] = y + size;
		clickranges_te[clickranges_max] = e;
		clickranges_size = size * 2;
		clickranges_max++;
	
		switch (color)
		{
			case 0: g.setColor(Color.red); break;
			case 1: g.setColor(Color.cyan); break;
			case 2: g.setColor(Color.green); break;
			default: g.setColor(Color.orange); break;
		}

		//g.setColor(Color.orange);
		g.fillOval(x-size/2,y-size/2,size,size);
		return;
	}

	public void mouseClicked(MouseEvent e)
	{
		if (high == -1)
			return;

		ActionEvent ae = new ActionEvent(clickranges_te[high], 0, "-");
		
		Iterator<ActionListener> i = alist.iterator();
		while (i.hasNext())
			i.next().actionPerformed(ae);
		repaint();
	}
	/**
	 * This will sets an action listener for this object.
	 *
	 * @return Nothing.
	 * @param A the new ActionListener to add.
	 */
	public void addActionListener(ActionListener A)
	{
		if (A != null)
			alist.add(A);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		for (int i = 0; i < clickranges_max; i++)
		{
			if (e.getX() >= clickranges_x1[i] &&
			    e.getX() <= clickranges_x2[i])
			{
				if (e.getY() >= clickranges_y1[i] &&
				    e.getY() <= clickranges_y2[i])
				{
					high = i;
					repaint();
					return;
				}
			}
		}

		high = -1;
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }

	public void mouseDragged(MouseEvent e) { }

}
