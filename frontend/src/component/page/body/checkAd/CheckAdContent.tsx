import React, {useState} from 'react';
import {AdCheckKwd} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import AdCheckKwdFind from "./contentBody/AdCheckKwdFind";
import AdCheckList from "./contentBody/AdCheckList";

function CheckAdContent() {
	const [adCheckList, setAdCheckList] = useState<AdCheckKwd[]>([]);
	const [kwdNameSearch, setKwdNameSearch] = useState<string>("");

	return (
		<AdPageBody title="광고 검수">
			<AdCheckKwdFind kwdNameSearch={kwdNameSearch} setKwdNameSearch={setKwdNameSearch} setAdCheckList={setAdCheckList}/>
			<AdCheckList adCheckList={adCheckList} setAdCheckList={setAdCheckList} kwdNameSearch={kwdNameSearch}/>
		</AdPageBody>
	);
}

export default CheckAdContent;