package org.quarkusrenardtrial.restclient;

import java.time.Instant;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;


@Entity
public class Asset extends PanacheEntity{
    
    
    private long assetId;
    
    private Instant createdDate;
    
    
    private Instant lastModifiedDate;

  
    private AssetStatus status;

   
    private String tenantOwner; 

   
    private String type;
    public Asset() {
    }

    public Asset(long assetId, Instant createdDate, Instant lastModifiedDate, AssetStatus status, String tenantOwner, String type) {
        this.assetId = assetId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.status = status;
        this.tenantOwner = tenantOwner;
        this.type = type;
    }
   
    public long getAssetId() {
        return this.assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public AssetStatus getStatus() {
        return this.status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public String getTenantOwner() {
        return this.tenantOwner;
    }

    public void setTenantOwner(String tenantOwner) {
        this.tenantOwner = tenantOwner;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Asset assetId(long assetId) {
        setAssetId(assetId);
        return this;
    }

    public Asset createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public Asset lastModifiedDate(Instant lastModifiedDate) {
        setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public Asset status(AssetStatus status) {
        setStatus(status);
        return this;
    }

    public Asset tenantOwner(String tenantOwner) {
        setTenantOwner(tenantOwner);
        return this;
    }

    public Asset type(String type) {
        setType(type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) o;
        return assetId == asset.assetId && Objects.equals(createdDate, asset.createdDate) && Objects.equals(lastModifiedDate, asset.lastModifiedDate) && Objects.equals(status, asset.status) && Objects.equals(tenantOwner, asset.tenantOwner) && Objects.equals(type, asset.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId, createdDate, lastModifiedDate, status, tenantOwner, type);
    }

    @Override
    public String toString() {
        return "{" +
            " assetId='" + getAssetId() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", tenantOwner='" + getTenantOwner() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
    
}
