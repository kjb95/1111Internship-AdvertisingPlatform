package backend.week8.domain.dadDet.entity;

import backend.week8.domain.ad.entity.Ad;
import backend.week8.domain.cnrReq.entity.CnrReq;
import backend.week8.domain.dadDet.entity.enums.DadCnrStatus;
import backend.week8.domain.kwd.entity.Kwd;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "DAD_DET")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadDet {

	public DadDet(Ad ad, Kwd kwd, CnrReq cnrReq) {
		this.ad = ad;
		this.kwd = kwd;
		this.dadCnrStatus = kwd.getManualCnrKwdYn() == 1 ? DadCnrStatus.REQ : DadCnrStatus.APPROVAL;
		this.cnrReq = cnrReq;
		this.dadUseConfigYn = 1;
		this.dadActYn = 1;
		this.regTime = LocalDateTime.now();
	}

	@Id
	@Column(name = "DAD_DET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dadDetId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AD_ID", nullable = false)
	private Ad ad;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KWD_ID", nullable = false)
	private Kwd kwd;
	@Column(name = "DAD_CNR_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private DadCnrStatus dadCnrStatus;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CNR_REQ_ID", nullable = false)
	private CnrReq cnrReq;
	@Column(name = "DAD_USE_CONFIG_YN", nullable = false)
	private int dadUseConfigYn;
	@Column(name = "DAD_ACT_YN", nullable = false)
	private int dadActYn;
	@Column(name = "REG_TIME", nullable = false)
	private LocalDateTime regTime;

	public void updateDadCnrStatus(DadCnrStatus dadCnrStatus) {
		this.dadCnrStatus = dadCnrStatus;
	}
}
