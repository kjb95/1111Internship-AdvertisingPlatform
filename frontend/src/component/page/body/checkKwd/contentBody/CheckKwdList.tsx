import {Button, Modal, Table} from "antd";
import Column from "antd/es/table/Column";
import React, {useState} from 'react';
import {CSVLink} from "react-csv";
import {updateManualCnrKwdYnOff} from "../../../../../api/kwd/KwdApi";
import {updateCheckKwds} from "../../../../../constants/Function";
import {CheckKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import CheckKwdRegisterModal from "../modal/CheckKwdRegisterModal";

const csvHeaders = [
	{label: "키워드 명", key: "kwdName"}
];

interface Props {
	checkKwd: CheckKwd[],
	setCheckKwd: React.Dispatch<React.SetStateAction<CheckKwd[]>>,
	kwdNameSearch: string
}

function CheckKwdList({checkKwd, setCheckKwd, kwdNameSearch}: Props) {
	const [isCheckKwdRegisterModalOpen, setIsCheckKwdRegisterModalOpen] = useState<boolean>(false);

	function updateManualCnrKwdYnOffSuccess() {
		updateCheckKwds(kwdNameSearch, setCheckKwd)
		return Modal.error({title: "검수 키워드 삭제가 완료되었습니다"})
	}

	function handleDeleteClick(kwdId: string) {
		updateManualCnrKwdYnOff(kwdId)
			.then(() => updateManualCnrKwdYnOffSuccess())
			.catch(e => console.log(e));
	}

	return (
		<section className="wrap-section wrap-datagrid">
			<CheckKwdRegisterModal isCheckKwdRegisterModalOpen={isCheckKwdRegisterModalOpen} setIsCheckKwdRegisterModalOpen={setIsCheckKwdRegisterModalOpen} kwdNameSearch={kwdNameSearch} setCheckKwd={setCheckKwd}/>
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">검수 키워드 리스트</h2>
				<>
					<Button type="primary" className="pink" onClick={() => setIsCheckKwdRegisterModalOpen(true)}>검수 키워드 등록</Button>
					<CSVLink data={checkKwd} headers={csvHeaders} style={{marginLeft: "8px"}}>
						<Button type="primary" className="pink">키워드 다운로드</Button>
					</CSVLink>
				</>
			</SectionHeader>
			<SectionBody>
				<Table dataSource={checkKwd} bordered pagination={{showTotal: ((total) => <p>총 {total}건</p>)}}>
					<Column title="키워드 명" dataIndex="kwdName" align="center"/>
					<Column title="검수 키워드 삭제" dataIndex="checkKwdDelete" align="center" render={(value, record: CheckKwd) =>
						<Button type="primary" size="small" className="pink" onClick={() => handleDeleteClick(record.key)}>삭제</Button>
					}/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default CheckKwdList;