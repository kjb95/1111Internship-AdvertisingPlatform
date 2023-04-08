import {Input} from "antd";
import {Button} from "antd/lib";
import React from 'react';
import {useParams} from "react-router";
import {onPressEnter, updateItemsInAdgroup} from "../../../../../constants/Function";
import {AdMngItem} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";

interface Props {
	itemName: string,
	setItemName: React.Dispatch<React.SetStateAction<string>>,
	itemNo: string,
	setItemNo: React.Dispatch<React.SetStateAction<string>>,
	setItems: React.Dispatch<React.SetStateAction<AdMngItem[]>>,
}

function ItemSearch({itemName, setItemName, itemNo, setItemNo, setItems}: Props) {
	const params = useParams();

	function onClick() {
		return updateItemsInAdgroup(params.id, itemName, itemNo, setItems);
	}

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">상품 검색</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dd>상품 명
						: <Input style={{width: 300}} type="text" placeholder="상품명을 입력해주세요" value={itemName} onChange={(e) => setItemName(e.target.value)} onPressEnter={(e) => onPressEnter(e, onClick)}/></Dd>
					<Dd>상품 번호
						: <Input style={{width: 300}} type="text" placeholder="상품번호를 입력해주세요" value={itemNo} onChange={(e) => setItemNo(e.target.value)} onPressEnter={(e) => onPressEnter(e, onClick)}/></Dd>
					<div className="box-right"><Dd><Button type="primary" className="gray" onClick={onClick}>상품 조회</Button></Dd></div>
				</dl>
			</SectionBody>
		</section>
	);
}

export default ItemSearch;