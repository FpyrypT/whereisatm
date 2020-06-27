/*
 * Сервис проверки статуса банкоматов
 * Сервис, возвращающий информацию о банкоматах Альфа-Банка
 *
 * OpenAPI spec version: 1.0.0
 * Contact: apisupport@alfabank.ru
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.fpyrypt.whereisatm.model.atm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * BankATMStatus
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-27T12:29:08.941+03:00")
public class BankATMStatus {
  @JsonProperty("atms")
  private List<ATMStatus> atms = null;

  @JsonProperty("bankLicense")
  private String bankLicense = null;

  public BankATMStatus atms(List<ATMStatus> atms) {
    this.atms = atms;
    return this;
  }

  public BankATMStatus addAtmsItem(ATMStatus atmsItem) {
    if (this.atms == null) {
      this.atms = new ArrayList<ATMStatus>();
    }
    this.atms.add(atmsItem);
    return this;
  }

   /**
   * Список статусов банкоматов
   * @return atms
  **/
  @ApiModelProperty(value = "Список статусов банкоматов")
  public List<ATMStatus> getAtms() {
    return atms;
  }

  public void setAtms(List<ATMStatus> atms) {
    this.atms = atms;
  }

  public BankATMStatus bankLicense(String bankLicense) {
    this.bankLicense = bankLicense;
    return this;
  }

   /**
   * Номер лицензии Альфа-Банка
   * @return bankLicense
  **/
  @ApiModelProperty(value = "Номер лицензии Альфа-Банка")
  public String getBankLicense() {
    return bankLicense;
  }

  public void setBankLicense(String bankLicense) {
    this.bankLicense = bankLicense;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankATMStatus bankATMStatus = (BankATMStatus) o;
    return Objects.equals(this.atms, bankATMStatus.atms) &&
        Objects.equals(this.bankLicense, bankATMStatus.bankLicense);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atms, bankLicense);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankATMStatus {\n");
    
    sb.append("    atms: ").append(toIndentedString(atms)).append("\n");
    sb.append("    bankLicense: ").append(toIndentedString(bankLicense)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

