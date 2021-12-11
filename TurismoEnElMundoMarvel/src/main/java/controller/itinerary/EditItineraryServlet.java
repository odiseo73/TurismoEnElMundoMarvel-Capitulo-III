package controller.itinerary;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Atraccion;
import modelos.Itinerario;
import services.AttractionService;
import services.ItineraryService;

@WebServlet("/itinerarys/edit.do")
public class EditItineraryServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Itinerario itinerario = itineraryService.find(id);
		//crear metodo find en el servie
		req.setAttribute("itinerary", itinerario);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/itinerarys/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("name");
		Double precio = Double.parseDouble(req.getParameter("precio"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null : Integer.parseInt(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));

		Itinerario itinerario = itineraryService.update(id, nombre, precio, duration, capacity);
		//crear update tambien

		if (!itinerario.getUsuario().isEmpty()) {
			resp.sendRedirect("/turismo/itinerarys/index.do");
		} else {
			req.setAttribute("itinerary", itinerario);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/itinerarys/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
