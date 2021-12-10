package services;

import java.util.List;

import modelos.Usuario;
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
}
