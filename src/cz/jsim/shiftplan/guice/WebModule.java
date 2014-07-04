package cz.jsim.shiftplan.guice;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;

import cz.jsim.shiftplan.web.controller.HomeController;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import javax.inject.Singleton;

public class WebModule extends ServletModule {

	private static Logger logger = Logger.getLogger(WebModule.class.getName());

	@Override
	protected void configureServlets() {

		bindFreeMarkerTemplate();

		filter("/*").through(ObjectifyFilter.class);
		bind(ObjectifyFilter.class).in(Singleton.class);

		serve("/home.htm").with(HomeController.class);
	}

	private void bindFreeMarkerTemplate() {

		Configuration cfg = new Configuration();

		try {
			cfg.setDirectoryForTemplateLoading(new File("WEB-INF/templates"));
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Template directory error", e);
		}

		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		
		// Sets how errors will appear. Here we assume we are developing HTML pages.
		// For production systems TemplateExceptionHandler.RETHROW_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		// Bind instance for DI
		bind(Configuration.class).toInstance(cfg);
	}

}
