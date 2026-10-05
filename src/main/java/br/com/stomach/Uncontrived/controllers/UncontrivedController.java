package br.com.stomach.Uncontrived.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.stomach.Uncontrived.models.Artless;
import br.com.stomach.Uncontrived.services.ArtlessService;
import br.com.stomach.Uncontrived.services.interfaces.IArtlessService;

@Path("")
public class UncontrivedController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello, World Development With Jersey And Uncontrived.";
    }

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Artless> all() {
		try {
//---
			String connection = "development";
			IArtlessService artlessService = new ArtlessService(connection);
			List<Artless> artlesss =  new ArrayList<>();; 
			artlesss = artlessService.GetAll();
//---
			return artlesss;
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Artless insertArtless(Artless artless) {
		try {
//---
			String development = "development";
			IArtlessService artlessService = new ArtlessService(development);
			String production = "production";
			IArtlessService artlessService2 = new ArtlessService(production);
			String test = "test";
			IArtlessService artlessService3 = new ArtlessService(test);
			artlessService.Create(artless);
			artlessService2.Create(artless);
			artlessService3.Create(artless);
//---
			return artless;
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
}
