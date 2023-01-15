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

/** This is an auto generated class representing the Accounts type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Accounts")
@Index(name = "locationIDIndex", fields = {"locationID"})
public final class Accounts implements Model {
  public static final QueryField ID = field("Accounts", "id");
  public static final QueryField BASE_ACCOUNT_TYPE = field("Accounts", "baseAccountType");
  public static final QueryField ACCOUNT_ID = field("Accounts", "accountID");
  public static final QueryField ACCOUNT_NAME = field("Accounts", "accountName");
  public static final QueryField LOCATION_ID = field("Accounts", "locationID");
  public static final QueryField LATITUDE = field("Accounts", "latitude");
  public static final QueryField LONGITUDE = field("Accounts", "longitude");
  public static final QueryField CATEGORY1 = field("Accounts", "category1");
  public static final QueryField CATEGORY2 = field("Accounts", "category2");
  public static final QueryField CATEGORY3 = field("Accounts", "category3");
  public static final QueryField ACTIVE_STATUS = field("Accounts", "activeStatus");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String baseAccountType;
  private final @ModelField(targetType="String", isRequired = true) String accountID;
  private final @ModelField(targetType="String", isRequired = true) String accountName;
  private final @ModelField(targetType="ID", isRequired = true) String locationID;
  private final @ModelField(targetType="Float") Double latitude;
  private final @ModelField(targetType="Float") Double longitude;
  private final @ModelField(targetType="String") String category1;
  private final @ModelField(targetType="String") String category2;
  private final @ModelField(targetType="String") String category3;
  private final @ModelField(targetType="String") String activeStatus;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getBaseAccountType() {
      return baseAccountType;
  }
  
  public String getAccountId() {
      return accountID;
  }
  
  public String getAccountName() {
      return accountName;
  }
  
  public String getLocationId() {
      return locationID;
  }
  
  public Double getLatitude() {
      return latitude;
  }
  
  public Double getLongitude() {
      return longitude;
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
  
  public String getActiveStatus() {
      return activeStatus;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Accounts(String id, String baseAccountType, String accountID, String accountName, String locationID, Double latitude, Double longitude, String category1, String category2, String category3, String activeStatus) {
    this.id = id;
    this.baseAccountType = baseAccountType;
    this.accountID = accountID;
    this.accountName = accountName;
    this.locationID = locationID;
    this.latitude = latitude;
    this.longitude = longitude;
    this.category1 = category1;
    this.category2 = category2;
    this.category3 = category3;
    this.activeStatus = activeStatus;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Accounts accounts = (Accounts) obj;
      return ObjectsCompat.equals(getId(), accounts.getId()) &&
              ObjectsCompat.equals(getBaseAccountType(), accounts.getBaseAccountType()) &&
              ObjectsCompat.equals(getAccountId(), accounts.getAccountId()) &&
              ObjectsCompat.equals(getAccountName(), accounts.getAccountName()) &&
              ObjectsCompat.equals(getLocationId(), accounts.getLocationId()) &&
              ObjectsCompat.equals(getLatitude(), accounts.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), accounts.getLongitude()) &&
              ObjectsCompat.equals(getCategory1(), accounts.getCategory1()) &&
              ObjectsCompat.equals(getCategory2(), accounts.getCategory2()) &&
              ObjectsCompat.equals(getCategory3(), accounts.getCategory3()) &&
              ObjectsCompat.equals(getActiveStatus(), accounts.getActiveStatus()) &&
              ObjectsCompat.equals(getCreatedAt(), accounts.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), accounts.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getBaseAccountType())
      .append(getAccountId())
      .append(getAccountName())
      .append(getLocationId())
      .append(getLatitude())
      .append(getLongitude())
      .append(getCategory1())
      .append(getCategory2())
      .append(getCategory3())
      .append(getActiveStatus())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Accounts {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("baseAccountType=" + String.valueOf(getBaseAccountType()) + ", ")
      .append("accountID=" + String.valueOf(getAccountId()) + ", ")
      .append("accountName=" + String.valueOf(getAccountName()) + ", ")
      .append("locationID=" + String.valueOf(getLocationId()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("category1=" + String.valueOf(getCategory1()) + ", ")
      .append("category2=" + String.valueOf(getCategory2()) + ", ")
      .append("category3=" + String.valueOf(getCategory3()) + ", ")
      .append("activeStatus=" + String.valueOf(getActiveStatus()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BaseAccountTypeStep builder() {
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
  public static Accounts justId(String id) {
    return new Accounts(
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
      baseAccountType,
      accountID,
      accountName,
      locationID,
      latitude,
      longitude,
      category1,
      category2,
      category3,
      activeStatus);
  }
  public interface BaseAccountTypeStep {
    AccountIdStep baseAccountType(String baseAccountType);
  }
  

  public interface AccountIdStep {
    AccountNameStep accountId(String accountId);
  }
  

  public interface AccountNameStep {
    LocationIdStep accountName(String accountName);
  }
  

  public interface LocationIdStep {
    BuildStep locationId(String locationId);
  }
  

  public interface BuildStep {
    Accounts build();
    BuildStep id(String id);
    BuildStep latitude(Double latitude);
    BuildStep longitude(Double longitude);
    BuildStep category1(String category1);
    BuildStep category2(String category2);
    BuildStep category3(String category3);
    BuildStep activeStatus(String activeStatus);
  }
  

  public static class Builder implements BaseAccountTypeStep, AccountIdStep, AccountNameStep, LocationIdStep, BuildStep {
    private String id;
    private String baseAccountType;
    private String accountID;
    private String accountName;
    private String locationID;
    private Double latitude;
    private Double longitude;
    private String category1;
    private String category2;
    private String category3;
    private String activeStatus;
    @Override
     public Accounts build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Accounts(
          id,
          baseAccountType,
          accountID,
          accountName,
          locationID,
          latitude,
          longitude,
          category1,
          category2,
          category3,
          activeStatus);
    }
    
    @Override
     public AccountIdStep baseAccountType(String baseAccountType) {
        Objects.requireNonNull(baseAccountType);
        this.baseAccountType = baseAccountType;
        return this;
    }
    
    @Override
     public AccountNameStep accountId(String accountId) {
        Objects.requireNonNull(accountId);
        this.accountID = accountId;
        return this;
    }
    
    @Override
     public LocationIdStep accountName(String accountName) {
        Objects.requireNonNull(accountName);
        this.accountName = accountName;
        return this;
    }
    
    @Override
     public BuildStep locationId(String locationId) {
        Objects.requireNonNull(locationId);
        this.locationID = locationId;
        return this;
    }
    
    @Override
     public BuildStep latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        this.longitude = longitude;
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
    
    @Override
     public BuildStep activeStatus(String activeStatus) {
        this.activeStatus = activeStatus;
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
    private CopyOfBuilder(String id, String baseAccountType, String accountId, String accountName, String locationId, Double latitude, Double longitude, String category1, String category2, String category3, String activeStatus) {
      super.id(id);
      super.baseAccountType(baseAccountType)
        .accountId(accountId)
        .accountName(accountName)
        .locationId(locationId)
        .latitude(latitude)
        .longitude(longitude)
        .category1(category1)
        .category2(category2)
        .category3(category3)
        .activeStatus(activeStatus);
    }
    
    @Override
     public CopyOfBuilder baseAccountType(String baseAccountType) {
      return (CopyOfBuilder) super.baseAccountType(baseAccountType);
    }
    
    @Override
     public CopyOfBuilder accountId(String accountId) {
      return (CopyOfBuilder) super.accountId(accountId);
    }
    
    @Override
     public CopyOfBuilder accountName(String accountName) {
      return (CopyOfBuilder) super.accountName(accountName);
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
    
    @Override
     public CopyOfBuilder activeStatus(String activeStatus) {
      return (CopyOfBuilder) super.activeStatus(activeStatus);
    }
  }
  
}
