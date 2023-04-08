import React from 'react';
import {ChildProps} from "../../constants/Interface";

function Dd({children}: ChildProps) {
	return (
		<dd>
			<div className="form-group">
				{children}
			</div>
		</dd>
	);
}

export default Dd;