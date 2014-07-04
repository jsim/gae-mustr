package cz.jsim.shiftplan.guice;

import com.google.inject.AbstractModule;
import cz.jsim.shiftplan.service.MessageService;
import cz.jsim.shiftplan.service.impl.MessageServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(MessageServiceImpl.class);
    }

}