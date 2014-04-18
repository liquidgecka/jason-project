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
 * Contains the information necessary to generate the next GameState from the
 * current GameState in a Tic Tac Toe game.
 *
 * @author Charles Paulson
 * @version 1.0
 */

public class TicTacToeMove implements Move
{
	int boardPos;

	/**
	 * Constructor:  Returns a Move object which represents a move to
	 * position (x, y).
	 *
	 * @param x The x value of the position to be moved to (range 0-2).
	 * @param y The y value of the position to be moved to (range 0-2).
	 */
	public TicTacToeMove(int x, int y) {
		boardPos = y + (x << 2);
	}

	/**
	 * Returns the X-component of the position to be moved to.
	 *
	 * @return the X-value of the position to be moved to (range 0-2).
	 */

	 public int getXpos() {
	 	return boardPos >> 2;
	 }
	
	/**
	 * Returns the X-component of the position to be moved to.
	 *
	 * @return the X-value of the position to be moved to (range 0-2).
	 */

	 public int getYpos() {
	 	return boardPos & 3;
	 }
}



