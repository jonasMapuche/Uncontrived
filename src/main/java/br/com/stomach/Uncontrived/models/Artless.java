package br.com.stomach.Uncontrived.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Artless {

	//@SuppressWarnings("unused")
	//private BigInteger _id;
	
	private List<String> name;
	private String framework;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate date_in;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate date_out;
	private String description;
	private Epigraph epigraph;
	
	public Artless() {}

    public Artless(List<String> name, String framework, String description, LocalDate date_in, LocalDate date_out, Epigraph epigraph) {
        this.name = name;
        this.framework = framework;
        this.description = description;
        this.date_in = date_in;
        this.date_out = date_out;
        this.epigraph = epigraph;
    }
    
    public Artless(List<String> name, String framework, String description, String date_in, String date_out, Epigraph epigraph) {
        this.name = name;
        this.framework = framework;
        this.description = description;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date_input = LocalDate.parse(date_in, formatter);
		this.date_in = date_input;
		LocalDate date_output = LocalDate.parse(date_out, formatter);
		this.date_out = date_output;
        this.epigraph = epigraph;
    }

	public void setName(List<String> name) { this.name = name; }
	public List<String> getName() { return this.name; }
	
	public void setFramework(String framework) { this.framework = framework; }
	public String getFramework() { return this.framework; }

	public void setDate_in(LocalDate date_in) { this.date_in = date_in; }
	public LocalDate getDate_in() {	return this.date_in; }
	
	public void setDate_out(LocalDate date_out) { this.date_out = date_out; }
	public LocalDate getDate_out() { return this.date_out; }
	
	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return this.description; }
	
	public void setEpigraph(Epigraph epigraph) { this.epigraph = epigraph; }
	public Epigraph getEpigraph() {	return this.epigraph; }
	
}
