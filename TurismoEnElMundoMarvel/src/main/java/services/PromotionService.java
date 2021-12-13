package services;

import java.util.List;

import modelos.Atraccion;
import modelos.Promocion;
import modelos.PromocionAbsoluta;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import utils.PromotionSelector;

public class PromotionService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(String nombre,String tipo, Integer descuento) {

		Promocion promocion = PromotionSelector.clasificarPromocionSinId(nombre, tipo, descuento);

		if (promocion.esPromocion()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public Promocion update(Integer id, String name, String tipo, Integer descuento) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);
promocion.setNombre(name);
promocion.setTipo(tipo);
promocion.setDescuento(descuento);
		//no se como hacer este metodo

		return promocion;
	}

	public void delete(Integer id) {
		Promocion promocion = new PromocionAbsoluta(id,null,null,null);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	public Promocion find(Integer id) {
		return DAOFactory.getPromocionDAO().find(id);
	}

}
