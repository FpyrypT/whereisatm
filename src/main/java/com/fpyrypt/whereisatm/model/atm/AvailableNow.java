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

/**
 * AvailableNow
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-27T12:29:08.941+03:00")
public class AvailableNow {
  @JsonProperty("cashIn")
  private String cashIn = null;

  @JsonProperty("cashOut")
  private String cashOut = null;

  @JsonProperty("online")
  private String online = null;

  @JsonProperty("payments")
  private String payments = null;

  public AvailableNow cashIn(String cashIn) {
    this.cashIn = cashIn;
    return this;
  }

   /**
   * Доступность АТМ клиенту для внесения денег (функция внесения денег), заполняется для устройств, обладающих функцией приема денег.  Y &#x3D; АТМ позволяет внести деньги  N &#x3D; АТМ не позволяет внести деньги (поломка, иная причина)  Z &#x3D; нет информации о статусе данной функции АТМ  
   * @return cashIn
  **/
  @ApiModelProperty(value = "Доступность АТМ клиенту для внесения денег (функция внесения денег), заполняется для устройств, обладающих функцией приема денег.  Y = АТМ позволяет внести деньги  N = АТМ не позволяет внести деньги (поломка, иная причина)  Z = нет информации о статусе данной функции АТМ  ")
  public String getCashIn() {
    return cashIn;
  }

  public void setCashIn(String cashIn) {
    this.cashIn = cashIn;
  }

  public AvailableNow cashOut(String cashOut) {
    this.cashOut = cashOut;
    return this;
  }

   /**
   * Доступность АТМ клиенту для получения денег (функция выдачи денег), заполняется для устройств, обладающих функцией выдачи денег.  Y &#x3D; АТМ выдает деньги (деньги получить можно)  N &#x3D; АТМ не выдает деньги (закончились, поломка)  Z &#x3D; нет информации о статусе данной функции АТМ  
   * @return cashOut
  **/
  @ApiModelProperty(value = "Доступность АТМ клиенту для получения денег (функция выдачи денег), заполняется для устройств, обладающих функцией выдачи денег.  Y = АТМ выдает деньги (деньги получить можно)  N = АТМ не выдает деньги (закончились, поломка)  Z = нет информации о статусе данной функции АТМ  ")
  public String getCashOut() {
    return cashOut;
  }

  public void setCashOut(String cashOut) {
    this.cashOut = cashOut;
  }

  public AvailableNow online(String online) {
    this.online = online;
    return this;
  }

   /**
   * Текущий статус работоспособности АТМ для клиента  Y &#x3D; АТМ открыт и на связи, хотя бы одна из его функций доступна  N &#x3D; АТМ закрыт или не на связи, клиент не может совершать операции с АТМ  Z &#x3D; нет информации о статусе АТМ
   * @return online
  **/
  @ApiModelProperty(value = "Текущий статус работоспособности АТМ для клиента  Y = АТМ открыт и на связи, хотя бы одна из его функций доступна  N = АТМ закрыт или не на связи, клиент не может совершать операции с АТМ  Z = нет информации о статусе АТМ")
  public String getOnline() {
    return online;
  }

  public void setOnline(String online) {
    this.online = online;
  }

  public AvailableNow payments(String payments) {
    this.payments = payments;
    return this;
  }

   /**
   * Доступность АТМ клиенту для оплаты (функция оплаты товаров и услуг), заполняется для устройств, обладающих функцией оплаты.  Y &#x3D; АТМ позволяет оплатить (сделать хотя бы один платеж)  N &#x3D; АТМ не позволяет провести оплату (поломка, иная причина)  Z &#x3D; нет информации о статусе данной функции АТМ
   * @return payments
  **/
  @ApiModelProperty(value = "Доступность АТМ клиенту для оплаты (функция оплаты товаров и услуг), заполняется для устройств, обладающих функцией оплаты.  Y = АТМ позволяет оплатить (сделать хотя бы один платеж)  N = АТМ не позволяет провести оплату (поломка, иная причина)  Z = нет информации о статусе данной функции АТМ")
  public String getPayments() {
    return payments;
  }

  public void setPayments(String payments) {
    this.payments = payments;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailableNow availableNow = (AvailableNow) o;
    return Objects.equals(this.cashIn, availableNow.cashIn) &&
        Objects.equals(this.cashOut, availableNow.cashOut) &&
        Objects.equals(this.online, availableNow.online) &&
        Objects.equals(this.payments, availableNow.payments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cashIn, cashOut, online, payments);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableNow {\n");
    
    sb.append("    cashIn: ").append(toIndentedString(cashIn)).append("\n");
    sb.append("    cashOut: ").append(toIndentedString(cashOut)).append("\n");
    sb.append("    online: ").append(toIndentedString(online)).append("\n");
    sb.append("    payments: ").append(toIndentedString(payments)).append("\n");
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

