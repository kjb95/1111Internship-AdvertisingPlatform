package backend.week8.domain.ad.entity;

import backend.week8.domain.adv.entity.Adv;
import backend.week8.domain.agroup.entity.AGroup;
import backend.week8.domain.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "AD")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

	public Ad(AGroup aGroup, Item item, Adv adv) {
		this.agroup = aGroup;
		this.item = item;
		this.adUseConfigYn = 1;
		this.regTime = LocalDateTime.now();
		this.adActYn = 1;
		this.adv = adv;
	}

	@Id
	@Column(name = "AD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGROUP_ID", nullable = false)
	private AGroup agroup;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID", nullable = false)
	private Item item;
	@Column(name = "AD_USE_CONFIG_YN", nullable = false)
	private int adUseConfigYn;
	@Column(name = "REG_TIME", nullable = false)
	private LocalDateTime regTime;
	@Column(name = "AD_ACT_YN", nullable = false)
	private int adActYn;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADV_ID", nullable = false)
	private Adv adv;
}
