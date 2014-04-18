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
 * Player objects wait for a game to advise them that it is their turn, then
 * select a Move and return it to the Game.  Player objects may be computer
 * controlled, or may be human controlled (e.g. through a GUI interface).
 *
 * @author Charles Paulson
 * @version 1.0
 */
     
public interface Player {

	/**
	 * Advise the player that it is their turn.
	 *
	 * @param s The current GameState.
	 * @return The Player's selected Move.
	 */
	public Move yourMove(GameState s);

	/**
	 * This is called when the game is cleared. If it is currently the players
	 * turn then the next move is ignored.
	 *
	 * @return Nothing.
	 */
	public void clear();
}


