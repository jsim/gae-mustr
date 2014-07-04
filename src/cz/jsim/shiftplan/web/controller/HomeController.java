package cz.jsim.shiftplan.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;

import cz.jsim.shiftplan.domain.Message;
import cz.jsim.shiftplan.service.MessageService;
import cz.jsim.shiftplan.web.helpers.ModelAndView;

@Singleton
public class HomeController extends BaseController {

	@Inject
	private MessageService messageService;

	@Override
	ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String s = req.getParameter("id");
		if (s != null) {
			Message m = new Message();
			m.setKey(s);
			m.setText(s);
			messageService.saveMessage(m);
		}

		Map data = new HashMap();
		
		data.put("user", "Jan Simonik");
		data.put("messages", messageService.getAllMessages());

		return new ModelAndView("home.ftl", data);

	}

}
