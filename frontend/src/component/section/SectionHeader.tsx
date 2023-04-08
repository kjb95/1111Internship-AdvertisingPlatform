import React from 'react';
import {ChildProps} from "../../constants/Interface";

function SectionHeader({children}: ChildProps) {
	const childrenArr = React.Children.toArray(children);

	return (
		<div className="box-header">
			<div className="box-left">
				{childrenArr[0]}
			</div>
			<div className="box-right">
				{childrenArr[1]}
			</div>
		</div>
	);
}

export default SectionHeader;