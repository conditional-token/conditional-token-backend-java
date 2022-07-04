package com.matching.bet.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.matching.bet.api.model.GroupingModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruopsModel")
@Data
public class GroupsModelOpenApi {

	private GroupsEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("GruposEmbeddedModel")
    @Data
    public class GroupsEmbeddedModelOpenApi {
        
        private List<GroupingModel> grupos;
        
    }   
}
