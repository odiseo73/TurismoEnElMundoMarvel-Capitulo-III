package modelos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Itinerario {
	private Integer id;
	private String username;
	private String productosComprados;
	private double horasNecesarias;
	private double puntos;
	private HashMap<String,String> errores;

	public Itinerario(Integer id, String username, String productosComprados, double horasNecesarias, double puntos) {
		this.id = id;
		this.username = username;
		this.productosComprados = productosComprados;
		this.horasNecesarias = horasNecesarias;
		this.puntos = puntos;
	}

	public String getUsername() {
		return username;
	}

	public String getProductos() {
		return productosComprados;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setHorasNecesarias(double horasNecesarias) {
		this.horasNecesarias = horasNecesarias;
	}

	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public double getHorasNecesarias() {
		return horasNecesarias;
	}

	public double getPuntos() {
		return puntos;
	}

	public List<Producto> getProductosComprados(List<Atraccion> atracciones, List<Promocion> promociones) {

		List<Producto> productos = new LinkedList<Producto>();
		String[] array = productosComprados.split(",");

		for (String string : array) {
			for (Atraccion atraccion : atracciones) {
				if (atraccion.getNombre().equals(string)) {
					productos.add(atraccion);
				}
			}
			for (Promocion promocion : promociones) {
				if (promocion.getNombre().equals(string)) {
					productos.add(promocion);
				}
			}
		}
		return productos;

	}

	public void setProductosComprados(List<Producto> productosComprados) {

		for (Producto producto : productosComprados) {
			this.productosComprados += producto.getNombre() + ",";
			this.horasNecesarias += producto.getTiempoEnHoras();
			this.puntos += producto.getPrecio();
		}
	}

	public boolean isNull() {
		return false;
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	public void validar() {
		errores = new HashMap<String, String>();

		if (horasNecesarias < 0) {
			errores.put("horasNecesarias", "No debe ser negativo");
		}
		if (puntos < 0) {
			errores.put("puntos", "No debe ser negativo");
		}
	}

}
