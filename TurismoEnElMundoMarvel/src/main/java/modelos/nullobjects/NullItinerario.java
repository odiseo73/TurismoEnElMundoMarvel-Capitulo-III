package modelos.nullobjects;

import modelos.Itinerario;

public class NullItinerario extends Itinerario{

public static Itinerario build() {
		
		return new NullItinerario();
	}

	public NullItinerario() {
		super(0,"","",0.0,0.0);
	}
	
	@Override
	public boolean isNull() {
		return true;
	}
}
