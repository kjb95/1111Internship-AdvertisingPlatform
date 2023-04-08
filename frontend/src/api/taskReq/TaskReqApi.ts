/**
 * 테스크 요청 파일 업로드
 */
import {RcFile} from "antd/es/upload";
import {api, multipartFormDataApi} from "../Api";

/**
 * 테스크 작업 요청
 */
export function createTaskReq(memberId: string | null, taskName: string, taskReqFile: string) {
	return api.post("/api/taskReq", {
		memberId: memberId,
		taskName: taskName,
		taskReqFile: taskReqFile
	})
}

/**
 * 테스크 요청 파일 삭제
 */
export function deleteTaskReqFile(taskReqFile: string) {
	return api.delete("/api/taskReq?taskReqFile=" + taskReqFile);
}

/**
 * 작업 요청 내역 조회
 */
export function findTaskReqHistory() {
	return api.get("/api/taskReq/all");
}

/**
 * 테스크 요청 파일 다운로드
 */
export function downloadTaskReqFile(fileName: string) {
	return api.get("api/taskReq/file?filename=" + fileName);
}

/**
 * 테스크 요청 파일 업로드
 */
export function uploadTaskReqFile(file: string | Blob | RcFile, action: string) {
	const formData = new FormData();
	formData.append('file', file);
	return multipartFormDataApi.post(action, formData);
}