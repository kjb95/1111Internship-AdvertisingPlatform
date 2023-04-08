import {Button, Divider, Menu, MenuProps, Space} from 'antd';
import {Header} from "antd/es/layout/layout";
import React from 'react';
import {useLocation} from 'react-router';
import {AUTHENTICATED_MEMBER_ID, ROLE, ROLE_ADMIN, ROLE_ADV} from '../../constants/Constant';
import adReg from "../../pages/AdReg";

function createItem(label: string, key: string, icon: JSX.Element) {
	return {label: label, key: key, icon: icon};
}

function createItems(roleAdv: boolean | undefined, roleAdmin: boolean | undefined) {
	const items = []
	if (roleAdv) {
		items.push(createItem('광고 등록', 'adReg', <i className="ico ico-menu-01"/>));
		items.push(createItem('광고 관리', 'adMng', <i className="ico ico-menu-01"/>));
	}
	if (roleAdmin) {
		items.push(createItem('키워드 검수', 'checkKwd', <i className="ico ico-menu-02"/>));
		items.push(createItem('광고 검수', 'checkAd', <i className="ico ico-menu-02"/>));
		items.push(createItem('작업 관리', 'massMng', <i className="ico ico-menu-02"/>));
		items.push(createItem('광고 현황', 'adStatus', <i className="ico ico-menu-02"/>));
	}
	return items;
}

function logout() {
	sessionStorage.clear();
	window.location.href = "/";
}

const movePage: MenuProps['onClick'] = (e) => {
	window.location.href = "/" + e.key;
}

function AdPageHeader() {
	const roles = sessionStorage.getItem(ROLE)
		?.split(",");
	const roleAdv = roles?.includes(ROLE_ADV);
	const roleAdmin = roles?.includes(ROLE_ADMIN);
	const location = useLocation()
		.pathname
		.split('/')[1];
	const menuItems: MenuProps['items'] = createItems(roleAdv, roleAdmin);

	return (
		<Header>
			<a href={"#/"} className="logo"><span>NHNAD</span> Bidding Solution</a>
			<Menu onClick={movePage} selectedKeys={[location]} mode="horizontal" items={menuItems}/>
			<div className="user-info">
				<Space split={<Divider type="vertical"/>}>
					<Space>
						<i className="ico ico-user-info"/>
						<span className="fz-16 fc-gray-300">{sessionStorage.getItem(AUTHENTICATED_MEMBER_ID)}</span>
					</Space>
					<Button className="gray" size="small" onClick={logout}>로그아웃</Button>
				</Space>
			</div>
		</Header>);
}

export default AdPageHeader;