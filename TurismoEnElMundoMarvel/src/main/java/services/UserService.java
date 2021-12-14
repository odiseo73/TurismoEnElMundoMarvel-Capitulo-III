package services;

import java.util.LinkedList;
import java.util.List;

import modelos.Atraccion;
import modelos.Itinerario;
import modelos.Producto;
import modelos.Promocion;
import modelos.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class UserService {

	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario create(String username, String password, Double dinero, Double tiempoDisponible, Boolean admin) {
	

		Usuario usuario = new Usuario(username,password,dinero, tiempoDisponible,admin);
		usuario.setPassword(password);
		if (usuario.esValido()) {
			DAOFactory.getUsuarioDAO().insert(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;
	}
public List<Producto> setProductosComprados(Usuario usuario){
	List<Producto> productosComprados = new LinkedList<Producto>();
	String [] productosCompradosString = usuario.getItinerario().getProductosArray();
	List<Atraccion> atracciones = DAOFactory.getAtraccionDAO().findAll();
	List<Promocion> promociones = DAOFactory.getPromocionDAO().findAll();
	for (String producto : productosCompradosString) {
			for (Promocion promocion : promociones) {
				if(producto.equals(promocion.getNombre())) {
					productosComprados.add(promocion);
				}
			}
			for(Atraccion atraccion : atracciones) {
				if(producto.equals(atraccion.getNombre())) {
					productosComprados.add(atraccion);
				}
			}
	}
	return productosComprados;
}
	public Usuario find(Integer id) {
	Usuario usuario = DAOFactory.getUsuarioDAO().find(id);
		ItineraryService itineraryService = new ItineraryService();
		Itinerario itinerario = itineraryService.find(usuario.getId());
		usuario.setItinerario(itinerario);
		List<Producto> productosComprados = setProductosComprados(usuario);
		usuario.setProductosComprados(productosComprados);
		return usuario;
	}

	public Usuario update(Integer id, String username, Double dinero, Double tiempoEnHoras, Boolean admin) {
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.find(id);
usuario.setUsername(username);
usuario.setDinero(dinero);
usuario.setTiempo(tiempoEnHoras);
usuario.setAdmin(admin);
		//no se como hacerlo

		if (usuario.esValido()) {
			usuarioDAO.update(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;

	}
	
	
}
