package services;

import java.util.HashMap;
import java.util.Map;

import modelos.Atraccion;
import modelos.Usuario;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		UserService userService = new UserService();
		Usuario usuario = userService.find(userId);
		Atraccion atraccion = atraccionDAO.find(attractionId);

		if (!atraccion.verificarCupo()) {
			errors.put("Atraccion", "No hay cupo disponible");
		}
		if (!usuario.tieneDinero(atraccion)) {
			errors.put("usuario", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(atraccion)) {
			errors.put("usuario", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			usuario.comprarProducto(atraccion);
			atraccion.restarCupo();

			atraccionDAO.update(atraccion);
			usuario.setProductosAlItinerario();
			itinerarioDAO.update(usuario.getItinerario());
			usuarioDAO.update(usuario);
		}

		return errors;

	}

}
