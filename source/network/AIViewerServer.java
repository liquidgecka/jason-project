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

/**
 * Manages the server side of a AIPlayerListener. This will mimic calls to
 * an AIListener object.
 *
 * @author Brady Catherman
 * @version 1.0
 */
public class AIViewerServer implements Runnable
{
	/** Stores all of the listener objects that are watching this game. */
	private LinkedList<TicTacToeAIListener> listeners = 
	              new LinkedList<TicTacToeAIListener>();
	
	/** The socket used to accept new connections. */
	private ServerSocket sock = null;
	
	/** The continue flag. */
	private boolean cont = true;

	/**
	 * Creates an AIViewerSerer object that will listen on the given port.
	 * @param p The port to listen on.
	 * @throws IOException if its unable to open the port.
	 */
	public AIViewerServer(int p) throws IOException
	{
		sock = new ServerSocket(p);
	}

	public void addListener(TicTacToeAIListener l) {
		listeners.add(l);
	}
				

	/**
	 * Called when all the listeners need to be told to go "down"
	 * @param s The TicTacToeGameState that we have moved too.
	 * @return Nothing.
	 */
    private void down(TicTacToeGameState s)
	{
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);
				
		while (i.hasNext())
			i.next().down(s);
	}
	
	private void up(int val)
	{
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);
	
		while (i.hasNext())
			i.next().up(val);
	}
	
	private void done()
	{
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);
	
		while (i.hasNext())
			i.next().done();
	}
	
	
	private void scan(TicTacToeGameState g)
	{
		ListIterator<TicTacToeAIListener> i = listeners.listIterator(0);
	
		while (i.hasNext())
			i.next().scan(g);
	}

	public void run()
	{
		ObjectInputStream ois;
		OutputStream os;
		TicTacToeGameState gs;
	
		while (cont)
		{
			try
			{
				int op = 0;
				Socket s = sock.accept();

				ois = new ObjectInputStream(s.getInputStream());
				os = s.getOutputStream();
				while (!s.isInputShutdown())
				{
					int call = ois.readInt();
					if (call == 1)
					{
						gs = (TicTacToeGameState) ois.readObject();
						down(gs);
						os.write(1);
						os.flush();
					} else if (call == 2) {
						int i = ((Integer) ois.readObject()).intValue();
						up(i);
						os.write(2);
						os.flush();
					} else if (call == 3) {
						gs = (TicTacToeGameState) ois.readObject();
						scan(gs);
						os.write(3);
						os.flush();
					} else if (call == 4) {
						done();
						os.write(4);
						os.flush();
					} else {
						System.err.println("Unknown Error: Contact Brady.\n");
					}
				}

				s.close();
	
			} catch (IOException e) {
				System.err.println("IOException: Unknown IO Error.");
			} catch (ClassNotFoundException e) {
				System.err.println("Class version mismatch. Please Recompile.");
				System.exit(1);
			}
		}
	}

}

