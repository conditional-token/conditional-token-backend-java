package com.matching.bet.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptedPassInput {
	
	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String actualCryptedPass;
	
	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String newCryptedPass;

}
