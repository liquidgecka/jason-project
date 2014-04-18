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


public class NetworkPlayer
{
	private static TicTacToeHumanPlayer human = new TicTacToeHumanPlayer();
	private static TicTacToeAIPlayer ai       = new TicTacToeAIPlayer();
	
	public static void main(String[] args)
	{
		TicTacToeGamePlayer frame = new TicTacToeGamePlayer(ai, human);
		
		try
		{
			AIViewerClient aivc;
			if (args.length > 0)
				aivc = new AIViewerClient(args[0], 10121);
			else
				aivc = new AIViewerClient("127.0.0.1", 10121);
			
			ai.addListener(aivc);
		} catch (IOException e) {
			System.err.println("Communication error!");
			System.exit(1);
		}
		
		frame.setSize(500,600);
		frame.setVisible(true);
		
		frame.start();
		
	}
		
}
