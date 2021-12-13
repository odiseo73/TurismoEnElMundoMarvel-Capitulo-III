package modelos.nullobjects;

import modelos.Promocion;
import modelos.PromocionAbsoluta;


public class NullPromocion extends PromocionAbsoluta {

	public static Promocion build() {
		return new NullPromocion();
	}
	
	public NullPromocion() {
		super(0, "", 0, "");
	}
	
	@Override
	public boolean isNull() {
		return true;
	}

}
