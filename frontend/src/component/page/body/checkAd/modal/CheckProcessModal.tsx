import {Button, Modal} from "antd";
import React from 'react';
import {updateCheckProcess} from "../../../../../api/cnrReq/CnrReqApi";
import {updateAdCheckList} from "../../../../../constants/Function";
import {AdCheckKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import DdTableCell from "../../../../table/DdTableCell";
import DtModal from "../../../../table/DtModal";

interface Props {
	isCheckProcessModalOpen: boolean,
	setIsCheckProcessModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	adCheckKwd: AdCheckKwd,
	setAdCheckList: React.Dispatch<React.SetStateAction<AdCheckKwd[]>>,
	kwdNameSearch: string,
	setIsSetRejectModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
}

function CheckProcessModal({isCheckProcessModalOpen, setIsCheckProcessModalOpen, adCheckKwd, setAdCheckList, kwdNameSearch, setIsSetRejectModalOpen}: Props) {
	function handleReject() {
		setIsCheckProcessModalOpen(false);
		setIsSetRejectModalOpen(true);
	}

	function updateCheckProcessSuccess() {
		setIsCheckProcessModalOpen(false);
		updateAdCheckList(kwdNameSearch, setAdCheckList);
		return Modal.success({title: "검수 처리가 승인되었습니다"})
	}

	function handleApproval() {
		updateCheckProcess(adCheckKwd.key, true)
			.then(() => updateCheckProcessSuccess())
			.catch(e => console.log(e));
	}

	return (
		<Modal title="검수 처리" width={800} open={isCheckProcessModalOpen} onCancel={() => setIsCheckProcessModalOpen(false)}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={handleReject}>반려</Button>,
			       <Button key="register" type="primary" size="large" className="pink" onClick={handleApproval}>승인</Button>
		       ]}
		>
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<DtModal title="상품 명"/>
						<DdTableCell>{adCheckKwd.itemName}</DdTableCell>
					</dl>
					<dl>
						<DtModal title="키워드 명"/>
						<DdTableCell>{adCheckKwd.kwdName}</DdTableCell>
					</dl>
					<dl>
						<DtModal title="검수 사유"/>
						<DdTableCell>{adCheckKwd.checkReason}</DdTableCell>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default CheckProcessModal;