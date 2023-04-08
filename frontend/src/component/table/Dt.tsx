import React from 'react';

interface Props {
	title: string
}

function Dt({title}: Props) {
	return (
		<dt>
			<div className="dt-inner">
				<span className="fz-15 fc-gray-500">{title}</span>
			</div>
		</dt>
	);
}

export default Dt;