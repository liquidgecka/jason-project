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

import tictactoe.*;
import java.awt.*;
import javax.swing.*;

/**
 * This displays a single game state. It should display all the information
 * about the given state that is needed to see what is inside.
 *
 * @author Brady Catherman
 * @version 1.0
 */
public class JGameStateViewer extends JPanel
{
	/** This holds the GameState that we are currently viewing. */
	private TicTacToeGameState gamestate = null;

	/** Holds the graphics for the TicTacToe field. */
	private JTicTacToePanel tttp = new JTicTacToePanel();

	/** Text description of this game state. */
	private JTextPane jtp = new JTextPane();

	/**
	 * Creates a new gamestate viewer object with no information in it.
	 */
	public JGameStateViewer()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.weightx = 10;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(tttp, c);
		
		c = new GridBagConstraints();
		c.gridx = 10;
		c.gridy = 0;
		c.gridwidth = 20;
		c.gridheight = 10;
		c.weightx = 20;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(jtp, c);
	}


	/**
	 * This method sets the game state that we are currently
	 * viewing.
	 * @param gs the new GameState to view.
	 * @return Nothing.
	 */
	public void setGameState(TicTacToeGameState gs)
	{
		gamestate = gs;

		if (gs != null)
		{
			tttp.getXorOPanel(0).setDisplay(gs.getField()[0][0]);
			tttp.getXorOPanel(1).setDisplay(gs.getField()[0][1]);
			tttp.getXorOPanel(2).setDisplay(gs.getField()[0][2]);
			tttp.getXorOPanel(3).setDisplay(gs.getField()[1][0]);
			tttp.getXorOPanel(4).setDisplay(gs.getField()[1][1]);
			tttp.getXorOPanel(5).setDisplay(gs.getField()[1][2]);
			tttp.getXorOPanel(6).setDisplay(gs.getField()[2][0]);
			tttp.getXorOPanel(7).setDisplay(gs.getField()[2][1]);
			tttp.getXorOPanel(8).setDisplay(gs.getField()[2][2]);
		}
	}

}
