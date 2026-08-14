package br.gov.sp.etec;

import java.util.UUID;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entidade {
	
	public String id; 
	
	public double x, y, vx, vy;

	public static double GRAVIDADE = 0.5;
	public static double VENTO = 0.01;

	public Entidade(double x, double y) {
		this.id = UUID.randomUUID().toString();
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
	}

    public void atualizar(double alturaTela) {
        vy += Quadrado.GRAVIDADE;
        vx += Quadrado.VENTO;
        y += vy;
        x += vx;

        // BUG da aula anterior resolvido: colideComChao() nunca era chamado, formas caíam para sempre e "sumiam" da tela
        if (colideComChao(alturaTela)) {
            y = alturaTela - getAltura();
            vy = -vy * 0.7;
        }
    }

	public abstract void desenhar(GraphicsContext gc);

	public abstract boolean colideComChao(double alturaTela);

	public abstract double getAltura();
}
