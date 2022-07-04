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
public class UsersModel extends UsersShortModel{

	@ApiModelProperty(example = "12345678900")
	private String govNumber;
	
	@ApiModelProperty(example = "1984-07-26T00:00:00.000+0000")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar birthday;
	

}
