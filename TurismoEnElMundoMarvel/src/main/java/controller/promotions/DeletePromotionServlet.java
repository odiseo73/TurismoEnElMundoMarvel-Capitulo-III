package controller.promotions;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promotions/delete.do")
public class DeletePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		promotionService.delete(id);

		resp.sendRedirect("/TurismoEnElMundoMarvel_Webapp/promotions/index.do");
	}


}
