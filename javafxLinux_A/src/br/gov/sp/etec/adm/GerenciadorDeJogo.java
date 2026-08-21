package br.gov.sp.etec.adm;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.etec.Circulo;
import br.gov.sp.etec.Controlavel;
import br.gov.sp.etec.Entidade;
import br.gov.sp.etec.Jogador;
import br.gov.sp.etec.Quadrado;
import br.gov.sp.etec.bd.EntidadeDAO;
import br.gov.sp.etec.bd.EntidadeDAOSQlite;

public class GerenciadorDeJogo {
	private final EntidadeDAO dao = new EntidadeDAOSQlite();
	private List<Entidade> entidades;

	public GerenciadorDeJogo(List<Entidade> l) {
		this.entidades = l;
	}

	public void carregarJogoSalvo(Entidade jogador) {
		entidades = new ArrayList<>(dao.listarTodas());

		// Se a quantidade de elementos for zero, então os cria;
		if (entidades.size() == 0) {
			// Cria alguns quadrados iniciais
			entidades.add(new Quadrado(200, 100, 40));
			entidades.add(new Quadrado(100, 100, 30));
			entidades.add(new Quadrado(300, 100, 50));

			// Cria alguns círculos iniciais
			entidades.add(new Circulo(400, 100, 40));
			entidades.add(new Circulo(500, 100, 30));
			entidades.add(new Circulo(600, 100, 25));
			
			jogador = new Jogador(400, 500, 30);
		}
	}

	public void salvarProgresso(Entidade jogador) {
		for (Entidade e : entidades) {
			dao.salvar(e);
		}
		
		dao.salvar(jogador);
	}
}
