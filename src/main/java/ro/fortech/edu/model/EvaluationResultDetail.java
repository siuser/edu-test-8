package ro.fortech.edu.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EVALUATION_RESULT_DETAIL database table.
 * 
 */
@Entity
@Table(name="EVALUATION_RESULT_DETAIL")
@NamedQuery(name="EvaluationResultDetail.findAll", query="SELECT e FROM EvaluationResultDetail e")
public class EvaluationResultDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EVALUATION_RESULT_DETAIL")
	private long idEvaluationResultDetail;

	@Column(name="ID_EVALUATION_RULE")
	private long idEvaluationRule;

	@Lob
	private String message;

	@Column(name="RULE_STATUS")
	private String ruleStatus;

	//bi-directional many-to-one association to EvaluationResult
	@ManyToOne
	@JoinColumn(name="ID_EVALUATION_RESULT_IND")
	private EvaluationResult evaluationResult;

	public EvaluationResultDetail() {
	}

	public long getIdEvaluationResultDetail() {
		return this.idEvaluationResultDetail;
	}

	public void setIdEvaluationResultDetail(long idEvaluationResultDetail) {
		this.idEvaluationResultDetail = idEvaluationResultDetail;
	}

	public long getIdEvaluationRule() {
		return this.idEvaluationRule;
	}

	public void setIdEvaluationRule(long idEvaluationRule) {
		this.idEvaluationRule = idEvaluationRule;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRuleStatus() {
		return this.ruleStatus;
	}

	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public EvaluationResult getEvaluationResult() {
		return this.evaluationResult;
	}

	public void setEvaluationResult(EvaluationResult evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

}