import {Input} from "antd";
import {Button} from "antd/lib";
import React from 'react';
import {useParams} from "react-router";
import {onPressEnter, updateKeywords} from "../../../../../constants/Function";
import {AdMngKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";
import Dt from "../../../../table/Dt";

interface Props {
	keywordNameSearch: string,
	setKeywordNameSearch: React.Dispatch<React.SetStateAction<string>>
	setKeywords: React.Dispatch<React.SetStateAction<AdMngKwd[]>>
}

function KeywordSearch({keywordNameSearch, setKeywordNameSearch, setKeywords}: Props) {
	const params = useParams();

	function onClick() {
		return updateKeywords(params.id, keywordNameSearch, setKeywords);
	}

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">키워드 검색</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="키워드명 검색"/>
					<Dd><Input style={{width: 300}} type="text" placeholder="키워드명을 입력해주세요" value={keywordNameSearch} onChange={(e) => setKeywordNameSearch(e.target.value)} onPressEnter={(e) => onPressEnter(e, onClick)}/></Dd>
					<div className="box-right"><Dd><Button type="primary" className="gray" onClick={onClick}>키워드 조회</Button></Dd></div>
				</dl>
			</SectionBody>
		</section>
	);
}

export default KeywordSearch;