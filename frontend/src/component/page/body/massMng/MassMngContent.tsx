import React, {useEffect, useState} from 'react';
import {updateTaskReqHistory} from "../../../../constants/Function";
import {Task} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import TaskReq from "./contentBody/TaskReq";
import TaskReqHistory from "./contentBody/TaskReqHistory";

function DataDownloadContent() {
	const [taskHistory, setTaskHistory] = useState<Task[]>([]);

	useEffect(() => {
		updateTaskReqHistory(setTaskHistory);
	}, [setTaskHistory]);

	return (
		<AdPageBody title="작업 관리">
			<TaskReq setTaskHistory={setTaskHistory}/>
			<TaskReqHistory taskHistory={taskHistory} setTaskHistory={setTaskHistory}/>
		</AdPageBody>
	);
}

export default DataDownloadContent;