package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Locations type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Locations")
public final class Locations implements Model {
  public static final QueryField ID = field("Locations", "id");
  public static final QueryField BASE_LOCATION_TYPE = field("Locations", "baseLocationType");
  public static final QueryField LOCATION_NAME = field("Locations", "locationName");
  public static final QueryField ADDRESS = field("Locations", "address");
  public static final QueryField LATITUDE = field("Locations", "latitude");
  public static final QueryField LONGITUDE = field("Locations", "longitude");
  public static final QueryField CLASSIFICATION = field("Locations", "classification");
  public static final QueryField OWNER = field("Locations", "owner");
  public static final QueryField CATEGORY1 = field("Locations", "category1");
  public static final QueryField CATEGORY2 = field("Locations", "category2");
  public static final QueryField CATEGORY3 = field("Locations", "category3");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String baseLocationType;
  private final @ModelField(targetType="String", isRequired = true) String locationName;
  private final @ModelField(targetType="String", isRequired = true) String address;
  private final @ModelField(targetType="Float", isRequired = true) Double latitude;
  private final @ModelField(targetType="Float", isRequired = true) Double longitude;
  private final @ModelField(targetType="String") String classification;
  private final @ModelField(targetType="Assets") @HasMany(associatedWith = "locationID", type = Assets.class) List<Assets> assets = null;
  private final @ModelField(targetType="String") String owner;
  private final @ModelField(targetType="String") String category1;
  private final @ModelField(targetType="String") String category2;
  private final @ModelField(targetType="String") String category3;
  private final @ModelField(targetType="Accounts") @HasMany(associatedWith = "locationID", type = Accounts.class) List<Accounts> accounts = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getBaseLocationType() {
      return baseLocationType;
  }
  
  public String getLocationName() {
      return locationName;
  }
  
  public String getAddress() {
      return address;
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
  
  public List<Assets> getAssets() {
      return assets;
  }
  
  public String getOwner() {
      return owner;
  }
  
  public String getCategory1() {
      return category1;
  }
  
  public String getCategory2() {
      return category2;
  }
  
  public String getCategory3() {
      return category3;
  }
  
  public List<Accounts> getAccounts() {
      return accounts;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Locations(String id, String baseLocationType, String locationName, String address, Double latitude, Double longitude, String classification, String owner, String category1, String category2, String category3) {
    this.id = id;
    this.baseLocationType = baseLocationType;
    this.locationName = locationName;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.classification = classification;
    this.owner = owner;
    this.category1 = category1;
    this.category2 = category2;
    this.category3 = category3;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Locations locations = (Locations) obj;
      return ObjectsCompat.equals(getId(), locations.getId()) &&
              ObjectsCompat.equals(getBaseLocationType(), locations.getBaseLocationType()) &&
              ObjectsCompat.equals(getLocationName(), locations.getLocationName()) &&
              ObjectsCompat.equals(getAddress(), locations.getAddress()) &&
              ObjectsCompat.equals(getLatitude(), locations.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), locations.getLongitude()) &&
              ObjectsCompat.equals(getClassification(), locations.getClassification()) &&
              ObjectsCompat.equals(getOwner(), locations.getOwner()) &&
              ObjectsCompat.equals(getCategory1(), locations.getCategory1()) &&
              ObjectsCompat.equals(getCategory2(), locations.getCategory2()) &&
              ObjectsCompat.equals(getCategory3(), locations.getCategory3()) &&
              ObjectsCompat.equals(getCreatedAt(), locations.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), locations.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getBaseLocationType())
      .append(getLocationName())
      .append(getAddress())
      .append(getLatitude())
      .append(getLongitude())
      .append(getClassification())
      .append(getOwner())
      .append(getCategory1())
      .append(getCategory2())
      .append(getCategory3())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Locations {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("baseLocationType=" + String.valueOf(getBaseLocationType()) + ", ")
      .append("locationName=" + String.valueOf(getLocationName()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("classification=" + String.valueOf(getClassification()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("category1=" + String.valueOf(getCategory1()) + ", ")
      .append("category2=" + String.valueOf(getCategory2()) + ", ")
      .append("category3=" + String.valueOf(getCategory3()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BaseLocationTypeStep builder() {
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
  public static Locations justId(String id) {
    return new Locations(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      baseLocationType,
      locationName,
      address,
      latitude,
      longitude,
      classification,
      owner,
      category1,
      category2,
      category3);
  }
  public interface BaseLocationTypeStep {
    LocationNameStep baseLocationType(String baseLocationType);
  }
  

  public interface LocationNameStep {
    AddressStep locationName(String locationName);
  }
  

  public interface AddressStep {
    LatitudeStep address(String address);
  }
  

  public interface LatitudeStep {
    LongitudeStep latitude(Double latitude);
  }
  

  public interface LongitudeStep {
    BuildStep longitude(Double longitude);
  }
  

  public interface BuildStep {
    Locations build();
    BuildStep id(String id);
    BuildStep classification(String classification);
    BuildStep owner(String owner);
    BuildStep category1(String category1);
    BuildStep category2(String category2);
    BuildStep category3(String category3);
  }
  

  public static class Builder implements BaseLocationTypeStep, LocationNameStep, AddressStep, LatitudeStep, LongitudeStep, BuildStep {
    private String id;
    private String baseLocationType;
    private String locationName;
    private String address;
    private Double latitude;
    private Double longitude;
    private String classification;
    private String owner;
    private String category1;
    private String category2;
    private String category3;
    @Override
     public Locations build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Locations(
          id,
          baseLocationType,
          locationName,
          address,
          latitude,
          longitude,
          classification,
          owner,
          category1,
          category2,
          category3);
    }
    
    @Override
     public LocationNameStep baseLocationType(String baseLocationType) {
        Objects.requireNonNull(baseLocationType);
        this.baseLocationType = baseLocationType;
        return this;
    }
    
    @Override
     public AddressStep locationName(String locationName) {
        Objects.requireNonNull(locationName);
        this.locationName = locationName;
        return this;
    }
    
    @Override
     public LatitudeStep address(String address) {
        Objects.requireNonNull(address);
        this.address = address;
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
     public BuildStep category1(String category1) {
        this.category1 = category1;
        return this;
    }
    
    @Override
     public BuildStep category2(String category2) {
        this.category2 = category2;
        return this;
    }
    
    @Override
     public BuildStep category3(String category3) {
        this.category3 = category3;
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
    private CopyOfBuilder(String id, String baseLocationType, String locationName, String address, Double latitude, Double longitude, String classification, String owner, String category1, String category2, String category3) {
      super.id(id);
      super.baseLocationType(baseLocationType)
        .locationName(locationName)
        .address(address)
        .latitude(latitude)
        .longitude(longitude)
        .classification(classification)
        .owner(owner)
        .category1(category1)
        .category2(category2)
        .category3(category3);
    }
    
    @Override
     public CopyOfBuilder baseLocationType(String baseLocationType) {
      return (CopyOfBuilder) super.baseLocationType(baseLocationType);
    }
    
    @Override
     public CopyOfBuilder locationName(String locationName) {
      return (CopyOfBuilder) super.locationName(locationName);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
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
     public CopyOfBuilder classification(String classification) {
      return (CopyOfBuilder) super.classification(classification);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
    
    @Override
     public CopyOfBuilder category1(String category1) {
      return (CopyOfBuilder) super.category1(category1);
    }
    
    @Override
     public CopyOfBuilder category2(String category2) {
      return (CopyOfBuilder) super.category2(category2);
    }
    
    @Override
     public CopyOfBuilder category3(String category3) {
      return (CopyOfBuilder) super.category3(category3);
    }
  }
  
}
