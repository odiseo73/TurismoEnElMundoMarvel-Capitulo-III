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
import modelos.nullobjects.NullPromocion;
import persistence.PromocionDAO;
import persistence.commons.MissingDataException;
import utils.PromotionSelector;

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

		

		Integer id = result.getInt("id_promociones");
		String nombre = result.getString("nombre");

		String tipo = result.getString("tipo");
		Integer descuento = result.getInt("descuento");

		Promocion prom = PromotionSelector.clasificarPromocionConId(id, nombre, tipo, descuento);
		return prom;
	}

	@Override
	public Promocion find(Integer id) {
		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE ID_PROMOCIONES = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = NullPromocion.build();

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
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
	public int update(Promocion promocion) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE PROMOCIONES SET NOMBRE = ?, TIPO = ?, DESCUENTO = ? WHERE ID_PROMOCIONES = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getTipo());
			statement.setDouble(3, promocion.getPrecioConDescuento());
			statement.setInt(4, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE ID_PROMOCIONES = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
