package backend.week8.domain.kwd.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "KWD")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Kwd {

    public Kwd(String kwdName) {
        this.kwdName = kwdName;
        this.sellPossKwdYn = 1;
        this.manualCnrKwdYn = 1;
    }

    @Id
    @Column(name = "KWD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kwdId;

    @Column(name = "KWD_NAME", nullable = false)
    private String kwdName;
    @Column(name = "SELL_POSS_KWD_YN", nullable = false)
    private int sellPossKwdYn;
    @Column(name = "MANUAL_CNR_KWD_YN", nullable = false)
    private int manualCnrKwdYn;
}
