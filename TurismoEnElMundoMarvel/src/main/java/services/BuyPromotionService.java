package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelos.Atraccion;
import modelos.Promocion;
import modelos.Usuario;
import persistence.AtraccionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyPromotionService {

	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	public Map<String, String> buy(Integer userId, Integer promotionId) {
		Map<String, String> errors = new HashMap<String, String>();
PromotionService promotionService = new PromotionService();
		Usuario usuario = usuarioDAO.find(userId);
		Promocion promocion = promotionService.find(promotionId);

		if (!promocion.verificarCupo()) {
			errors.put("Promocion", "No hay cupo para esta promocion");
		}
		
		if (!usuario.tieneDinero(promocion)) {
			errors.put("usuario", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(promocion)) {
			errors.put("usuario", "No tienes tiempo suficiente");
		}
		
		if (errors.isEmpty()) {
			usuario.comprarProducto(promocion);
			promocion.restarCupo();
			List<Atraccion> atraccionesDePromo = promocion.getAtracciones();
			for (Atraccion atraccion : atraccionesDePromo) {
				atraccionDAO.update(atraccion);
			}
			usuarioDAO.update(usuario);
		}
		
		return errors;

	}

}
