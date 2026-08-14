package br.gov.sp.etec.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	private static final String URL = "jdbc:sqlite:jogo.db";

	public static Connection obter() throws SQLException {
		return DriverManager.getConnection(URL);
	}

	public static void inicializar() throws SQLException {
		try (Connection conn = obter(); Statement stmt = conn.createStatement()) {
			stmt.execute("""
					    CREATE TABLE IF NOT EXISTS entidade (
					        id TEXT PRIMARY KEY,
					        tipo TEXT NOT NULL,
					        x REAL, y REAL, tamanho REAL
					    )
					""");
		}
	}

}
