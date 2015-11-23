package ro.fortech.edu.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ro.fortech.edu.model.EvaluationRule;
import ro.fortech.edu.model.MarketRule;

@Stateless
public class EvaluationRuleService {

	@PersistenceContext
    private EntityManager entityManager;
	
	@EJB
	private MarketRuleService marketRuleService;

   
	/**
	 * Persist a new EvaluationRule
	 * @param evaluationRule
	 * @throws Exception
	 */
    public void register(EvaluationRule evaluationRule) throws Exception {    	
    	entityManager.persist(evaluationRule);        
    }
    
    /**
     * Persist a list of EvaluationRule
     * @param evaluationRuleList
     * @throws Exception
     */
    public void registerList(List<EvaluationRule> evaluationRuleList) throws Exception {  
    	for(EvaluationRule evaluationRule:evaluationRuleList){
    		entityManager.persist(evaluationRule); 
    	}    	       
    }
    
    /**
     * Remove an EvaluationRule from db
     * @param evaluationRule
     * @throws Exception
     */
    public void delete(EvaluationRule evaluationRule) throws Exception {    	
    	entityManager.remove(evaluationRule);       
    }
    
    /**
     * Update an EvaluationRule
     * @param evaluationRule
     * @throws Exception
     */
    public void update(EvaluationRule evaluationRule) throws Exception {    	
    	entityManager.merge(entityManager.merge(evaluationRule));        
    }
    
    /**
     * Find an EvaluationRule by its id
     * @param id
     * @return
     */
    public EvaluationRule findEvaluationRuleById(long id){    	
    	return entityManager.find(EvaluationRule.class, id);
    }
    
    /**
     * Find all EvaluationRule from db
     * @return
     */
    public List<EvaluationRule> findAllEvaluationRules() {
    	entityManager.setProperty("javax.persistence.cache.storeMode", "USE");
    	Cache cache = entityManager.getEntityManagerFactory().getCache();
    	
    	if (cache.contains(EvaluationRule.class, 1)) {
    		  System.out.println("Rule id=1 in cache");
    		} else {
    			System.out.println("Rule id=1 NOT in cache");
    		}
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EvaluationRule> criteriaQuery = criteriaBuilder.createQuery(EvaluationRule.class);
        Root<EvaluationRule> evaluationRule = criteriaQuery.from(EvaluationRule.class);           
        criteriaQuery.select(evaluationRule);   
        criteriaQuery.orderBy(criteriaBuilder.asc((evaluationRule.get("idEvaluationRule"))));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    
    /**
     * Find the MarketRule corresponding to an EvaluationRule
     * @param evaluationRule
     * @return 
     */
    public MarketRule getMarketRule(EvaluationRule evaluationRule){
    	long marketRuleId = evaluationRule.getMarketRuleId();
    	return marketRuleService.findMarketRuleById(marketRuleId);    	 
    }
}