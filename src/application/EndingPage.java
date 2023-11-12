package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import logic.GameController;
import logic.util.IDUtil;

/**
 * 
 * This class represent the ending page
 *
 */
public class EndingPage {

	/**
	 * A field represents the Text for the end game text
	 */
	@FXML
	private Text endGameText;

	/**
	 * A initialize function when the page has been opened
	 */
	@FXML
	private void initialize() {
		if (GameController.getInstance().isMoneyPassEndGame()) {
			endGameText.setText("Through your unwavering dedication and hard work, "
					+ "you manage to gather the required 3000 units of money within the given 25 days. "
					+ "The bank representative arrives on the final day to evaluate your progress, and with joy in their eyes, "
					+ "they inform you that you have successfully repaid your debts. Your farm remains under your ownership, "
					+ "and the community celebrates your victory.");
		} else {
			endGameText.setText(
					"Despite your tireless efforts, you fall short of gathering the 3000 units of money within the given timeframe. "
							+ "The bank representative arrives on the final day, and with a heavy heart, they inform you that your farm will be taken away.");
		}
	}

	/**
	 * A event function for the button to switch the scene
	 * 
	 * @param event event from action event
	 */
	@FXML
	private void switchSceneEventHandler(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button clickedButton = (Button) event.getSource();
			String buttonId = IDUtil.getSelectedSceneId(clickedButton.getId());
			Main.switchScene(buttonId + ".fxml");
		}
	}
}
