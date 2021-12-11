package modelos.nullobjects;

import modelos.Atraccion;

public class NullAtraccion extends Atraccion {

	public static Atraccion build() {
		
		return new NullAtraccion();
	}

	public NullAtraccion() {
		super(0, "", 0.0, 0.0, 0);
	}
	
	@Override
	public boolean isNull() {
		return true;
	}
}
