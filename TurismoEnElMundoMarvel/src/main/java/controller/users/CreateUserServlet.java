package controller.users;

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

@WebServlet("/users/create.do")
public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private UserService userService;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Double dinero = Double.parseDouble(req.getParameter("coins"));
		Double tiempo = Double.parseDouble(req.getParameter("time"));
		Boolean admin = Boolean.parseBoolean(req.getParameter("admin"));

		Usuario tmp_user = userService.create(username, password, dinero, tiempo, admin);
		itineraryService.create(username, "", 0.0, 0.0);
		if (tmp_user.esValido()) {
			req.setAttribute("success", "Se ha creado un nuevo usuario");
			resp.sendRedirect("/TurismoEnElMundoMarvel_Webapp/users/index.do");
			
		} else {
			req.setAttribute("tmp_user", tmp_user);
			req.setAttribute("flash", "No se pudo crear el usuario");
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
