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
import javax.swing.*;

/**
 * Draws a single Line inside of a panel. This is used to create the grid
 * within a tic-tac-toe game.
 *
 * @author Brady Catherman
 * @version 1.0
 */
public class JLinePanel extends JPanel
{
	/** Defines if we should draw a horizontal line. */
	private boolean hor;

	/** Defines if we should draw a vertical line. */
	private boolean ver;

	/**
	 * Creates a LinePanel.
	 * @param V Vertical line (true/false)
	 * @param H Horizontal line (true/false)
	 */
	public JLinePanel(boolean H, boolean V)
	{
		hor = H;
		ver = V;
	}

	/**
	 * Paints the given lines.
	 *
	 * @param G The graphics object to play with.
	 * @return Nothing.
	 */
	public void paint(Graphics G)
	{
		int h = getHeight();
		int w = getWidth();
		
		G.setColor(Color.BLACK);
		G.fillRect(0, 0, w, h);
		
		G.setColor(Color.WHITE);
		
		if (ver)
			G.drawLine(w / 2, 0, w / 2, h);

		if (hor)
			G.drawLine(0, h / 2, w, h / 2);
	}
}
