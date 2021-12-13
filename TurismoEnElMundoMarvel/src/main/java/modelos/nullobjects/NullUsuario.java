package modelos.nullobjects;

import modelos.Usuario;

public class NullUsuario extends Usuario {

	public static Usuario build() {
		return new NullUsuario();
	}
	
	public NullUsuario() {
		super(0, "", "", 0.0, 0.0,false);
	}
	
	@Override
	public boolean isNull() {
		return true;
	}
	
}
