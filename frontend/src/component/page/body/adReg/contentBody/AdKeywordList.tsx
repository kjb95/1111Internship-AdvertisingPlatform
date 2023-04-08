import {Button, Table} from "antd";
import Column from "antd/es/table/Column";
import React, {useState} from 'react';
import {AdRegKwd} from "../../../../../constants/Interface";
import AddKeywordModal from "../modal/AddKeywordModal";
import SetKeywordBidModal from "../modal/SetKeywordBidModal";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

interface Props {
	keywordList: AdRegKwd[],
	setKeywordList: React.Dispatch<React.SetStateAction<AdRegKwd[]>>,
}

function AdKeywordList({keywordList, setKeywordList}: Props) {
	const [isAddKeywordModalOpen, setIsAddKeywordModalOpen] = useState<boolean>(false);
	const [isSetBidModalOpen, setIsSetBidModalOpen] = useState<boolean>(false);

	function handleItemDelete(record: AdRegKwd) {
		const filteredKeywordList = keywordList.filter(item => item.key !== record.key);
		setKeywordList(filteredKeywordList);
	}

	return (

		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">광고 키워드 리스트</h2>
				<>
					<Button type="default" className="pink" onClick={() => setIsAddKeywordModalOpen(true)}>키워드 추가</Button>
					<Button type="default" className="gray" onClick={() => setIsSetBidModalOpen(true)}>입찰가 일괄 설정</Button>
				</>
			</SectionHeader>
			<SectionBody>
				<Table dataSource={keywordList} bordered pagination={{showSizeChanger: true, showTotal: ((total) => <p>Total {total} items</p>)}}>
					<Column title="키워드명" dataIndex="keywordName" align="center"/>
					<Column title="입찰가" dataIndex="bid" align="center"/>
					<Column title="키워드 삭제" dataIndex="keywordDelete" align="center"
					        render={(_, record: AdRegKwd) => (<Button type="default" size="small" className="pink" onClick={() => handleItemDelete(record)}>삭제</Button>)}
					/>
				</Table>
			</SectionBody>
			<AddKeywordModal isAddKeywordModalOpen={isAddKeywordModalOpen} setIsAddKeywordModalOpen={setIsAddKeywordModalOpen} keywordList={keywordList} setKeywordList={setKeywordList}/>
			<SetKeywordBidModal isSetBidModalOpen={isSetBidModalOpen} setIsSetBidModalOpen={setIsSetBidModalOpen} keywordList={keywordList} setKeywordList={setKeywordList}/>
		</section>
	);
}

export default AdKeywordList;