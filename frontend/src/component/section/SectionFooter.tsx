import React from 'react';
import {ChildProps} from "../../constants/Interface";

function SectionFooter({children}: ChildProps) {
	return (
		<div className="box-footer">
			<div className="box-center">
				{children}
			</div>
		</div>
	);
}

export default SectionFooter;