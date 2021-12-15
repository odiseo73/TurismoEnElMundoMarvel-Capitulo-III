package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Atraccion;
import services.AttractionService;

@WebServlet("/attractions/create.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/attractions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		Double precio = Double.parseDouble(req.getParameter("precio"));
		double duration = Integer.parseInt(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));
		String description = req.getParameter("description");
		
		Atraccion atraccion = attractionService.create(name, precio, duration, capacity,description);
		
		if (atraccion.esValida()) {
			req.setAttribute("success", "Se creó una nueva atracción");
			resp.sendRedirect("/TurismoEnElMundoMarvel_Webapp/attractions/index.do");
			
		} else {
			req.setAttribute("attraction", atraccion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
