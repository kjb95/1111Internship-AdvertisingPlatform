import {Button, Modal, Table} from "antd";
import Column from "antd/es/table/Column";
import React from 'react';
import {downloadTaskReqFile} from "../../../../../api/taskReq/TaskReqApi";
import {updateTaskReqHistory} from "../../../../../constants/Function";
import {Task} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

interface Props {
	taskHistory: Task[],
	setTaskHistory: React.Dispatch<React.SetStateAction<Task[]>>
}

function TaskReqHistory({taskHistory, setTaskHistory}: Props) {
	function downloadTaskReqFileSuccess(data: string) {
		const link = document.createElement('a');
		const url = window.URL.createObjectURL(new Blob([data]));
		link.href = url;
		link.setAttribute('download', "요청파일.csv");
		link.click();
	}

	function handleDownload(reqFile: string) {
		downloadTaskReqFile(reqFile)
			.then(res => downloadTaskReqFileSuccess(res.data))
	}

	function handleRefresh() {
		updateTaskReqHistory(setTaskHistory);
		return Modal.success({title:"새로고침 되었습니다"})
	}

	return (
		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">작업 요청 내역</h2>
				<Button type="primary" className="pink" onClick={handleRefresh}>새로고침</Button>
			</SectionHeader>
			<SectionBody>
				<Table dataSource={taskHistory} bordered pagination={{showTotal: ((total) => <p>총 {total}건</p>)}}>
					<Column title="작업명" dataIndex="taskName" align="center"/>
					<Column title="작업상태" dataIndex="taskStatus" align="center"/>
					<Column title="등록자" dataIndex="registrant" align="center"/>
					<Column title="등록시간" dataIndex="regTime" align="center"/>
					<Column title="요청파일" dataIndex="reqFile" align="center" render={(value, record: Task) =>
						<Button type="primary" size="small" className="pink" onClick={() => handleDownload(record.reqFile)}>다운로드</Button>
					}/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default TaskReqHistory;