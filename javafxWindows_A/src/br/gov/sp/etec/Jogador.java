package br.gov.sp.etec;

public class Jogador extends Entidade implements Controlavel {

	private double tamanho;
	private static final double VELOCIDADE = 5;
	private static final double FORCA_PULO = -10;

	public Jogador(double x, double y, double tamanho) {
		super(x, y);
		this.tamanho = tamanho;
	}

	@Override
	public void desenhar(javafx.scene.canvas.GraphicsContext gc) {
		gc.setFill(javafx.scene.paint.Color.GREEN);
		gc.fillRect(x, y, tamanho, tamanho);
	}

	@Override
	public boolean colideComChao(double alturaTela) {
		return y + tamanho >= alturaTela;
	}

	@Override
	public void moverEsquerda() {
		vx = -VELOCIDADE;
	}

	@Override
	public void moverDireita() {
		vx = VELOCIDADE;
	}

	@Override
	public void pular() {
		if (colideComChao(600))
			vy = FORCA_PULO;
	}

	@Override
	public double getAltura() {
		return this.tamanho;
	}
}
