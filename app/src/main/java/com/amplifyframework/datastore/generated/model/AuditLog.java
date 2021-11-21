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

/** This is an auto generated class representing the AuditLog type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "AuditLogs")
@Index(name = "auditByDeviceIndex", fields = {"baseActionType","device"})
@Index(name = "auditByStoreIndex", fields = {"baseActionType","storeName"})
public final class AuditLog implements Model {
  public static final QueryField ID = field("AuditLog", "id");
  public static final QueryField BASE_ACTION_TYPE = field("AuditLog", "baseActionType");
  public static final QueryField USER = field("AuditLog", "user");
  public static final QueryField DEVICE = field("AuditLog", "device");
  public static final QueryField DEVICE_LATITUDE = field("AuditLog", "deviceLatitude");
  public static final QueryField DEVICE_LONGITUDE = field("AuditLog", "deviceLongitude");
  public static final QueryField STORE_NAME = field("AuditLog", "storeName");
  public static final QueryField SCAN_TIME = field("AuditLog", "scanTime");
  public static final QueryField CONFIRM_TIME = field("AuditLog", "confirmTime");
  public static final QueryField SELECTED_STORE_NAME = field("AuditLog", "selectedStoreName");
  public static final QueryField OWNER = field("AuditLog", "owner");
  public static final QueryField RSSI_MAX = field("AuditLog", "rssiMax");
  public static final QueryField RSSI_MIN = field("AuditLog", "rssiMin");
  public static final QueryField RSSI_AVG = field("AuditLog", "rssiAvg");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String baseActionType;
  private final @ModelField(targetType="String") String user;
  private final @ModelField(targetType="String") String device;
  private final @ModelField(targetType="Float") Double deviceLatitude;
  private final @ModelField(targetType="Float") Double deviceLongitude;
  private final @ModelField(targetType="String") String storeName;
  private final @ModelField(targetType="String") String scanTime;
  private final @ModelField(targetType="String") String confirmTime;
  private final @ModelField(targetType="ScannedAssetsAuditLog") @HasMany(associatedWith = "auditLogID", type = ScannedAssetsAuditLog.class) List<ScannedAssetsAuditLog> scannedAssets = null;
  private final @ModelField(targetType="String") String selectedStoreName;
  private final @ModelField(targetType="String") String owner;
  private final @ModelField(targetType="Float") Double rssiMax;
  private final @ModelField(targetType="Float") Double rssiMin;
  private final @ModelField(targetType="Float") Double rssiAvg;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getBaseActionType() {
      return baseActionType;
  }
  
  public String getUser() {
      return user;
  }
  
  public String getDevice() {
      return device;
  }
  
  public Double getDeviceLatitude() {
      return deviceLatitude;
  }
  
  public Double getDeviceLongitude() {
      return deviceLongitude;
  }
  
  public String getStoreName() {
      return storeName;
  }
  
  public String getScanTime() {
      return scanTime;
  }
  
  public String getConfirmTime() {
      return confirmTime;
  }
  
  public List<ScannedAssetsAuditLog> getScannedAssets() {
      return scannedAssets;
  }
  
  public String getSelectedStoreName() {
      return selectedStoreName;
  }
  
  public String getOwner() {
      return owner;
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
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private AuditLog(String id, String baseActionType, String user, String device, Double deviceLatitude, Double deviceLongitude, String storeName, String scanTime, String confirmTime, String selectedStoreName, String owner, Double rssiMax, Double rssiMin, Double rssiAvg) {
    this.id = id;
    this.baseActionType = baseActionType;
    this.user = user;
    this.device = device;
    this.deviceLatitude = deviceLatitude;
    this.deviceLongitude = deviceLongitude;
    this.storeName = storeName;
    this.scanTime = scanTime;
    this.confirmTime = confirmTime;
    this.selectedStoreName = selectedStoreName;
    this.owner = owner;
    this.rssiMax = rssiMax;
    this.rssiMin = rssiMin;
    this.rssiAvg = rssiAvg;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      AuditLog auditLog = (AuditLog) obj;
      return ObjectsCompat.equals(getId(), auditLog.getId()) &&
              ObjectsCompat.equals(getBaseActionType(), auditLog.getBaseActionType()) &&
              ObjectsCompat.equals(getUser(), auditLog.getUser()) &&
              ObjectsCompat.equals(getDevice(), auditLog.getDevice()) &&
              ObjectsCompat.equals(getDeviceLatitude(), auditLog.getDeviceLatitude()) &&
              ObjectsCompat.equals(getDeviceLongitude(), auditLog.getDeviceLongitude()) &&
              ObjectsCompat.equals(getStoreName(), auditLog.getStoreName()) &&
              ObjectsCompat.equals(getScanTime(), auditLog.getScanTime()) &&
              ObjectsCompat.equals(getConfirmTime(), auditLog.getConfirmTime()) &&
              ObjectsCompat.equals(getSelectedStoreName(), auditLog.getSelectedStoreName()) &&
              ObjectsCompat.equals(getOwner(), auditLog.getOwner()) &&
              ObjectsCompat.equals(getRssiMax(), auditLog.getRssiMax()) &&
              ObjectsCompat.equals(getRssiMin(), auditLog.getRssiMin()) &&
              ObjectsCompat.equals(getRssiAvg(), auditLog.getRssiAvg()) &&
              ObjectsCompat.equals(getCreatedAt(), auditLog.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), auditLog.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getBaseActionType())
      .append(getUser())
      .append(getDevice())
      .append(getDeviceLatitude())
      .append(getDeviceLongitude())
      .append(getStoreName())
      .append(getScanTime())
      .append(getConfirmTime())
      .append(getSelectedStoreName())
      .append(getOwner())
      .append(getRssiMax())
      .append(getRssiMin())
      .append(getRssiAvg())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("AuditLog {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("baseActionType=" + String.valueOf(getBaseActionType()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("device=" + String.valueOf(getDevice()) + ", ")
      .append("deviceLatitude=" + String.valueOf(getDeviceLatitude()) + ", ")
      .append("deviceLongitude=" + String.valueOf(getDeviceLongitude()) + ", ")
      .append("storeName=" + String.valueOf(getStoreName()) + ", ")
      .append("scanTime=" + String.valueOf(getScanTime()) + ", ")
      .append("confirmTime=" + String.valueOf(getConfirmTime()) + ", ")
      .append("selectedStoreName=" + String.valueOf(getSelectedStoreName()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("rssiMax=" + String.valueOf(getRssiMax()) + ", ")
      .append("rssiMin=" + String.valueOf(getRssiMin()) + ", ")
      .append("rssiAvg=" + String.valueOf(getRssiAvg()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BaseActionTypeStep builder() {
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
  public static AuditLog justId(String id) {
    return new AuditLog(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      baseActionType,
      user,
      device,
      deviceLatitude,
      deviceLongitude,
      storeName,
      scanTime,
      confirmTime,
      selectedStoreName,
      owner,
      rssiMax,
      rssiMin,
      rssiAvg);
  }
  public interface BaseActionTypeStep {
    BuildStep baseActionType(String baseActionType);
  }
  

  public interface BuildStep {
    AuditLog build();
    BuildStep id(String id);
    BuildStep user(String user);
    BuildStep device(String device);
    BuildStep deviceLatitude(Double deviceLatitude);
    BuildStep deviceLongitude(Double deviceLongitude);
    BuildStep storeName(String storeName);
    BuildStep scanTime(String scanTime);
    BuildStep confirmTime(String confirmTime);
    BuildStep selectedStoreName(String selectedStoreName);
    BuildStep owner(String owner);
    BuildStep rssiMax(Double rssiMax);
    BuildStep rssiMin(Double rssiMin);
    BuildStep rssiAvg(Double rssiAvg);
  }
  

  public static class Builder implements BaseActionTypeStep, BuildStep {
    private String id;
    private String baseActionType;
    private String user;
    private String device;
    private Double deviceLatitude;
    private Double deviceLongitude;
    private String storeName;
    private String scanTime;
    private String confirmTime;
    private String selectedStoreName;
    private String owner;
    private Double rssiMax;
    private Double rssiMin;
    private Double rssiAvg;
    @Override
     public AuditLog build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new AuditLog(
          id,
          baseActionType,
          user,
          device,
          deviceLatitude,
          deviceLongitude,
          storeName,
          scanTime,
          confirmTime,
          selectedStoreName,
          owner,
          rssiMax,
          rssiMin,
          rssiAvg);
    }
    
    @Override
     public BuildStep baseActionType(String baseActionType) {
        Objects.requireNonNull(baseActionType);
        this.baseActionType = baseActionType;
        return this;
    }
    
    @Override
     public BuildStep user(String user) {
        this.user = user;
        return this;
    }
    
    @Override
     public BuildStep device(String device) {
        this.device = device;
        return this;
    }
    
    @Override
     public BuildStep deviceLatitude(Double deviceLatitude) {
        this.deviceLatitude = deviceLatitude;
        return this;
    }
    
    @Override
     public BuildStep deviceLongitude(Double deviceLongitude) {
        this.deviceLongitude = deviceLongitude;
        return this;
    }
    
    @Override
     public BuildStep storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }
    
    @Override
     public BuildStep scanTime(String scanTime) {
        this.scanTime = scanTime;
        return this;
    }
    
    @Override
     public BuildStep confirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
        return this;
    }
    
    @Override
     public BuildStep selectedStoreName(String selectedStoreName) {
        this.selectedStoreName = selectedStoreName;
        return this;
    }
    
    @Override
     public BuildStep owner(String owner) {
        this.owner = owner;
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
    private CopyOfBuilder(String id, String baseActionType, String user, String device, Double deviceLatitude, Double deviceLongitude, String storeName, String scanTime, String confirmTime, String selectedStoreName, String owner, Double rssiMax, Double rssiMin, Double rssiAvg) {
      super.id(id);
      super.baseActionType(baseActionType)
        .user(user)
        .device(device)
        .deviceLatitude(deviceLatitude)
        .deviceLongitude(deviceLongitude)
        .storeName(storeName)
        .scanTime(scanTime)
        .confirmTime(confirmTime)
        .selectedStoreName(selectedStoreName)
        .owner(owner)
        .rssiMax(rssiMax)
        .rssiMin(rssiMin)
        .rssiAvg(rssiAvg);
    }
    
    @Override
     public CopyOfBuilder baseActionType(String baseActionType) {
      return (CopyOfBuilder) super.baseActionType(baseActionType);
    }
    
    @Override
     public CopyOfBuilder user(String user) {
      return (CopyOfBuilder) super.user(user);
    }
    
    @Override
     public CopyOfBuilder device(String device) {
      return (CopyOfBuilder) super.device(device);
    }
    
    @Override
     public CopyOfBuilder deviceLatitude(Double deviceLatitude) {
      return (CopyOfBuilder) super.deviceLatitude(deviceLatitude);
    }
    
    @Override
     public CopyOfBuilder deviceLongitude(Double deviceLongitude) {
      return (CopyOfBuilder) super.deviceLongitude(deviceLongitude);
    }
    
    @Override
     public CopyOfBuilder storeName(String storeName) {
      return (CopyOfBuilder) super.storeName(storeName);
    }
    
    @Override
     public CopyOfBuilder scanTime(String scanTime) {
      return (CopyOfBuilder) super.scanTime(scanTime);
    }
    
    @Override
     public CopyOfBuilder confirmTime(String confirmTime) {
      return (CopyOfBuilder) super.confirmTime(confirmTime);
    }
    
    @Override
     public CopyOfBuilder selectedStoreName(String selectedStoreName) {
      return (CopyOfBuilder) super.selectedStoreName(selectedStoreName);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
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
  }
  
}
