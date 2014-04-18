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


import java.io.*;
import java.awt.*;
import javax.swing.*;
import tictactoe.*;
import network.*;
import gui.*;
import game.*;


public class LocalPlayer
{
	private static TicTacToeHumanPlayer human = new TicTacToeHumanPlayer();
	private static TicTacToeAIPlayer ai       = new TicTacToeAIPlayer();
	
	public static void main(String[] args)
	{
		TicTacToeGamePlayer frame = new TicTacToeGamePlayer(ai, human);
		JTicTacToeAIViewer itttv = new JTicTacToeAIViewer();
		
		frame.setTitle("TTT: Display");
		frame.setSize(500,600);
		frame.setVisible(true);
		
		itttv.setLocation(510,0);
		itttv.setTitle("AI Viewer");
		itttv.setSize(500,500);
		itttv.setVisible(true);
		
		ai.addListener(itttv);
		
		frame.start();
		
	}
		
}
