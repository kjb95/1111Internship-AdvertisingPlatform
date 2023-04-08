import {UploadOutlined} from '@ant-design/icons';
import {Input, Modal, Upload, UploadFile, UploadProps} from "antd";
import {RcFile} from "antd/es/upload";
import {Button} from "antd/lib";
import React, {useState} from 'react';
import {CSVLink} from "react-csv";
import {createTaskReq, deleteTaskReqFile, uploadTaskReqFile} from "../../../../../api/taskReq/TaskReqApi";
import {AUTHENTICATED_MEMBER_ID, taskReqHistoryCsvHeaders} from "../../../../../constants/Constant";
import {onPressEnter, updateTaskReqHistory} from "../../../../../constants/Function";
import {Task} from "../../../../../constants/Interface";
import {hasBlank} from "../../../../../utils/Utils";

import SectionBody from "../../../../section/SectionBody";
import SectionFooter from "../../../../section/SectionFooter";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";
import Dt from "../../../../table/Dt";

interface Props {
	setTaskHistory: React.Dispatch<React.SetStateAction<Task[]>>
}

function TaskReq({setTaskHistory}: Props) {
	const [fileList, setFileList] = useState<UploadFile[]>([]);
	const [taskName, setTaskName] = useState<string>("")
	const [savedFileName, setSavedFileName] = useState<string>("");

	const handleFileChange: UploadProps['onChange'] = ({fileList}) => {
		setFileList([...fileList]);
	};
	const uploadProps: UploadProps = {fileList, onChange: handleFileChange, onRemove: handleFileRemove};

	function handleRegister() {
		const errorMessage = validHandleRegister();
		if (errorMessage) {
			return Modal.error({title: errorMessage});
		}
		createTaskReq(sessionStorage.getItem(AUTHENTICATED_MEMBER_ID), taskName, savedFileName)
			.then(() => Modal.success({title: "작업 요청 등록 성공", onOk: createTaskReqSuccess}))
			.catch(e => console.log(e));
	}

	function validHandleRegister() {
		if (hasBlank(taskName)) {
			return "작업명에 공백은 입력할 수 없습니다";
		}
		if (fileList.length === 0) {
			return "파일 업로드는 필수 입니다";
		}
		return null;
	}

	function createTaskReqSuccess() {
		setTaskName("");
		setFileList([]);
		updateTaskReqHistory(setTaskHistory);
	}

	function handleCancel() {
		setTaskName("");
		handleFileRemove();
	}

	function handleFileRemove() {
		if (fileList.length === 0) {
			return;
		}
		deleteTaskReqFile(savedFileName)
			.then(() => setFileList([]))
			.catch(e => console.log(e));
	}

	function uploadCustomRequest(file: string | Blob | RcFile, action: string, onSuccess: ((body: any, xhr?: (XMLHttpRequest | undefined)) => void) | undefined) {
		uploadTaskReqFile(file, action)
			.then((res) => uploadTaskReqFileSuccess(res.data.savedFileName, onSuccess))
			.catch(e => console.log(e));
	}

	function uploadTaskReqFileSuccess(savedFileName: string, onSuccess: ((body: any, xhr?: (XMLHttpRequest | undefined)) => void) | undefined) {
		setSavedFileName(savedFileName)
		onSuccess!(true)
	}

	return (
		<section className="wrap-section wrap-tbl">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">작업 요청</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="요청 템플릿"/>
					<Dd>
						<CSVLink data={[]} headers={taskReqHistoryCsvHeaders}>
							<Button type="primary" className="pink">템플릿 다운로드</Button>
						</CSVLink>
					</Dd>
				</dl>
				<dl>
					<Dt title="요청 파일 업로드"/>
					<Dd>
						<Upload {...uploadProps} maxCount={1} action="/api/taskReq/file" customRequest={({file, action, onSuccess}) => uploadCustomRequest(file, action, onSuccess)}>
							<Button icon={<UploadOutlined/>}>업로드</Button>
						</Upload>
					</Dd>
				</dl>
				<dl>
					<Dt title="작업명"/>
					<Dd>
						<Input style={{width: 300}} type="text" value={taskName} onChange={(e) => setTaskName(e.target.value)} onPressEnter={(e) => onPressEnter(e, handleRegister)}/>
					</Dd>
				</dl>
			</SectionBody>
			<SectionFooter>
				<Button htmlType="button" type="primary" size="large" className="gray" onClick={handleCancel}>취소</Button>
				<Button htmlType="submit" type="primary" size="large" className="pink" onClick={handleRegister}>등록</Button>
			</SectionFooter>
		</section>
	);
}

export default TaskReq;