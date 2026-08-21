package br.gov.sp.etec.fases;

import br.gov.sp.etec.bd.FaseDAO;
import br.gov.sp.etec.bd.FaseDAOImpl;
import br.gov.sp.etec.models.Fase;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GerenciadorFases extends Stage {
	private final FaseDAO dao = new FaseDAOImpl();
	private final TableView<Fase> tabela = new TableView<>();
	private final TextField campoNome = new TextField();
	private final TextField campoDificuldade = new TextField();
	private final TextField campoGravidade = new TextField();

	public GerenciadorFases() {
		TableColumn<Fase, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Fase, Integer> colDificuldade = new TableColumn<>("Dificuldade");
		colDificuldade.setCellValueFactory(new PropertyValueFactory<>("dificuldade"));

		tabela.getColumns().addAll(colNome, colDificuldade);

		Button btnSalvar = new Button("Salvar");
		Button btnExcluir = new Button("Excluir selecionada");

		btnSalvar.setOnAction(e -> {
			Fase fase = new Fase(campoNome.getText(), Integer.parseInt(campoDificuldade.getText()),
					Double.parseDouble(campoGravidade.getText()));

			dao.salvar(fase);
			carregarTabela();
			campoNome.clear();
			campoDificuldade.clear();
			campoGravidade.clear();
		});

		btnExcluir.setOnAction(e -> {
			Fase selecionada = tabela.getSelectionModel().getSelectedItem();

			if (selecionada != null) {
				dao.excluir(selecionada.getId());
				carregarTabela();
			}
		});

		VBox root = new VBox(10, new HBox(10, campoNome, campoDificuldade, campoGravidade, btnSalvar), tabela,
				btnExcluir);

		carregarTabela();
		
		tabela.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				Fase selecionada = tabela.getSelectionModel().getSelectedItem();
				if (selecionada != null) {
					campoNome.setText(selecionada.getNome());
					campoDificuldade.setText(String.valueOf(selecionada.getDificuldade()));
					campoGravidade.setText(String.valueOf(selecionada.getGravidade()));

					btnSalvar.setText("Atualizar");
					
					btnSalvar.setOnAction(ev -> {
						selecionada.setNome(campoNome.getText());
						selecionada.setDificuldade(Integer.parseInt(campoDificuldade.getText()));
						selecionada.setGravidade(Double.parseDouble(campoGravidade.getText()));
						dao.atualizar(selecionada);
						carregarTabela();
						btnSalvar.setText("Salvar");
					});
				}
			}
		});

		this.setScene(new Scene(root, 500, 400));
		this.setTitle("Gerenciador de Fases");
		this.show();
	}

	private void carregarTabela() {
		ObservableList<Fase> dados = FXCollections.observableArrayList(dao.listarTodas());
		tabela.setItems(dados);
	}
}