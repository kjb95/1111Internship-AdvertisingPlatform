import React from 'react';
import {ChildProps} from "../../constants/Interface";

function SectionBody({children}: ChildProps) {
	return (
		<div className="box-body">
			<div className="tbl">
				{children}
			</div>
		</div>
	);
}

export default SectionBody;