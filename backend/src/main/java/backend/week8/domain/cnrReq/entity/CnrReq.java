package backend.week8.domain.cnrReq.entity;

import backend.week8.domain.cnrReq.entity.enums.CnrFailCause;
import backend.week8.domain.cnrReq.entity.enums.CnrIngStatus;
import backend.week8.domain.cnrReq.entity.enums.CnrInputDiv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "CNR_REQ")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CnrReq {

	public CnrReq(int manualCnrKwdYn) {
		dadDetId = 0L;
		cnrIngStatus = manualCnrKwdYn == 1 ? CnrIngStatus.REQ : CnrIngStatus.APPROVAL;
		cnrInputDiv = CnrInputDiv.INPUT_CNR;
		cnrReqTime = LocalDateTime.now();
		cnrProcTime = manualCnrKwdYn == 1 ? null : LocalDateTime.now();
		cnrCompleteYn = manualCnrKwdYn == 1 ? 0 : 1;
	}

	@Id
	@Column(name = "CNR_REQ_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnrReqId;

	@Column(name = "DAD_DET_ID", nullable = false)
	private Long dadDetId;
	@Column(name = "CNR_ING_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private CnrIngStatus cnrIngStatus;
	@Column(name = "CNR_INPUT_DIV", nullable = false)
	@Enumerated(EnumType.STRING)
	private CnrInputDiv cnrInputDiv;
	@Column(name = "CNR_REQ_TIME", nullable = false)
	private LocalDateTime cnrReqTime;
	@Column(name = "CNR_PROC_TIME")
	private LocalDateTime cnrProcTime;
	@Column(name = "CNR_COMPLETE_YN", nullable = false)
	private int cnrCompleteYn;
	@Column(name = "CNR_FAIL_CAUSE")
	@Enumerated(EnumType.STRING)
	private CnrFailCause cnrFailCause;
	@Column(name = "CNR_FAIL_COMT")
	private String cnrFailComt;

	public void setCnrIngStatus(boolean cnrIngStatus) {
		this.cnrIngStatus = cnrIngStatus ? CnrIngStatus.APPROVAL : CnrIngStatus.REJECT;
		cnrProcTime = LocalDateTime.now();
		cnrCompleteYn = 1;
	}

	public void updateDadDetId(Long dadDetId) {
		this.dadDetId = dadDetId;
	}

	public void updateCnrFail(CnrFailCause cnrFailCause, String cnrFailComt) {
		this.cnrFailCause = cnrFailCause;
		this.cnrFailComt = cnrFailComt;
	}
}
