package ro.fortech.edu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ro.fortech.edu.model.EvaluationRule;
import ro.fortech.edu.model.EvaluationRules;
import ro.fortech.edu.model.RuleActivity;
import ro.fortech.edu.model.RuleCondition;
import ro.fortech.edu.rest.EvaluationRuleRestClient;
import ro.fortech.edu.rest.EvaluationRuleRestServer;
import ro.fortech.edu.service.EvaluationRuleService;

@RequestScoped
@ManagedBean(name = "evaluationRuleController")
public class EvaluationRuleController {

	@EJB
	private EvaluationRuleService evaluationRuleService;
	
	@EJB
	private EvaluationRuleRestServer evaluationRuleRestServer;
	
	@EJB
	private EvaluationRuleRestClient evaluationRuleRestClient;

	
	private EvaluationRule newEvaluationRule;
	
	public EvaluationRule getNewEvaluationRule() {
		return newEvaluationRule;
	}

	public void setNewEvaluationRule(EvaluationRule newEvaluationRule) {
		this.newEvaluationRule = newEvaluationRule;
	}

	

	@PostConstruct
	public void initNewEvaluationRule() {
		newEvaluationRule = new EvaluationRule();
		getAllEvaluationRuleList();
		
	}
	

	

	private long idMarketRule;

	public long getIdMarketRule() {
		return idMarketRule;
	}

	public void setIdMarketRule(long idMarketRule) {
		this.idMarketRule = idMarketRule;
	}
	
	private long idEvaluationRule;
	
	public long getIdEvaluationRule() {
		return idEvaluationRule;
	}

	public void setIdEvaluationRule(long idEvaluationRule) {
		this.idEvaluationRule = idEvaluationRule;
	}

	/**
	 * 
	 * @return A List of all EvaluationRule
	 */
	public List<EvaluationRule> getAllEvaluationRuleList() {
		//System.out.println("Rules nr= "+evaluationRuleService.findAllEvaluationRules().size());
		return evaluationRuleService.findAllEvaluationRules();
	}

	public void register() throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			evaluationRuleService.register(newEvaluationRule);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewEvaluationRule();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
	
	public void getEvaluationRuleByIdRest(){
		System.out.println("Enter getEvaluationRuleByIdRest");
		long evaluationRuleId = 1;		
		evaluationRuleRestClient.invokeGetEvaluationRuleById(evaluationRuleId);
	}
	
	public void getAllEvaluationRuleRest(){
		System.out.println("Enter getAllEvaluationRuleRest");
		evaluationRuleRestClient.invokeGetAllEvaluationRule();
	}
	
	public void addEvaluationRuleRest(){
		System.out.println("Enter addEvaluationRuleRest");
		//Data should come from other sources
		EvaluationRule evaluationRule = new EvaluationRule();	
		evaluationRule.setMarketRuleId(100);
		evaluationRuleRestClient.invokePostEvaluationRule(evaluationRule);;
	}
	
	public void deleteEvaluationRuleRest(EvaluationRule evaluationRule){
		System.out.println("Enter deleteEvaluationRuleRest");
		//Data should come from other sources
		//EvaluationRule evaluationRule = new EvaluationRule();	
		//evaluationRule.setMarketRuleId(100);
		evaluationRuleRestClient.invokeDeleteEvaluationRule(evaluationRule);;
	}
	
	public void marshallingEvaluationRuleToXmlFile() throws JAXBException{
		System.out.println("Enter marshallingToXmlFile");
		EvaluationRules evaluationRules = new EvaluationRules();
		
		EvaluationRule evaluationRule1 = new EvaluationRule();	
		RuleCondition ruleCondition1 = new RuleCondition();
		ruleCondition1.setVehicleAttributeName("engineCode");
		ruleCondition1.setVehicleAttributeValue("UTR39");
		evaluationRule1.getRuleConditions().add(ruleCondition1);
		RuleActivity ruleActivity1 =  new RuleActivity();
		ruleActivity1.setVehicleAttributeName("version");
		ruleActivity1.setVehicleAttributeValue("gutte");
		evaluationRule1.getRuleActivities().add(ruleActivity1);
		evaluationRule1.setMarketRuleId(101);
		
		EvaluationRule evaluationRule2 = new EvaluationRule();	
		evaluationRule2.setMarketRuleId(101);
		
		
		
		List<EvaluationRule> evaluationRuleList = new ArrayList<>();
		evaluationRuleList.add(evaluationRule1);
		evaluationRuleList.add(evaluationRule2);
		
		evaluationRules.setEvaluationRules(evaluationRuleList);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(EvaluationRules.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the evaluationRules list in console
	    jaxbMarshaller.marshal(evaluationRules, System.out);
	     
	    //Marshal the employees list in file
	    jaxbMarshaller.marshal(evaluationRules, new File("E:\\Workspaces\\Websphere-2\\edu-test-8\\docs\\evaluationRules.xml"));
		
	}
	
	public List<EvaluationRule> unmarshallingEvaluationRuleFromXmlFile() throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(EvaluationRules.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    //We had written this file in marshalling example
	    EvaluationRules evaluationRules = (EvaluationRules) jaxbUnmarshaller.unmarshal( new File("E:\\Workspaces\\Websphere-2\\edu-test-8\\docs\\evaluationRules.xml") );
	    List<EvaluationRule> evaluationRuleList =evaluationRules.getEvaluationRules();
	    return evaluationRuleList;
	}
	
	public void addBatchEvaluationRuleRest() throws JAXBException{		
		System.out.println("Enter addBatchEvaluationRuleRest");
		marshallingEvaluationRuleToXmlFile();
		//Data should come from other sources
		/*
		List<EvaluationRule> evaluationRuleList = new ArrayList<>();
		EvaluationRule evaluationRule1 = new EvaluationRule();	
		evaluationRule1.setMarketRuleId(101);
		evaluationRuleList.add(evaluationRule1);
		
		EvaluationRule evaluationRule2 = new EvaluationRule();	
		evaluationRule2.setMarketRuleId(101);
		evaluationRuleList.add(evaluationRule2);
		*/
		
		evaluationRuleRestClient.invokePostBatchEvaluationRule(unmarshallingEvaluationRuleFromXmlFile());;
	}


	

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

}
