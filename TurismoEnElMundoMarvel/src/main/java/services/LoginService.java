package services;


import modelos.Usuario;
import modelos.nullobjects.NullUsuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public Usuario login(String username, String password) {
		UsuarioDAO usuarioDao = DAOFactory.getUsuarioDAO();
    	Usuario usuario = usuarioDao.findByUsername(username);
    	
    	if (usuario.isNull() || !usuario.checkPassword(password)) {
    		usuario = NullUsuario.build();
    	}
    	return usuario;
	}
	
}
