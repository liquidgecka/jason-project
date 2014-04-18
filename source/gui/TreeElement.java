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

package gui;

import tictactoe.*;
import java.util.*;

public class TreeElement
{
	private int path = 0;
	private int color = 0;
	private TreeElement parent;
	private LinkedList<TreeElement> children = new LinkedList<TreeElement>();
	private TicTacToeGameState currentstate;
	private boolean lockflag = false;

	public TreeElement(TicTacToeGameState GS, TreeElement p)
	{
		currentstate = GS;
		parent = p;
	}

	public synchronized void lock()
	{
		while(lockflag)
			try { wait(); } catch (InterruptedException e) { return; }
		lockflag = true;
	}

	public synchronized void unlock()
	{
		lockflag = false;
		notify();
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int c)
	{
		color = c;
	}

	public int getPath()
	{
		return path;
	}
	
	public void setPath(int s)
	{
		path = s;
	}
	
	public LinkedList<TreeElement> getChildren()
	{
		return children;
	}
		
	public TicTacToeGameState getCurrentState()
	{
		return currentstate;
	}

	public TreeElement getParent()
	{
		return parent;
	}

	public void addChild(TreeElement te)
	{
		children.add(te);
	}
}

