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

/** This is an auto generated class representing the Users type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class Users implements Model {
  public static final QueryField ID = field("Users", "id");
  public static final QueryField NAME = field("Users", "name");
  public static final QueryField DESCRIPTION = field("Users", "description");
  public static final QueryField BASE_USER_TYPE = field("Users", "baseUserType");
  public static final QueryField COMPANY = field("Users", "company");
  public static final QueryField EMAIL = field("Users", "email");
  public static final QueryField STATUS = field("Users", "status");
  public static final QueryField SCAN_TIME = field("Users", "scanTime");
  public static final QueryField PHONE_NUMBER = field("Users", "phoneNumber");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="String", isRequired = true) String baseUserType;
  private final @ModelField(targetType="String", isRequired = true) String company;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="String") String status;
  private final @ModelField(targetType="Float") Double scanTime;
  private final @ModelField(targetType="String") String phoneNumber;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getBaseUserType() {
      return baseUserType;
  }
  
  public String getCompany() {
      return company;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getStatus() {
      return status;
  }
  
  public Double getScanTime() {
      return scanTime;
  }
  
  public String getPhoneNumber() {
      return phoneNumber;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Users(String id, String name, String description, String baseUserType, String company, String email, String status, Double scanTime, String phoneNumber) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.baseUserType = baseUserType;
    this.company = company;
    this.email = email;
    this.status = status;
    this.scanTime = scanTime;
    this.phoneNumber = phoneNumber;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Users users = (Users) obj;
      return ObjectsCompat.equals(getId(), users.getId()) &&
              ObjectsCompat.equals(getName(), users.getName()) &&
              ObjectsCompat.equals(getDescription(), users.getDescription()) &&
              ObjectsCompat.equals(getBaseUserType(), users.getBaseUserType()) &&
              ObjectsCompat.equals(getCompany(), users.getCompany()) &&
              ObjectsCompat.equals(getEmail(), users.getEmail()) &&
              ObjectsCompat.equals(getStatus(), users.getStatus()) &&
              ObjectsCompat.equals(getScanTime(), users.getScanTime()) &&
              ObjectsCompat.equals(getPhoneNumber(), users.getPhoneNumber()) &&
              ObjectsCompat.equals(getCreatedAt(), users.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), users.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getDescription())
      .append(getBaseUserType())
      .append(getCompany())
      .append(getEmail())
      .append(getStatus())
      .append(getScanTime())
      .append(getPhoneNumber())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Users {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("baseUserType=" + String.valueOf(getBaseUserType()) + ", ")
      .append("company=" + String.valueOf(getCompany()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("status=" + String.valueOf(getStatus()) + ", ")
      .append("scanTime=" + String.valueOf(getScanTime()) + ", ")
      .append("phoneNumber=" + String.valueOf(getPhoneNumber()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
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
  public static Users justId(String id) {
    return new Users(
      id,
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
      name,
      description,
      baseUserType,
      company,
      email,
      status,
      scanTime,
      phoneNumber);
  }
  public interface NameStep {
    BaseUserTypeStep name(String name);
  }
  

  public interface BaseUserTypeStep {
    CompanyStep baseUserType(String baseUserType);
  }
  

  public interface CompanyStep {
    EmailStep company(String company);
  }
  

  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    Users build();
    BuildStep id(String id);
    BuildStep description(String description);
    BuildStep status(String status);
    BuildStep scanTime(Double scanTime);
    BuildStep phoneNumber(String phoneNumber);
  }
  

  public static class Builder implements NameStep, BaseUserTypeStep, CompanyStep, EmailStep, BuildStep {
    private String id;
    private String name;
    private String baseUserType;
    private String company;
    private String email;
    private String description;
    private String status;
    private Double scanTime;
    private String phoneNumber;
    @Override
     public Users build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Users(
          id,
          name,
          description,
          baseUserType,
          company,
          email,
          status,
          scanTime,
          phoneNumber);
    }
    
    @Override
     public BaseUserTypeStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public CompanyStep baseUserType(String baseUserType) {
        Objects.requireNonNull(baseUserType);
        this.baseUserType = baseUserType;
        return this;
    }
    
    @Override
     public EmailStep company(String company) {
        Objects.requireNonNull(company);
        this.company = company;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep status(String status) {
        this.status = status;
        return this;
    }
    
    @Override
     public BuildStep scanTime(Double scanTime) {
        this.scanTime = scanTime;
        return this;
    }
    
    @Override
     public BuildStep phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    private CopyOfBuilder(String id, String name, String description, String baseUserType, String company, String email, String status, Double scanTime, String phoneNumber) {
      super.id(id);
      super.name(name)
        .baseUserType(baseUserType)
        .company(company)
        .email(email)
        .description(description)
        .status(status)
        .scanTime(scanTime)
        .phoneNumber(phoneNumber);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder baseUserType(String baseUserType) {
      return (CopyOfBuilder) super.baseUserType(baseUserType);
    }
    
    @Override
     public CopyOfBuilder company(String company) {
      return (CopyOfBuilder) super.company(company);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder status(String status) {
      return (CopyOfBuilder) super.status(status);
    }
    
    @Override
     public CopyOfBuilder scanTime(Double scanTime) {
      return (CopyOfBuilder) super.scanTime(scanTime);
    }
    
    @Override
     public CopyOfBuilder phoneNumber(String phoneNumber) {
      return (CopyOfBuilder) super.phoneNumber(phoneNumber);
    }
  }
  
}
