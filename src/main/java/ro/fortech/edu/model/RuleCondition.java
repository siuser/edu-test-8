package ro.fortech.edu.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the RULE_CONDITION database table.
 * 
 */
@Entity
@Table(name="RULE_CONDITION")
@XmlRootElement 
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="RuleCondition.findAll", query="SELECT r FROM RuleCondition r")
public class RuleCondition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RULE_CONDITION")
	private long idRuleCondition;

	@Column(name="VEHICLE_ATTRIBUTE_NAME")
	private String vehicleAttributeName;

	@Column(name="VEHICLE_ATTRIBUTE_VALUE")
	private String vehicleAttributeValue;

	//bi-directional many-to-one association to EvaluationRule
	@ManyToOne
	@JoinColumn(name="ID_EVALUATION_RULE_IND")
	@XmlTransient
	private EvaluationRule evaluationRule;
	
	public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
		   this.evaluationRule = (EvaluationRule) parent;
		}

	public RuleCondition() {
	}

	public long getIdRuleCondition() {
		return this.idRuleCondition;
	}

	public void setIdRuleCondition(long idRuleCondition) {
		this.idRuleCondition = idRuleCondition;
	}

	public String getVehicleAttributeName() {
		return this.vehicleAttributeName;
	}

	public void setVehicleAttributeName(String vehicleAttributeName) {
		this.vehicleAttributeName = vehicleAttributeName;
	}

	public String getVehicleAttributeValue() {
		return this.vehicleAttributeValue;
	}

	public void setVehicleAttributeValue(String vehicleAttributeValue) {
		this.vehicleAttributeValue = vehicleAttributeValue;
	}

	public EvaluationRule getEvaluationRule() {
		return this.evaluationRule;
	}

	public void setEvaluationRule(EvaluationRule evaluationRule) {
		this.evaluationRule = evaluationRule;
	}

}