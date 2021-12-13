package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelos.Atraccion;
import modelos.Promocion;
import modelos.nullobjects.NullAtraccion;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;


public class Atraccion_PromocionDAOImpl {

	private List<Atraccion> atracciones;
	private List<Promocion> promociones;

	public void setAtracciones (List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}
	 public void setPromociones (List<Promocion> promociones) {
		 this.promociones = promociones;
	 }
	 
		public int update(Promocion promocion, Atraccion atraccion) {
			try {
				Connection connection = ConnectionProvider.getConnection();
				String sql = "UPDATE ATRACCIONES_PROMOCIONES SET ID_ATRACCION = ? WHERE ID_PROMOCION = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, atraccion.getId());
			    statement.setInt(2, promocion.getId());
				

				int rows = statement.executeUpdate();
			

				return rows;

			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
		public int insert(Promocion promocion, Atraccion atraccion) {
			try {
				Connection connection = ConnectionProvider.getConnection();
				String sql = "INSERT INTO ATRACCIONES_PROMOCIONES (ID_ATRACCION, ID_PROMOCION) VALUES (?,?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, atraccion.getId());
				statement.setInt(2, promocion.getId());
				

				int rows = statement.executeUpdate();

				return rows;

			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
		
		public int deletePromotion(Promocion promocion) {
			try {
				Connection connection = ConnectionProvider.getConnection();
				String sql = "DELETE FROM ATRACCIONES_PROMOCIONES WHERE ID_PROMOCIONES = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, promocion.getId());
				

				int rows = statement.executeUpdate();

				return rows;

			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
		public Integer find(Integer id_promocion) {
			try {
				String sql = "SELECT * FROM ATRACCIONES_PROMOCIONES WHERE ID_ATRACCION = ?";
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id_promocion);
				ResultSet resultados = statement.executeQuery();

				Integer id_atraccion = resultados.getInt("id_atraccion");

			return id_atraccion;
			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
	public List<Promocion> findAll() throws SQLException {

		Connection connection = ConnectionProvider.getConnection();
		String sql = "SELECT * FROM atracciones_promociones";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			agregarAtraccion(result);
		}
		return promociones;
	}
	private void agregarAtraccion(ResultSet result) throws SQLException {

		Integer id_atraccion = result.getInt("id_atraccion");
		Integer id_promocion = result.getInt("id_promocion");

		for (Atraccion atraccion : atracciones) {
			if (atraccion.getId().equals(id_atraccion)) {
				for (Promocion promocion : promociones) {
					if (promocion.getId().equals(id_promocion)) {
						promocion.agregarAtraccion(atraccion);
					}
				}
			}
		}
	}
	

}
