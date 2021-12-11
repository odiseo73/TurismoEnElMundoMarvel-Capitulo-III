package controller.itinerary;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Itinerario;
import modelos.Usuario;
import services.ItineraryService;
import services.UserService;

@WebServlet("/itinerarys/create.do")
public class CreateItineraryServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/itinerarys/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String prodComp = req.getParameter("prodComp");
		Double hsNec = Double.parseDouble(req.getParameter("hsNec"));
		Double puntos = Double.parseDouble(req.getParameter("puntos"));

		Itinerario tmp_it = itineraryService.create(user, prodComp, hsNec, puntos);
		
		if (!tmp_it.getUsuario().isEmpty()) {
			resp.sendRedirect("/turismo/itinerarys/index.do");
		} else {
			req.setAttribute("tmp_it", tmp_it);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/itinerarys/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
