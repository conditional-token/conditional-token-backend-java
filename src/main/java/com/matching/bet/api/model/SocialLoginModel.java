package com.matching.bet.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "social_logins")
@Getter
@Setter
public class SocialLoginModel extends RepresentationModel<SocialLoginModel>{

	@ApiModelProperty(example = "1")
    private Long id;	
	
	@ApiModelProperty(example = "1")
	private Short media;//0 Google, 1 Facebook, 2 Apple
	
	@ApiModelProperty(example = "1")
	private Short status;
	
	@ApiModelProperty(example = "joaodasilva@email.com")
	private String email;
	
	@ApiModelProperty(example = "João")
	private String name;
	
	@ApiModelProperty(example = "https://www.google.com/image.jpg")
	private String photoUrl;

}
