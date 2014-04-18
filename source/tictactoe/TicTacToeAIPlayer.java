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

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import game.*;

/**
 * Player objects wait for a game to advise them that it is their turn, then
 * select a Move and return it to the Game.  Player objects may be computer
 * controlled, or may be human controlled (e.g. through a GUI interface).
 *
 * @author Charles Paulson
 * @version 1.0
 */
     
public class TicTacToeAIPlayer implements Player {
	private LinkedList<TicTacToeAIListener> listeners = new
	        LinkedList<TicTacToeAIListener>();
	private final int infinity = 1024;
	private boolean doclear;
	private Random ran = new Random();

	/**
	 * Advise the player that it is their turn.
	 *
	 * @param s The current GameState.
	 * @return The Player's selected Move.
	 */
	public Move yourMove(GameState s) {
		TicTacToeGameState state = (TicTacToeGameState) s;
		Move[] moves             = state.validMoves();
		Move bestMove            = null;
		int  bestScore           = -infinity;
		int  player              = state.nextPlayer();

		scan(state);

		for (int i = 0; i < moves.length; i++) {
			int newScore = Min(state.evaluateMove(moves[i]),
			                   player);

			if (newScore > bestScore) {
				bestScore = newScore;
				bestMove  = moves[i];
			} else if (newScore == bestScore && ran.nextBoolean()) {
				bestMove  = moves[i];
			}

			if (doclear)
			{
				doclear = false;
				done();
				return null;
			}
		}

		done();
		return bestMove;
	}

	/**
	 * Add to the list of listeners needing to be informed as the player
	 * searches moves.
	 *
	 * @param l The TicTacToeAIListener object to be added to the list.
	 * @return Nothing.
	 */
	public void addListener(TicTacToeAIListener l) {
		listeners.add(l);
	}

	/**
	 * Returns the largest score that player can guarantee for himself,
	 * even though the current player is trying to minimize his/her score.
	 *
	 * @param s The current game state.
	 * @param player The player for whom the game is being scored.
	 * @return The best score that player should expect from this state.
	 */
	private int Min(TicTacToeGameState s, int player) {
		down(s);
		if (s.isGameOver()) {
			int value = s.gameRank(player);
			up(value);
			return value;
		}

		Move[] moves   = s.validMoves();
		int  bestScore = infinity;

		for (int i = 0; i < moves.length; i++) {
			int newScore = Max(s.evaluateMove(moves[i]), player);
			if (newScore < bestScore)
				bestScore = newScore;
			
			
			if (doclear)
			{
				return -1000;
			}
		}

		up(bestScore);
		return bestScore;
	}
	
	/**
	 * Returns the largest score that player can guarantee for himself.
	 * In this case (unlike in Min), player will choose the move from this
	 * GameState.
	 *
	 * @param s The current game state.
	 * @param player The player for whom the game is being scored.
	 * @return The best score that player should expect from this state.
	 */
	private int Max(TicTacToeGameState s, int player) {
		down(s);
		if (s.isGameOver()) {
			int value = s.gameRank(player);
			up(value);
			return value;
		}

		Move[] moves   = s.validMoves();
		int  bestScore = -infinity;

		for (int i = 0; i < moves.length; i++) {
			int newScore = Min(s.evaluateMove(moves[i]), player);
			if (newScore > bestScore)
				bestScore = newScore;
			
			
			if (doclear)
			{
				return -1000;
			}
		}

		up(bestScore);
		return bestScore;
	}

	private void down(TicTacToeGameState s) {
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);

		while (i.hasNext())
			i.next().down(s);
	}
	
	private void done() {
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);

		while (i.hasNext())
			i.next().done();
	}
	
	private void scan(TicTacToeGameState gs) {
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);

		while (i.hasNext())
			i.next().scan(gs);
	}

	private void up(int val) {
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);

		while (i.hasNext())
			i.next().up(val);
	}


	public void clear()
	{
		doclear = true;
	}
}


