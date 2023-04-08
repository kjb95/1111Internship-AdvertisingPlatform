import React from "react";
import {findAdGroup, findAllAdGroupsByAdGroupName} from "../api/agroup/AgroupApi";
import {findAllAdCheckList} from "../api/cnrReq/CnrReqApi";
import {findKeywordsInItem} from "../api/dadDet/DadDetApi";
import {findItemsInAdGroup} from "../api/item/ItemApi";
import {findAllKwd} from "../api/kwd/KwdApi";
import {findTaskReqHistory} from "../api/taskReq/TaskReqApi";
import {hasBlank, hasForbiddenStr, isEndStr, isValidStrLen} from "../utils/Utils";
import {AUTHENTICATED_MEMBER_ID} from "./Constant";
import {AdCheckKwd, AdMngAdGroupListAdGroup, AdMngItem, AdMngKwd, AdMngSetAdGroup, CheckKwd, Task} from "./Interface";

export function updateAdGroups(adGroupNameSearchKeyword: string, setAdGroups: React.Dispatch<React.SetStateAction<AdMngAdGroupListAdGroup[]>>) {
	findAllAdGroupsByAdGroupName(adGroupNameSearchKeyword)
		.then(res => setAdGroups(res.data.agroups))
		.catch(e => console.log(e))
}

export function updateAdGroup(adGroupId: string | undefined, setAdGroup: React.Dispatch<React.SetStateAction<AdMngSetAdGroup>>) {
	findAdGroup(adGroupId, sessionStorage.getItem(AUTHENTICATED_MEMBER_ID))
		.then(res => setAdGroup(res.data))
		.catch(e => console.log(e));
}

export function updateItemsInAdgroup(adGroupId: string | undefined, itemName: string, itemNo: string, setItems: React.Dispatch<React.SetStateAction<AdMngItem[]>>) {
	findItemsInAdGroup(adGroupId, itemName, itemNo)
		.then(res => setItems(res.data.items))
		.catch(e => console.log(e));
}

export function updateKeywords(itemId: string | undefined, keywordNameSearch: string, setKeywords: React.Dispatch<React.SetStateAction<AdMngKwd[]>>) {
	findKeywordsInItem(itemId, keywordNameSearch)
		.then(res => setKeywords(res.data.keywords))
		.catch(e => console.log(e));
}

export function validAdGroupName(adGroupName: string) {
	if (hasBlank(adGroupName)) {
		return "공백은 입력할 수 없습니다";
	}
	if (!isEndStr(adGroupName, '그룹')) {
		return "광고그룹 명은 ~~~ 그룹 으로 끝나야 합니다";
	}
	if (!isValidStrLen(adGroupName, 3, 10)) {
		return "광고그룹 명은 3글자 이상, 10글자 이하 이어야 합니다";
	}
	if (hasForbiddenStr(adGroupName, '!')) {
		return "!는 금칙어 입니다";
	}
	return '';
}

interface HandleOnCLick {
	(): void;
}

export function onPressEnter(e: React.KeyboardEvent<HTMLInputElement>, handleOnClick: HandleOnCLick) {
	if (!e.nativeEvent.isComposing) {
		return handleOnClick();
	}
}

export function updateCheckKwds(kwdName: string, setCheckKwd: React.Dispatch<React.SetStateAction<CheckKwd[]>>) {
	findAllKwd(kwdName)
		.then(res => setCheckKwd(res.data.kwds))
		.catch(e => console.log(e));
}

export function updateAdCheckList(kwdName: string, setAdCheckList: React.Dispatch<React.SetStateAction<AdCheckKwd[]>>) {
	findAllAdCheckList(kwdName)
		.then(res => setAdCheckList(res.data.adCheckList))
		.catch(e => console.log(e));
}

export function updateTaskReqHistory(setTaskHistory: React.Dispatch<React.SetStateAction<Task[]>>) {
	findTaskReqHistory()
		.then(res => setTaskHistory(res.data.taskReqHistory))
		.catch(e => console.log(e));
}