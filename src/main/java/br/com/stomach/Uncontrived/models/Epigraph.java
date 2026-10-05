package br.com.stomach.Uncontrived.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Epigraph {

	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate date_in;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate date_out;
	private String title;
	
	public Epigraph() {}
	
	public Epigraph(String name, String title, LocalDate date_in, LocalDate date_out) {
		this.name = name;
		this.title = title;
		this.date_in = date_in;
		this.date_out = date_out;
	}
	
	public Epigraph(String name, String title, String date_in, String date_out) {
		this.name = name;
		this.title = title;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date_input = LocalDate.parse(date_in, formatter);
		this.date_in = date_input;
		LocalDate date_output = LocalDate.parse(date_out, formatter);
		this.date_out = date_output;
	}
	
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	
	public void setDate_in(LocalDate datein) { this.date_in = datein; }
	public LocalDate getDate_in() { return this.date_in; }
	
	public void setDate_out(LocalDate date_out) { this.date_out = date_out; }
	public LocalDate getDate_out() { return this.date_out; }
	
	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }
}
