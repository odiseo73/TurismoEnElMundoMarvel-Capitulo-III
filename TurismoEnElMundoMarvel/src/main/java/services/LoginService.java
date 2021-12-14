package services;

import java.util.List;

import modelos.Itinerario;
import modelos.Producto;
import modelos.Usuario;
import modelos.nullobjects.NullUsuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public Usuario login(String username, String password) {
		UsuarioDAO usuarioDao = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDao.findByUsername(username);

		ItineraryService itineraryService = new ItineraryService();
		Itinerario itinerario = itineraryService.find(usuario.getId());
		usuario.setItinerario(itinerario);
		UserService userService = new UserService();
		List<Producto> productosComprados = userService.setProductosComprados(usuario);
		usuario.setProductosComprados(productosComprados);
		if (usuario.isNull() || !usuario.checkPassword(password)) {
			usuario = NullUsuario.build();
		}
		return usuario;
	}

}
