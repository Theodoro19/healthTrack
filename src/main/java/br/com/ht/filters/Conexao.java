package br.com.ht.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.ht.managedbeans.Config;

public class Conexao implements Serializable{

	private static final long serialVersionUID = 1L;

	private static Config config = new Config(new ConexaoGeral().getBanco());

	private static String driver = config.Conf().getProperty("jdbc.server.driver");
	private static String user = config.Conf().getProperty("jdbc.server.user");
	private static String pass = config.Conf().getProperty("jdbc.server.password");
	private static String url = config.Conf().getProperty("jdbc.server.omacorp.url");
	

	public static Connection getConexao()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		return con;
	}

}
