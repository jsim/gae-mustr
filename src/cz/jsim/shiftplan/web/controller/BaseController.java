package cz.jsim.shiftplan.web.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;

import cz.jsim.shiftplan.web.helpers.ModelAndView;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class BaseController extends HttpServlet {

	private static Logger logger = Logger.getLogger(BaseController.class.getName());

	@Inject
	private Configuration cfg;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		render(processRequest(req, resp), resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		render(processRequest(req, resp), resp);
	}

	private void render(ModelAndView mv, HttpServletResponse resp) throws IOException {

		Template temp = cfg.getTemplate(mv.getView());

		resp.setContentType("text/html; charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);

		try {
			temp.process(mv.getModel(), resp.getWriter());
		} catch (TemplateException e) {
			logger.log(Level.SEVERE, "Error while processing FreeMarker template", e);
		}
	}

	abstract ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException;

}
