package services;

import java.util.List;

import modelos.Atraccion;
import modelos.Promocion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromotionService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(Integer id,String nombre,String tipo) {

		Promocion promocion = new Promocion(id,nombre,tipo);

		if (promocion.esPromocion()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public Promocion update(Integer id, String name, String tipo) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		//no se como hacer este metodo

		return promocion;
	}

	public void delete(Integer id) {
		Promocion promocion = new Promocion(id,null,null);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	public Promocion find(Integer id) {
		return DAOFactory.getPromocionDAO().find(id);
	}

}
