import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {updateItemsInAdgroup} from "../../../../constants/Function";
import {AdMngItem, AdMngSetAdGroup} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import AdGroupSet from "./contentBody/AdGroupSet";
import ItemList from './contentBody/ItemList';
import ItemSearch from "./contentBody/ItemSearch";

const AdMngAdGroupDefaultValue: AdMngSetAdGroup = {
	agroupName: "",
	agroupUseConfigYn: false,
	agroupItemsCount: 0,
	regTime: ""
}

function AdMngItemsContent() {
	const [items, setItems] = useState<AdMngItem[]>([]);
	const [itemName, setItemName] = useState<string>("");
	const [itemNo, setItemNo] = useState<string>("");
	const [adGroup, setAdGroup] = useState<AdMngSetAdGroup>(AdMngAdGroupDefaultValue);
	const params = useParams();

	useEffect(() => {
		updateItemsInAdgroup(params.id, "", "", setItems);
	}, [params.id, setItems]);

	return (
		<AdPageBody title="광고관리 / 상품">
			<AdGroupSet adGroup={adGroup} setAdGroup={setAdGroup}/>
			<ItemSearch itemName={itemName} setItemName={setItemName} itemNo={itemNo} setItemNo={setItemNo} setItems={setItems}/>
			<ItemList items={items} itemName={itemName} itemNo={itemNo} setItems={setItems} setAdGroup={setAdGroup}/>
		</AdPageBody>
	);
}

export default AdMngItemsContent;