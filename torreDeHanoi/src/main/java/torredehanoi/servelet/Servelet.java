package torredehanoi.servelet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import torredehanoi.algoritimos.AlgoritimoByGrafo;

@WebServlet(urlPatterns = { "/grafo" })
public class Servelet extends HttpServlet {

	private static final long serialVersionUID = 3150746837771236162L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String init = req.getParameter("init").replace('-', '|');
		String fim = req.getParameter("fim").replace('-', '|');
		
		resp.setContentType("text/html");
		
		if(init != null && fim != null) {
			AlgoritimoByGrafo alg = new AlgoritimoByGrafo(init, fim).calcular();
//			alg.imprimir(new LogMovimentosDireto());
			resp.getWriter().write(alg.getResult());
		} else {
			resp.getWriter().write("Vazio");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
