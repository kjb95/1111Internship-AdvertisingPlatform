package backend.week8.jwt.controller;

import backend.week8.jwt.dto.AuthenticateClientRequestDto;
import backend.week8.jwt.dto.JwtTokenResponseDto;
import backend.week8.jwt.filter.JwtFilter;
import backend.week8.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jwt")
public class JwtController {

	private final JwtService jwtService;

	/**
	 * 유효한 사용자면 jwt 토큰 반환
	 */
	@PostMapping
	public ResponseEntity<JwtTokenResponseDto> authenticateClient(@RequestBody @Validated AuthenticateClientRequestDto authenticateClientRequestDto, HttpServletResponse response) {
		String jwt = jwtService.authenticateMember(authenticateClientRequestDto);
		response.setHeader(JwtFilter.AUTHORIZATION_HEADER, JwtFilter.BEARER_TOKEN_PREFIX + jwt);
		return ResponseEntity.ok()
				.body(new JwtTokenResponseDto(jwt));
	}
}
