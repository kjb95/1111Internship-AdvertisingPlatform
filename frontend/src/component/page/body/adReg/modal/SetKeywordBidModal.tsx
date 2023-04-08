import {Button, InputNumber, message, Modal} from "antd";
import React, {useState} from 'react';
import {MAX_BID, MIN_BID} from "../../../../../constants/Constant";
import {onPressEnter} from "../../../../../constants/Function";
import {AdRegKwd} from "../../../../../constants/Interface";
import {isInvalidRageNumber} from "../../../../../utils/Utils";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";

interface Props {
	isSetBidModalOpen: boolean
	setIsSetBidModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	keywordList: AdRegKwd[],
	setKeywordList: React.Dispatch<React.SetStateAction<AdRegKwd[]>>,
}

function SetKeywordBidModal({isSetBidModalOpen, setIsSetBidModalOpen, keywordList, setKeywordList}: Props) {
	const [bid, setBid] = useState<string>("0");
	const [messageApi, contextHolder] = message.useMessage();

	function closeModal() {
		setIsSetBidModalOpen(false);
		setBid("");
	}

	function handleRegister() {
		if (isInvalidRageNumber(Number(bid), MIN_BID, MAX_BID)) {
			return messageApi.error("입찰가는 90원이상, 99000원 이하");
		}
		const changeBidKeywordList = keywordList.map(changeBid);
		setKeywordList(changeBidKeywordList);
		closeModal();
	}

	function changeBid(keyword: AdRegKwd) {
		keyword.bid = bid;
		return keyword;
	}

	return (
		<Modal title="키워드 입찰가 일괄 설정" width={800} open={isSetBidModalOpen} onCancel={closeModal}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={closeModal}>취소</Button>,
			       <Button key="reegister" type="primary" size="large" className="pink" onClick={handleRegister}>등록</Button>
		       ]}
		>
			{contextHolder}
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<DtModal title="입찰가 입력"/>
						<Dd>
							<InputNumber style={{width: 300}} type="number" value={bid} step="100"
							             onChange={(e) => setBid(e!)}
							             onPressEnter={(e) => onPressEnter(e, handleRegister)}/>
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default SetKeywordBidModal;