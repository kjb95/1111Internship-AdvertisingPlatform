import {Button, message, Switch} from "antd";
import React, {useEffect, useState} from 'react';
import {findAdv, updateAdIngActYn} from "../../../../../api/adv/AdvApi";
import {AUTHENTICATED_MEMBER_ID} from "../../../../../constants/Constant";
import {Adv} from "../../../../../constants/Interface";
import {toWon} from "../../../../../utils/Utils";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";
import DdTableCell from "../../../../table/DdTableCell";
import Dt from "../../../../table/Dt";
import SetDayLimitBudgetModal from "../modal/SetDayLimitBudgetModal";

const AdvDefaultValue: Adv = {
	adIngActYn: false,
	balance: 0,
	eventMoney: 0,
	dayLimitBudget: 0
}

function AdvAccountSet() {
	const [adv, setAdv] = useState<Adv>(AdvDefaultValue);
	const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
	const [messageApi, contextHolder] = message.useMessage();
	const advId = sessionStorage.getItem(AUTHENTICATED_MEMBER_ID);

	useEffect(() => {
		findAdv(advId)
			.then(res => setAdv(res.data))
			.catch(e => console.log(e))
	}, [advId]);

	function computeBalanceStatus() {
		return adv.balance + adv.eventMoney > 0 ? "정상" : "잔액 없음";
	}

	function computeDayLimitBudget() {
		return adv.dayLimitBudget === 0 ? "무제한" : toWon(adv.dayLimitBudget);
	}

	function handleAdSetSuccess(checked: boolean) {
		setAdv({adIngActYn: checked, balance: adv.balance, eventMoney: adv.eventMoney, dayLimitBudget: adv.dayLimitBudget})
		return messageApi.success("광고 설정이 변경되었습니다");
	}

	function handleAdSet(checked: boolean) {
		updateAdIngActYn(advId, checked)
			.then(() => handleAdSetSuccess(checked))
			.catch(e => console.log(e));
	}


	return (
		<section className="wrap-section wrap-tbl">
			{contextHolder}
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">광고주 계정 설정 및 정보</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="광고 설정"/>
					<Dd><Switch checkedChildren="ON" unCheckedChildren="OFF" size="small" onClick={(checked) => handleAdSet(checked)} checked={adv.adIngActYn}/></Dd>
				</dl>
				<dl>
					<Dt title="충전금 잔액"/>
					<DdTableCell>{toWon(adv.balance)}</DdTableCell>
				</dl>
				<dl>
					<Dt title="이벤트 머니 잔액"/>
					<DdTableCell>{toWon(adv.eventMoney)}</DdTableCell>
				</dl>
				<dl>
					<Dt title="잔액 상태"/>
					<DdTableCell>{computeBalanceStatus()}</DdTableCell>
				</dl>
				<dl>
					<Dt title="일일 허용 예산"/>
					<DdTableCell>
						<span>{computeDayLimitBudget()}</span>
						<Button type="primary" className="pink" style={{marginLeft: "20px"}} onClick={() => setIsModalOpen(true)}>일일 허용 예산 설정</Button>
						<SetDayLimitBudgetModal isModalOpen={isModalOpen} setIsModalOpen={setIsModalOpen} adv={adv} setAdv={setAdv}/>
					</DdTableCell>
				</dl>
			</SectionBody>
		</section>
	);
}

export default AdvAccountSet;