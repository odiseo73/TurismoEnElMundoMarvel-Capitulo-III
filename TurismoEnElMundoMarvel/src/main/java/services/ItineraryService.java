package services;

import java.util.List;
import modelos.Itinerario;
import modelos.Usuario;
import persistence.ItinerarioDAO;
import persistence.commons.DAOFactory;

public class ItineraryService {
	public List<Itinerario> list() {
		return DAOFactory.getItinerarioDAO().findAll();
	}

	public Itinerario create(Usuario usuario) {

		Itinerario itinerario = usuario.getItinerario();

		if (itinerario.esValido()) {
			ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
			itinerarioDAO.insert(itinerario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return itinerario;
	}

	public Itinerario update(Usuario usuario) {

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		Itinerario itinerario = usuario.getItinerario();

		if (itinerario.esValido()) {
			itinerarioDAO.update(itinerario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return itinerario;
	}

	public void delete(Usuario usuario) {

		Itinerario itinerario = usuario.getItinerario();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		itinerarioDAO.delete(itinerario);
	}

	public Itinerario find(Integer id) {
		return DAOFactory.getItinerarioDAO().find(id);
	}

}
