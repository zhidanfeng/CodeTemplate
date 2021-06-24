package com.zhi.listing.model.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long tenantId;

  private Integer current = 1;

  private Integer size = 100;
}
