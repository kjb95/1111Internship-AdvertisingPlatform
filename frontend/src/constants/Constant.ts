import {AdRegItem} from "./Interface";

export const JWT_TOKEN = "jwtToken";
export const AUTHENTICATED_MEMBER_ID = "authenticatedMemberId";
export const ROLE_ADV = "ROLE_ADV";
export const ROLE_ADMIN = "ROLE_ADMIN";
export const ROLE = "ROLE";

export const MIN_BID = 90;
export const MAX_BID = 99000;

export const ItemDefaultValue: AdRegItem = {
	key: "",
	itemNo: "",
	itemName: "",
	adultYn: "",
	itemOrgCost: 0,
	itemActYn: "",
}

export const taskReqHistoryCsvHeaders = [
	{label: "날짜", key: "date"},
	{label: "직접 광고 ID", key: "dadDetId"},
	{label: "노출수", key: "impressions"},
	{label: "클릭수", key: "clicks"},
	{label: "평균 노출 순위", key: "averageImpressionRank"},
	{label: "평균 클릭 비용", key: "averageClickCost"},
	{label: "광고비", key: "advertisingCost"}
];