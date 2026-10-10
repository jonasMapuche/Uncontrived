package br.com.stomach.Uncontrived.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.stomach.Uncontrived.models.Artless;
import br.com.stomach.Uncontrived.models.UserLogin;
import br.com.stomach.Uncontrived.services.ArtlessService;
import br.com.stomach.Uncontrived.services.TokenService;
import br.com.stomach.Uncontrived.services.interfaces.IArtlessService;

@Path("")
public class UncontrivedController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
		try {
//---
			return "Hello, World Development With Jersey And Uncontrived.";
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
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
	
	@GET
    @Path("/security")
	@RolesAllowed("ADMIN")
    public String security() {
		try {
//---
			return "Hello, Security World Development With Jersey And Uncontrived.";
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	@POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response token(UserLogin login) {
        try {
//---
        	if (!"usuario_valido".equals(login.getUser()) && !"senha123".equals(login.getPassword())) {
                String jwt = TokenService.generateToken(login.getUser());
                Map<String, String> resposta = new HashMap<>();
                resposta.put("token_type", "Bearer");
                resposta.put("access_token", jwt);
//---                
                return Response.ok(resposta).build();
            }
//---            
            return Response.status(Response.Status.UNAUTHORIZED).entity("User or password are incorrect.").build();
//---            
        } catch (Exception ex) {
			System.out.println(ex.getMessage());
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
	
}
