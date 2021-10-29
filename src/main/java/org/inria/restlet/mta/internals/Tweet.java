package org.inria.restlet.mta.internals;

public class Tweet {
	
	// id interne
	private int id_;
	//contenu publié
	private String contenu_;

	public Tweet(String contenu) {
		contenu_ = contenu;
	}

	public String getContenu() {
		return contenu_ ;
	}

	public void setContenu(String contenu) {
		contenu_ = contenu;
	}

	public int getId() {
		return id_;
	}

	public void setId(int id) {
		id_ = id;
	}

}
