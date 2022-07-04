package com.matching.bet.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInput {

	@ApiModelProperty(example = "Dealer", required = true)
    @NotBlank
	private String name;
	
}
