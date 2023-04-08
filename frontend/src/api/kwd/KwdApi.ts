import {api} from "../Api";

/**
 * 모든 키워드 조회(수동 검수 키워드 1)
 */
export function findAllKwd(kwdName: string) {
	return api.get("/api/kwd?kwdName=" + kwdName);
}

/**
 * 키워드 등록
 */
export function registerKwd(kwdName: string) {
	return api.post("/api/kwd", {
		kwdName:kwdName
	})
}

/**
 * 수동 검수 키워드 여부 끄기
 */
export function updateManualCnrKwdYnOff(kwdId: string) {
	return api.put("/api/kwd/manualCnrKwdYnOff", {
		kwdId: kwdId
	})
}