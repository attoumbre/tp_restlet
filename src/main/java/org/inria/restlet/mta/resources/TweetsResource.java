package org.inria.restlet.mta.resources;

import java.util.ArrayList;

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
    
    
    /**
    *
    * Returns the list of all the users
    *
    * @return  JSON representation of the users
    * @throws JSONException
    */
//   @Get("json")
//   public Representation getTweet() throws JSONException
//   {
//       Collection<User> users = backend_.getDatabase().getUsers();
//       Collection<JSONObject> jsonUsers = new ArrayList<JSONObject>();
//
//       for (User user : users)
//       {
//           JSONObject current = new JSONObject();
//           current.put("id", user.getId());
//           current.put("name", user.getName());
//           current.put("url", getReference() + "/" + user.getId());
//           jsonUsers.add(current);
//
//       }
//       JSONArray jsonArray = new JSONArray(jsonUsers);
//       return new JsonRepresentation(jsonArray);
//   }
    
    @Post("json")
    public Representation createTweet(JsonRepresentation representation)
        throws Exception
    {
    	
    	String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        //appele utilisateur et faire une methode dans user qui permet d'ajouter un tweet a celui ci 
       ArrayList<Tweet> tw= backend_.getDatabase().getUser(userId).getTweets();
        
        
        JSONObject object = representation.getJsonObject();
        String contenu = object.getString("contenu");
     

        // Save the tweet
      Tweet tweet = backend_.getDatabase().createTweet(contenu);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("contenu", tw.getContenu());
        resultObject.put("id_tweet", tweet.getId_tweet());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }


}
