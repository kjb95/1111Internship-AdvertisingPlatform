import {Button, Input, Modal} from "antd";
import React, {useState} from 'react';
import {registerKwd} from "../../../../../api/kwd/KwdApi";
import {onPressEnter, updateCheckKwds} from "../../../../../constants/Function";
import {CheckKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import Dd from "../../../../table/Dd";
import DtModal from "../../../../table/DtModal";

interface Props {
	isCheckKwdRegisterModalOpen: boolean,
	setIsCheckKwdRegisterModalOpen: React.Dispatch<React.SetStateAction<boolean>>,
	kwdNameSearch: string
	setCheckKwd: React.Dispatch<React.SetStateAction<CheckKwd[]>>,

}

function CheckKwdRegisterModal({isCheckKwdRegisterModalOpen, setIsCheckKwdRegisterModalOpen, kwdNameSearch, setCheckKwd}: Props) {
	const [kwdName, setKwdName] = useState<string>("");

	function closeModal() {
		setIsCheckKwdRegisterModalOpen(false);
		setKwdName("");
	}

	function registerKwdSuccess() {
		updateCheckKwds(kwdNameSearch, setCheckKwd)
		closeModal();
		return Modal.success({title: "검수 키워드 등록 성공"});
	}

	function handleRegister() {
		registerKwd(kwdName)
			.then(() => registerKwdSuccess())
			.catch((e) => Modal.error({title: e.response.data.message}));
	}

	return (
		<Modal title="검수 대상 키워드 등록" width={800} open={isCheckKwdRegisterModalOpen} onCancel={closeModal}
		       footer={[
			       <Button key="cancel" type="primary" size="large" className="gray" onClick={closeModal}>취소</Button>,
			       <Button key="register" type="primary" size="large" className="pink" onClick={handleRegister}>등록</Button>
		       ]}
		>
			<section className="wrap-section wrap-tbl">
				<SectionBody>
					<dl>
						<DtModal title="키워드 명"/>
						<Dd>
							<Input style={{width: 300}} type="text" value={kwdName} placeholder="키워드 명을 입력하세요"
							       onChange={(e) => setKwdName(e.target.value)}
							       onPressEnter={(e) => onPressEnter(e, handleRegister)}
							/>
						</Dd>
					</dl>
				</SectionBody>
			</section>
		</Modal>
	);
}

export default CheckKwdRegisterModal;