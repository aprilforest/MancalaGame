package mancala;

import java.awt.*;
import java.awt.geom.*;

public class EasterBoardFormat implements BoardFormat {

	/**
	 * Returns the background image for this format
	 * @return the image for this format
	 */
	public Image backgroundImg() {
		Image img = Toolkit.getDefaultToolkit().getImage("easter_board.jpg");
        return img;
	}

	@Override
	public Shape formatPits(PitShape ps) {
		return new RoundRectangle2D.Double(ps.getX(), ps.getY(), ps.getWidth(), ps.getHeight(), 15, 15);
	}
	
	@Override
	public int getWidth() {
		return 1280;
	}

	@Override
	public int getMancalaLeft() {
		return 50;
	}

	@Override
	public int getMancalaTop() {
		return 130;
	}

	@Override
	public int getMancalaWidth() {
		return 110;
	}

	@Override
	public int getMancalaHeight() {
		return 200;
	}

	@Override
	public int getPitLeft() {
		return 165;
	}

	@Override
	public int getPitTop() {
		return 60;
	}

	@Override
	public int getPitWidth() {
		return 155;
	}

	@Override
	public int getPitHeight() {
		return 160;
	}

	@Override
	public int getPitBorderX() {
		return 5;
	}

	@Override
	public int getPitBorderY() {
		return 10;
	}
}


