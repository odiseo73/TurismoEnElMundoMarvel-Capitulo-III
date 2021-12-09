package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import modelos.Usuario;
import modelos.nullobjects.NullUser;
import persistence.UsuarioDAO;
import persistence.commons.MissingDataException;

public class UsuarioDAOImpl implements UsuarioDAO {

	
	public int update(Usuario user) {
		try {
		Connection connection = ConnectionProvider.getConnection();
		String sql = "UPDATE USUARIOS SET DINERO = ?,TIEMPODISPONIBLE = ? WHERE ID_USUARIOS = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, user.getDinero());
		statement.setInt(3, user.getId());
		//statement.setString(2, user.getNombre());
		
		statement.setDouble(2, user.getTiempoEnHoras());
		
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
			usuarios.add(toUser(result));
		}

		return usuarios;
	}catch(Exception e) {
			throw new MissingDataException(e);
		}	
	}
	

	private Usuario toUser(ResultSet result) throws SQLException {
		Integer id = result.getInt("id_usuarios");
	
		String nombre = result.getString("nombre");
		Double dinero = result.getDouble("dinero");
		Double tiempo = result.getDouble("tiempoDisponible");
		

		return new Usuario(id,nombre, dinero, tiempo);
	}


	@Override
	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE,DINERO,TIEMPODISPONIBLE) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			statement.setDouble(2, user.getDinero());
			statement.setDouble(3, user.getTiempoEnHoras());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	@Override
	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}




	public Usuario findByUsername(String username) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	@Override
	public Usuario find(Integer id) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
