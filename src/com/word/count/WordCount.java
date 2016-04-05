package com.word.count;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/wordCount")
public class WordCount {

	  @Path("{word}")
	  @GET
	  @Produces("application/json")
	  public Response getUsageAndOccurancesOf(@PathParam("word") String word) throws IOException, Exception {
		JSONObject jsonObject = new JSONObject();
		StringLogic strLogic = new StringLogic(word);
		Count count = strLogic.findOccurances();
		jsonObject.put("Word Occurances in Files = ", count.getCountInFiles()); 
		jsonObject.put("Count exactly as Parameter = ", count.getCountExactMatch());
		String result = "Count Information: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
	
}
