import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {updateKeywords} from "../../../../constants/Function";
import {AdMngKwd} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import ItemInfo from "./contentBody/ItemInfo";
import KeywordList from "./contentBody/KeywordList";
import KeywordSearch from "./contentBody/KeywordSearch";

function AdMngKeywordsContent() {
	const [keywordNameSearch, setKeywordNameSearch] = useState<string>("");
	const [keywords, setKeywords] = useState<AdMngKwd[]>([]);

	const params = useParams();

	useEffect(() => {
		updateKeywords(params.id, "", setKeywords);
	}, [params.id, setKeywords])

	return (
		<AdPageBody title="광고관리 / 키워드">
			<ItemInfo/>
			<KeywordSearch keywordNameSearch={keywordNameSearch} setKeywordNameSearch={setKeywordNameSearch} setKeywords={setKeywords}/>
			<KeywordList keywordNameSearch={keywordNameSearch} keywords={keywords} setKeywords={setKeywords}/>
		</AdPageBody>
	);
}

export default AdMngKeywordsContent;