import {Content} from "antd/es/layout/layout";
import React, {ReactNode} from 'react';

interface Props {
	children: ReactNode,
	title: string
}

function AdPageBody({children, title}: Props) {
	return (
		<Content>
			<div className="site-layout-content">
				<div className="inner-content">
					<div className="content-header"><h1 className="fz-32 fc-gray-900">{title}</h1></div>
					<div className="content-body">
						{children}
					</div>
				</div>
			</div>
		</Content>
	);
}

export default AdPageBody;