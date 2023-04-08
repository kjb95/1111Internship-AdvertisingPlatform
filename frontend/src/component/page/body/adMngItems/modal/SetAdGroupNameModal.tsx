import {Button, Input, message, Modal} from "antd";
import React, {useState} from 'react';
import {useParams} from "react-router";
import {updateAdGroupName} from "../../../../../api/agroup/AgroupApi";
import {onPressEnter, updateAdGroup, validAdGroupName} from "../../../../../constants/Function";
import {AdMngSetAdGroup} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";

interface Props {
	isModalOpen: boolean,
	setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	setAdGroup: React.Dispatch<React.SetStateAction<AdMngSetAdGroup>>,
}

function SetAdGroupNameModal({isModalOpen, setIsModalOpen, setAdGroup}: Props) {
	const [adGroupName, setAdGroupName] = useState<string>("");
	const [messageApi, contextHolder] = message.useMessage();
	const params = useParams();

	function modalClose() {
		setAdGroupName("");
		setIsModalOpen(false);
	}

	function updateAdGroupNameSuccess() {
		updateAdGroup(params.id, setAdGroup);
		modalClose();
		return messageApi.success("그룹명 변경 성공");
	}

	function handleOnClick() {
		const errMsg = validAdGroupName(adGroupName);
		if (errMsg !== '') {
			return messageApi.error(errMsg);
		}
		updateAdGroupName(params.id, adGroupName)
			.then(() => updateAdGroupNameSuccess())
			.catch((e) => messageApi.error(e.response.data.message));
	}

	return (
		<Modal title="광고그룹명 변경" width={800} open={isModalOpen} onCancel={modalClose}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={modalClose}>취소</Button>,
			       <Button key="update" type="primary" size="large" className="pink" onClick={handleOnClick}>변경</Button>
		       ]}
		>
			<section className="wrap-section wrap-tbl">
				{contextHolder}
				<SectionBody>
					<dl>
						<DtModal title="광고그룹 명"/>
						<Dd><Input style={{width: 300}} type="string" placeholder="변경할 광고그룹 명을 입력하세요" value={adGroupName} onChange={(e) => setAdGroupName(e.target.value)} onPressEnter={(e) => onPressEnter(e, handleOnClick)}/></Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default SetAdGroupNameModal;