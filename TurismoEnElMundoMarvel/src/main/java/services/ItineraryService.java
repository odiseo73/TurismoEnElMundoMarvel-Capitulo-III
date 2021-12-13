package services;

import java.util.List;

import modelos.Itinerario;

import persistence.ItinerarioDAO;
import persistence.commons.DAOFactory;

public class ItineraryService {

	public List<Itinerario> list() {
		return DAOFactory.getItinerarioDAO().findAll();
	}

	public Itinerario create(String user, String productosComprados, Double horasNecesarias, Double puntos) {
		
		Itinerario itinerario = new Itinerario(user,productosComprados,horasNecesarias,puntos);
		if (!itinerario.esValido()) {
			DAOFactory.getItinerarioDAO().insert(itinerario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return itinerario;
	}
	
	
	public Itinerario update(Integer id, String name, Double cost, Double duration, Integer capacity) {

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		Itinerario itinerario = itinerarioDAO.find(id);

		//no se como hacer este metodo

		if (!itinerario.esValido()) {
			itinerarioDAO.update(itinerario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return itinerario;
	}

	
	public Itinerario find(Integer id) {
		return DAOFactory.getItinerarioDAO().find(id);
	}

}
