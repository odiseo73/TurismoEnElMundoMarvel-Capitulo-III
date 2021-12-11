package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import modelos.Usuario;
import modelos.nullobjects.NullUsuario;
import persistence.UsuarioDAO;
import persistence.commons.MissingDataException;

public class UsuarioDAOImpl implements UsuarioDAO {

	
	public int update(Usuario user) {
		try {
		Connection connection = ConnectionProvider.getConnection();
		String sql = "UPDATE USUARIOS SET USERNAME = ?, PASSWORD =?, DINERO = ?,TIEMPODISPONIBLE = ?, ADMIN = ? WHERE ID_USUARIOS = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setDouble(3, user.getDinero());
		statement.setDouble(4, user.getTiempoEnHoras());
		statement.setBoolean(5, user.esAdmin());
		statement.setInt(6, user.getId());

		
		int rows = statement.executeUpdate();

		return rows;
		}catch(Exception e) {
			throw new MissingDataException(e);
		}		
	}
	
	
	
	public List<Usuario> findAll(){
try {
		Connection connection = ConnectionProvider.getConnection();
		String sql = "SELECT * FROM USUARIOS";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Usuario> usuarios = new LinkedList<Usuario>();

		while (result.next()) {
			usuarios.add(toUsuario(result));
		}

		return usuarios;
	}catch(Exception e) {
			throw new MissingDataException(e);
		}	
	}
	

	private Usuario toUsuario(ResultSet result) throws SQLException {
		Integer id = result.getInt("id_usuarios");
	
		String username = result.getString("username");
		String password = result.getString("password");
		Double dinero = result.getDouble("dinero");
		Double tiempo = result.getDouble("tiempoDisponible");
		Boolean admin = result.getBoolean("admin");
		

		return new Usuario(id,username,password, dinero, tiempo, admin);
	}


	@Override
	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO USUARIOS (USERNAME,PASSWORD,DINERO,TIEMPODISPONIBLE,ADMIN) VALUES (?,?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setDouble(3, user.getDinero());
			statement.setDouble(4, user.getTiempoEnHoras());
			statement.setBoolean(4, user.esAdmin());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	@Override
	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM USUARIOS WHERE ID_USUARIOS = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}




	public Usuario findByUsername(String username) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUsuario.build();

			if (resultados.next()) {
				user = toUsuario(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	@Override
	public Usuario find(Integer id) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE ID_USUARIOS = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUsuario.build();

			if (resultados.next()) {
				user = toUsuario(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
