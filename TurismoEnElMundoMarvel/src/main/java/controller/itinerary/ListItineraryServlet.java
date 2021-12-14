package controller.itinerary;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Atraccion;
import modelos.Itinerario;
import modelos.Usuario;
import services.AttractionService;
import services.ItineraryService;

@WebServlet("/itinerary/index.do")
public class ListItineraryServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -5499931951882215763L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		Itinerario itinerario = user.getItinerario();
		req.setAttribute("itinerario", itinerario);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/itinerary/index.jsp");
		dispatcher.forward(req, resp);

	}
}
