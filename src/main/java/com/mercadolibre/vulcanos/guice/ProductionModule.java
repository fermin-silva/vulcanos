package com.mercadolibre.vulcanos.guice;

import com.google.inject.servlet.ServletModule;
import com.mercadolibre.vulcanos.ForecastDao;
import com.mercadolibre.vulcanos.PeriodBuilder;
import com.mercadolibre.vulcanos.PlanetaryModel;
import com.mercadolibre.vulcanos.Worker;
import com.mercadolibre.vulcanos.rest.WeatherResource;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

public class ProductionModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(WeatherResource.class).asEagerSingleton();
        bind(PlanetaryModel.class).asEagerSingleton();
        bind(ForecastDao.class).asEagerSingleton();
        bind(PeriodBuilder.class).asEagerSingleton();
        bind(Worker.class).asEagerSingleton();

        Map<String, String> params = new HashMap<String, String>();
        serve("/*").with(GuiceContainer.class, params);
    }

}
