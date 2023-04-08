import {Button, message, Table} from "antd";
import Column from "antd/es/table/Column";
import React, {Key, useState} from 'react';
import {CSVLink} from "react-csv";
import {useParams} from "react-router";
import {updateDadDetActOff, updateDadDetUseConfig} from "../../../../../api/dadDet/DadDetApi";
import {updateKeywords} from "../../../../../constants/Function";
import {AdMngKwd} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

const csvHeaders = [
	{label: "번호", key: "key"},
	{label: "키워드 명", key: "kwdName"},
	{label: "키워드 ON/OFF", key: "dadUseConfigYn"},
];

interface Props {
	keywordNameSearch: string,
	keywords: AdMngKwd[],
	setKeywords: React.Dispatch<React.SetStateAction<AdMngKwd[]>>,
}

function KeywordList({keywordNameSearch, keywords, setKeywords}: Props) {
	const [messageApi, contextHolder] = message.useMessage();
	const [selectedRowKeys, setSelectedRowKeys] = useState<Key[]>([]);
	const rowSelection = {onChange: (keys: React.Key[]) => setSelectedRowKeys(keys), selectedRowKeys: selectedRowKeys};
	const params = useParams();

	function updateKeywordAndInitRowKeys(message: string) {
		updateKeywords(params.id, keywordNameSearch, setKeywords);
		setSelectedRowKeys([]);
		return messageApi.success(message);
	}

	function handleOnOffClick(record: AdMngKwd) {
		const kwdIds: Key[] = [record.key];
		const isOn = record.dadUseConfigYn === 'ON';

		updateDadDetUseConfig(kwdIds, !isOn)
			.then(() => updateKeywordAndInitRowKeys("직접광고 사용 설정 여부가 변경되었습니다"))
			.catch(e => console.log(e));
	}

	function handleOnOffGroupClick(checked: boolean) {
		if (selectedRowKeys.length === 0) {
			return messageApi.error("체크된 체크박스가 없습니다");
		}
		updateDadDetUseConfig(selectedRowKeys, checked)
			.then(() => updateKeywordAndInitRowKeys("직접광고 사용 설정 여부가 변경되었습니다"))
			.catch(e => console.log(e));
	}

	function handleDeleteClick() {
		if (selectedRowKeys.length === 0) {
			return messageApi.error("체크된 체크박스가 없습니다");
		}
		updateDadDetActOff(selectedRowKeys)
			.then(() => updateKeywordAndInitRowKeys("키워드 삭제가 완료되었습니다"))
			.catch(e => console.log(e));
	}

	return (
		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">키워드 리스트</h2>
				<>
					{contextHolder}
					<Button type="primary" className="pink" onClick={() => handleOnOffGroupClick(true)}>ON</Button>
					<Button type="primary" className="gray" onClick={() => handleOnOffGroupClick(false)}>OFF</Button>
					<Button type="primary" className="gray" onClick={handleDeleteClick}>키워드 삭제</Button>
					<CSVLink data={keywords} headers={csvHeaders} style={{marginLeft: "8px"}}>
						<Button type="primary" className="pink">키워드 다운로드</Button>
					</CSVLink>
				</>
			</SectionHeader>
			<SectionBody>
				{contextHolder}
				<Table dataSource={keywords} bordered rowSelection={rowSelection} pagination={{showTotal: ((total) => <p>총 {total}건</p>)}}>
					<Column title="번호" dataIndex="key" align="center"/>
					<Column title="키워드 명" dataIndex="kwdName" align="center"/>
					<Column title="키워드 ON/OFF" dataIndex="dadUseConfigYn" align="center" render={(value, record: AdMngKwd) =>
						<Button type="primary" size="small" className={value === "ON" ? "pink" : "gray"} onClick={() => handleOnOffClick(record)}>{value}</Button>
					}/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default KeywordList;