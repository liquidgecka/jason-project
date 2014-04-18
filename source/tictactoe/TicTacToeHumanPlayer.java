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

package tictactoe;

import game.*;

/**
 * Player objects wait for a game to advise them that it is their turn, then
 * select a Move and return it to the Game.  In this case, the Player object
 * listens to a GUI interface form human-selected moves.
 *
 * @author Charles Paulson
 * @version 1.0
 */
     
public class TicTacToeHumanPlayer implements Player 
{
	private boolean            myTurn = false;
	private boolean            dont_wait = false;
	private TicTacToeGameState state;
	private Move               myMove;

	/**
	 * Advise the player that it is their turn.
	 *
	 * @param s The current GameState.
	 * @return The Player's selected Move.
	 */
	synchronized public Move yourMove(GameState s)
	{
		state  = (TicTacToeGameState) s;
		myTurn = true;

		try {
	// System.err.println("This is a really long line");
			if (!dont_wait)
				wait();
			dont_wait = false;
	// System.err.println("Woo hoo, It's a loaf of bread");
		}
		catch (java.lang.InterruptedException e) {}

		myTurn = false;
		return myMove;
	}

	/**
	 * This method is called when a player selects a board position.
	 * If it is not the player's turn, the selection is ignored.
	 * Otherwise, the move is validated, and (if valid) arrangements will
	 * be made to send it back to the waiting GameState.
	 *
	 * @param x The x-coordinate of the selection in the playing field.
	 * @param y The y-coordinate of the selection in the playing field.
	 */
	synchronized public void selected(int x, int y)
	{
		if (!myTurn)
			return;

		TicTacToeMove move = new TicTacToeMove(x, y);

		if (state.isValidMove(move))
		{
			myMove = move;
			notify();
		}
	}

	/**
	 * Clears this players move if a clear() function call is made to the
	 * Game object.
	 *
	 * @return Nothing.
	 */
	synchronized public void clear()
	{
		myMove = null;
		dont_wait = true;
		notify();
	}
	 
}


