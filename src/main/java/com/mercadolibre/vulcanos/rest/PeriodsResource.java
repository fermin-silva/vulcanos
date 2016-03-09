package com.mercadolibre.vulcanos.rest;

import com.mercadolibre.vulcanos.Dao;
import com.mercadolibre.vulcanos.PeriodBuilder;
import com.mercadolibre.vulcanos.PeriodBuilder.Period;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.HttpURLConnection;
import java.sql.SQLException;

/**
 * Created by Fermin on 8/3/16.
 */
@Path("/periods")
public class PeriodsResource {

    //in a real big application this shouldn't be part of the rest
    //layer and should be moved to a controller or similar

    @Inject
    private Dao dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("day") Integer day) throws SQLException {
        if (day == null) {
            throw new WebApplicationException(
                    Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
                            .entity("day parameter is mandatory")
                            .build()
            );
        }

        JSONArray arr = new JSONArray();
        
        for (Period period : dao.getPeriods()) {
        	JSONObject jPeriod = new JSONObject();
            
        	jPeriod.put("dayFrom", period.getDayFrom());
            jPeriod.put("dayTo", period.getDayTo());
            jPeriod.put("forecast", period.getForecast().name());
            
            arr.put(jPeriod);
		}
        
        return arr.toString();
    }
}
