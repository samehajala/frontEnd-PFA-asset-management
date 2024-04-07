package org.quarkusrenardtrial.restclient;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "http://localhost:8083") 
@Path("/assets")
public interface AssetRemoteService {

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   List<Asset> getAllAssetsFromRemote();
   
   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   Asset findAssetById(@PathParam("id") Long id);
   
   @GET
   @Path("/status/{status}")
   @Produces(MediaType.APPLICATION_JSON)
   List<Asset> findAssetsByStatus(@PathParam("status") AssetStatus status);
   
   @GET
   @Path("/type/{type}")
   @Produces(MediaType.APPLICATION_JSON)
   List<Asset> findAssetsByType(@PathParam("type") String type);
   
   @POST
   void saveAssetToRemote(Asset asset);
   
   @DELETE
   @Path("/{id}")
   void deleteAsset(@PathParam("id") long id);
   
   @PUT 
   Asset updateAssetStatus(Asset asset);
}
