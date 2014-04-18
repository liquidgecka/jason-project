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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class JTicTacToeAIViewer extends JFrame implements TicTacToeAIListener,
                                                          ActionListener
{
	private JGameStateViewer jgsv = new JGameStateViewer();
	private JTreePanel tp = new JTreePanel();
	private JPanel jbut = new JPanel(new GridLayout(1, 3));
	private JButton visual = new JButton("Visualization: ON");
	private JButton upint = new JButton("Repaint: 2");
	private JButton downint = new JButton("Repaint: 1");
	private TreeElement te = null;
	private TreeElement curte = null;
	private boolean tereset = true;
	private int repaintint = 1;
	private int repaintc = 1;
	private boolean visualization = true;

	public JTicTacToeAIViewer()
	{
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tp.addActionListener(this);
		visual.addActionListener(this);
		upint.addActionListener(this);
		downint.addActionListener(this);
		jbut.add(visual);
		jbut.add(upint);
		jbut.add(downint);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 100;
		c.gridheight = 60;
		c.weightx = 100;
		c.weighty = 60;
		c.fill = GridBagConstraints.BOTH;
		add(tp, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 60;
		c.gridwidth = 100;
		c.gridheight = 40;
		c.weightx = 100;
		c.weighty = 40;
		c.fill = GridBagConstraints.BOTH;
		add(jgsv, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 100;
		c.gridwidth = 100;
		c.gridheight = 1;
		c.weightx = 100;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		add(jbut, c);
	}

	public void down(TicTacToeGameState g)
	{
		if (te == null)
			return;
	
		te.lock();
	
		TreeElement n = new TreeElement(g, curte);
		curte.addChild(n);
		curte.setPath(curte.getChildren().size() - 1);
		curte = n;
		TreeElement walk = curte;
		int i = 1;
		
		te.unlock();

		repaintc++;
		if (repaintc % repaintint == 0 && visualization == true)
		{
			jgsv.setGameState(g);
			tp.repaint();
		}
		
	}
	
	public void done()
	{
		tp.setTreeElement(te);
		tp.repaint();
		jgsv.setGameState(null);
	}

	public void up(int i)
	{
		if (curte == null)
			return;
		
		te.lock();
		
		if (i < 0)
			curte.setColor(0);
		else if (i == 0)
			curte.setColor(1);
		else if (i > 0)
			curte.setColor(2);

		curte = curte.getParent();

		te.unlock();
		repaintc++;
		if (repaintc % repaintint == 0 && visualization == true)
		{
			tp.repaint();
		}
	}

	public void scan(TicTacToeGameState gs)
	{
		System.gc();
		
		te = new TreeElement(gs.easyClone(), null);
		curte = te;
		if (visualization == true)
			tp.setTreeElement(te);
			
		tp.repaint();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof TreeElement)
		{
			TreeElement t  = (TreeElement) e.getSource();
			jgsv.setGameState(t.getCurrentState());
			TreeElement pa = t.getParent();
			if (pa == null)
				return;

			ListIterator i = pa.getChildren().listIterator(0);
			
			int j;
			for (j = 0; i.next() != t; j++);
			pa.setPath(j);
		} else if (e.getSource() == visual) {
			visualization = !visualization;
			if (visualization)
				visual.setText("Visualization: ON");
			else
				visual.setText("Visualization: OFF");
		} else if (e.getSource() == upint) {
			repaintint = repaintint + 1;
			upint.setText("Repaint: " + (repaintint + 1));
			downint.setText("Repaint: " + (repaintint - 1));
		} else if (e.getSource() == downint) {
			repaintint = repaintint - 1;
			if (repaintint < 1)
				repaintint = 1;
			
			upint.setText("Repaint: " + (repaintint + 1));
			if (repaintint - 1 < 1)
				downint.setText("Repaint: " + (repaintint - 1));
			else
				downint.setText("Repaint: 1");
			
		}
	}
}
