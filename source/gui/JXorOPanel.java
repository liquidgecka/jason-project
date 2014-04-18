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

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 * Displays a single X, or O in the given position.
 *
 * @author Brady Catherman
 * @version 1.0
 */
public class JXorOPanel extends JPanel implements MouseListener
{
	/** Stores the current display image that should be used. */
	private int display = 0;

	/** Stores all of the ActionListeners associated with this object. */
	private LinkedList<ActionListener> alist = new LinkedList<ActionListener>();

	/**
	 * Creates a new JXorOPanel object.
	 */
	public JXorOPanel()
	{
		addMouseListener(this);
	}

	/**
	 * This method is called when the panel should be repainted.
	 *
	 * @param G the graphics object to paint on.
	 */
	public void paint(Graphics G)
	{
		int w = getWidth();
		int h = getHeight();

		int[] x1pnts = {(int) (w * 0.1), (int) (w * 0.97), (int) (w * 0.90),
		                (int) (w * 0.03)};
		int[] y1pnts = {(int) (h * 0.97), (int) (h * 0.1), (int) (h * 0.03),
		                (int) (h * 0.90)};
		int[] x2pnts = {(int) (w * 0.03), (int) (w * 0.90), (int) (w * 0.97),
		                (int) (w * 0.10)};
		int[] y2pnts = {(int) (h * 0.1), (int) (h * 0.97), (int) (h * 0.90),
		                (int) (h * 0.03)};

		G.setColor(Color.BLACK);
		G.fillRect(0, 0, getWidth(), getHeight());
		
		switch(display)
		{
			case 1:
				G.setColor(Color.WHITE);
				G.fillPolygon(x1pnts, y1pnts, 4);
				G.fillPolygon(x2pnts, y2pnts, 4);
				break;
			
			

			case 2:
				G.setColor(Color.WHITE);
				G.fillOval((int) (w * 0.03), (int) (h * 0.03), (int) (w * 0.94),
				           (int) (h * 0.94));
				G.setColor(Color.BLACK);
				G.fillOval((int) (w * 0.1), (int) (h * 0.1), (int) (w * 0.8),
				           (int) (h * 0.8));
				break;
			
			case 3:
				G.setColor(Color.YELLOW);
				G.fillPolygon(x1pnts, y1pnts, 4);
				G.fillPolygon(x2pnts, y2pnts, 4);
				break;
			
			case 4:
				G.setColor(Color.YELLOW);
				G.fillOval((int) (w * 0.03), (int) (h * 0.03), (int) (w * 0.94),
				           (int) (h * 0.94));
				G.setColor(Color.BLACK);
				G.fillOval((int) (w * 0.1), (int) (h * 0.1), (int) (w * 0.8),
				           (int) (h * 0.8));
				break;

			default:
				break;
		}
	}

	/**
	 * This method displays a single X, or O.
	 *
	 * @param n New value to use.
	 * @return Nothing.
	 */
	void setDisplay(int n)
	{
		display = n;
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

	/**
	 * This method is called when the mouse is clicked on this object.
	 *
	 * @param e the event object.
	 * @return nothing.
	 */
	public void mouseClicked(MouseEvent e)
	{
		ActionEvent ae = new ActionEvent(this, 0, "");
	
		Iterator<ActionListener> i = alist.iterator();
		while (i.hasNext())
			i.next().actionPerformed(ae);
	}

	/**
	 * This method is called when the mouse enters this region. It is
	 * currently ignored.
	 *
	 * @param e the event object.
	 * @return nothing.
	 */
	public void mouseEntered(MouseEvent e) { }

	/**
	 * This method is called when the mouse exited this region. It is
	 * currently ignored.
	 *
	 * @param e the event object.
	 * @return nothing.
	 */
	public void mouseExited(MouseEvent e) { }

	/**
	 * This method is called when the mouse is pressed in this region. It is
	 * currently ignored.
	 *
	 * @param e the event object.
	 * @return nothing.
	 */
	public void mousePressed(MouseEvent e) { }
	
	/**
	 * This method is called when the mouse is released in this region. It is
	 * currently ignored.
	 *
	 * @param e the event object.
	 * @return nothing.
	 */
	public void mouseReleased(MouseEvent e) { }


}
