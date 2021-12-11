package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Atraccion;
import modelos.Usuario;
import services.AttractionService;
import services.UserService;

@WebServlet("/attractions/edit.do")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Usuario usuario = userService.find(id);
		//crear metodo find
		req.setAttribute("user", usuario);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/users/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*String username, String password, Double dinero, Double tiempoEnHoras*/
		Integer id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Double dinero = Double.parseDouble(req.getParameter("dinero"));
		Double tiempoEnHoras = Double.parseDouble(req.getParameter("tiempoEnHoras"));

		Usuario usuario = userService.update(id, username, password, dinero, tiempoEnHoras);
		//crear metodo update

		if (usuario.esValido()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("user", usuario);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
