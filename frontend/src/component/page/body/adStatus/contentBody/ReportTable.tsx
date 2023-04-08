import {Button, Table} from "antd";
import Column from "antd/es/table/Column";
import React from 'react';
import { CSVLink } from "react-csv";
import {DadDetReportTable} from "../../../../../constants/Interface";
import {addComma, toWon} from "../../../../../utils/Utils";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

const csvHeaders = [
	{label: "날짜", key: "date"},
	{label: "노출수", key: "impressions"},
	{label: "클릭수", key: "clicks"},
	{label: "클릭률", key: "clicksRate"},
	{label: "평균 노출 순위", key: "averageImpressionRank"},
	{label: "평균 클릭 비용", key: "averageClickCost"},
	{label: "광고비", key: "advertisingCost"},
];

interface Props {
	selectedItemName: string,
	dadDetReportTable: DadDetReportTable[]
}

function ReportTable({selectedItemName, dadDetReportTable}: Props) {
	return (
		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">{selectedItemName} 리포트 테이블</h2>
				<CSVLink data={dadDetReportTable} headers={csvHeaders}>
					<Button type="primary" className="pink">다운로드</Button>
				</CSVLink>
			</SectionHeader>
			<SectionBody>
				<Table dataSource={dadDetReportTable} bordered pagination={false}>
					<Column title="날짜" dataIndex="date" align="center"/>
					<Column title="노출수" dataIndex="impressions" align="center" render={(text) => addComma(text)}/>
					<Column title="클릭수" dataIndex="clicks" align="center" render={(text) => addComma(text)}/>
					<Column title="클릭률" dataIndex="clicksRate" align="center"/>
					<Column title="평균 노출 순위" dataIndex="averageImpressionRank" align="center" render={(text) => addComma(text)}/>
					<Column title="평균 클릭 비용" dataIndex="averageClickCost" align="center" render={(text) => toWon(text)}/>
					<Column title="광고비" dataIndex="advertisingCost" align="center" render={(text) => toWon(text)}/>
				</Table>
			</SectionBody>
		</section>
	);
}

export default ReportTable;