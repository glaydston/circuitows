package br.com.circuito.bo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.circuito.model.Event;

import com.google.gson.Gson;

public class Parser {
	private final static String URL = "http://www.circuitoculturalliberdade.com.br/plus/modulos/listas/index.php?tac=agenda&layout=programacao";
	
	public String request(Date date){
		List<Event> events = new ArrayList<Event>();
		Event event = null;
		try {
			Document doc = Jsoup.connect(URL.concat("&data=").concat(new SimpleDateFormat("dd/MM/yyyy").format(date))).get();
			Elements es = doc.select(".box .box-square");			
			
			for(Element e : es){
				event = new Event();
				event.setTitle(e.select(".agenda-title").text());
				event.setImagem(e.select(".agenda-image img").attr("src"));
				event.setPlace(e.select(".info .info-value").get(1).text());
				event.setPrice(e.select(".info .info-value").get(0).text());
				event.setDescription(e.select(".agenda-text").text());
				events.add(event);
			}

			Elements pagnav = doc.select(".paginacao a");
			
			List<String> pages = new ArrayList<String>();
			
			for(Element e : pagnav){
				System.out.println(e.text());
				if(!e.text().equals(">"))
					pages.add(e.text());
			}
			
			for(String page : pages){
				doc = Jsoup.connect(URL.concat("&pagina=").concat(page).concat("&data=").concat(new SimpleDateFormat("dd/MM/yyyy").format(date))).get();
				es = doc.select(".box .box-square");			
				
				for(Element e : es){
					event = new Event();
					event.setTitle(e.select(".agenda-title").text());
					event.setImagem(e.select(".agenda-image img").attr("src"));
					event.setPlace(e.select(".info .info-value").get(1).text());
					event.setPrice(e.select(".info .info-value").get(0).text());
					event.setDescription(e.select(".agenda-text").text());
					events.add(event);
				}
			}		
			
		} catch (IOException e) {
			System.out.println("[ERROR]: " + e.getMessage());
			e.printStackTrace();
		}
		return new Gson().toJson(events);
	}
	
	public String request(String place){
		return null;
	}
	
	public String request (Date date, String place){
		return null;
	}
	

}
