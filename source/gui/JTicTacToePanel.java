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
import tictactoe.TicTacToeGameState;
import game.GameListener;
import game.GameState;

/**
 * This class is used to display a game of Tic-Tac-Toe. This contains all of
 * the sub-classes needs to form a single pane. If you want to listen to
 * each X/O object then you will need to add an action listener to them
 * using the getXorOPanel() method.
 *
 * @author Brady Catherman
 * @version 1.0
 */
public class JTicTacToePanel extends JPanel implements GameListener
{
	/** Used to store the JXorOPanel objects. */
	private JXorOPanel[] xos = new JXorOPanel[9];

	/**
	 * Adds a row that contains X/O objects to the content pane.
	 * @param y The y coordinate to start this row at.
	 * @param p1 The index into the xos array for the first element.
	 * @param p2 The index into the xos array for the second element.
	 * @param p3 The index into the xos array for the third element.
	 * @return Nothing.
	 */
	private void addrow1(int y, int p1, int p2, int p3)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = y;
		c.gridwidth = 5;
		c.gridheight = 40;
		c.weightx = 5;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, false), c);
		
		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = y;
		c.gridwidth = 40;
		c.gridheight = 40;
		c.weightx = 40;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(xos[p1], c);
		
		c = new GridBagConstraints();
		c.gridx = 45;
		c.gridy = y;
		c.gridwidth = 10;
		c.gridheight = 40;
		c.weightx = 10;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, true), c);
		
		c = new GridBagConstraints();
		c.gridx = 55;
		c.gridy = y;
		c.gridwidth = 40;
		c.gridheight = 40;
		c.weightx = 40;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(xos[p2], c);
		
		c = new GridBagConstraints();
		c.gridx = 95;
		c.gridy = y;
		c.gridwidth = 10;
		c.gridheight = 40;
		c.weightx = 10;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, true), c);
		
		c = new GridBagConstraints();
		c.gridx = 105;
		c.gridy = y;
		c.gridwidth = 40;
		c.gridheight = 40;
		c.weightx = 40;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(xos[p3], c);
		
		c = new GridBagConstraints();
		c.gridx = 145;
		c.gridy = y;
		c.gridwidth = 5;
		c.gridheight = 40;
		c.weightx = 5;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, false), c);
	}

	/**
	 * Adds a row that contains just the lines to the content pane.
	 * @param y The starting y coordinate.
	 * @return Nothing.
	 */
	private void addrow2(int y)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = y;
		c.gridwidth = 5;
		c.gridheight = 10;
		c.weightx = 5;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, false), c);
		
		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = y;
		c.gridwidth = 40;
		c.gridheight = 10;
		c.weightx = 40;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(true, false), c);
		
		c = new GridBagConstraints();
		c.gridx = 45;
		c.gridy = y;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.weightx = 10;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(true, true), c);
		
		c = new GridBagConstraints();
		c.gridx = 55;
		c.gridy = y;
		c.gridwidth = 40;
		c.gridheight = 10;
		c.weightx = 40;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(true, false), c);
		
		c = new GridBagConstraints();
		c.gridx = 95;
		c.gridy = y;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.weightx = 10;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(true, true), c);
		
		c = new GridBagConstraints();
		c.gridx = 105;
		c.gridy = y;
		c.gridwidth = 40;
		c.gridheight = 10;
		c.weightx = 45;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(true, false), c);
		
		c = new GridBagConstraints();
		c.gridx = 145;
		c.gridy = y;
		c.gridwidth = 5;
		c.gridheight = 10;
		c.weightx = 5;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, false), c);
	}

	
	/**
	 * This creates a TicTacToePanel() that can be used.
	 */
	public JTicTacToePanel()
	{
		for (int i = 0; i < 9; i++)
			xos[i] = new JXorOPanel();

		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 150;
		c.gridheight = 5;
		c.weightx = 150;
		c.weighty = 5;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, false), c);
		
		addrow1(5, 0, 1, 2);
		addrow2(45);
		addrow1(55, 3, 4, 5);
		addrow2(95);
		addrow1(105, 6, 7, 8);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 145;
		c.gridwidth = 150;
		c.gridheight = 5;
		c.weightx = 150;
		c.weighty = 5;
		c.fill = GridBagConstraints.BOTH;
		add(new JLinePanel(false, false), c);
	}

	public void gameEvent(GameState s)
	{
		TicTacToeGameState state = (TicTacToeGameState) s;

		int[][] field = state.getField();
		int k;

		for (int i = k = 0; i < 3; i++)
			for (int j = 0; j < 3; j++, k++)
				xos[k].setDisplay(field[i][j]);
	}

	public void gameOver(GameState s)
	{
		TicTacToeGameState state = (TicTacToeGameState) s;

		boolean[][] winField = state.getWinField();
		int    [][]    field = state.getField();
		int k;

		for (int i = k = 0; i < 3; i++)
			for (int j = 0; j < 3; j++, k++)
			{
				int value = field[i][j];
				if (winField[i][j])
					value += 2;
				xos[k].setDisplay(value);
			}
	}

	/**
	 * Returns the given XorOPanel object.
	 * @param x This is an int between 0 and 8.
	 * @return Nothing.
	 */
	public JXorOPanel getXorOPanel(int x)
	{
		if (x < 0 || x > 8)
			return null;
			
		return xos[x];
	}
	
}
