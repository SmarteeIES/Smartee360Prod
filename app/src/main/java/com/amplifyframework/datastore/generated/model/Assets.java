package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Assets type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Assets")
@Index(name = "locationIDIndex", fields = {"locationID"})
@Index(name = "assetByMacIndex", fields = {"baseAssetType","macAddress"})
@Index(name = "assetBylocationIDIndex", fields = {"baseAssetType","locationID"})
@Index(name = "assetByOwnerIndex", fields = {"baseAssetType","owner"})
public final class Assets implements Model {
  public static final QueryField ID = field("Assets", "id");
  public static final QueryField BASE_ASSET_TYPE = field("Assets", "baseAssetType");
  public static final QueryField ASSET_NAME = field("Assets", "assetName");
  public static final QueryField ASSET_ID = field("Assets", "assetID");
  public static final QueryField MAC_ADDRESS = field("Assets", "macAddress");
  public static final QueryField SERVICE_ID = field("Assets", "serviceID");
  public static final QueryField LOCATION_ID = field("Assets", "locationID");
  public static final QueryField UUID_NUMBER = field("Assets", "uuidNumber");
  public static final QueryField MAJOR = field("Assets", "major");
  public static final QueryField MINOR = field("Assets", "minor");
  public static final QueryField RSSI_MAX = field("Assets", "rssiMax");
  public static final QueryField RSSI_MIN = field("Assets", "rssiMin");
  public static final QueryField RSSI_AVG = field("Assets", "rssiAvg");
  public static final QueryField LATITUDE = field("Assets", "latitude");
  public static final QueryField LONGITUDE = field("Assets", "longitude");
  public static final QueryField CLASSIFICATION = field("Assets", "classification");
  public static final QueryField OWNER = field("Assets", "owner");
  public static final QueryField LOCATION_NAME = field("Assets", "locationName");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String baseAssetType;
  private final @ModelField(targetType="String", isRequired = true) String assetName;
  private final @ModelField(targetType="String", isRequired = true) String assetID;
  private final @ModelField(targetType="String", isRequired = true) String macAddress;
  private final @ModelField(targetType="String") String serviceID;
  private final @ModelField(targetType="ID", isRequired = true) String locationID;
  private final @ModelField(targetType="String") String uuidNumber;
  private final @ModelField(targetType="String") String major;
  private final @ModelField(targetType="String") String minor;
  private final @ModelField(targetType="Float") Double rssiMax;
  private final @ModelField(targetType="Float") Double rssiMin;
  private final @ModelField(targetType="Float") Double rssiAvg;
  private final @ModelField(targetType="Float", isRequired = true) Double latitude;
  private final @ModelField(targetType="Float", isRequired = true) Double longitude;
  private final @ModelField(targetType="String") String classification;
  private final @ModelField(targetType="String") String owner;
  private final @ModelField(targetType="String") String locationName;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getBaseAssetType() {
      return baseAssetType;
  }
  
  public String getAssetName() {
      return assetName;
  }
  
  public String getAssetId() {
      return assetID;
  }
  
  public String getMacAddress() {
      return macAddress;
  }
  
  public String getServiceId() {
      return serviceID;
  }
  
  public String getLocationId() {
      return locationID;
  }
  
  public String getUuidNumber() {
      return uuidNumber;
  }
  
  public String getMajor() {
      return major;
  }
  
  public String getMinor() {
      return minor;
  }
  
  public Double getRssiMax() {
      return rssiMax;
  }
  
  public Double getRssiMin() {
      return rssiMin;
  }
  
  public Double getRssiAvg() {
      return rssiAvg;
  }
  
  public Double getLatitude() {
      return latitude;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  public String getClassification() {
      return classification;
  }
  
  public String getOwner() {
      return owner;
  }
  
  public String getLocationName() {
      return locationName;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Assets(String id, String baseAssetType, String assetName, String assetID, String macAddress, String serviceID, String locationID, String uuidNumber, String major, String minor, Double rssiMax, Double rssiMin, Double rssiAvg, Double latitude, Double longitude, String classification, String owner, String locationName) {
    this.id = id;
    this.baseAssetType = baseAssetType;
    this.assetName = assetName;
    this.assetID = assetID;
    this.macAddress = macAddress;
    this.serviceID = serviceID;
    this.locationID = locationID;
    this.uuidNumber = uuidNumber;
    this.major = major;
    this.minor = minor;
    this.rssiMax = rssiMax;
    this.rssiMin = rssiMin;
    this.rssiAvg = rssiAvg;
    this.latitude = latitude;
    this.longitude = longitude;
    this.classification = classification;
    this.owner = owner;
    this.locationName = locationName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Assets assets = (Assets) obj;
      return ObjectsCompat.equals(getId(), assets.getId()) &&
              ObjectsCompat.equals(getBaseAssetType(), assets.getBaseAssetType()) &&
              ObjectsCompat.equals(getAssetName(), assets.getAssetName()) &&
              ObjectsCompat.equals(getAssetId(), assets.getAssetId()) &&
              ObjectsCompat.equals(getMacAddress(), assets.getMacAddress()) &&
              ObjectsCompat.equals(getServiceId(), assets.getServiceId()) &&
              ObjectsCompat.equals(getLocationId(), assets.getLocationId()) &&
              ObjectsCompat.equals(getUuidNumber(), assets.getUuidNumber()) &&
              ObjectsCompat.equals(getMajor(), assets.getMajor()) &&
              ObjectsCompat.equals(getMinor(), assets.getMinor()) &&
              ObjectsCompat.equals(getRssiMax(), assets.getRssiMax()) &&
              ObjectsCompat.equals(getRssiMin(), assets.getRssiMin()) &&
              ObjectsCompat.equals(getRssiAvg(), assets.getRssiAvg()) &&
              ObjectsCompat.equals(getLatitude(), assets.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), assets.getLongitude()) &&
              ObjectsCompat.equals(getClassification(), assets.getClassification()) &&
              ObjectsCompat.equals(getOwner(), assets.getOwner()) &&
              ObjectsCompat.equals(getLocationName(), assets.getLocationName()) &&
              ObjectsCompat.equals(getCreatedAt(), assets.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), assets.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getBaseAssetType())
      .append(getAssetName())
      .append(getAssetId())
      .append(getMacAddress())
      .append(getServiceId())
      .append(getLocationId())
      .append(getUuidNumber())
      .append(getMajor())
      .append(getMinor())
      .append(getRssiMax())
      .append(getRssiMin())
      .append(getRssiAvg())
      .append(getLatitude())
      .append(getLongitude())
      .append(getClassification())
      .append(getOwner())
      .append(getLocationName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Assets {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("baseAssetType=" + String.valueOf(getBaseAssetType()) + ", ")
      .append("assetName=" + String.valueOf(getAssetName()) + ", ")
      .append("assetID=" + String.valueOf(getAssetId()) + ", ")
      .append("macAddress=" + String.valueOf(getMacAddress()) + ", ")
      .append("serviceID=" + String.valueOf(getServiceId()) + ", ")
      .append("locationID=" + String.valueOf(getLocationId()) + ", ")
      .append("uuidNumber=" + String.valueOf(getUuidNumber()) + ", ")
      .append("major=" + String.valueOf(getMajor()) + ", ")
      .append("minor=" + String.valueOf(getMinor()) + ", ")
      .append("rssiMax=" + String.valueOf(getRssiMax()) + ", ")
      .append("rssiMin=" + String.valueOf(getRssiMin()) + ", ")
      .append("rssiAvg=" + String.valueOf(getRssiAvg()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("classification=" + String.valueOf(getClassification()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("locationName=" + String.valueOf(getLocationName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BaseAssetTypeStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Assets justId(String id) {
    return new Assets(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      baseAssetType,
      assetName,
      assetID,
      macAddress,
      serviceID,
      locationID,
      uuidNumber,
      major,
      minor,
      rssiMax,
      rssiMin,
      rssiAvg,
      latitude,
      longitude,
      classification,
      owner,
      locationName);
  }
  public interface BaseAssetTypeStep {
    AssetNameStep baseAssetType(String baseAssetType);
  }
  

  public interface AssetNameStep {
    AssetIdStep assetName(String assetName);
  }
  

  public interface AssetIdStep {
    MacAddressStep assetId(String assetId);
  }
  

  public interface MacAddressStep {
    LocationIdStep macAddress(String macAddress);
  }
  

  public interface LocationIdStep {
    LatitudeStep locationId(String locationId);
  }
  

  public interface LatitudeStep {
    LongitudeStep latitude(Double latitude);
  }
  

  public interface LongitudeStep {
    BuildStep longitude(Double longitude);
  }
  

  public interface BuildStep {
    Assets build();
    BuildStep id(String id);
    BuildStep serviceId(String serviceId);
    BuildStep uuidNumber(String uuidNumber);
    BuildStep major(String major);
    BuildStep minor(String minor);
    BuildStep rssiMax(Double rssiMax);
    BuildStep rssiMin(Double rssiMin);
    BuildStep rssiAvg(Double rssiAvg);
    BuildStep classification(String classification);
    BuildStep owner(String owner);
    BuildStep locationName(String locationName);
  }
  

  public static class Builder implements BaseAssetTypeStep, AssetNameStep, AssetIdStep, MacAddressStep, LocationIdStep, LatitudeStep, LongitudeStep, BuildStep {
    private String id;
    private String baseAssetType;
    private String assetName;
    private String assetID;
    private String macAddress;
    private String locationID;
    private Double latitude;
    private Double longitude;
    private String serviceID;
    private String uuidNumber;
    private String major;
    private String minor;
    private Double rssiMax;
    private Double rssiMin;
    private Double rssiAvg;
    private String classification;
    private String owner;
    private String locationName;
    @Override
     public Assets build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Assets(
          id,
          baseAssetType,
          assetName,
          assetID,
          macAddress,
          serviceID,
          locationID,
          uuidNumber,
          major,
          minor,
          rssiMax,
          rssiMin,
          rssiAvg,
          latitude,
          longitude,
          classification,
          owner,
          locationName);
    }
    
    @Override
     public AssetNameStep baseAssetType(String baseAssetType) {
        Objects.requireNonNull(baseAssetType);
        this.baseAssetType = baseAssetType;
        return this;
    }
    
    @Override
     public AssetIdStep assetName(String assetName) {
        Objects.requireNonNull(assetName);
        this.assetName = assetName;
        return this;
    }
    
    @Override
     public MacAddressStep assetId(String assetId) {
        Objects.requireNonNull(assetId);
        this.assetID = assetId;
        return this;
    }
    
    @Override
     public LocationIdStep macAddress(String macAddress) {
        Objects.requireNonNull(macAddress);
        this.macAddress = macAddress;
        return this;
    }
    
    @Override
     public LatitudeStep locationId(String locationId) {
        Objects.requireNonNull(locationId);
        this.locationID = locationId;
        return this;
    }
    
    @Override
     public LongitudeStep latitude(Double latitude) {
        Objects.requireNonNull(latitude);
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        Objects.requireNonNull(longitude);
        this.longitude = longitude;
        return this;
    }
    
    @Override
     public BuildStep serviceId(String serviceId) {
        this.serviceID = serviceId;
        return this;
    }
    
    @Override
     public BuildStep uuidNumber(String uuidNumber) {
        this.uuidNumber = uuidNumber;
        return this;
    }
    
    @Override
     public BuildStep major(String major) {
        this.major = major;
        return this;
    }
    
    @Override
     public BuildStep minor(String minor) {
        this.minor = minor;
        return this;
    }
    
    @Override
     public BuildStep rssiMax(Double rssiMax) {
        this.rssiMax = rssiMax;
        return this;
    }
    
    @Override
     public BuildStep rssiMin(Double rssiMin) {
        this.rssiMin = rssiMin;
        return this;
    }
    
    @Override
     public BuildStep rssiAvg(Double rssiAvg) {
        this.rssiAvg = rssiAvg;
        return this;
    }
    
    @Override
     public BuildStep classification(String classification) {
        this.classification = classification;
        return this;
    }
    
    @Override
     public BuildStep owner(String owner) {
        this.owner = owner;
        return this;
    }
    
    @Override
     public BuildStep locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String baseAssetType, String assetName, String assetId, String macAddress, String serviceId, String locationId, String uuidNumber, String major, String minor, Double rssiMax, Double rssiMin, Double rssiAvg, Double latitude, Double longitude, String classification, String owner, String locationName) {
      super.id(id);
      super.baseAssetType(baseAssetType)
        .assetName(assetName)
        .assetId(assetId)
        .macAddress(macAddress)
        .locationId(locationId)
        .latitude(latitude)
        .longitude(longitude)
        .serviceId(serviceId)
        .uuidNumber(uuidNumber)
        .major(major)
        .minor(minor)
        .rssiMax(rssiMax)
        .rssiMin(rssiMin)
        .rssiAvg(rssiAvg)
        .classification(classification)
        .owner(owner)
        .locationName(locationName);
    }
    
    @Override
     public CopyOfBuilder baseAssetType(String baseAssetType) {
      return (CopyOfBuilder) super.baseAssetType(baseAssetType);
    }
    
    @Override
     public CopyOfBuilder assetName(String assetName) {
      return (CopyOfBuilder) super.assetName(assetName);
    }
    
    @Override
     public CopyOfBuilder assetId(String assetId) {
      return (CopyOfBuilder) super.assetId(assetId);
    }
    
    @Override
     public CopyOfBuilder macAddress(String macAddress) {
      return (CopyOfBuilder) super.macAddress(macAddress);
    }
    
    @Override
     public CopyOfBuilder locationId(String locationId) {
      return (CopyOfBuilder) super.locationId(locationId);
    }
    
    @Override
     public CopyOfBuilder latitude(Double latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder serviceId(String serviceId) {
      return (CopyOfBuilder) super.serviceId(serviceId);
    }
    
    @Override
     public CopyOfBuilder uuidNumber(String uuidNumber) {
      return (CopyOfBuilder) super.uuidNumber(uuidNumber);
    }
    
    @Override
     public CopyOfBuilder major(String major) {
      return (CopyOfBuilder) super.major(major);
    }
    
    @Override
     public CopyOfBuilder minor(String minor) {
      return (CopyOfBuilder) super.minor(minor);
    }
    
    @Override
     public CopyOfBuilder rssiMax(Double rssiMax) {
      return (CopyOfBuilder) super.rssiMax(rssiMax);
    }
    
    @Override
     public CopyOfBuilder rssiMin(Double rssiMin) {
      return (CopyOfBuilder) super.rssiMin(rssiMin);
    }
    
    @Override
     public CopyOfBuilder rssiAvg(Double rssiAvg) {
      return (CopyOfBuilder) super.rssiAvg(rssiAvg);
    }
    
    @Override
     public CopyOfBuilder classification(String classification) {
      return (CopyOfBuilder) super.classification(classification);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
    
    @Override
     public CopyOfBuilder locationName(String locationName) {
      return (CopyOfBuilder) super.locationName(locationName);
    }
  }
  
}
