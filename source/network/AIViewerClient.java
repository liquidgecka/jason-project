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
			   

package network;

import java.io.*;
import java.util.*;
import java.net.*;
import tictactoe.*;

public class AIViewerClient implements TicTacToeAIListener
{
	/** Stores all of the listener objects that are watching this game. */
	private LinkedList<TicTacToeAIListener> listeners = 
	              new LinkedList<TicTacToeAIListener>();
	
	/** The socket used to accept new connections. */
	private Socket sock = null;
	
	/** The continue flag. */
	private boolean cont = true;

	private ObjectOutputStream oos;
	private InputStream is;

	public AIViewerClient(String ip, int p) throws IOException
	{
		sock = new Socket(ip, p);

		oos = new ObjectOutputStream(sock.getOutputStream());
		is = sock.getInputStream();
	}

    public void down(TicTacToeGameState s)
	{
		if (sock == null)
			return;

		try
		{
			oos.writeInt(1);
			oos.writeObject(s);
			int i = is.read();
		} catch (IOException e) {
			System.err.println("Socket communication error. 1");
			sock = null;
		}
	}
	
	public void up(int val)
	{
		if (sock == null)
			return;

		try
		{
			oos.writeInt(2);
			oos.writeObject(new Integer(val));
			int i = is.read();
		} catch (IOException e) {
			System.err.println("Socket communication error. 2");
			sock = null;
		}
	}
	
	public void scan(TicTacToeGameState gs)
	{
		if (sock == null)
			return;

		try
		{
			oos.writeInt(3);
			oos.writeObject(gs);
			int i = is.read();
		} catch (IOException e) {
			System.err.println("Socket communication error. 3");
			sock = null;
		}
	}
	
	public void done()
	{
		if (sock == null)
			return;

		try
		{
			oos.writeInt(4);
			int i = is.read();
		} catch (IOException e) {
			System.err.println("Socket communication error. 3");
			sock = null;
		}
	}
}

