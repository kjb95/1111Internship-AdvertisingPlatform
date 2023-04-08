import React from "react";
import AdPage from "../component/page/AdPage";

interface Props {
	title: string;
}

function SimplePage({title}: Props) {
	return (
		<AdPage>
			<h1 style={{textAlign: "center", margin: "400px"}}>{title}</h1>
		</AdPage>
	);
}

export default SimplePage;
