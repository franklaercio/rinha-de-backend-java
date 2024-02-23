package com.github.rinha.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.util.Strings;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transact {

  @JsonProperty("valor")
  private String value;

  @JsonProperty("tipo")
  private String kind;

  @JsonProperty("descricao")
  private String description;

  public Transact() {
  }

  public Transact(String value, String kind, String description) {
    this.value = value;
    this.kind = kind;
    this.description = description;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isValueValid() {
    try {
      return Strings.isNotBlank(this.value) && Integer.parseInt(this.value) > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public boolean isTypeValid() {
    return Strings.isNotBlank(this.kind) && (this.kind.equals("c") || this.kind.equals("d"));
  }

  public boolean isDescriptionValid() {
    return Strings.isNotBlank(this.description) && this.description.length() <= 10;
  }

  public int parseValueToInt() {
    return Integer.parseInt(this.value);
  }

  public boolean isRequestValid() {
    return this.isValueValid() && this.isTypeValid() && this.isDescriptionValid();
  }

}
