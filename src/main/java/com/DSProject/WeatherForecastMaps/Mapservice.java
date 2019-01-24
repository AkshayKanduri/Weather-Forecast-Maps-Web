package com.DSProject.WeatherForecastMaps;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

@Controller
public class Mapservice {
	
	@Autowired
	Factory factory;
	
	//Default request call to the index page
	@RequestMapping("/")
	public String anypage() 
	{
		return "index.jsp";
	}
	
	//"apirequest" is the same form action given in the index.jsp page
	@RequestMapping("/apirequest")
	public ModelAndView APICall(HttpServletRequest hr) 
	{
		GeoApiContext context=new GeoApiContext.Builder().apiKey("APIKEY").build();//Calling Google Maps API
		OpenWeatherMap owm=new OpenWeatherMap("APIKEY");//Calling Open Weather API
		DirectionsResult result=null;
		//getting the source and destination data
		String source=hr.getParameter("source");
		String destination=hr.getParameter("destination");
		
		try {
			result=DirectionsApi.getDirections(context, source, destination).await();
		} catch (ApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Creating lists for the values that are required for Way points and Weather information
		ArrayList<Float> lat=new ArrayList<Float>();
		ArrayList<Float> lon=new ArrayList<Float>();
		ArrayList<Float> maxtemp=new ArrayList<Float>();
		ArrayList<Float> mintemp=new ArrayList<Float>();
	    ArrayList<String> weacond=new ArrayList<String>();
	    
	    //passing the response of maps api and weather api to the above array lists
	    for(Integer i=0;i < Integer.parseInt(new GsonBuilder().create().toJson(result.routes[0].legs[0].steps.length));i++)
		{	
	    	Float lat1=Float.parseFloat(result.routes[0].legs[0].steps[i].startLocation.lat+"");
			Float lon1=Float.parseFloat(result.routes[0].legs[0].steps[i].startLocation.lng+"");
			lat.add(lat1);
			lon.add(lon1);
			CurrentWeather cw=owm.currentWeatherByCoordinates(lat1, lon1);
			maxtemp.add(cw.getMainInstance().getMaxTemperature());
			mintemp.add(cw.getMainInstance().getMinTemperature());
			weacond.add("\""+cw.getWeatherInstance(0).getWeatherName()+"\"");
		}
			//setting model and view passing objects
			ModelAndView model=new ModelAndView();
			model.setViewName("result.jsp");
			model.addObject("source", source);
			model.addObject("destination", destination);
			model.addObject("lat",lat);
			model.addObject("lon",lon);
			model.addObject("maxtemp",maxtemp);
			model.addObject("mintemp",mintemp);
			model.addObject("weacond",weacond);
			
			return model;

	}	
}
