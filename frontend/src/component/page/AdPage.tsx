import {Layout} from "antd";
import React from 'react';
import {ChildProps} from "../../constants/Interface";
import AdPageFooter from "./AdPageFooter";
import AdPageHeader from "./AdPageHeader";

function AdPage({children}: ChildProps) {
	return (
		<Layout>
			<AdPageHeader/>
			{children}
			<AdPageFooter/>
		</Layout>
	);
}

export default AdPage;