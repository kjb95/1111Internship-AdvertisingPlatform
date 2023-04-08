import {api} from "../Api";

/**
 * 리포트 차트 데이터 조회
 */
export function findDadDetReport(dadDetId: number) {
	return api.get("/api/dadDetReport/"+dadDetId);
}

/**
 * 직접광고 리포트의 모든 데이터 카테고리 조회
 */
export function findAllDadDetReportCategory() {
	return api.get("/api/dadDetReport/category");
}