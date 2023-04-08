import {api} from "../Api";

/**
 * 로그인 성공시 jwt 토큰 반환
 */
export function jwtAuthenticate(username: string, password: string) {
	return api.post("/api/jwt", {
		username: username,
		password: password
	});
}