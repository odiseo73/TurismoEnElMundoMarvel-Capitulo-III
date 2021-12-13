package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import modelos.Atraccion;
import modelos.nullobjects.NullAtraccion;

import persistence.AtraccionDAO;

import persistence.commons.MissingDataException;


public class AtraccionDAOImpl implements AtraccionDAO {

	public int update(Atraccion atraccion) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE ATRACCIONES SET NOMBRE = ?, PRECIO = ?, CUPODISPONIBLE = ?, TIEMPOENHORAS = ? WHERE ID_ATRACCIONES = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getPrecio());
			statement.setDouble(3, atraccion.getCupoDisponible());
			statement.setDouble(4, atraccion.getTiempoEnHoras());
			statement.setInt(5, atraccion.getId());

			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Atraccion> findAll() {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM atracciones ORDER BY precio DESC, tiempoEnHoras DESC";
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			List<Atraccion> atracciones = new LinkedList<Atraccion>();

			while (result.next()) {
				atracciones.add(toAtraccion(result));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet result) throws SQLException {
		Integer id = result.getInt("id_atracciones");

		String nombre = result.getString("nombre");
		Double precio = result.getDouble("precio");
		Integer cupo = result.getInt("cupoDisponible");
		Double tiempo = result.getDouble("tiempoEnHoras");

		return new Atraccion(id, nombre, precio, tiempo, cupo);
	}

	@Override
	public int insert(Atraccion atraccion) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO ATRACCIONES (NOMBRE, PRECIO, CUPODISPONIBLE, TIEMPOENHORAS) VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getPrecio());
			statement.setDouble(3, atraccion.getCupoDisponible());
			statement.setDouble(4, atraccion.getTiempoEnHoras());
			
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM ATRACCIONES WHERE ID_ATRACCIONES = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion find(Integer id) {
		try {
			String sql = "SELECT * FROM ATRACCIONES WHERE ID_ATRACCIONES = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = NullAtraccion.build();

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
