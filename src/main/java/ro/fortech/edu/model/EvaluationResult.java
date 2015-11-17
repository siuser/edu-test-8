package ro.fortech.edu.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the EVALUATION_RESULT database table.
 * 
 */
@Entity
@Table(name="EVALUATION_RESULT")
@NamedQuery(name="EvaluationResult.findAll", query="SELECT e FROM EvaluationResult e")
public class EvaluationResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EVALUATION_RESULT")
	private long idEvaluationResult;

	@Column(name="DATE_OF_EVALUATION")
	private Timestamp dateOfEvaluation;

	@Column(name="EVALUATION_RULE_IDS_APPLIED")
	private String evaluationRuleIdsApplied;

	@Column(name="EVALUATION_RULE_IDS_NOT_IN_DATABASE")
	private String evaluationRuleIdsNotInDatabase;

	@Column(name="EVALUATION_RULE_IDS_TO_BE_APPLIED")
	private String evaluationRuleIdsToBeApplied;

	@Column(name="EVALUATION_RULES_APPLIED")
	private String evaluationRulesApplied;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="ID_VEHICLE_IND")
	private Vehicle vehicle;

	//bi-directional many-to-one association to EvaluationResultDetail
	@OneToMany(mappedBy="evaluationResult", cascade=CascadeType.ALL)
	private List<EvaluationResultDetail> evaluationResultDetails = new ArrayList<>();

	public EvaluationResult() {
	}

	public long getIdEvaluationResult() {
		return this.idEvaluationResult;
	}

	public void setIdEvaluationResult(long idEvaluationResult) {
		this.idEvaluationResult = idEvaluationResult;
	}

	public Timestamp getDateOfEvaluation() {
		return this.dateOfEvaluation;
	}

	public void setDateOfEvaluation(Timestamp dateOfEvaluation) {
		this.dateOfEvaluation = dateOfEvaluation;
	}

	public String getEvaluationRuleIdsApplied() {
		return this.evaluationRuleIdsApplied;
	}

	public void setEvaluationRuleIdsApplied(String evaluationRuleIdsApplied) {
		this.evaluationRuleIdsApplied = evaluationRuleIdsApplied;
	}

	public String getEvaluationRuleIdsNotInDatabase() {
		return this.evaluationRuleIdsNotInDatabase;
	}

	public void setEvaluationRuleIdsNotInDatabase(String evaluationRuleIdsNotInDatabase) {
		this.evaluationRuleIdsNotInDatabase = evaluationRuleIdsNotInDatabase;
	}

	public String getEvaluationRuleIdsToBeApplied() {
		return this.evaluationRuleIdsToBeApplied;
	}

	public void setEvaluationRuleIdsToBeApplied(String evaluationRuleIdsToBeApplied) {
		this.evaluationRuleIdsToBeApplied = evaluationRuleIdsToBeApplied;
	}

	public String getEvaluationRulesApplied() {
		return this.evaluationRulesApplied;
	}

	public void setEvaluationRulesApplied(String evaluationRulesApplied) {
		this.evaluationRulesApplied = evaluationRulesApplied;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<EvaluationResultDetail> getEvaluationResultDetails() {
		return this.evaluationResultDetails;
	}

	public void setEvaluationResultDetails(List<EvaluationResultDetail> evaluationResultDetails) {
		this.evaluationResultDetails = evaluationResultDetails;
	}

	public EvaluationResultDetail addEvaluationResultDetail(EvaluationResultDetail evaluationResultDetail) {
		getEvaluationResultDetails().add(evaluationResultDetail);
		evaluationResultDetail.setEvaluationResult(this);

		return evaluationResultDetail;
	}

	public EvaluationResultDetail removeEvaluationResultDetail(EvaluationResultDetail evaluationResultDetail) {
		getEvaluationResultDetails().remove(evaluationResultDetail);
		evaluationResultDetail.setEvaluationResult(null);

		return evaluationResultDetail;
	}

}