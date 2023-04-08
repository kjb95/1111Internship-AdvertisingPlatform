import {api} from "../Api";

/**
 * 검수 진행 상태, 검수 처리 시간, 검수 완료 여부, 직접광고 검수 상태, 검수 실패 사유, 검수 실패 코멘트 변경
 */
export function updateCheckProcess(dadDetId: number, cnrIngStatus: boolean, selectedCnrFailCause?: string, cnrFailComt?: string) {
	return api.put("/api/cnrReq", {
		dadDetId: dadDetId,
		cnrIngStatus: cnrIngStatus,
		selectedCnrFailCause: selectedCnrFailCause,
		cnrFailComt: cnrFailComt
	});
}

/**
 * 모든 검수 실패 사유 조회
 */
export function findAllCnrFailCause() {
	return api.get("/api/cnrReq/cnrFailCause");
}

/**
 * 광고 검수 대상 리스트 조회
 */
export function findAllAdCheckList(kwdName: string) {
	return api.get("/api/cnrReq/checkList?kwdName=" + kwdName)
}