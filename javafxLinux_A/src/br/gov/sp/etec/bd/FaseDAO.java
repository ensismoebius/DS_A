package br.gov.sp.etec.bd;

import java.util.List;

import br.gov.sp.etec.models.Fase;

public interface FaseDAO {
	void excluir(int id);
	void salvar(Fase fase);
	void atualizar(Fase fase);
	List<Fase> listarTodas();
}