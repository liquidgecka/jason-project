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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tictactoe.*;
import game.*;


public class TicTacToeGamePlayer extends JFrame implements ActionListener
{
	private JTicTacToePanel tttpanel = new JTicTacToePanel();
	private JButton clear = new JButton("Clear");
	private JButton p1 = new JButton("Player1: Human");
	private JButton p2 = new JButton("Player2: AI");
	private JPanel jay = new JPanel(new GridLayout(1,3));
	private TicTacToeGameState state = new TicTacToeGameState();
	private Game game = new Game(state);
	private Thread threa = null;
	private TicTacToeHumanPlayer human;
	private TicTacToeAIPlayer ai;

	private int player1 = 1;
	private int player2 = 0;
	private Player[][][] players = new Player[2][2][2];
	
	public TicTacToeGamePlayer(TicTacToeAIPlayer a, TicTacToeHumanPlayer h)
	{
		threa = new Thread(game);
		human = h;
		ai    = a;

		players[0][0][0] = players[0][0][1] =
		players[0][1][0] = players[1][0][1] = ai;

		players[0][1][1] = players[1][0][0] =
		players[1][1][0] = players[1][1][1] = human;

		game.setPlayers(players[player1][player2]);
		game.addGameListener(tttpanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(tttpanel, "Center");
		getContentPane().add(jay, "South");
		jay.add(clear);
		jay.add(p1);
		jay.add(p2);

		for (int i = 0; i < 9; i++)
			tttpanel.getXorOPanel(i).addActionListener(this);

		clear.addActionListener(this);
		p1.addActionListener(this);
		p2.addActionListener(this);
	}

	public void start()
	{
		threa.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < 9; i++)
			if (e.getSource() == tttpanel.getXorOPanel(i))
			{
				human.selected(i/3, i%3);
				return;
			}
		
		if (e.getSource() == clear)
		{
			game.setPlayers(players[player1][player2]);
			game.clear();
		}
		else if (e.getSource() == p1)
		{
			if (player1 == 0) {
				player1 = 1;
				p1.setText("Player1: Human");
			} else {
				player1 = 0;
				p1.setText("Player1: AI");
			}
		}
		else if (e.getSource() == p2)
		{
			if (player2 == 0) {
				player2 = 1;
				p2.setText("Player2: Human");
			} else {
				player2 = 0;
				p2.setText("Player2: AI");
			}
		}
	}
}


