package mancala;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaGame implements ChangeListener {
	private static Font font = new Font("Arial", Font.BOLD, 18);
	private DataModel dataModel;
	private BoardPanel board;
	private JLabel msgLabel;
	private JButton nextButton;
	private JButton undoButton;
	private JLabel undoTimesLabel;
	
	public MancalaGame() {
		dataModel = new DataModel();
		dataModel.attach(this);
		board = new BoardPanel(dataModel);
		dataModel.attach(board);
		
		JPanel buttonPanel = new JPanel();
		nextButton = new JButton("Next");
		nextButton.setFont(font);
		nextButton.addActionListener(e -> {
			dataModel.nextPlayer();
		});
		undoTimesLabel = new JLabel();
		undoTimesLabel.setFont(font);
		undoButton = new JButton("Undo");
		undoButton.addActionListener(e -> {
			dataModel.undoATurn();
		});
		undoButton.setFont(font);
		buttonPanel.add(nextButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(undoTimesLabel);
		JPanel msgPanel = new JPanel();
		msgLabel = new JLabel();
		msgLabel.setFont(font);
		msgPanel.add(msgLabel);
		
		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(board, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.add(msgPanel, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		final ConfigDialog dialog = new ConfigDialog();
		dialog.attach(e -> {
			board.setFormat(dialog.getStyle());
			dataModel.init(dialog.getMarbleCount());
			frame.setVisible(true);
			update();
		});
		dialog.pack();
		dialog.setVisible(true);
	}
	
	private void update() {
		int a = dataModel.getMancalaA();
		int b = dataModel.getMancalaB();
		nextButton.setEnabled(dataModel.isTurnEnd());
		undoButton.setEnabled(dataModel.allowUndo());
		undoTimesLabel.setText(String.format("%d times left", dataModel.getUndoTimes()));
		boolean end = dataModel.checkGameEnd();
		if (end) {
			msgLabel.setText(String.format("Game End: %d vs %d, Player %s win", a, b, a > b ? "A" : "B"));
		} else if (dataModel.isTurnEnd()) {
			msgLabel.setText(String.format("Player %s's turn ends, click Next or Undo", dataModel.getIsPlayerA() ? "A" : "B"));
		} else {
			msgLabel.setText(String.format("It's player %s's turn", dataModel.getIsPlayerA() ? "A" : "B"));
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		update();
	}
}
