package services;

import java.util.List;

import modelos.Itinerario;
import modelos.Usuario;

import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class UserService {

	public List<Usuario> list() {
		
				List<Usuario> usuarios = DAOFactory.getUsuarioDAO().findAll();
				return addItinerariesToUsers(usuarios);
	}
	public List<Usuario> addItinerariesToUsers(List<Usuario> usuarios) {
		List<Itinerario> itinerarios = DAOFactory.getItinerarioDAO().findAll();
		for (Itinerario itinerario : itinerarios) {
			for (Usuario usuario : usuarios) {
				if (itinerario.getUsername().equals(usuario.getUsername())) {
					usuario.setItinerario(itinerario);
				}
			}
			}
		return usuarios;
	}
	public Usuario create(String username, String password, Double dinero, Double tiempoDisponible) {
		Usuario usuario = new Usuario(username, password, dinero, tiempoDisponible, false);
		usuario.setPassword(password);

		if (usuario.esValido()) {
			DAOFactory.getUsuarioDAO().insert(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;
	}

	public Usuario update(Integer id, String username, String password, Double dinero, Double tiempoDisponible) {

		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.find(id);

		usuario.setUsername(username);
		usuario.setPassword(username);
		usuario.setDinero(dinero);
		usuario.setTiempo(tiempoDisponible);

		if (usuario.esValido()) {
			usuarioDAO.update(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;
	}

	public void delete(Integer id) {
		Usuario usuario = new Usuario(id, "", "", 0.0, 0.0, false);

		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.delete(usuario);
	}

	public Usuario find(Integer id) {
		return DAOFactory.getUsuarioDAO().find(id);
	}
}
