package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import modelos.Promocion;
import modelos.PromocionAbsoluta;
import modelos.PromocionAxB;
import modelos.PromocionPorcentual;
import persistence.PromocionDAO;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	public List<Promocion> findAll() {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM PROMOCIONES";
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			List<Promocion> promocion = new LinkedList<Promocion>();

			while (result.next()) {
				promocion.add(toPromocion(result));
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet result) throws SQLException {

		Promocion prom = null;

		Integer id = result.getInt("id_promociones");
		String nombre = result.getString("nombre");

		String tipo = result.getString("tipo");
		Integer descuento = result.getInt("descuento");

		if (tipo.equals("porcentual")) {
			prom = new PromocionPorcentual(id, nombre, descuento, tipo);
		}

		if (tipo.equals("absoluta")) {
			prom = new PromocionAbsoluta(id, nombre, descuento, tipo);

		}
		if (tipo.equals("axb")) {
			prom = new PromocionAxB(id, nombre, tipo);
		}
		return prom;
	}

	@Override
	public Promocion find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Promocion promocion) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO PROMOCIONES (NOMBRE, TIPO, DESCUENTO) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getTipo());
			statement.setDouble(3, promocion.getPrecioConDescuento());

			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
