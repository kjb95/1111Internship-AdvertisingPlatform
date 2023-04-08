import React, {Key} from "react";
import {api} from "../Api";

/**
 * 모든 광고 그룹의 아이디와 이름만 조회
 */
export function findAllAdGroup() {
	return api.get("/api/agroup/all");
}

/**
 * 조건에 따른 광고 그룹 검색
 */
export function findAllAdGroupsByAdGroupName(groupNameCondition: string) {
	return api.get("/api/agroup/all/condition?groupName=" + groupNameCondition);
}

/**
 * 광고 그룹의 사용 설정 여부 변경
 */
export function updateAdGroupUseConfig(adGroupIds: (Key | undefined)[], on: boolean) {
	return api.put("/api/agroup/useConfig", {
		adGroupIds: adGroupIds,
		on: on
	})
}

/**
 * 광고 그룹 등록
 */
export function registerAdGroup(adGroupName: string) {
	return api.post("/api/agroup", {adGroupName: adGroupName});
}

/**
 * 광고 그룹 활성 여부 끄기
 */
export function updateAdGroupActOff(adGroupIds: React.Key[]) {
	return api.put("/api/agroup/actOff", {
		adGroupIds: adGroupIds
	})
}

/**
 * 광고 그룹 한개 조회
 */
export function findAdGroup(adGroupId: string | undefined, advId: string | null) {
	return api.post("/api/agroup/search", {
		adGroupId: adGroupId,
		advId: advId
	});
}

/**
 * 광고 그룹명 변경
 */
export function updateAdGroupName(adGroupId: string | undefined, adGroupName: string) {
	return api.put("/api/agroup/adGroupName", {
		adGroupId: adGroupId,
		adGroupName: adGroupName
	})
}