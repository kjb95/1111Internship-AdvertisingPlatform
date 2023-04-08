import {api} from "../Api";

/**
 * 조건(상품 명, 상품 번호)에 따라 상품들 조회
 */
export function findItems(itemName: string, itemNo: string) {
	return api.post("/api/item/search", {
		itemName: itemName,
		itemNo: itemNo
	});
}

/**
 * 조건(상품 명, 상품 번호)에 따라 한 광고 그룹에 속한 상품들 조회
 */
export function findItemsInAdGroup(adGroupId: string | undefined, itemName: string, itemNo: string) {
	return api.post("/api/item/inAdGroup/search", {
		adGroupId: adGroupId,
		itemName: itemName,
		itemNo: itemNo
	})
}

/**
 * 상품 한개 조회
 */
export function findItem(itemId: string | undefined) {
	return api.get("/api/item/"+itemId);
}
