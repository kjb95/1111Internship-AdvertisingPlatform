import React, {useState} from 'react';
import {DadDetReportChart, DadDetReportTable} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import AdChartReport from "./contentBody/AdChartReport";
import AdStatusList from "./contentBody/AdStatusList";
import ReportTable from "./contentBody/ReportTable";

function AdStatusContent() {
	const [selectedItemName, setSelectedItemName] = useState<string>("");
	const [dadDetReportChart, setDadDetReportChart] = useState<DadDetReportChart[]>([]);
	const [dadDetReportTable, setDadDetReportTable] = useState<DadDetReportTable[]>([]);

	return (
		<AdPageBody title="광고 현황">
			<AdStatusList setSelectedItemName={setSelectedItemName} setDadDetReportChart={setDadDetReportChart} setDadDetReportTable={setDadDetReportTable}/>
			{selectedItemName !== "" && <>
				<AdChartReport selectedItemName={selectedItemName} dadDetReportChart={dadDetReportChart}/>
				<ReportTable selectedItemName={selectedItemName} dadDetReportTable={dadDetReportTable}/>
			</>}
		</AdPageBody>
	);
}

export default AdStatusContent;