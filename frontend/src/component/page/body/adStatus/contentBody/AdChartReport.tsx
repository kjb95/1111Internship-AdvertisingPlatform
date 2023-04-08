import {Line, LineConfig} from "@ant-design/plots";
import {Select} from "antd";
import React, {useEffect, useState} from 'react';
import {findAllDadDetReportCategory} from "../../../../../api/dadDetReport/DadDetReportApi";
import {DadDetReportChart, SelectOption} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";

interface Props {
	selectedItemName: string,
	dadDetReportChart: DadDetReportChart[]
}

function AdChartReport({selectedItemName, dadDetReportChart}: Props) {
	const [leftSelect, setLeftSelect] = useState<string>("노출 수");
	const [rightSelect, setRightSelect] = useState<string>("클릭 수");
	const [dadDetReportCategory, setDadDetReportCategory] = useState<SelectOption[]>([]);
	const [selectedDadDetReport, setSelectedDadDetReport] = useState<DadDetReportChart[]>([]);

	const lineConfig: LineConfig = {
		data: selectedDadDetReport,
		xField: 'date',
		yField: 'value',
		seriesField: 'category',
		legend: {position: 'bottom', offsetY: 20,},
		interactions: [{type: 'legend-filter', enable: false}],
		point: {size: 7},
		xAxis: {
			grid: {
				line: {style: {stroke: '#ddd', lineDash: [10, 5]}},
				alternateColor: 'rgba(0,0,150,0.05)',
			},
		},
		yAxis: {
			grid: {
				line: {style: {stroke: '#ddd', lineDash: [10, 5]}},
				alternateColor: 'rgba(0,0,150,0.05)',
			},
		},
		label: {offset: 20},
		smooth: true,
		animation: {appear: {duration: 5000}},
	}

	useEffect(() => {
		findAllDadDetReportCategory()
			.then((res) => setDadDetReportCategory(res.data.categories))
			.catch((e) => console.log(e))
	}, []);

	useEffect(() => {
		const filteredDadDetReportChart = dadDetReportChart.filter(dadReport => dadReport.category === leftSelect || dadReport.category === rightSelect);
		setSelectedDadDetReport(filteredDadDetReportChart);
	}, [leftSelect, rightSelect, dadDetReportChart]);

	return (
		<section className="wrap-section wrap-datagrid">
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">{selectedItemName} 리포트</h2>
				<>
					<Select options={dadDetReportCategory} value={leftSelect} onChange={(value) => setLeftSelect(value)}/>
					<Select style={{marginLeft: 20}} options={dadDetReportCategory} value={rightSelect} onChange={(value) => setRightSelect(value)}/>
				</>
			</SectionHeader>
			<SectionBody>
				<Line {...lineConfig}/>
			</SectionBody>
		</section>
	);
}

export default AdChartReport;