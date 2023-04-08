import {Button, message, Table} from "antd";
import Column from "antd/es/table/Column";
import React, {Key, useState} from 'react';
import {CSVLink} from "react-csv";
import {Link} from "react-router-dom";
import {updateAdGroupActOff, updateAdGroupUseConfig} from "../../../../../api/agroup/AgroupApi";
import {updateAdGroups} from "../../../../../constants/Function";
import {AdMngAdGroupListAdGroup} from "../../../../../constants/Interface";
import AdMngAddAdGroupModal from "../modal/AdMngAddAdGroupModal";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

const csvHeaders = [
	{label: "번호", key: "key"},
	{label: "그룹명", key: "agroupName"},
	{label: "그룹 ON/OFF", key: "agroupUseConfigYn"},
	{label: "상품 수(LIVE/전체)", key: "itemCountLiveAndAll"}
];

interface Props {
	adGroupNameSearchKeyword: string,
	adGroups: AdMngAdGroupListAdGroup[],
	setAdGroups: React.Dispatch<React.SetStateAction<AdMngAdGroupListAdGroup[]>>,
}

function GroupList({adGroupNameSearchKeyword, adGroups, setAdGroups}: Props) {
	const [messageApi, contextHolder] = message.useMessage();
	const [isAddGroupModalOpen, setIsAddGroupModalOpen] = useState<boolean>(false);
	const [selectedRowKeys, setSelectedRowKeys] = useState<Key[]>([]);
	const rowSelection = {onChange: (keys: React.Key[]) => setSelectedRowKeys(keys), selectedRowKeys: selectedRowKeys};

	function updateAdGroupsAndInitRowKeys(message: string) {
		updateAdGroups(adGroupNameSearchKeyword, setAdGroups)
		setSelectedRowKeys([]);
		return messageApi.success(message);
	}

	function handleOnOffClick(record: AdMngAdGroupListAdGroup) {
		const adGroupIds: Key[] = [record.key];
		const isOn = record.agroupUseConfigYn === 'ON';

		updateAdGroupUseConfig(adGroupIds, !isOn)
			.then(() => updateAdGroupsAndInitRowKeys("광고그룹 사용 설정 여부가 변경되었습니다"))
			.catch(e => console.log(e));
	}

	function handleOnOffGroupClick(checked: boolean) {
		if (selectedRowKeys.length === 0) {
			return messageApi.error("체크된 체크박스가 없습니다");
		}
		updateAdGroupUseConfig(selectedRowKeys, checked)
			.then(() => updateAdGroupsAndInitRowKeys("광고그룹 사용 설정 여부가 변경되었습니다"))
			.catch(e => console.log(e));
	}

	function handleDeleteClick() {
		if (selectedRowKeys.length === 0) {
			return messageApi.error("체크된 체크박스가 없습니다");
		}
		updateAdGroupActOff(selectedRowKeys)
			.then(() => updateAdGroupsAndInitRowKeys("광고그룹 삭제가 완료되었습니다"))
			.catch(e => console.log(e));
	}

	return (
		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">그룹 리스트</h2>
				<>
					{contextHolder}
					<AdMngAddAdGroupModal isAddGroupModalOpen={isAddGroupModalOpen} setIsAddGroupModalOpen={setIsAddGroupModalOpen} adGroupNameSearchKeyword={adGroupNameSearchKeyword} setAdGroups={setAdGroups}/>
					<Button type="primary" className="pink" onClick={() => handleOnOffGroupClick(true)}>ON</Button>
					<Button type="primary" className="gray" onClick={() => handleOnOffGroupClick(false)}>OFF</Button>
					<Button type="primary" className="pink" onClick={() => setIsAddGroupModalOpen(true)}>그룹 추가</Button>
					<Button type="primary" className="gray" onClick={handleDeleteClick}>그룹 삭제</Button>
					<CSVLink data={adGroups} headers={csvHeaders} style={{marginLeft: "8px"}}>
						<Button type="primary" className="pink">그룹 다운로드</Button>
					</CSVLink>
				</>
			</SectionHeader>
			<SectionBody>
				{contextHolder}
				<Table dataSource={adGroups} bordered rowSelection={rowSelection} pagination={{showTotal: ((total) => <p>총 {total}건</p>)}}>
					<Column title="번호" dataIndex="key" align="center"/>
					<Column title="그룹명" dataIndex="agroupName" align="center" render={(value, record: AdMngAdGroupListAdGroup) => <Link to={"/adMng/group/" + record.key}>{value}</Link>}/>
					<Column title="그룹 ON/OFF" dataIndex="agroupUseConfigYn" align="center" render={(value, record: AdMngAdGroupListAdGroup) =>
						<Button type="primary" size="small" className={value === "ON" ? "pink" : "gray"} onClick={() => handleOnOffClick(record)}>{value}</Button>
					}/>
					<Column title="상품 수(LIVE/전체)" dataIndex="itemCountLiveAndAll" align="center"/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default GroupList;