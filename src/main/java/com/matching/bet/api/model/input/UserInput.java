package com.matching.bet.api.model.input;

import java.util.Calendar;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

	@ApiModelProperty(example = "João da Silva", required = true)
	@NotBlank
	private String userName;
	
	@ApiModelProperty(example = "joao.ger@corporative.com.br", required = true)
	@NotBlank
	@Email
	private String userEmail;
	
	@ApiModelProperty(example = "Bookmaker profissional com 10 anos no mercado", required = false)
	private String description;
	
	@ApiModelProperty(example = "12345678900", required = true)
	private String govNumber;
	
	@ApiModelProperty(example = "1984-26-07", required = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar birthday;
	
	@ApiModelProperty(example = "https://www.google.com/image.jpg", required = false)
	@URL
	private String photo;
	
}
