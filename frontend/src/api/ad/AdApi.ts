import React, {Key} from "react";
import {AdRegKwdWithoutKey} from "../../constants/Interface";
import {api} from "../Api";

/**
 * 광고 등록
 */
export function registerAd(agroupId: string, itemId: string, advId: string | null, keywordList: AdRegKwdWithoutKey[]) {
	return api.post("/api/ad", {
		agroupId: agroupId,
		itemId: itemId,
		advId: advId,
		keywordList: keywordList
	})
}

/**
 * 광고 사용 설정 여부, 직접광고 사용 설정 여부 변경
 */
export function updateAdUseConfigAndDadUseConfig(itemIds: Key[], on: boolean) {
	return api.put("/api/ad/useConfig", {
		itemIds: itemIds,
		on: on
	})
}

/**
 * 광고 활성 여부 끄기
 */
export function updateAdActOff(itemIds: React.Key[]) {
	return api.put("/api/ad/actOff", {
		itemIds: itemIds
	})
}