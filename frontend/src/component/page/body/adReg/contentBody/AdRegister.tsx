import {Button, message, Modal} from "antd";
import React from 'react';
import {registerAd} from "../../../../../api/ad/AdApi";
import {AUTHENTICATED_MEMBER_ID} from "../../../../../constants/Constant";
import {AdRegKwd, AdRegKwdWithoutKey} from "../../../../../constants/Interface";
import SectionFooter from "../../../../section/SectionFooter";

interface Props {
	keywordList: AdRegKwd[],
	agroupId: string,
	itemId: string
}

function AdRegister({keywordList, agroupId, itemId}: Props) {
	const [messageApi, contextHolder] = message.useMessage();

	function handleOnClick() {
		const kwds: AdRegKwdWithoutKey[] = keywordList.map(keyword => ({keywordName: keyword.keywordName, bid: keyword.bid}));

		if (agroupId === "") {
			return messageApi.error("광고 그룹 등록이 필요합니다");
		}
		if (keywordList.length === 0) {
			return messageApi.error("키워드 등록이 필요합니다");
		}
		registerAd(agroupId, itemId, sessionStorage.getItem(AUTHENTICATED_MEMBER_ID), kwds)
			.then(() => Modal.success({title: "광고 등록 성공", onOk: () => window.location.reload()}))
			.catch((e) => messageApi.error(e.response.data.message))
	}

	return (
		<SectionFooter>
			{contextHolder}
			<Button type="primary" size="large" className="pink" block={true} onClick={() => handleOnClick()}>광고 등록</Button>
		</SectionFooter>
	);
}

export default AdRegister;