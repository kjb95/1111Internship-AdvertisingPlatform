import {ReactNode} from "react";

export interface ChildProps {
	children: ReactNode
}

export interface Adv {
	adIngActYn: boolean,
	balance: number,
	eventMoney: number,
	dayLimitBudget: number
}

export interface AdMngItem {
	key: string,
	itemNo: string,
	itemName: string,
	adUseConfigYn: string
}

export interface AdRegItem {
	key: string,
	itemNo: string,
	itemName: string,
	adultYn: string,
	itemOrgCost: number,
	itemActYn: string
}

export interface AdRegKwd {
	key: string,
	keywordName: string,
	bid: string
}

export interface AdRegKwdWithoutKey {
	keywordName: string,
	bid: string
}

export interface AdMngAdGroupListAdGroup {
	key: string,
	agroupName: string,
	agroupUseConfigYn: string,
	itemCountLiveAndAll: string,
}

export interface AdMngSetAdGroup {
	agroupName: string,
	agroupUseConfigYn: boolean,
	agroupItemsCount: number,
	regTime: string
}

export interface AdMngKwd {
	key: string,
	kwdName: string,
	dadUseConfigYn: string
}

export interface CheckKwd {
	key: string,
	kwdName: string
}

export interface AdCheckKwd {
	key: number,
	itemName: string,
	kwdName: string,
	checkReason: string,
}

export interface SelectOption {
	value: string,
	label: string
}

export interface Task {
	key: number,
	taskName: string,
	taskStatus: string,
	registrant: string,
	regTime: string,
	reqFile: string,
}

export interface DadDetReportChart {
	category: string;
	date: string;
	value: number;
}

export interface DadDetReportTable {
	key: string;
	date: string;
	impressions: number;
	clicks: number;
	clicksRate: string;
	averageImpressionRank: number;
	averageClickCost: number;
	advertisingCost: number;
}