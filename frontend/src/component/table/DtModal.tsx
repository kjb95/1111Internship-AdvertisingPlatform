import React from 'react';

interface Props {
	title: string
}

function DtModal({title}: Props) {
	return (
		<dt>
			<div className="dt-inner">
				<span className="fz-16 fw-med fc-7">
					{title}
					<i className="txt-essential"/>
				</span>
			</div>
		</dt>
	);
}

export default DtModal;