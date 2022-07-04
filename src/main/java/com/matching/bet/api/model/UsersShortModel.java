package com.matching.bet.api.model;

import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "users")
@Getter
@Setter
public class UsersShortModel extends RepresentationModel<UsersShortModel>{

	@ApiModelProperty(example = "1")
    private Long id;	
	
	@ApiModelProperty(example = "joaodasilva@email.com")
	private String userEmail;
	
	@ApiModelProperty(example = "1")
	private String status;
	
	@ApiModelProperty(example = "João")
	private String userName;
	
	@ApiModelProperty(example = "Bookmaker profissional com 10 anos no mercado")
	private String description;
	
	@ApiModelProperty(example = "https://www.google.com/image.jpg")
	private String photo;
	
	@ApiModelProperty
	private List<SocialLoginModel> socialLogins;

}
