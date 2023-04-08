package backend.week8.domain.agroup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "AGroup")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AGroup {

	public AGroup(String aGroupName) {
		this.agroupName = aGroupName;
		regTime = LocalDateTime.now();
		agroupActYn = 1;
		agroupUseConfigYn = 1;
	}

	@Id
	@Column(name = "AGROUP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agroupId;

	@Column(name = "AGROUP_NAME", nullable = false)
	private String agroupName;
	@Column(name = "REG_TIME", nullable = false)
	private LocalDateTime regTime;
	@Column(name = "AGROUP_ACT_YN", nullable = false)
	private int agroupActYn;
	@Column(name = "AGROUP_USE_CONFIG_YN", nullable = false)
	private int agroupUseConfigYn;
}
