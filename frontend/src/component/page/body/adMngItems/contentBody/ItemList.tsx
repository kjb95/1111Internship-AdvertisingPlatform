import {Button, message, Table} from "antd";
import Column from "antd/es/table/Column";
import React, {Key, useState} from 'react';
import {CSVLink} from "react-csv";
import {useParams} from "react-router";
import {Link} from "react-router-dom";
import {updateAdActOff, updateAdUseConfigAndDadUseConfig} from "../../../../../api/ad/AdApi";
import {updateAdGroup, updateItemsInAdgroup} from "../../../../../constants/Function";
import {AdMngItem, AdMngSetAdGroup} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

const csvHeaders = [
	{label: "번호", key: "key"},
	{label: "상품번호", key: "itemNo"},
	{label: "상품명", key: "itemName"},
	{label: "광고 상품 ON/OFF", key: "adUseConfigYn"}
];

interface Props {
	items: AdMngItem[],
	setItems: React.Dispatch<React.SetStateAction<AdMngItem[]>>,
	itemName: string,
	itemNo: string,
	setAdGroup: React.Dispatch<React.SetStateAction<AdMngSetAdGroup>>,
}

function ItemList({items, setItems, itemName, itemNo, setAdGroup}: Props) {
	const [messageApi, contextHolder] = message.useMessage();
	const [selectedRowKeys, setSelectedRowKeys] = useState<Key[]>([]);
	const rowSelection = {onChange: (keys: React.Key[]) => setSelectedRowKeys(keys), selectedRowKeys: selectedRowKeys};
	const params = useParams();

	function updateItemsInAdgroupAndInitRowKeys(message: string) {
		updateItemsInAdgroup(params.id, itemName, itemNo, setItems);
		setSelectedRowKeys([]);
		return messageApi.success(message);
	}

	function handleOnOffGroupClick(checked: boolean) {
		if (selectedRowKeys.length === 0) {
			return messageApi.error("체크된 체크박스가 없습니다");
		}
		updateAdUseConfigAndDadUseConfig(selectedRowKeys, checked)
			.then(() => updateItemsInAdgroupAndInitRowKeys("광고, 직접광고 사용 설정 여부가 변경되었습니다"))
			.catch(e => console.log(e));
	}

	function updateAdActOffSuccess() {
		updateAdGroup(params.id, setAdGroup);
		return updateItemsInAdgroupAndInitRowKeys("광고 상품이 삭제되었습니다");
	}

	function handleDeleteClick() {
		if (selectedRowKeys.length === 0) {
			return messageApi.error("체크된 체크박스가 없습니다");
		}
		updateAdActOff(selectedRowKeys)
			.then(() => updateAdActOffSuccess())
			.catch(e => console.log(e));
	}

	function handleOnOffClick(record: AdMngItem) {
		const itemIds: Key[] = [record.key];
		const isOn = record.adUseConfigYn === 'ON';

		updateAdUseConfigAndDadUseConfig(itemIds, !isOn)
			.then(() => updateItemsInAdgroupAndInitRowKeys("광고, 직접광고 사용 설정 여부가 변경되었습니다"))
			.catch(e => console.log(e));
	}

	return (
		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">상품 리스트</h2>
				<>
					<Button type="primary" className="pink" onClick={() => handleOnOffGroupClick(true)}>ON</Button>
					<Button type="primary" className="gray" onClick={() => handleOnOffGroupClick(false)}>OFF</Button>
					<Button type="primary" className="gray" onClick={handleDeleteClick}>광고 상품 삭제</Button>
					<CSVLink data={items} headers={csvHeaders} style={{marginLeft: "8px"}}>
						<Button type="primary" className="pink">광고 상품 다운로드</Button>
					</CSVLink>
				</>
			</SectionHeader>
			<SectionBody>
				{contextHolder}
				<Table dataSource={items} rowSelection={rowSelection} bordered pagination={{showTotal: ((total) => <p>총 {total}건</p>)}}>
					<Column title="번호" dataIndex="key" align="center"/>
					<Column title="상품번호" dataIndex="itemNo" align="center" render={(value, record: AdMngItem) => <Link to={"/adMng/item/" + record.key}>{value}</Link>}/>
					<Column title="상품명" dataIndex="itemName" align="center"/>
					<Column title="광고 상품 ON/OFF" dataIndex="adUseConfigYn" align="center" render={(value, record: AdMngItem) =>
						<Button type="primary" size="small" className={value === "ON" ? "pink" : "gray"} onClick={() => handleOnOffClick(record)}>{value}</Button>}/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default ItemList;