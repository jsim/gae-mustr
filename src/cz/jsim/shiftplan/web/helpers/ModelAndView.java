package cz.jsim.shiftplan.web.helpers;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

	private String view;
	private Map model;

	public ModelAndView(String view) {
		this.view = view;
		this.model = new HashMap();
	}
	
	public ModelAndView(String view, Map model) {
		this.view = view;
		this.model = model;
	}

	public String getView() {
		return view;
	}

	public Map getModel() {
		return model;
	}
	
}
