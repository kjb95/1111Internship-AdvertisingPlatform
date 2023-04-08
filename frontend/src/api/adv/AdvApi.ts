import {api} from "../Api";

/**
 * 광고주 조회
 */
export function findAdv(advId: string | null) {
	return api.get("/api/adv/" + advId);
}

/**
 * 일일 허용 예산 설정 변경
 */
export function updateDayLimitBudget(advId: string | null, dayLimitBudget: string | null) {
	return api.post("/api/adv/dayLimitBudget", {
		advId: advId,
		dayLimitBudget: dayLimitBudget
	});
}


/**
 * 광고주의 광고 진행 활성 여부 변경
 */
export function updateAdIngActYn(advId: string | null, isOn: boolean) {
	return api.post("/api/adv/adIngActYn", {
		advId: advId,
		on: isOn
	})
}