package br.gov.sp.etec.bd;

import java.util.List;

import br.gov.sp.etec.Entidade;

public interface EntidadeDAO {
	void salvar(Entidade e);

	List<Entidade> listarTodas();

	void remover(String id);
}
