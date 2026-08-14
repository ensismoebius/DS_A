package br.gov.sp.etec;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circulo extends Entidade {
	private double raio;

	public Circulo(double x, double y, double raio) {
		super(x, y);
		this.raio = raio;
	}

	@Override
	public void desenhar(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(x, y, raio * 2, raio * 2);
	}

	@Override
	public boolean colideComChao(double alturaTela) {
		return y + (raio * 2) >= alturaTela;
	}

	@Override
	public double getAltura() {
		return raio * 2;
	}
}