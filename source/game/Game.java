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

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A Game object is a driver for a generic game.  The specifics of the game
 * are handled by the GameState and GameListeners.
 *
 * @author Charles Paulson
 * @version 1.0
 */
     
public class Game implements Runnable{
	private boolean    clearflag = false;
	private GameState  state;
	private Player[]   players;
	private LinkedList<GameListener> listeners = new LinkedList<GameListener>();
	private boolean clearing = false;
	private boolean over = false;

	/**
	 * Create a new Game.
	 *
	 * @param s The GameState which will be used for this game.  The type
	 * of game being played is determined by the nature of s.
	 */
	public Game(GameState s) {
		state = s;
	}

	/**
	 * Returns the GameState object for this game.
	 *
	 * @return The GameState for this game.
	 */
	public GameState getGameState() {
		return state;
	}
	
	/**
	 * Returns the player list for this game.
	 *
	 * @return an array of Player objects.
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	/**
	 * Sets the player list for this game.
	 *
	 * @param p An array of Players which will be playing this game.
	 * @return Nothing.
	 */
	public void setPlayers(Player[] p) {
		if (players != null)
			clear();
		players = p;
	}

	/**
	 * Add to the list of listeners needing to be informed when the state
	 * of the game changes.
	 *
	 * @param g The GameListener object to be added to the list.
	 * @return Nothing.
	 */
	public void addGameListener(GameListener g) {
		listeners.add(g);
	}

	/**
	 * Start a new game.
	 * 
	 * @return Nothing.
	 */
	public synchronized void clear() {
		
		clearflag = true;
		for (int i = 0; i < players.length; i++)
			players[i].clear();

		waitNotify();
		clearflag = false;
	}

	public void run() {
		while (true)
		{
			while (!state.isGameOver()) {
				int currentPlayer = state.nextPlayer();
				if (clearflag) {
					notifyWait();
					state.clear();
				}
				ListIterator<GameListener> i=listeners.listIterator(0);
				while (i.hasNext()) {
					i.next().gameEvent(state);
				}
	
				Move nextMove = players[currentPlayer].yourMove(state);
				if (nextMove != null) {
					state.makeMove(nextMove);
				}
			}
	
			ListIterator<GameListener> i = listeners.listIterator(0);
			while (i.hasNext()) {
				i.next().gameOver(state);
			}

			waitOnly();
			notifyWait();
			state.clear();
		}
	}

	synchronized private void waitOnly() {
		try { wait(); } catch (InterruptedException e) {}
	}

	synchronized private void waitNotify() {
		notify();
		try { wait(); } catch (InterruptedException e) {}
		notify();
	}

	synchronized private void notifyWait() {
		notify();
		try { wait(); }
		catch (InterruptedException e) { return; }
	}
}


