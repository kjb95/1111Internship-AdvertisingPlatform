package backend.week8.domain.dadDetBid.entity;

import backend.week8.domain.dadDet.entity.DadDet;
import lombok.*;

import javax.persistence.*;

@Table(name = "DAD_DET_BID")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadDetBid {

	public DadDetBid(DadDet dadDet, int bidCost) {
		this.dadDet = dadDet;
		this.bidCost = bidCost;
	}

	@Id
	@Column(name = "DAD_DET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dadDetId;
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "DAD_DET_ID")
	private DadDet dadDet;

	@Column(name = "BID_COST")
	private int bidCost;
}
