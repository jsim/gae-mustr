package cz.jsim.shiftplan.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(
                new ServiceModule(),
                new WebModule()
        );
    }

}
