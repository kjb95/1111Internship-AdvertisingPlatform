import React, {useState} from 'react';
import {CheckKwd} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import CheckKwdFind from "./contentBody/CheckKwdFind";
import CheckKwdList from "./contentBody/CheckKwdList";


function CheckKwdContent() {
	const [checkKwd, setCheckKwd] = useState<CheckKwd[]>([]);
	const [kwdNameSearch, setKwdNameSearch] = useState<string>("");

	return (
		<AdPageBody title="키워드 검수">
			<CheckKwdFind setCheckKwd={setCheckKwd} kwdNameSearch={kwdNameSearch} setKwdNameSearch={setKwdNameSearch}/>
			<CheckKwdList checkKwd={checkKwd} setCheckKwd={setCheckKwd} kwdNameSearch={kwdNameSearch}/>
		</AdPageBody>
	);
}

export default CheckKwdContent;