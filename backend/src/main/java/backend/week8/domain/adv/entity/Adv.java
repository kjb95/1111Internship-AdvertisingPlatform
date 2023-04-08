package backend.week8.domain.adv.entity;

import backend.week8.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Table(name = "ADV")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adv {

    @Id
    @Column(name = "ADV_ID")
    private String advId;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ADV_ID")
    private Member member;

    @Column(name = "AD_ING_ACT_YN")
    private int adIngActYn;
    @Column(name = "BALANCE")
    private int balance;
    @Column(name = "EVENT_MONEY")
    private int eventMoney;
    @Column(name = "DAY_LIMIT_BUDGET")
    private int dayLimitBudget;
}
