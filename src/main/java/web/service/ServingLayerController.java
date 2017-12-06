package web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import web.scraping.jsoup.CanadaPatent;
import web.scraping.jsoup.TestJsoupCanadaPatents;

@RestController
public class ServingLayerController {

	@RequestMapping("/patents")
	public List<CanadaPatent> serve(
			@RequestParam(value = "search", required = true, defaultValue = "null") String tag)
			throws JsonParseException, JsonMappingException, IOException {
		return TestJsoupCanadaPatents.getInfoFromCanadaPatents(tag);
	}
	
}