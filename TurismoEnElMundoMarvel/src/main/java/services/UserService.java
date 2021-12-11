package services;

import java.util.List;


import modelos.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class UserService {

	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario create(String username, String password, Double dinero, Double tiempoDisponible) {
		//Usuario usuario = new Usuario(-1, username, password, coins, time, false);
//		usuario.setPassword(password);
		Usuario usuario = new Usuario(username,password,dinero, tiempoDisponible);
		if (usuario.esValido()) {
			DAOFactory.getUsuarioDAO().insert(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;
	}

	public Usuario find(Integer id) {
		return DAOFactory.getUsuarioDAO().find(id);
	}

	public Usuario update(Integer id, String username, String password, Double dinero, Double tiempoEnHoras) {
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.find(id);

		usuario.setPassword(password);
		//no se como hacerlo

		if (usuario.esValido()) {
			usuarioDAO.update(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;

	}
	
	
}
