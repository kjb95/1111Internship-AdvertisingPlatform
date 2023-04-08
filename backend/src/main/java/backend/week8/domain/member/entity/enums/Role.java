package backend.week8.domain.member.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {
    ROLE_ADV("ROLE_ADV"), ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    public List<String> findAllRoles() {
        return Arrays.stream(role.split(","))
                .collect(Collectors.toList());
    }
}
