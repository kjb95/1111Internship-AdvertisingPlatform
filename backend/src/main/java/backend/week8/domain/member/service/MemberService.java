package backend.week8.domain.member.service;

import backend.week8.domain.member.dto.response.RoleResponseDto;
import backend.week8.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static backend.week8.common.constant.Constant.NOT_FOUND_USERNAME;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;

	public RoleResponseDto findRoles(String username) {
		List<String> allRoles = memberRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + NOT_FOUND_USERNAME))
				.getRole()
				.findAllRoles();
		return new RoleResponseDto(allRoles);
	}
}
