package lectorDeArchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import modelos.Atraccion;
import modelos.Itinerario;
import modelos.Producto;
import modelos.Promocion;
import modelos.Usuario;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.Atraccion_PromocionDAOImpl;
import persistence.impl.ItinerarioDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class Parque {
	private UsuarioDAOImpl usuarioDAO;
	private AtraccionDAOImpl atraccionDAO;
	private PromocionDAOImpl promocionDAO;
	private ItinerarioDAOImpl itinerarioDAO;
	private Atraccion_PromocionDAOImpl atraccion_promocionDAO;

	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	private List<Itinerario> itinerarios;

	public Parque() throws FileNotFoundException, SQLException {

		this.usuarioDAO = new UsuarioDAOImpl();
		this.atraccionDAO = new AtraccionDAOImpl();
		this.promocionDAO = new PromocionDAOImpl();
		this.itinerarioDAO = new ItinerarioDAOImpl();
		this.atraccion_promocionDAO = new Atraccion_PromocionDAOImpl();
		leerArchivos();
		aniadirItinerariosAUsuarios();
		ofrecerProductos();

	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void leerArchivos() throws FileNotFoundException, SQLException {

		usuarios = usuarioDAO.findAll();
		atracciones = atraccionDAO.findAll();

		promociones = promocionDAO.findAll();
		atraccion_promocionDAO.setAtracciones(atracciones);
		atraccion_promocionDAO.setPromociones(promociones);		
		
		promociones = atraccion_promocionDAO.findAll();
		
		itinerarios = itinerarioDAO.findAll();

	}

	private void aniadirItinerariosAUsuarios() {
		for (Itinerario itinerario : itinerarios) {
			for (Usuario usuario : usuarios) {
				if (itinerario.getUsername().equals(usuario.getUsername())) {
					usuario.setItinerario(itinerario);
				}
			}
		}
	}

	private void aniadirAlItinerario(Usuario usuario, List<Producto> ProductosComprados) throws SQLException {

		Itinerario itinerario = usuario.getItinerario();

		itinerario.setProductosComprados(ProductosComprados);
		itinerarioDAO.update(itinerario);
		usuarioDAO.update(usuario);

		List<Atraccion> atraccionesCompradas = usuario.getAtraccionesCompradas();
		for (Atraccion atraccion : atraccionesCompradas) {
			atraccionDAO.update(atraccion);
		}

	}

	private List<Atraccion> aniadirAtraccionComprada(List<Atraccion> atraccionesCompradas, Producto producto) {

		if (producto.esPromocion()) {
			List<Atraccion> lista = producto.getAtracciones();
			for (Atraccion atraccion : lista) {
				if (!compararNombresIguales(atraccionesCompradas, atraccion)) {
					atraccionesCompradas.add(atraccion);
				}
			}
		}
		if (!producto.esPromocion()) {

			if (!compararNombresIguales(atraccionesCompradas, producto)) {
				atraccionesCompradas.add((Atraccion) producto);
			}
		}
		return atracciones;
	}

	private List<Producto> aniadirProductoComprado(List<Producto> productosComprados, Producto producto) {
		productosComprados.add(producto);
		return productosComprados;
	}

	private boolean compararNombresIguales(List<Atraccion> atracciones, Producto producto) {
		boolean bandera = false;
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(producto.getNombre())) {
				bandera = true;
			}
		}

		return bandera;
	}

	private void ofrecerProductos() throws FileNotFoundException, SQLException {

		for (Usuario usuario : usuarios) {

			Itinerario itinerario = usuario.getItinerario();
			List<Producto> productosComprados = itinerario.getProductosComprados(atracciones, promociones);
			usuario.setAtraccionesCompradas(productosComprados);
			// List<String> productosComprados = new ArrayList<String>();
			System.out.println("----------------------------------------------");
			System.out.println("Bienvenido/a a Mundo Marvel");
			System.out.println("Nombre de Visitante: " + usuario.getUsername());
			System.out.println("Le ofrecemos los siguientes productos");
			System.out.println();

			ofrecerPromociones(usuario, productosComprados);
			ofrecerAtracciones(usuario, productosComprados);

		}
	}

	void ofrecerPromociones(Usuario usuario, List<Producto> productosComprados) throws SQLException {

		List<Atraccion> atraccionesCompradas = usuario.getAtraccionesCompradas();

		for (Promocion promocion : promociones) {

			if ((usuario.tieneDinero(promocion) && promocion.verificarCupo(atraccionesCompradas)
					&& !usuario.tieneComprado(promocion))) {

				System.out.println(promocion);
				System.out.println("Acepta la sugerencia?" + " Ingrese SI o NO");
				Scanner sc = new Scanner(System.in);

				String respuesta;
				respuesta = sc.nextLine().toUpperCase();

				while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
					System.out.println("Por favor, ingrese SI o NO.");
					respuesta = sc.nextLine().toUpperCase();
				}
				if (respuesta.equals("SI")) {
					usuario.comprarProducto(promocion);
					aniadirProductoComprado(productosComprados, promocion);
					aniadirAtraccionComprada(atraccionesCompradas, promocion);
					promocion.restarCupo(atraccionesCompradas);
					aniadirAlItinerario(usuario, productosComprados);

				}

				System.out.println("----------------------------------------------");
			}

		}

	}

	void ofrecerAtracciones(Usuario usuario, List<Producto> productosComprados) throws SQLException {

		List<Atraccion> atraccionesCompradas = usuario.getAtraccionesCompradas();

		for (Atraccion atraccionOfrecida : atracciones) {
			if ((usuario.tieneDinero(atraccionOfrecida) && verificarCupo(atraccionesCompradas, atraccionOfrecida)
					&& !usuario.tieneComprado(atraccionOfrecida))) {

				System.out.println(atraccionOfrecida);
				System.out.println("Acepta la sugerencia?" + " Ingrese SI o NO");
				Scanner sc = new Scanner(System.in);
				String respuesta;
				respuesta = sc.nextLine().toUpperCase();

				while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
					System.out.println("Por favor, ingrese SI o NO.");
					respuesta = sc.nextLine().toUpperCase();
				}
				if (respuesta.equals("SI")) {
					usuario.comprarProducto(atraccionOfrecida);

					aniadirProductoComprado(productosComprados, atraccionOfrecida);
					atraccionOfrecida.restarCupo();
					aniadirAtraccionComprada(atraccionesCompradas, atraccionOfrecida);

					aniadirAlItinerario(usuario, productosComprados);
				}
				System.out.println("----------------------------------------------");
			}
		}
	}

	public boolean verificarCupo(List<Atraccion> atraccionesCompradas, Atraccion atraccionOfrecida) {
		boolean hayCupo = true;
		for (Atraccion atraccion : atraccionesCompradas) {
			if (!atraccion.verificarCupo()) {
				hayCupo = false;
			}
		}
		if (!atraccionOfrecida.verificarCupo()) {
			hayCupo = false;
		}

		return hayCupo;
	}
}
