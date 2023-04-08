package backend.week8.domain.item.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "ITEM")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "ITEM_NO")
    private String itemNo;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Column(name = "ADULT_YN")
    private int adultYn;
    @Column(name = "ITEM_ORG_COST")
    private int itemOrgCost;
    @Column(name = "ITEM_ACT_YN", nullable = false)
    private int itemActYn;
}
