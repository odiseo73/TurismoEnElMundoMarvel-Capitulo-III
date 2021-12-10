package services;

import java.util.List;

import modelos.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create(String nombre, Double cost, Double duration, Integer capacity) {

		Atraccion atraccion = new Atraccion(nombre, cost, duration, capacity);

		if (atraccion.esValida()) {
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			atraccionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public Atraccion update(Integer id, String name, Double cost, Double duration, Integer capacity) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.find(id);

		atraccion.setNombre(name);
		atraccion.setPrecio(cost);;
		atraccion.setTiempoEnHoras(duration);
		atraccion.setCupoDisponible(capacity);

		if (atraccion.esValida()) {
			atraccionDAO.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void delete(Integer id) {
		Atraccion atraccion = new Atraccion(id, null, null, null, null);

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Atraccion find(Integer id) {
		return DAOFactory.getAtraccionDAO().find(id);
	}

}
