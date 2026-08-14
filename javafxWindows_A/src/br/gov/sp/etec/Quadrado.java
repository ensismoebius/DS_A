package br.gov.sp.etec;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Quadrado extends Entidade {
	double tamanho;

	// BUG da aula anterior resolvido: quadrado sempre era desenhado azul fixo; agora tem cor própria
	private Color cor = Color.BLUE;
	private static int totalQuadrados = 0;

	public Quadrado() {
		this(400, 300, 30, 0, 0);
	}

	public Quadrado(double x, double y, double tamanho) {
		this(x, y, tamanho, 0, 0);
	}

	public Quadrado(double x, double y, double tamanho, double vx, double vy) {
		super(x, y);
		this.tamanho = tamanho;
		this.vx = vx;
		this.vy = vy;
		Quadrado.totalQuadrados++;
	}

	public static int getTotalQuadrados() {
		return totalQuadrados;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	@Override
	public void desenhar(GraphicsContext gc) {
		gc.setFill(cor);
		gc.fillRect(x, y, tamanho, tamanho);
	}

	@Override
	public boolean colideComChao(double alturaTela) {
		return y + tamanho >= alturaTela;
	}

	@Override
	public double getAltura() {
		return tamanho;
	}
}











