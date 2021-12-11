package utils;

import modelos.Promocion;
import modelos.PromocionAbsoluta;
import modelos.PromocionAxB;
import modelos.PromocionPorcentual;

public class PromotionSelector {
	public static Promocion clasificarPromocionSinId(String nombre, String tipo, Integer descuento) {
		Promocion prom = null;
		if (tipo.equals("Porcentual")) {
			prom = new PromocionPorcentual(nombre, descuento, tipo);
		}

		if (tipo.equals("Absoluta")) {
			prom = new PromocionAbsoluta(nombre, descuento, tipo);

		}
		if (tipo.equals("AxB")) {
			prom = new PromocionAxB(nombre, tipo);
		}
		return prom;
	}

	public static Promocion clasificarPromocionConId(Integer id, String nombre, String tipo, Integer descuento) {
		Promocion prom = null;
		if (tipo.equals("Porcentual")) {
			prom = new PromocionPorcentual(id, nombre, descuento, tipo);
		}

		if (tipo.equals("Absoluta")) {
			prom = new PromocionAbsoluta(id, nombre, descuento, tipo);

		}
		if (tipo.equals("AxB")) {
			prom = new PromocionAxB(id, nombre, tipo);
		}
		return prom;
	}
}
