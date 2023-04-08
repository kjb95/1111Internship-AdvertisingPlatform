import {Footer} from "antd/es/layout/layout";
import React from 'react';

function AdPageFooter() {
	return (
		<Footer>
			<div className="box-left">
				<div className="company-info">
					<span className="fz-14 fw-exbold fc-gray-400">NHNAD</span>
					<span className="fz-14 fw-reg fc-gray-300">사업자등록번호: 144-81-27690</span>
					<span className="fz-14 fw-reg fc-gray-300">대표: 심도섭</span>
					<span className="fz-14 fw-reg fc-gray-300">전화번호: 1800-3987</span>
					<span className="fz-14 fw-reg fc-gray-300">주소: 경기도 성남시 분당구 344 아이디스타워 3층 NHNAD 플레이스페이스</span>
				</div>
			</div>
			<div className="box-right">
				<span className="fz-14 fw-reg fc-gray-300">Copyright NHN AD. All right reserved.</span>
			</div>
		</Footer>
	);
}

export default AdPageFooter;