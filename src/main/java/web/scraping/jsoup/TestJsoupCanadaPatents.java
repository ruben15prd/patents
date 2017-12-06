package web.scraping.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class TestJsoupCanadaPatents {
	

	
	public static List<CanadaPatent> getInfoFromCanadaPatents(String search) throws MalformedURLException, IOException {
		//System.out.println(search);
		
		
		String URL_BASE2 = "http://www.ic.gc.ca/opic-cipo/cpd/eng/search/results.html?query=%28%28"+search+"%29+%3CIN%3E+INVENTOR%29&start=1&num=50&type=advanced_search&newSearch=0";
		
		
		Document doc = Jsoup.parse(new URL(URL_BASE2), 10000);
		
		Elements elements= doc.getElementsByTag("tbody");
		
		Elements filtered3 = elements.select("td");
		
		List<CanadaPatent> cPatents = new ArrayList<CanadaPatent>();
		
		int contador = 0;
		String idPatent = "";
		String title = "";
		String percentage = "";
		
		
		
		for (String s : filtered3.toString().split("\\n")) {
		
		/*
		String idPablo = "y2qDI6IAAAAJ";
		String idJorge = "O719x-wAAAAJ";
		*/
		int division = contador%4;
		if(division == 0) {
			division = division++;
		}
		//System.out.println("------------------------------------");
		if(division == 1) {
			//System.out.println("El contador es: "+ contador + " y estoy en division2" + s);
			Element tag = Jsoup.parse(s, "", Parser.xmlParser());
			idPatent = tag.text();
			//System.out.println("id: " + idPatent);
		}
		if(division == 2) {
			//System.out.println("El contador es: "+ contador + " y estoy en division3"+ s);
			Element tag = Jsoup.parse(s, "", Parser.xmlParser());
			title = tag.text();
			//System.out.println("title: "+ title);
		}
		if(division == 3) {
			//System.out.println("El contador es: "+ contador + " y estoy en division4"+ s);
			Element tag = Jsoup.parse(s, "", Parser.xmlParser());
			percentage = tag.text();
			//System.out.println("percentage: "+ percentage);
			
			CanadaPatent cPatent = new CanadaPatent(idPatent,title,percentage);
			//System.out.println(cPatent.toString());
			cPatents.add(cPatent);
			
			
		}
		
		contador=contador + 1;
		
		
	
	
		
	}
		//Element tag = Jsoup.parse(html, "", Parser.xmlParser());
		//String aux = "holaaaaaaaaa";
		return cPatents;	
	}
}
