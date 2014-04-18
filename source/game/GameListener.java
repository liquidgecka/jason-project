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
 * The GameListener interface allows a Game to send an updated GameState to
 * an interested party (most likely some sort of graphical display or GUI).
 *
 * @author Charles Paulson
 * @version 1.0
 */
     
public interface GameListener {
	/**
	 * Notifies the listener that the state of the game has changed.
	 *
	 * @param s The new state of the game.
	 */
	public void gameEvent(GameState s);

	/**
	 * Notifies the listener that the game has ended.
	 *
	 * @param s The final state of the game.
	 */
	public void gameOver(GameState s);
}


