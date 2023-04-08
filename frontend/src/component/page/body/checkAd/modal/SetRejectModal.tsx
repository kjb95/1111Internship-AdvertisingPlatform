import {Button, Input, Modal, Select} from "antd";
import React, {useEffect, useState} from 'react';
import {findAllCnrFailCause, updateCheckProcess} from "../../../../../api/cnrReq/CnrReqApi";
import {onPressEnter, updateAdCheckList} from "../../../../../constants/Function";
import {AdCheckKwd, SelectOption} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import Dt from "../../../../table/Dt";

interface Props {
	isSetRejectModalOpen: boolean
	setIsSetRejectModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	dadDetId: number,
	setAdCheckList: React.Dispatch<React.SetStateAction<AdCheckKwd[]>>,
	kwdNameSearch: string
}

function SetRejectModal({isSetRejectModalOpen, setIsSetRejectModalOpen, dadDetId, setAdCheckList, kwdNameSearch}: Props) {
	const [selectedCnrFailCause, setSelectedCnrFailCause] = useState<string>("");
	const [cnrFailComt, setCnrFailComt] = useState<string>("");
	const [cnrFailCauseOptions, setCnrFailCauseOptions] = useState<SelectOption[]>([]);

	useEffect(() => {
		findAllCnrFailCause()
			.then(res => setCnrFailCauseOptions(res.data.failCauses))
			.catch(e => console.log(e));
	}, []);

	function updateCheckProcessSuccess() {
		setIsSetRejectModalOpen(false);
		updateAdCheckList(kwdNameSearch, setAdCheckList);
		return Modal.success({title: "검수 처리가 반려되었습니다"});
	}

	function handleClick() {
		updateCheckProcess(dadDetId, false, selectedCnrFailCause, cnrFailComt)
			.then(() => updateCheckProcessSuccess())
			.catch(e => console.log(e));
	}

	return (
		<Modal title="검수 반려 처리" width={800} open={isSetRejectModalOpen} onCancel={() => setIsSetRejectModalOpen(false)}
		       footer={[
			       <Button key="register" type="primary" size="large" className="pink" onClick={handleClick}>확인</Button>
		       ]}
		>
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<Dt title="검수 실패 사유"/>
						<Dd>
							<Select style={{width: 150}} options={cnrFailCauseOptions} value={selectedCnrFailCause} placeholder="검수 실패 사유를 선택해주세요" onChange={(value) => setSelectedCnrFailCause(value)}/>
						</Dd>
					</dl>
					<dl>
						<Dt title="검수 실패 코멘트"/>
						<Dd>
							<Input value={cnrFailComt} placeholder="검수 실패 코멘트를 입력하세요." onChange={(e) => setCnrFailComt(e.target.value)} onPressEnter={(e) => onPressEnter(e, handleClick)}/>
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default SetRejectModal;