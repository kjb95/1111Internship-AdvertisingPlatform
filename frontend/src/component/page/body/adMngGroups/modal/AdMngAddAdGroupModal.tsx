import {Button, Input, message, Modal} from "antd";
import React, {useState} from 'react';
import {registerAdGroup} from "../../../../../api/agroup/AgroupApi";
import {onPressEnter, updateAdGroups, validAdGroupName} from "../../../../../constants/Function";
import {AdMngAdGroupListAdGroup} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";

interface Props {
	isAddGroupModalOpen: boolean,
	setIsAddGroupModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	adGroupNameSearchKeyword: string,
	setAdGroups: React.Dispatch<React.SetStateAction<AdMngAdGroupListAdGroup[]>>,
}

function AdMngAddAdGroupModal({isAddGroupModalOpen, setIsAddGroupModalOpen, adGroupNameSearchKeyword, setAdGroups}: Props) {
	const [messageApi, contextHolder] = message.useMessage();
	const [adGroupName, setAdGroupName] = useState<string>("");

	function closeModal() {
		setIsAddGroupModalOpen(false);
		setAdGroupName("");
	}

	function registerAdGroupSuccess() {
		updateAdGroups(adGroupNameSearchKeyword, setAdGroups);
		closeModal();
		return messageApi.success("광고 그룹 등록 성공");
	}

	function handleRegister() {
		const errMsg = validAdGroupName(adGroupName);
		if (errMsg !== '') {
			return messageApi.error(errMsg);
		}
		registerAdGroup(adGroupName)
			.then(() => registerAdGroupSuccess())
			.catch((e) => messageApi.error(e.response.data.message));
	}

	return (
		<Modal title="광고그룹 등록" width={800} open={isAddGroupModalOpen} onCancel={closeModal}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={closeModal}>취소</Button>,
			       <Button key="register" type="primary" size="large" className="pink" onClick={handleRegister}>등록</Button>
		       ]}
		>
			{contextHolder}
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<DtModal title="광고그룹 명"/>
						<Dd>
							<Input style={{width: 300}} type="text" value={adGroupName} placeholder="등록할 광고그룹 명을 입력하세요"
							       onChange={(e) => setAdGroupName(e.target.value)}
							       onPressEnter={(e) => onPressEnter(e, handleRegister)}
							/>
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default AdMngAddAdGroupModal;