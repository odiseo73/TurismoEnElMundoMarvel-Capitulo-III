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
import modelos.Itinerario;
import modelos.Usuario;
import services.ItineraryService;
import services.UserService;

@WebServlet("/itinerarys/index.do")
public class ListItenerarysServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Itinerario> itinerarys = itineraryService.list();
		req.setAttribute("itinerarys", itinerarys);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/itinerarys/index.jsp");
		dispatcher.forward(req, resp);

	}

}
