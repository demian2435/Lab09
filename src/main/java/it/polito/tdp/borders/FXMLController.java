
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="comboBox"
	private ComboBox<Country> comboBox; // Value injected by FXMLLoader

	@FXML // fx:id="btnVicini"
	private Button btnVicini; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		txtResult.clear();
		int anno = 0;

		try {
			anno = Integer.parseInt(txtAnno.getText());

		} catch (Exception e) {
			txtResult.appendText("Inserire una data valida compresa tra il 1816 ed il 2016");
			return;
		}

		if (anno < 1816 || anno > 2016) {
			txtResult.appendText("Inserire una data tra il 1816 ed il 2016");
			return;
		}

		Map<Country, Border> result = model.loadAllBorder(anno);

		for (Border b : result.values()) {
			txtResult.appendText(b.toString());
		}
	}

	@FXML
	void doVicini(ActionEvent event) {
		txtResult.clear();
		int anno = 0;

		try {
			anno = Integer.parseInt(txtAnno.getText());

		} catch (Exception e) {
			txtResult.appendText("Inserire una data valida compresa tra il 1816 ed il 2016");
			return;
		}

		if (anno < 1816 || anno > 2016) {
			txtResult.appendText("Inserire una data tra il 1816 ed il 2016");
			return;
		}

		Map<Country, Border> result = model.loadAllBorder(anno);

		try {
			txtResult.appendText(result.get(comboBox.getValue()).toString());
		} catch (Exception e) {
			txtResult.appendText("Nessun bordo trovato per la nazione selezionata");
			return;
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		comboBox.getItems().addAll(model.getAllCountry());
		comboBox.getSelectionModel().selectFirst();
	}
}
