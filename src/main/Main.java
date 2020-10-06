package main;

import frames.CreditsFrame;
import frames.LogFrame;
import frames.MainMenuFrame;
import frames.OptionsMenuFrame;
import frames.SelectCharacterFrame;
import frames.StartFrame;
import frames.CombatFrame;
import frames.VictoryFrame;

public class Main {

	public static void main(String[] args) {
		// x - x
		new MainMenuFrame();
		new StartFrame();
		new LogFrame();
		new CreditsFrame();
		new SelectCharacterFrame();
		new OptionsMenuFrame();
		new CombatFrame();
		new VictoryFrame();
	}
}
