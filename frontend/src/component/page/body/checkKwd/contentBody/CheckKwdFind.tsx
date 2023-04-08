import {Input} from "antd";
import {Button} from "antd/lib";
import React, {useEffect} from 'react';
import {onPressEnter, updateCheckKwds} from "../../../../../constants/Function";
import {CheckKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";
import Dt from "../../../../table/Dt";

interface Props {
	setCheckKwd: React.Dispatch<React.SetStateAction<CheckKwd[]>>,
	kwdNameSearch: string,
	setKwdNameSearch: React.Dispatch<React.SetStateAction<string>>
}

function CheckKwdFind({setCheckKwd, kwdNameSearch, setKwdNameSearch}: Props) {

	function onClick() {
		updateCheckKwds(kwdNameSearch, setCheckKwd);
	}

	useEffect(() => {
		updateCheckKwds("", setCheckKwd);
	}, [setCheckKwd]);

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">검수 키워드 조회</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="키워드명"/>
					<Dd>
						<Input style={{width: 300}} type="text" placeholder="키워드명을 입력해주세요" value={kwdNameSearch}
						       onChange={(e) => setKwdNameSearch(e.target.value)}
						       onPressEnter={(e) => onPressEnter(e, onClick)}
						/>
					</Dd>
					<div className="box-right"><Dd><Button type="primary" className="gray" onClick={onClick}>키워드 조회</Button></Dd></div>
				</dl>
			</SectionBody>
		</section>
	);
}

export default CheckKwdFind;