import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {findItem} from "../../../../../api/item/ItemApi";
import {toWon} from "../../../../../utils/Utils";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import DdTableCell from "../../../../table/DdTableCell";
import Dt from "../../../../table/Dt";

interface Item {
	itemNo: string,
	itemName: string,
	adultYn: boolean,
	itemOrgCost: number
}

const ItemDefaultValue: Item = {
	itemNo: "",
	itemName: "",
	adultYn: false,
	itemOrgCost: 0
}

function ItemInfo() {
	const params = useParams();
	const [item, setItem] = useState<Item>(ItemDefaultValue);
	const adultYn = item.adultYn ? "성인상품" : "일반상품";


	useEffect(() => {
		findItem(params.id)
			.then((res) => setItem(res.data))
			.catch(e => console.log(e));
	}, [params.id]);

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">선택한 상품 정보</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="상품번호"/>
					<DdTableCell>{item.itemNo}</DdTableCell>
				</dl>
				<dl>
					<Dt title="상품명"/>
					<DdTableCell>{item.itemName}</DdTableCell>
				</dl>
				<dl>
					<Dt title="성인여부"/>
					<DdTableCell>{adultYn}</DdTableCell>
				</dl>
				<dl>
					<Dt title="상품 원본 금액"/>
					<DdTableCell>{toWon(item.itemOrgCost)}</DdTableCell>
				</dl>
			</SectionBody>
		</section>
	);
}

export default ItemInfo;