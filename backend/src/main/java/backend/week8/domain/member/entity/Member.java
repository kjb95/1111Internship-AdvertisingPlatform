package backend.week8.domain.member.entity;

import backend.week8.domain.member.entity.enums.Role;
import lombok.*;

import javax.persistence.*;

@Table(name = "MEMBER")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PWD", nullable = false)
    private String pwd;
    @Column(name = "ROLE_GROUP")
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
