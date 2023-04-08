import {api} from "../Api";

/**
 * 유저가 가진 모든 권한 조회
 */
export function findAuthorities(username: string) {
	return api.get("/api/member/" + username);
}