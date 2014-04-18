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

package game;

/**
 * GameState objects store everything needed for an AI to interface with
 * with this game.
 *
 * @author Brady Catherman
 * @version 1.0
 */
public interface GameState
{
	//protected Game gameparent;

	/**
	 * This sets the Game object that is controlling the current GameState
	 * object.
	 *
	 * @param G the Game that is controllnig this Game State.
	 * @return Nothing.
	 */
	//public void setGame(Game G)
	//{
		//gameparent = G;
	//}

	/**
	 * Returns true if this game has ended.
	 *
	 * @return True if the game has ended.
	 */
	public abstract boolean isGameOver();

	/**
	 * Performs the given move on this field. This will perform checking and
	 * alter the state of the existing game. If the move isn't valid then
	 * the state of the game may not be valid. 
	 *
	 * @param m the move that is being performed.
	 * @return Nothing.
	 */
	public abstract void makeMove(Move m);

	/**
	 * Evaluates the given move and returns a new GameState object that 
	 * reflects the changes. This is used for AI evaluation. The existing
	 * GameState is uneffected by this call. If the move is not valid
	 * then the state of the game may not be valid.
	 *
	 * @param m the move the be performed.
	 * @return The new GameState
	 */
	public abstract GameState evaluateMove(Move m);

	/**
	 * This returns the number of the next player. It is assumed that the
	 * return value is correct and not out of bounds or negative. If an
	 * invalid number is returned then the stability of the Game object
	 * is not insured.
	 *
	 * @return The number of the next player.
	 */
	public abstract int nextPlayer();

	/**
	 * Evaluates a field and returns a numeric score. This score will be
	 * compared against other GameState objects of this same type. Higher
	 * scores are to be considered better, lower are worse.
	 *
	 * @param player The player whom the score is relative to.
	 * @return a numeric score that ranks this game.
	 */
	public abstract int gameRank(int player);

	/**
	 * Returns a list of all of the valid moves for this field. This is used
	 * by the AI function to loop through and search for the best move that
	 * can be made.
	 *
	 * @return An array of Move objects.
	 */
	public abstract Move[] validMoves();

	/**
	 * Clears the game state and returns it back to the starting condition.
	 * 
	 * @return Nothing.
	 */
	public abstract void clear();
}

