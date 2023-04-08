import React from 'react';
import {AdRegItem} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import DdTableCell from "../../../../table/DdTableCell";
import Dt from "../../../../table/Dt";

interface Props {
	selectedItem: AdRegItem,
}

function ItemSelected({selectedItem}: Props) {
	const adultYn = selectedItem.adultYn === "YES" ? "성인상품" : "일반상품";

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">선택한 상품 정보</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="상품번호"/>
					<DdTableCell>{selectedItem.itemNo}</DdTableCell>
				</dl>
				<dl>
					<Dt title="상품명"/>
					<DdTableCell>{selectedItem.itemName}</DdTableCell>
				</dl>
				<dl>
					<Dt title="성인여부"/>
					<DdTableCell>{adultYn}</DdTableCell>
				</dl>
			</SectionBody>
		</section>
	);
}

export default ItemSelected;