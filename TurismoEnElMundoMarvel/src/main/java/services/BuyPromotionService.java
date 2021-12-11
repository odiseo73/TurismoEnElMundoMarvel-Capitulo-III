package services;

import java.util.HashMap;
import java.util.Map;

import modelos.Atraccion;
import modelos.Promocion;
import modelos.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyPromotionService {

	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

	public Map<String, String> buy(Integer userId, Integer promotionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.find(userId);
		Promocion promocion = promocionDAO.find(promotionId);

		if (promocion.verificarCupo(null)) {
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

			promocionDAO.update(promocion);
			usuarioDAO.update(usuario);
		}
		
		return errors;

	}

}
