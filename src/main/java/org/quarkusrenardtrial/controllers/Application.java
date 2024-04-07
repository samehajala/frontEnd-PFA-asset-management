package org.quarkusrenardtrial.controllers;

import java.time.Instant;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.quarkusrenardtrial.restclient.Asset;
import org.quarkusrenardtrial.restclient.AssetRemoteService;
import org.quarkusrenardtrial.restclient.AssetStatus;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Blocking
@ApplicationScoped
public class Application extends Controller {
    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance index();

        public static native TemplateInstance about(String param);

        public static native TemplateInstance home();

        public static native TemplateInstance allAssets(List<Asset> assets);

        public static native TemplateInstance updateAsset();
        public static native TemplateInstance deleteAsset() ; 

    }

    @Inject
    @RestClient
    private AssetRemoteService assetRemoteService;

    @Path("/index")
    public TemplateInstance index() {
        return Templates.index();
    }

    @Path("/updateAsset")
    public TemplateInstance updateAsset() {
        return Templates.updateAsset();
    }

    @Path("/about")
    public TemplateInstance about() {
        return Templates.about("o93ed nazel 3aychq");
    }

    @Path("/")
    public TemplateInstance home() {
        return Templates.home();
    }
    @Path("/deleteAsset")
    public TemplateInstance deleteAsset()
    {
        return Templates.deleteAsset() ; 
    }
    @Path("/index")
    @POST
    public void addAsset(@FormParam("createdDate") String createdDateStr,
            @FormParam("lastModifiedDate") String lastModifiedDateStr,
            @FormParam("status") String status,
            @FormParam("tenantOwner") String tenantOwner,
            @FormParam("type") String type) {
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        Asset asset = new Asset()
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .status(AssetStatus.valueOf(status))
                .tenantOwner(tenantOwner)
                .type(type);

        assetRemoteService.saveAssetToRemote(asset);
        allAssets();
    }

    @POST
    @Path("/updateAsset")
    public void updateAsset(@FormParam("assetId") String assetIdStr, @FormParam("status") String statusStr) {
        Long assetId = Long.parseLong(assetIdStr);
        Asset existingAsset = assetRemoteService.findAssetById(assetId);
        existingAsset.setAssetId(assetId);
        existingAsset.setStatus(AssetStatus.valueOf(statusStr));
        assetRemoteService.updateAssetStatus(existingAsset);
        allAssets();
    }

    @GET
    @Path("/allAssets")
    public TemplateInstance allAssets() {
        List<Asset> assets = assetRemoteService.getAllAssetsFromRemote();
        return Templates.allAssets(assets).data("assets", assets);
    }
    @POST
    @Path("/deleteAsset")
    public void deleteAsset(@FormParam("assetId") String assetIdStr)
    {
        Long assetId = Long.parseLong(assetIdStr);
        assetRemoteService.deleteAsset(assetId);
        allAssets() ; 
    }
}
