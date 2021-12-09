package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modelos.Atraccion;
import modelos.Promocion;
import persistence.commons.ConnectionProvider;


public class Atraccion_PromocionDAOImpl {

	private List<Atraccion> atracciones;
	private List<Promocion> promociones;

	public void setAtracciones (List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}
	 public void setPromociones (List<Promocion> promociones) {
		 this.promociones = promociones;
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

		Integer id_atracciones = result.getInt("id_atracciones");
		Integer id_promociones = result.getInt("id_promociones");

		for (Atraccion atraccion : atracciones) {
			if (atraccion.getId().equals(id_atracciones)) {
				for (Promocion promocion : promociones) {
					if (promocion.getId().equals(id_promociones)) {
						promocion.agregarAtraccion(atraccion);
					}
				}
			}
		}
	}

}
