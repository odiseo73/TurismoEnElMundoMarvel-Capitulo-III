package services;

import java.util.List;


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

	public Usuario find(Integer id) {
		return DAOFactory.getUsuarioDAO().find(id);
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
