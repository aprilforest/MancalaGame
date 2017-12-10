package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardPanel extends JPanel implements ChangeListener, MouseListener {
	private DataModel data;
	private BoardFormat boardFormat;
	private PitShape[] pitInRowA;
	private PitShape[] pitInRowB;
	private PitShape mancalaA;
	private PitShape mancalaB;
	private List<PitShape> pits;

	public BoardPanel(DataModel data) {
		setPreferredSize(new Dimension(1280, 500));
		this.data = data;
		boardFormat = new GOBoardFormat();
		pitInRowA = new PitShape[6];
		pitInRowB = new PitShape[6];
		pits = new ArrayList<PitShape>();
	}

	public void setFormat(BoardFormat format) {
		boardFormat = format;
		int width = format.getWidth();
		int mancalaLeft = format.getMancalaLeft();
		int mancalaTop = format.getMancalaTop();
		int mancalaWidth = format.getMancalaWidth();
		int mancalaHeight = format.getMancalaHeight();
		int pitLeft = format.getPitLeft();
		int pitTop = format.getPitTop();
		int pitWidth = format.getPitWidth();
		int pitHeight = format.getPitHeight();
		int pitBorderX = format.getPitBorderX();
		int pitBorderY = format.getPitBorderY();
		mancalaA = new PitShape(mancalaLeft, mancalaTop, mancalaWidth, mancalaHeight);
		mancalaA.setShape(format.formatPits(mancalaA));
		mancalaB = new PitShape(width - mancalaLeft - mancalaWidth, mancalaTop, mancalaWidth, mancalaHeight);
		mancalaB.setShape(format.formatPits(mancalaB));
		pits.clear();
		pits.add(mancalaA);
		pits.add(mancalaB);
		for (int i = 0; i < 6; i++) {
			int x = pitLeft + i * (pitWidth + pitBorderX);
			int y = pitTop;
			pitInRowA[i] = new PitShape(x, y, pitWidth, pitHeight);
			pitInRowA[i].setShape(format.formatPits(pitInRowA[i]));
			pitInRowB[i] = new PitShape(x, y + pitHeight + pitBorderY, pitWidth, pitHeight);
			pitInRowB[i].setShape(format.formatPits(pitInRowB[i]));
			pits.add(pitInRowA[i]);
			pits.add(pitInRowB[i]);
		}
		addMouseListener(this);
	}
	
	public void updateGame() {
		int[] rowA = data.getRowA();
		int[] rowB = data.getRowB();
		mancalaA.setMarbles(data.getMancalaA());
		mancalaB.setMarbles(data.getMancalaB());
		for (int i = 0; i < 6; i++) {
			pitInRowA[i].setMarbles(rowA[i]);
			pitInRowB[i].setMarbles(rowB[i]);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateGame();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardFormat.backgroundImg(), 0, 0, this);
		
		g.setColor(Color.BLUE);
		Font font = new Font("Futura", Font.BOLD, 100);
		g.setFont(font);
		g.drawString("A", 55, 100);
		g.drawString("B", 1155, 100);

		Graphics2D g2 = (Graphics2D) g;

		for (PitShape p : pits) {
			p.fill(g2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		PitShape[] pits;
		int x = 0;
		if (data.getIsPlayerA()) {
			x = 0;
			pits = pitInRowA;
		} else {
			x = 1;
			pits = pitInRowB;
		}
		for (int i = 0; i < 6; i++) {
			if (pits[i].contains(e.getPoint())) {
				data.distributeStones(x, i);
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
