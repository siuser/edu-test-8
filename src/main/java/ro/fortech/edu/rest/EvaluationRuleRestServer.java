package ro.fortech.edu.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ro.fortech.edu.model.EvaluationRule;
import ro.fortech.edu.service.EvaluationRuleService;

/**
 * 
 * This class produces a RESTful service to read/write evaluationRule 
 */
@Path("/evaluationRules")
@Stateless
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class EvaluationRuleRestServer {

	@EJB
	private EvaluationRuleService evaluationRuleService;

	@GET 	
	public Response getAllEvaluationRules() {
		//return evaluationRuleService.findAllEvaluationRules();		
		GenericEntity<List<EvaluationRule>> evaluationRuleWrapper = new GenericEntity<List<EvaluationRule>>(
				evaluationRuleService.findAllEvaluationRules()) {
		};
		return Response.ok(evaluationRuleWrapper).build();
	}
	
	@GET
    @Path("/{id}")    
    public EvaluationRule getEvaluationRuleById(@PathParam("id") long id) {
		EvaluationRule evaluationRule = evaluationRuleService.findEvaluationRuleById(id);
        if (evaluationRule == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return evaluationRule;
    }
	
	@DELETE
    @Path("/{id}")    
    public void DeleteEvaluationRuleById(@PathParam("id") long id) {
		EvaluationRule evaluationRule = evaluationRuleService.findEvaluationRuleById(id);
        if (evaluationRule == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else{
        	try {
				evaluationRuleService.delete(evaluationRule);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }
	
	@POST
	@Path("/evaluationRule")	
	public Response postEvaluationRule(EvaluationRule evaluationRule){
		if ( evaluationRule == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);		
		 	}
		try {
			if(evaluationRule.getIdEvaluationRule()==null){
				//New EvaluationRule
				evaluationRuleService.register(evaluationRule);
			}else{
				//EvaluationRule already in db
				evaluationRuleService.update(evaluationRule);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return Response.status(400).entity(
					"EvaluationRule create/update failed!").build();
		}
		
		return Response.ok(evaluationRule).build();
		
	}
	
	
	
	
}
