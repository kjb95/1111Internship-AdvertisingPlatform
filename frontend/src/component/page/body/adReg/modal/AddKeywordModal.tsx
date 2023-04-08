import {Button, Input, InputNumber, message, Modal} from "antd";
import React, {useState} from 'react';
import {MAX_BID, MIN_BID} from "../../../../../constants/Constant";
import {onPressEnter} from "../../../../../constants/Function";
import {AdRegKwd} from "../../../../../constants/Interface";
import {isInvalidRageNumber} from "../../../../../utils/Utils";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";
import {KeywordDefaultValue} from "../AdRegContent";

interface Props {
	isAddKeywordModalOpen: boolean
	setIsAddKeywordModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	keywordList: AdRegKwd[],
	setKeywordList: React.Dispatch<React.SetStateAction<AdRegKwd[]>>,
}

function AddKeywordModal({isAddKeywordModalOpen, setIsAddKeywordModalOpen, keywordList, setKeywordList}: Props) {
	const [keyword, setKeyword] = useState<AdRegKwd>(KeywordDefaultValue);
	const [messageApi, contextHolder] = message.useMessage();

	function closeModal() {
		setIsAddKeywordModalOpen(false);
		setKeyword(KeywordDefaultValue);
	}

	function isDuplicated() {
		return keywordList.filter(kwd => kwd.keywordName === keyword.keywordName).length !== 0;
	}

	function handleRegister() {
		if (isDuplicated()) {
			return messageApi.error("동일한 키워드명이 존재합니다 !!");
		}
		if (isInvalidRageNumber(Number(keyword.bid), MIN_BID, MAX_BID)) {
			return messageApi.error("입찰가는 90원이상, 99000원 이하");
		}
		setKeywordList([...keywordList, keyword]);
		closeModal();
	}

	return (
		<Modal title="키워드 추가" width={800} open={isAddKeywordModalOpen} onCancel={closeModal}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={closeModal}>취소</Button>,
			       <Button key="register" type="primary" size="large" className="pink" onClick={handleRegister}>등록</Button>
		       ]}
		>
			{contextHolder}
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<DtModal title="키워드명 입력"/>
						<Dd>
							<Input style={{width: 300}} type="text" value={keyword.keywordName} onPressEnter={(e) => onPressEnter(e, handleRegister)}
							       onChange={(e) => setKeyword({key: e.target.value, keywordName: e.target.value, bid: keyword.bid})}
							/>
						</Dd>
					</dl>
					<dl>
						<DtModal title="입찰가 입력"/>
						<Dd>
							<InputNumber style={{width: 300}} type="number" value={keyword.bid} step="100"
							             onPressEnter={(e) => onPressEnter(e, handleRegister)}
							             onChange={(e) => setKeyword({key: e!, keywordName: keyword.keywordName, bid: e!})}
							/>
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default AddKeywordModal;