package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;


public class Model {
	EventsDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> vertici;
	private List<Adiacenza> archi;
	
	
	public Model() {
		dao = new EventsDao(); //mettere il dao nel costruttore del model	 
	}

	
	public List<Integer> getYears(){
		return this.dao.getYears();
	}
	
	public List<String> getCategorie(){
		return this.dao.getCategorie();
	}
	
	public void creaGrafo(int anno, String categoria) {
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.vertici = new ArrayList<>(this.dao.getVertici(anno, categoria));
		Graphs.addAllVertices(this.grafo, this.vertici);
		archi = new ArrayList<Adiacenza>(this.dao.getArchi(anno, categoria));
		for(Adiacenza a : archi) {
			Graphs.addEdgeWithVertices(this.grafo, a.getTipo1(), a.getTipo2(), a.getPeso());
		}
		
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
}
