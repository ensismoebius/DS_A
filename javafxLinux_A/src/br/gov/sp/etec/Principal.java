package br.gov.sp.etec;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Principal extends Application {

	private List<Entidade> entidades = new ArrayList<>();

	private Pane root = new Pane();

	@Override
	public void start(Stage palco) throws Exception {
		Canvas canvas = new Canvas(800, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Cria alguns quadrados iniciais
		entidades.add(new Quadrado(200, 100, 40));
		entidades.add(new Quadrado(100, 100, 30));
		entidades.add(new Quadrado(300, 100, 50));

		// Cria alguns círculos iniciais
		entidades.add(new Circulo(400, 100, 40));
		entidades.add(new Circulo(500, 100, 30));
		entidades.add(new Circulo(600, 100, 25));

		Jogador jogador = new Jogador(400, 500, 30);

		// Cria as views (Rectangle do JavaFX) para cada quadrado
		for (Entidade e : entidades) {
			// BUG da aula anterior: Atualiza a posição da entidade
			e.atualizar(600);
			e.desenhar(gc);
			
			jogador.atualizar(600);
			jogador.desenhar(gc);
		}

		// BUG da aula anterior resolvido: Canvas nunca era adicionado ao root, nada
		// aparecia na tela
		root.getChildren().add(canvas);

		Scene scene = new Scene(root, 800, 600);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == javafx.scene.input.KeyCode.SPACE) {
				for (Entidade q : entidades) {
					q.vy = -12;
				}
			}
			
			processarEntrada(jogador, e);

		});

		root.setOnMouseClicked(e -> {
			Quadrado novo = new Quadrado(e.getX(), e.getY(), 30);
			// BUG da aula anterior resolvido: criava um Rectangle estático extra em vez de
			// colorir a própria entidade animada
			novo.setCor(Color.hsb(Math.random() * 360, 0.7, 0.9));
			entidades.add(novo);
			System.out.println("Total: " + Quadrado.getTotalQuadrados());
		});

		palco.setScene(scene);
		palco.setTitle("POO Avançada - Quadrados com Gravidade Static");
		palco.show();

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				gc.clearRect(0, 0, 800, 600);
				for (Entidade e : entidades) {
					e.atualizar(600);
					e.desenhar(gc);
				}
				
				jogador.atualizar(600);
				jogador.desenhar(gc);
			}
		}.start();
	}

	private void processarEntrada(Controlavel objeto, KeyEvent e) {
		switch (e.getCode()) {
			case LEFT -> objeto.moverEsquerda();
			case RIGHT -> objeto.moverDireita();
			case SPACE -> objeto.pular();
			default -> {}
		}
	}

	public static void main(String args[]) {
		launch(args);
	}
}
