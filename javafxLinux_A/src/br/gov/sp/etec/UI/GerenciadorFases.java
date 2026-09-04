package br.gov.sp.etec.UI;

import br.gov.sp.etec.bd.FaseDAO;
import br.gov.sp.etec.bd.FaseDAOImpl;
import br.gov.sp.etec.models.Fase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

		VBox root = new VBox(10, new HBox(10, campoNome, campoDificuldade, campoGravidade, btnSalvar), tabela,
				btnExcluir);

		carregarTabela();
		
		this.setScene(new Scene(root));
	}

	private void carregarTabela() {
		ObservableList<Fase> dados = FXCollections.observableArrayList(dao.listarTodas());
		tabela.setItems(dados);
	}

}
