package ro.fortech.edu.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class EvaluationRules {
	
	@XmlElement
    private List<EvaluationRule> evaluationRules= null;
 
    public List<EvaluationRule> getEvaluationRules() {
        return evaluationRules;
    }
 
    public void setEvaluationRules(List<EvaluationRule> evaluationRules) {
        this.evaluationRules = evaluationRules;
    }

}
