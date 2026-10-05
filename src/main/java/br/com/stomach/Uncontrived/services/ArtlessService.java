package br.com.stomach.Uncontrived.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.stomach.Uncontrived.models.Artless;
import br.com.stomach.Uncontrived.models.Epigraph;
import br.com.stomach.Uncontrived.services.interfaces.IArtlessService;

public class ArtlessService implements IArtlessService {

	private String development = "mongodb+srv://hipaciazatz:freedown@artless.yapo55a.mongodb.net/?appName=artless";
	private String production = "mongodb+srv://berthazatz:freedown@artless.3twaubu.mongodb.net/?appName=artless";
	private String test = "mongodb+srv://hipaciawang:freedown@artless.9lumvd1.mongodb.net/?appName=artless";
	
    private MongoCollection<Document> _artlessCollection;
    
	public ArtlessService(String connection) {
		try {
//---
			String uri = "";
			switch (connection) {
		    case "test":
		    	uri = this.test;
		    	break;
		    case "production":
		        uri = this.production;
		    	break;
		    default:
		    	uri = this.development;
		    	break;
			}
//---
			MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("stomach");
            MongoCollection<Document> collection = database.getCollection("artless");
            this._artlessCollection = collection;
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public List<Artless> GetAll() {
		try {
//---
			List<Artless> artlesss = new ArrayList<>();
        	ZoneId zone = ZoneId.of("UTC");
	    	for (Document doc : this._artlessCollection.find()) {
//---
	    		Document elegy = doc.get("epigraph", Document.class); 
        		String name_epigraph = elegy.getString("name");
        		String title_epigraph = elegy.getString("title");
	        	Instant date_input_epigraph = elegy.getDate("date_in").toInstant();
	        	LocalDate date_in_epigraph = date_input_epigraph.atZone(zone).toLocalDate();
	        	Instant date_output_epigraph = elegy.getDate("date_out").toInstant();
	        	LocalDate date_out_epigraph = date_output_epigraph.atZone(zone).toLocalDate();
	        	Epigraph epigraph = new Epigraph(name_epigraph, title_epigraph, date_in_epigraph, date_out_epigraph);
//---
	        	List<String> names = doc.getList("name", String.class);
	        	String framework = doc.getString("framework");
	        	String description = doc.getString("description");
	        	Instant date_input = doc.getDate("date_in").toInstant();
	        	LocalDate date_in = date_input.atZone(zone).toLocalDate();
	        	Instant date_output = doc.getDate("date_out").toInstant();
	        	LocalDate date_out = date_output.atZone(zone).toLocalDate();
//---
	        	Artless artless = new Artless(names, framework, description, date_in, date_out, epigraph);
	        	artlesss.add(artless);
	        }
	       return artlesss;
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
    }

	public void Create(Artless artless) {
		try {
//---
    		Epigraph elegy = artless.getEpigraph(); 
    		Document epigraph_doc = new Document("name", elegy.getName())
    				.append("title", elegy.getTitle())
    				.append("date_in", elegy.getDate_in())
    				.append("date_out", elegy.getDate_out());
    		Document artless_doc = new Document("name", artless.getName())
    				.append("framework", artless.getFramework())
    				.append("description", artless.getDescription())
    				.append("date_in", artless.getDate_in())
    				.append("date_out", artless.getDate_out())
    				.append("epigraph", epigraph_doc);
//---
    		this._artlessCollection.insertOne(artless_doc);
//---
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
    
}
