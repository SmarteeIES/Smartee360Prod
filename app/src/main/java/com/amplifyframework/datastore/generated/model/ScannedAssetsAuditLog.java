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

/** This is an auto generated class representing the ScannedAssetsAuditLog type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ScannedAssetsAuditLogs")
@Index(name = "auditLogIDIndex", fields = {"auditLogID"})
public final class ScannedAssetsAuditLog implements Model {
  public static final QueryField ID = field("ScannedAssetsAuditLog", "id");
  public static final QueryField BASE_ASSET_TYPE = field("ScannedAssetsAuditLog", "baseAssetType");
  public static final QueryField ASSET_NAME = field("ScannedAssetsAuditLog", "assetName");
  public static final QueryField ASSET_ID = field("ScannedAssetsAuditLog", "assetID");
  public static final QueryField MAC_ADDRESS = field("ScannedAssetsAuditLog", "macAddress");
  public static final QueryField SERVICE_ID = field("ScannedAssetsAuditLog", "serviceID");
  public static final QueryField AUDIT_LOG_ID = field("ScannedAssetsAuditLog", "auditLogID");
  public static final QueryField UUID_NUMBER = field("ScannedAssetsAuditLog", "uuidNumber");
  public static final QueryField MAJOR = field("ScannedAssetsAuditLog", "major");
  public static final QueryField MINOR = field("ScannedAssetsAuditLog", "minor");
  public static final QueryField RSSI1 = field("ScannedAssetsAuditLog", "rssi1");
  public static final QueryField RSSI2 = field("ScannedAssetsAuditLog", "rssi2");
  public static final QueryField RSSI3 = field("ScannedAssetsAuditLog", "rssi3");
  public static final QueryField CLASSIFICATION = field("ScannedAssetsAuditLog", "classification");
  public static final QueryField OWNER = field("ScannedAssetsAuditLog", "owner");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String baseAssetType;
  private final @ModelField(targetType="String", isRequired = true) String assetName;
  private final @ModelField(targetType="String", isRequired = true) String assetID;
  private final @ModelField(targetType="String", isRequired = true) String macAddress;
  private final @ModelField(targetType="String") String serviceID;
  private final @ModelField(targetType="ID", isRequired = true) String auditLogID;
  private final @ModelField(targetType="String") String uuidNumber;
  private final @ModelField(targetType="String") String major;
  private final @ModelField(targetType="String") String minor;
  private final @ModelField(targetType="Float") Double rssi1;
  private final @ModelField(targetType="Float") Double rssi2;
  private final @ModelField(targetType="Float") Double rssi3;
  private final @ModelField(targetType="String") String classification;
  private final @ModelField(targetType="String") String owner;
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
  
  public String getAuditLogId() {
      return auditLogID;
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
  
  public Double getRssi1() {
      return rssi1;
  }
  
  public Double getRssi2() {
      return rssi2;
  }
  
  public Double getRssi3() {
      return rssi3;
  }
  
  public String getClassification() {
      return classification;
  }
  
  public String getOwner() {
      return owner;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ScannedAssetsAuditLog(String id, String baseAssetType, String assetName, String assetID, String macAddress, String serviceID, String auditLogID, String uuidNumber, String major, String minor, Double rssi1, Double rssi2, Double rssi3, String classification, String owner) {
    this.id = id;
    this.baseAssetType = baseAssetType;
    this.assetName = assetName;
    this.assetID = assetID;
    this.macAddress = macAddress;
    this.serviceID = serviceID;
    this.auditLogID = auditLogID;
    this.uuidNumber = uuidNumber;
    this.major = major;
    this.minor = minor;
    this.rssi1 = rssi1;
    this.rssi2 = rssi2;
    this.rssi3 = rssi3;
    this.classification = classification;
    this.owner = owner;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ScannedAssetsAuditLog scannedAssetsAuditLog = (ScannedAssetsAuditLog) obj;
      return ObjectsCompat.equals(getId(), scannedAssetsAuditLog.getId()) &&
              ObjectsCompat.equals(getBaseAssetType(), scannedAssetsAuditLog.getBaseAssetType()) &&
              ObjectsCompat.equals(getAssetName(), scannedAssetsAuditLog.getAssetName()) &&
              ObjectsCompat.equals(getAssetId(), scannedAssetsAuditLog.getAssetId()) &&
              ObjectsCompat.equals(getMacAddress(), scannedAssetsAuditLog.getMacAddress()) &&
              ObjectsCompat.equals(getServiceId(), scannedAssetsAuditLog.getServiceId()) &&
              ObjectsCompat.equals(getAuditLogId(), scannedAssetsAuditLog.getAuditLogId()) &&
              ObjectsCompat.equals(getUuidNumber(), scannedAssetsAuditLog.getUuidNumber()) &&
              ObjectsCompat.equals(getMajor(), scannedAssetsAuditLog.getMajor()) &&
              ObjectsCompat.equals(getMinor(), scannedAssetsAuditLog.getMinor()) &&
              ObjectsCompat.equals(getRssi1(), scannedAssetsAuditLog.getRssi1()) &&
              ObjectsCompat.equals(getRssi2(), scannedAssetsAuditLog.getRssi2()) &&
              ObjectsCompat.equals(getRssi3(), scannedAssetsAuditLog.getRssi3()) &&
              ObjectsCompat.equals(getClassification(), scannedAssetsAuditLog.getClassification()) &&
              ObjectsCompat.equals(getOwner(), scannedAssetsAuditLog.getOwner()) &&
              ObjectsCompat.equals(getCreatedAt(), scannedAssetsAuditLog.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), scannedAssetsAuditLog.getUpdatedAt());
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
      .append(getAuditLogId())
      .append(getUuidNumber())
      .append(getMajor())
      .append(getMinor())
      .append(getRssi1())
      .append(getRssi2())
      .append(getRssi3())
      .append(getClassification())
      .append(getOwner())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ScannedAssetsAuditLog {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("baseAssetType=" + String.valueOf(getBaseAssetType()) + ", ")
      .append("assetName=" + String.valueOf(getAssetName()) + ", ")
      .append("assetID=" + String.valueOf(getAssetId()) + ", ")
      .append("macAddress=" + String.valueOf(getMacAddress()) + ", ")
      .append("serviceID=" + String.valueOf(getServiceId()) + ", ")
      .append("auditLogID=" + String.valueOf(getAuditLogId()) + ", ")
      .append("uuidNumber=" + String.valueOf(getUuidNumber()) + ", ")
      .append("major=" + String.valueOf(getMajor()) + ", ")
      .append("minor=" + String.valueOf(getMinor()) + ", ")
      .append("rssi1=" + String.valueOf(getRssi1()) + ", ")
      .append("rssi2=" + String.valueOf(getRssi2()) + ", ")
      .append("rssi3=" + String.valueOf(getRssi3()) + ", ")
      .append("classification=" + String.valueOf(getClassification()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
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
  public static ScannedAssetsAuditLog justId(String id) {
    return new ScannedAssetsAuditLog(
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
      auditLogID,
      uuidNumber,
      major,
      minor,
      rssi1,
      rssi2,
      rssi3,
      classification,
      owner);
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
    AuditLogIdStep macAddress(String macAddress);
  }
  

  public interface AuditLogIdStep {
    BuildStep auditLogId(String auditLogId);
  }
  

  public interface BuildStep {
    ScannedAssetsAuditLog build();
    BuildStep id(String id);
    BuildStep serviceId(String serviceId);
    BuildStep uuidNumber(String uuidNumber);
    BuildStep major(String major);
    BuildStep minor(String minor);
    BuildStep rssi1(Double rssi1);
    BuildStep rssi2(Double rssi2);
    BuildStep rssi3(Double rssi3);
    BuildStep classification(String classification);
    BuildStep owner(String owner);
  }
  

  public static class Builder implements BaseAssetTypeStep, AssetNameStep, AssetIdStep, MacAddressStep, AuditLogIdStep, BuildStep {
    private String id;
    private String baseAssetType;
    private String assetName;
    private String assetID;
    private String macAddress;
    private String auditLogID;
    private String serviceID;
    private String uuidNumber;
    private String major;
    private String minor;
    private Double rssi1;
    private Double rssi2;
    private Double rssi3;
    private String classification;
    private String owner;
    @Override
     public ScannedAssetsAuditLog build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ScannedAssetsAuditLog(
          id,
          baseAssetType,
          assetName,
          assetID,
          macAddress,
          serviceID,
          auditLogID,
          uuidNumber,
          major,
          minor,
          rssi1,
          rssi2,
          rssi3,
          classification,
          owner);
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
     public AuditLogIdStep macAddress(String macAddress) {
        Objects.requireNonNull(macAddress);
        this.macAddress = macAddress;
        return this;
    }
    
    @Override
     public BuildStep auditLogId(String auditLogId) {
        Objects.requireNonNull(auditLogId);
        this.auditLogID = auditLogId;
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
     public BuildStep rssi1(Double rssi1) {
        this.rssi1 = rssi1;
        return this;
    }
    
    @Override
     public BuildStep rssi2(Double rssi2) {
        this.rssi2 = rssi2;
        return this;
    }
    
    @Override
     public BuildStep rssi3(Double rssi3) {
        this.rssi3 = rssi3;
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
    private CopyOfBuilder(String id, String baseAssetType, String assetName, String assetId, String macAddress, String serviceId, String auditLogId, String uuidNumber, String major, String minor, Double rssi1, Double rssi2, Double rssi3, String classification, String owner) {
      super.id(id);
      super.baseAssetType(baseAssetType)
        .assetName(assetName)
        .assetId(assetId)
        .macAddress(macAddress)
        .auditLogId(auditLogId)
        .serviceId(serviceId)
        .uuidNumber(uuidNumber)
        .major(major)
        .minor(minor)
        .rssi1(rssi1)
        .rssi2(rssi2)
        .rssi3(rssi3)
        .classification(classification)
        .owner(owner);
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
     public CopyOfBuilder auditLogId(String auditLogId) {
      return (CopyOfBuilder) super.auditLogId(auditLogId);
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
     public CopyOfBuilder rssi1(Double rssi1) {
      return (CopyOfBuilder) super.rssi1(rssi1);
    }
    
    @Override
     public CopyOfBuilder rssi2(Double rssi2) {
      return (CopyOfBuilder) super.rssi2(rssi2);
    }
    
    @Override
     public CopyOfBuilder rssi3(Double rssi3) {
      return (CopyOfBuilder) super.rssi3(rssi3);
    }
    
    @Override
     public CopyOfBuilder classification(String classification) {
      return (CopyOfBuilder) super.classification(classification);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
  }
  
}
