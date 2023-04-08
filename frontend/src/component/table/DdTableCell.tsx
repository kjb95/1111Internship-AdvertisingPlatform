import React from 'react';
import {ChildProps} from "../../constants/Interface";

function DdTableCell({children}: ChildProps) {
	return (
		<dd>
			<div className="form-group">
				<span className="comp-txt">
					<span className="table">
						<span className="table-cell">
							<b className="fz-14 fc-gray-400">{children}</b>
						</span>
					</span>
				</span>
			</div>
		</dd>
	);
}

export default DdTableCell;