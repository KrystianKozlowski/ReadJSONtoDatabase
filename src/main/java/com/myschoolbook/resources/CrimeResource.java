package com.myschoolbook.resources;


import com.myschoolbook.service.CrimeService;

import javax.ws.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Krystian on 2017-06-26.
 */

@Path("/restapi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrimeResource {

    CrimeService crimeService = new CrimeService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @POST
    public Profile addMessage(Profile profile) {
        return profileService.addProfile(profile);
    }


    @GET
    @Path("/{profileName}")
    public Profile getMessageById(@PathParam("profileName") String profileName) {
        return profileService.getProfileByName(profileName);
    }
}