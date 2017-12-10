package mancala;

import java.awt.*;
import java.awt.geom.*;

public class GOBoardFormat implements BoardFormat {

	/**
	 * Returns the shape of the pit relative to this format
	 * @return the shape of the pit relative to this format
	 */
	public Shape formatPits(PitShape ps) {
		return new Ellipse2D.Double(ps.getX(), ps.getY(), ps.getWidth(), ps.getHeight());
	}

	/**
	 * Returns the background image for this format
	 * @return the image for this format
	 */
	public Image backgroundImg() {
		Image img = Toolkit.getDefaultToolkit().getImage("go_board.png");
        return img;
	}

	@Override
	public int getWidth() {
		return 1280;
	}

	@Override
	public int getMancalaLeft() {
		return 75;
	}

	@Override
	public int getMancalaTop() {
		return 100;
	}

	@Override
	public int getMancalaWidth() {
		return 100;
	}

	@Override
	public int getMancalaHeight() {
		return 260;
	}

	@Override
	public int getPitLeft() {
		return 207;
	}

	@Override
	public int getPitTop() {
		return 92;
	}

	@Override
	public int getPitWidth() {
		return 110;
	}

	@Override
	public int getPitHeight() {
		return 110;
	}

	@Override
	public int getPitBorderX() {
		return 37;
	}

	@Override
	public int getPitBorderY() {
		return 38;
	}
}

