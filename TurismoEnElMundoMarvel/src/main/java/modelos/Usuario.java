package modelos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.Crypt;

//import java.util.List;

public class Usuario {
	private Integer id;
	private String username;
	private double tiempoDisponible;
	private double dineroDisponible;
	private List<Atraccion> atraccionesCompradas;
	private List<Producto> productosComprados;
	private HashMap<String, String> errores;
	private Itinerario itinerario;
	private String password;
	private Boolean admin;

	public Usuario(String username, String password, Double dinero, Double tiempoEnHoras, Boolean admin) {
		this.username = username;
		this.password = password;
		this.tiempoDisponible = tiempoEnHoras;
		this.dineroDisponible = dinero;
		this.atraccionesCompradas = new LinkedList<Atraccion>();
		this.itinerario = new Itinerario(this.id,this.getUsername(), "", 0.0, 0.0);
		this.admin = admin;
		this.productosComprados = new LinkedList<Producto>();
	}

	public Usuario(Integer id, String username, String password, Double dinero, Double tiempoEnHoras,Boolean admin) {
		this(username,password, dinero, tiempoEnHoras,admin);
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public double getTiempoEnHoras() {
		return tiempoDisponible;
	}
	public boolean esAdmin() {
		return admin;
	}

	public double getDinero() {
		return dineroDisponible;
	}

	public int getId() {
		return id;
	}
	
	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}
	public boolean isNull() {
		return false;
	}
	public Itinerario getItinerario() {
		return this.itinerario;
	}
	
	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public boolean tieneComprado(Producto productoOfrecido) {
		boolean bandera = false;
		for (Producto productoComprado : productosComprados) {
			if (productoComprado.getNombre().equals(productoOfrecido.getNombre())) {
				bandera = true;
			}
		if (productoComprado.esPromocion()) {
			List<Atraccion> atraccionesDePromoComprada = productoComprado.getAtracciones();
			for (Atraccion atraccionComprada : atraccionesDePromoComprada) {
				if (atraccionComprada.getNombre().equals(productoOfrecido.getNombre())) {
					bandera = true;
				}
				if(productoOfrecido.esPromocion()) {
					List<Atraccion> atraccionesDePromoOfrecida = productoOfrecido.getAtracciones();
					for(Atraccion atraccionOfrecida : atraccionesDePromoOfrecida ) {
						if (atraccionComprada.getNombre().equals(atraccionOfrecida.getNombre())) {
							bandera = true;
						}
					}
				}
			}
		}
		if(!productoComprado.esPromocion()) {
			if(productoOfrecido.esPromocion()) {
				List<Atraccion> atraccionesDePromoOfrecida = productoOfrecido.getAtracciones();
						for(Atraccion atraccionOfrecida : atraccionesDePromoOfrecida ) {
					if (productoComprado.getNombre().equals(atraccionOfrecida.getNombre())) {
						bandera = true;
					}
				}
			}
		}
		}
		return bandera;
	}

	public void setAtraccionesCompradas(List<Producto> productosComprados) {
		
		for (Producto productoComprado : productosComprados) {
			if (productoComprado.esPromocion()) {
				for (Atraccion atraccion : productoComprado.getAtracciones()) {
					this.atraccionesCompradas.add(atraccion);
				}
			}
			if (!productoComprado.esPromocion()) {
				this.atraccionesCompradas.add((Atraccion) productoComprado);
			}
		}
	}

	public List<Atraccion> getAtraccionesCompradas() {
		return atraccionesCompradas;
	}
public void setProductosAlItinerario() {
	Double horasNecesarias = 0.0;
	Double puntos = 0.0;
	String productosAlItinerario = "";
	for (Producto producto : productosComprados) {
		productosAlItinerario += producto.getNombre() + ",";
		horasNecesarias += producto.getTiempoEnHoras();
		if(producto.esPromocion()) {
			puntos += producto.getPrecioConDescuento();
		} else {
			puntos += producto.getPrecio();
		}
		
	}
	this.itinerario.setHorasNecesarias(horasNecesarias);
	this.itinerario.setPuntos(puntos);
	this.itinerario.setProductosComprados(productosAlItinerario);
}
	public void comprarProducto(Producto producto) {

		
		this.tiempoDisponible -= producto.getTiempoEnHoras();

		if (producto.esPromocion()) {
			this.dineroDisponible -= producto.getPrecioConDescuento();
			
				this.productosComprados.add(producto);
			
		}
		if (!producto.esPromocion()) {
			this.dineroDisponible -= producto.getPrecio();
			this.productosComprados.add(producto);
		}
	}

	@Override
	public String toString() {
		return "Usuario [Nombre=" + username + ", TiempoEnHoras=" + tiempoDisponible + ", Dinero=" + dineroDisponible
				+ "]" + "\n";
	}

	public boolean tieneDinero(Producto o) {
		if (this.dineroDisponible >= o.getPrecio()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean tieneTiempo(Producto o) {
		if (this.tiempoDisponible >= o.getTiempoEnHoras()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	public void validar() {
		errores = new HashMap<String, String>();

		if (dineroDisponible < 0) {
			errores.put("coins", "No debe ser negativo");
		}
		if (tiempoDisponible < 0) {
			errores.put("time", "No debe ser negativo");
		}
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	public void setUsername(String username) {
		this.username = username;
		
	}

	public void setDinero(Double dinero) {
		this.dineroDisponible = dinero;
		
	}

	public void setTiempo(Double tiempo) {
		this.tiempoDisponible = tiempo;
		
	}

	public void setAdmin(Boolean admin) {
		
		this.admin = admin;
	}

}
