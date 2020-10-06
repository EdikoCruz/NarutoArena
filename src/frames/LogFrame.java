package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogFrame implements ActionListener {

	private JScrollPane scr;
	static JFrame frame = new JFrame();
	static JTextArea tArea = new JTextArea();

	public static boolean main = false;
	public static boolean option = false;
	public static boolean select = false;
	public static boolean combat = false;
	public static boolean victory = false;

	private boolean isPlaying = false;

	public LogFrame() {

		scr = new JScrollPane(tArea);
		scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		frame.setUndecorated(true);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.add(scr);

		tArea.addKeyListener(new KeyAdapterX());
		frame.addKeyListener(new KeyAdapterX());

	}

	// Set Visible
	public static void setVisible() {

		frame.setVisible(true);
	}

	// Set Invisible
	public static void setInvisible() {
		frame.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	private class KeyAdapterX extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {

			if (frame.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					LogFrame.addLog("Saiu do log\n");
					if (main) {
						MainMenuFrame.inLog = false;
						frame.setVisible(false);
					} else if (option) {
						OptionsMenuFrame.inLog = false;
						frame.setVisible(false);
					} else if (select) {
						SelectCharacterFrame.inLog = false;
						frame.setVisible(false);
					} else if (combat) {
						CombatFrame.inLog = false;
						frame.setVisible(false);
					} else if (victory) {
						VictoryFrame.inLog = false;
						frame.setVisible(false);
					}
					main = false;
					option = false;
					select = false;
					combat = false;
					victory = false;
				}
			}

		}

		public void keyReleased(KeyEvent e) {

		}
	}

	public void nextFrame() {

	}

	public static void addLog(String add) {
		tArea.append(add);
	}

	public static String getLog() {
		return tArea.getText();
	}

}
