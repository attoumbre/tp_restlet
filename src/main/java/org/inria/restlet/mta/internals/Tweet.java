package org.inria.restlet.mta.internals;

public class Tweet {
	private int id_tweet;
	private String contenu;

	public Tweet(String contenu2) {
		this.contenu= contenu2;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getId_tweet() {
		return id_tweet;
	}

	public void setId_tweet(int id_tweet) {
		this.id_tweet = id_tweet;
	}

}
