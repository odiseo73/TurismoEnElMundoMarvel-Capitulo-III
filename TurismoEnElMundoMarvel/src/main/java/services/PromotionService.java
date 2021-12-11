package services;

import java.util.List;

import modelos.Atraccion;
import modelos.Promocion;
import modelos.PromocionAbsoluta;

import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.Atraccion_PromocionDAOImpl;
import utils.PromotionSelector;

public class PromotionService {

	public List<Promocion> list() {

		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(String nombre, String tipo, Integer descuento, List<Atraccion> atracciones) {

		Promocion promocion = PromotionSelector.clasificarPromocionSinId(nombre, tipo, descuento);

		if (promocion.esValida()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			Atraccion_PromocionDAOImpl atr_promDAO = DAOFactory.getAtraccion_PromocionDAO();
			for (Atraccion atraccion : atracciones) {
				int id_promocion = promocion.getId();
				if (atr_promDAO.find(id_promocion).equals(atraccion.getId())) {
					atr_promDAO.insert(promocion, atraccion);
					promocionDAO.insert(promocion);
					// XXX: si no devuelve "1", es que hubo más errores
				}
			}
		}

		return promocion;
	}

	public Promocion update(Integer id, String name, String tipo, Integer descuento, List<Atraccion> atracciones) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setNombre(name);
		promocion.setTipo(tipo);
		promocion.setDescuento(descuento);

		if (promocion.esValida()) {
			Atraccion_PromocionDAOImpl atr_promDAO = DAOFactory.getAtraccion_PromocionDAO();
			for (Atraccion atraccion : atracciones) {
				int id_promocion = promocion.getId();
				if (atr_promDAO.find(id_promocion).equals(atraccion.getId())) {
					atr_promDAO.update(promocion, atraccion);
					promocionDAO.update(promocion);
					// XXX: si no devuelve "1", es que hubo más errores
				}
			}
		}

		return promocion;
	}

	public void delete(Integer id) {

		Promocion promocion = new PromocionAbsoluta(id, null, null, null);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Atraccion_PromocionDAOImpl atr_promDAO = DAOFactory.getAtraccion_PromocionDAO();
		atr_promDAO.deletePromotion(promocion);
		promocionDAO.delete(promocion);
	}

	public Promocion find(Integer id) {
		return DAOFactory.getPromocionDAO().find(id);
	}

}
