import {Button, Table} from "antd";
import Column from "antd/es/table/Column";
import React, {useState} from 'react';
import {AdCheckKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import CheckProcessModal from "../modal/CheckProcessModal";
import SetRejectModal from "../modal/SetRejectModal";

interface Props {
	adCheckList: AdCheckKwd[],
	setAdCheckList: React.Dispatch<React.SetStateAction<AdCheckKwd[]>>,
	kwdNameSearch: string
}

const AdCheckKwdDefaultValue: AdCheckKwd = {
	key: 0,
	itemName: "",
	kwdName: "",
	checkReason: ""
}

function AdCheckList({adCheckList, setAdCheckList, kwdNameSearch}: Props) {
	const [isCheckProcessModalOpen, setIsCheckProcessModalOpen] = useState<boolean>(false);
	const [isSetRejectModalOpen, setIsSetRejectModalOpen] = useState<boolean>(false)

	const [selectedCheckProcess, setSelectedCheckProcess] = useState<AdCheckKwd>(AdCheckKwdDefaultValue);

	function handleCheckProcess(adCheckKwd: AdCheckKwd) {
		setSelectedCheckProcess(adCheckKwd);
		setIsCheckProcessModalOpen(true);
	}

	return (
		<section className="wrap-section wrap-datagrid">
			<CheckProcessModal isCheckProcessModalOpen={isCheckProcessModalOpen} setIsCheckProcessModalOpen={setIsCheckProcessModalOpen} adCheckKwd={selectedCheckProcess} setAdCheckList={setAdCheckList} kwdNameSearch={kwdNameSearch} setIsSetRejectModalOpen={setIsSetRejectModalOpen}/>
			<SetRejectModal isSetRejectModalOpen={isSetRejectModalOpen} setIsSetRejectModalOpen={setIsSetRejectModalOpen} dadDetId={selectedCheckProcess.key} setAdCheckList={setAdCheckList} kwdNameSearch={kwdNameSearch}/>
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">광고 검수 대상 리스트</h2>
			</SectionHeader>
			<SectionBody>
				<Table dataSource={adCheckList} bordered pagination={{showTotal: ((total) => <p>총 {total}건</p>)}}>
					<Column title="상품 명" dataIndex="itemName" align="center"/>
					<Column title="키워드 명" dataIndex="kwdName" align="center"/>
					<Column title="검수 사유" dataIndex="checkReason" align="center"/>
					<Column title="검수 처리" dataIndex="checkProcess" align="center" render={(value, record: AdCheckKwd) =>
						<Button type="primary" size="small" className="pink" onClick={() => handleCheckProcess(record)}>검수</Button>
					}/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default AdCheckList;