import {Button, Input, message, Modal} from "antd";
import React, {useState} from 'react';
import {onPressEnter} from "../../../../../constants/Function";
import {SelectOption} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";

interface Props {
	isModalOpen: boolean,
	setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	aGroups: SelectOption[],
	setAGroups: React.Dispatch<React.SetStateAction<SelectOption[]>>,
	setAGroupId: React.Dispatch<React.SetStateAction<string>>,
}

function AdRegAddAdGroupModal({isModalOpen, setIsModalOpen, aGroups, setAGroups, setAGroupId}: Props) {
	const [aGroupName, setAGroupName] = useState<string>("");
	const [messageApi, contextHolder] = message.useMessage();

	function closeModal() {
		setIsModalOpen(false);
		setAGroupName("");
	}

	function isExistAGroup() {
		return aGroups.filter(aGroup => aGroup.label === aGroupName).length !== 0;
	}

	function handleRegister() {
		if (isExistAGroup()) {
			return messageApi.error("동일한 광고그룹명이 존재합니다 !!");
		}
		setAGroupId(aGroupName);
		setAGroups([...aGroups, {value: aGroupName, label: aGroupName}]);
		closeModal();
	}

	return (
		<Modal title="신규 광고 그룹 생성" width={800} open={isModalOpen} onCancel={closeModal}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={closeModal}>취소</Button>,
			       <Button key="register" type="primary" size="large" className="pink" onClick={handleRegister}>등록</Button>
		       ]}
		>
			{contextHolder}
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<DtModal title="신규 광고그룹 명"/>
						<Dd>
							<Input style={{width: 300}} type="text" value={aGroupName}
							       onChange={(e) => setAGroupName(e.target.value)}
							       onPressEnter={(e) => onPressEnter(e, handleRegister)}/>
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default AdRegAddAdGroupModal;