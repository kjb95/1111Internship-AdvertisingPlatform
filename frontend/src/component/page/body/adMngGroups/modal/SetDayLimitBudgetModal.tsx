import {Button, InputNumber, message, Modal} from "antd";
import React, {useState} from 'react';
import {updateDayLimitBudget} from "../../../../../api/adv/AdvApi";
import {AUTHENTICATED_MEMBER_ID} from "../../../../../constants/Constant";
import {onPressEnter} from "../../../../../constants/Function";
import {Adv} from "../../../../../constants/Interface";
import {isInvalidRageNumber} from "../../../../../utils/Utils";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";

interface Props {
	isModalOpen: boolean,
	setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	adv: Adv,
	setAdv: React.Dispatch<React.SetStateAction<Adv>>
}

function SetDayLimitBudgetModal({isModalOpen, setIsModalOpen, adv, setAdv}: Props) {
	const [budget, setBudget] = useState<string | null>("");
	const [messageApi, contextHolder] = message.useMessage();

	function handleOnClick() {
		if (Number(budget) % 100 !== 0) {
			return messageApi.error("입찰 금액은 100원 단위 이어야 함");
		}
		if (budget === "" || isInvalidRageNumber(Number(budget), 0, 100000000)) {
			return messageApi.error("입찰 금액은 0원 이상, 1억 이하이어야 함");
		}

		updateDayLimitBudget(sessionStorage.getItem(AUTHENTICATED_MEMBER_ID), budget)
			.then(updateDayLimitBudgetSuccess)
			.catch(e => console.log(e));
	}

	function updateDayLimitBudgetSuccess() {
		setAdv({adIngActYn: adv.adIngActYn, balance: adv.balance, eventMoney: adv.eventMoney, dayLimitBudget: Number(budget)});
		modalClose();
		return messageApi.success("일일 허용 예산 변경 성공");
	}

	function modalClose() {
		setBudget("");
		setIsModalOpen(false);
	}

	return (
		<Modal title="일일 허용 예산 설정" width={800} open={isModalOpen} onCancel={modalClose}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={modalClose}>취소</Button>,
			       <Button key="update" type="primary" size="large" className="pink" onClick={handleOnClick}>변경</Button>
		       ]}
		>
			<section className="wrap-section wrap-tbl">
				{contextHolder}
				<SectionBody>
					<dl>
						<DtModal title="일일 허용 예산"/>
						<Dd>
							<InputNumber style={{width: 300}} type="number" value={budget} step="100"
							             onChange={(e) => setBudget(e)}
							             onPressEnter={(e) => onPressEnter(e, handleOnClick)}
							/>원
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default SetDayLimitBudgetModal;