package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class TweetsResource extends ServerResource{
	
	 /** Backend.*/
    private Backend backend_;

    /** Utilisateur géré par cette resource.*/
    private User user_;
   
    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsResource() {
      super();
	  backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }
    
    
    @Post("json")
    public Representation createTweet(JsonRepresentation representation)throws Exception
    {
    	
    	String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        //appele utilisateur et faire une methode dans user qui permet d'ajouter un tweet a celui
        //Tweet tw= backend_.getDatabase().getUser(userId).getTweets().get(userId);
        
        //appel du user qui a l'id recupéré
       user_ = backend_.getDatabase().getUser(userId);
       
        JSONObject object = representation.getJsonObject();
        String contenu = object.getString("contenu");
       
        // Save the tweet
        Tweet tweets = backend_.getDatabase().createTweet(contenu);
        //ajout du tweet a la liste des tweets du user
        user_.addTweet(tweets);
        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("contenu", tweets.getContenu());
        resultObject.put("id", tweets.getId());
        JsonRepresentation result = new JsonRepresentation(resultObject);
       return result;
    }

}
