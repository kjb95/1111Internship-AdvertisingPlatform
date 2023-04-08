package backend.week8.security.service;

import backend.week8.domain.member.entity.Member;
import backend.week8.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static backend.week8.common.constant.Constant.NOT_FOUND_USERNAME;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * 유효한 사용자면 UserDetails 반환
     * 유효하지 않은 사용자면 UsernameNotFoundException 에러발생
     *
     * @see backend.week8.jwt.service.JwtService#authenticateMember
     * JwtService.authenticate() 호출시 loadUserByUsernae()가 호출됨
     * @see backend.week8.common.controller.ControllerAdvice#handleAuthenticationException(AuthenticationException)
     * 시큐리티가 UsernameNotFoundException 예외를 캐치하고, BadCredentialsException 예외를 던짐
     * 컨트롤러 어드바이스에서 BadCredentialsException 예외를 처리
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findById(username)
                .map(this::createMember)
                .orElseThrow(() -> new UsernameNotFoundException(username + NOT_FOUND_USERNAME));
    }

    private User createMember(Member member) {
        List<SimpleGrantedAuthority> grantedAuthorities = member.getRole()
                .findAllRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return new User(member.getMemberId(), member.getPwd(), grantedAuthorities);
    }
}
