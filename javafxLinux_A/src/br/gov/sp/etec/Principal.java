package br.gov.sp.etec;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principal extends Application {

	@Override
	public void start(Stage palco) throws Exception {
		VBox layout = new VBox(15);
		Button calcularSoma = new Button("Soma");

		Label descricaoCampoDeTexto = new Label("Primeiro número:");
		TextField campoDeTexto = new TextField();

		Label descricaoCampoDeTexto2 = new Label("Segundo número:");
		TextField campoDeTexto2 = new TextField();

		Label descricaoResultado = new Label("Resultado");
		TextField resultado = new TextField();

		layout.getChildren().add(descricaoCampoDeTexto);
		layout.getChildren().add(campoDeTexto);

		layout.getChildren().add(descricaoCampoDeTexto2);
		layout.getChildren().add(campoDeTexto2);

		layout.getChildren().add(descricaoResultado);
		layout.getChildren().add(resultado);

		layout.getChildren().add(calcularSoma);

		calcularSoma.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent dadosDoEvento) {
				float valor01 = Float.parseFloat(campoDeTexto.getText());
				float valor02 = Float.parseFloat(campoDeTexto2.getText());

				float resultado_ = valor01 + valor02;
				resultado.setText(String.valueOf(resultado_));
			}
		});

		Label descricaoCheckbox = new Label("Caixas de seleção");
		CheckBox laranja = new CheckBox("Laranja");
		CheckBox melao = new CheckBox("Melão");
		CheckBox manga = new CheckBox("Manga");
		CheckBox limao = new CheckBox("Limão");
		CheckBox maca = new CheckBox("Maçã");

		HBox caixasDeSelecao = new HBox();
		caixasDeSelecao.getChildren().addAll( //
				laranja, //
				melao, //
				manga, //
				limao, //
				maca//
		);

		layout.getChildren().add(caixasDeSelecao);

		Scene cena = new Scene(layout);
		palco.setScene(cena);
		palco.show();
	}

	public static void main(String args[]) {
		launch(args);
	}

}
