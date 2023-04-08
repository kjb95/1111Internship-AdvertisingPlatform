import {Button, Input} from "antd";
import React, {useState} from 'react';
import {findItems} from "../../../../../api/item/ItemApi";
import {onPressEnter} from "../../../../../constants/Function";
import {AdRegItem} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionFooter from "../../../../section/SectionFooter";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";
import Dt from "../../../../table/Dt";

interface Props {
	setItems: React.Dispatch<React.SetStateAction<AdRegItem[]>>,
}

function ItemLookUpByCondition({setItems}: Props) {
	const [itemName, setItemName] = useState<string>("");
	const [itemNo, setItemNo] = useState<string>("");

	function handleClick() {
		findItems(itemName, itemNo)
			.then((res) => setItems(res.data.items))
			.catch((e) => console.log(e))
	}

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">상품 조회</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="상품명"/>
					<Dd>
						<Input value={itemName} placeholder="상품명을 입력하세요." style={{width: 500}} onChange={(e) => setItemName(e.target.value)} onPressEnter={(e) => onPressEnter(e, handleClick)}/>
					</Dd>
				</dl>
				<dl>
					<Dt title="상품번호"/>
					<Dd>
						<Input value={itemNo} placeholder="상품번호를 입력하세요." style={{width: 500}} onChange={(e) => setItemNo(e.target.value)} onPressEnter={(e) => onPressEnter(e, handleClick)}/>
					</Dd>
				</dl>
			</SectionBody>
			<SectionFooter>
				<Button type="primary" size="large" className="pink" onClick={() => handleClick()}>조회</Button>
			</SectionFooter>
		</section>
	);
}

export default ItemLookUpByCondition;