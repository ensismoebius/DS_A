package br.gov.sp.etec.adm;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.etec.Entidade;
import br.gov.sp.etec.bd.EntidadeDAO;
import br.gov.sp.etec.bd.EntidadeDAOSQlite;

public class GerenciadorDeJogo {
	private final EntidadeDAO dao = new EntidadeDAOSQlite();
	private List<Entidade> entidades;

	public void carregarJogoSalvo() {
		entidades = new ArrayList<>(dao.listarTodas());
	}

	public void salvarProgresso() {
		for (Entidade e : entidades) {
			dao.salvar(e);
		}
	}
}
