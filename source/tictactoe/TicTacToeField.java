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
import java.io.*;

/**
 * Handles all the information related to a game of Tic Tac Toe.
 *
 * @author Charles Paulson
 * @version 1.0
 */
public class TicTacToeField implements Serializable
{
	private static TicTacToeField root;
	private static boolean        inverted;

	private int fieldMask;
	private Field parent;
	private LinkedList<TicTacToeField> children =
	        new LinkedList<TicTacToeField>();

	/**
	 * Create the tree when the class is loaded.
	 */
	{
		root = createTree();
	}

	/**
	 * Constructor
	 */
	private TicTacToeField(TicTacToeField papa, field) {
		parent    = papa;
		fieldMask = field;
	}

	private TicTacToeField createTree() {
		
	}


	private boolean endGame;
	private     int currentPlayer;
	private     int emptySquares;
	private     int winMask;

	/**
	 * Creates a GameState for a TicTacToe game, and sets the starting
	 * player to player 0.
	 */
	public TicTacToeField()
	{
		currentPlayer = 0;
		clear();
	}
	
	/**
	 * Returns true if this game has ended.
	 *
	 * @return True if the game has ended.
	 */
	public boolean isGameOver()
	{
		return endGame;
	}

	/**
	 * Performs the given move on this field. This will perform checking and
	 * alter the state of the existing game. If the move isn't valid then
	 * the state of the game may not be valid. 
	 *
	 * @param m the move that is being performed.
	 * @return Nothing.
	 */
	public void makeMove(Move m)
	{
		TicTacToeMove t = (TicTacToeMove) m;

		int x = t.getXpos();
		int y = t.getYpos();
		int boardOffset = 3 * x + y;
		int bitOffset = (boardOffset << 1) + currentPlayer;

		emptySquares--;
		board |= 1 << bitOffset;
		generateWinMask();
		currentPlayer = 1 - currentPlayer;

		endGame = (0 == emptySquares) || (winMask != 0);
	}

	/**
	 * Sets the winMask to reflect which board positions are part of the
	 * win.
	 */
	private void generateWinMask()
	{
		final int DIAGONAL1  = 65793;
		final int DIAGONAL2  = 4368;
		final int VERTICAL   = 4161;
		final int HORIZONTAL = 21;
		int tempboard = ~board >> currentPlayer;

		if (0 == (tempboard & DIAGONAL1))
			winMask |= DIAGONAL1;

		if (0 == (tempboard & DIAGONAL2))
			winMask |= DIAGONAL2;
		
		int vertical   = VERTICAL;
		int horizontal = HORIZONTAL;
		for (int i = 0; i < 3; i++)
		{
			if (0 == (tempboard & vertical))
				winMask |= vertical;

			if (0 == (tempboard & horizontal))
				winMask |= horizontal;

			vertical   <<= 2;
			horizontal <<= 6;
		}
	}

	/**
	 * Evaluates the given move and returns a new GameState object that 
	 * reflects the changes. This is used for AI evaluation. The existing
	 * GameState is uneffected by this call. If the move is not valid
	 * then the state of the game may not be valid.
	 *
	 * @param m the move the be performed.
	 * @return The new GameState
	 */
	public TicTacToeGameState evaluateMove(Move m)
	{
		TicTacToeGameState nextState = easyClone();

		nextState.makeMove(m);

		return nextState;
	}

	/**
	 * Creates a clone without having to worry about cloning exceptions.
	 *
	 * @return A new TicTacToeGameState which is identical to the current
	 * state.
	 */
	public TicTacToeGameState easyClone() {
		TicTacToeGameState iThinkImACloneNow = new TicTacToeGameState();

		iThinkImACloneNow.endGame       = endGame;
		iThinkImACloneNow.currentPlayer = currentPlayer;
		iThinkImACloneNow.emptySquares  = emptySquares;
		iThinkImACloneNow.winMask       = winMask;
		iThinkImACloneNow.board         = board;

		return iThinkImACloneNow;
	}

	/**
	 * This returns the number of the next player. It is assumed that the
	 * return value is correct and not out of bounds or negative. If an
	 * invalid number is returned then the stability of the Game object
	 * is not insured.
	 *
	 * @return The number of the next player.
	 */
	public int nextPlayer()
	{
		return currentPlayer;
	}

	/**
	 * Evaluates a field and returns a numeric score. This score will be
	 * compared against other GameState objects of this same type. Higher
	 * scores are to be considered better, lower are worse.
	 *
	 * @return a numeric score that ranks this game.
	 */
	public int gameRank(int player)
	{
		if (0 == winMask)
			return 0;	// No Winner.

		int value = 1 + emptySquares;
		return (player==currentPlayer) ? -value : value;
	}

	/**
	 * Returns a list of all of the valid moves for this field. This is used
	 * by the AI function to loop through and search for the best move that
	 * can be made.
	 *
	 * @return An array of Move objects.
	 */
	public Move[] validMoves()
	{
		Move[] moves = new Move[emptySquares];

		int mask = 3;
		int pos  = 0;
		for (int i = 0; i < emptySquares; i++, pos++, mask<<=2)
		{
			while ((board & mask) != 0)
			{
				mask <<= 2;
				pos++;
			}

			moves[i] = new TicTacToeMove(pos / 3, pos % 3);
		}

		return moves;
	}

	/**
	 * Returns true if and only if the given move is valid.
	 *
	 * @param m The move to test for validity.
	 * @return True if m is a valid move.  False otherwise.
	 */
	public boolean isValidMove(TicTacToeMove m)
	{
		int x = m.getXpos();
		int y = m.getYpos();
		int bitOffset = (3 * x + y) << 1;

		return (0 == (board & (3 << bitOffset)));
	}

	/**
	 * Clears the game state and returns it back to the starting condition.
	 * 
	 * @return Nothing.
	 */
	public void clear()
	{
		emptySquares  = 9;
		board         = 0;
		winMask       = 0;
		endGame       = false;
	}


	/**
	 * Returns the Field that is currently in use. The player number (1/2)
	 * represents an X or an O, 0 represents an empty field.
	 *
	 * @return an Array representing this Field.
	 */
	public int[][] getField()
	{
		int[][] field = new int[3][3];

		int tempboard = board;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
			{
				field[i][j] = tempboard & 3;
				tempboard >>= 2;
			}

		return field;
	}

	/**
	 * Returns a boolean array of the winning positions on the field. This
	 * is used by the display to show where the winning pieces are.
	 *
	 * @return a boolean array representing for each board position,
	 * whether it should be highlighted when displaying the winning
	 * positions.
	 */
	public boolean[][] getWinField()
	{
		boolean[][] wins = new boolean[3][3];

		int mask = 1;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++, mask <<= 2)
				wins[i][j] = (winMask & mask) != 0;

		return wins;
	}
}

