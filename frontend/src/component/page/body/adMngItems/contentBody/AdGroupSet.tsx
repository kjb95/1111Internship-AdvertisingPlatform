import {message, Switch} from "antd";
import {Button} from "antd/lib";
import React, {Key, useEffect, useState} from 'react';
import {useParams} from "react-router";
import {updateAdGroupUseConfig} from "../../../../../api/agroup/AgroupApi";
import {updateAdGroup} from "../../../../../constants/Function";
import {AdMngSetAdGroup} from "../../../../../constants/Interface";
import SectionBody from "../../../../section/SectionBody";
import SectionHeader from "../../../../section/SectionHeader";
import Dd from "../../../../table/Dd";
import DdTableCell from "../../../../table/DdTableCell";
import Dt from "../../../../table/Dt";
import SetAdGroupNameModal from "../modal/SetAdGroupNameModal";

interface Props {
	adGroup: AdMngSetAdGroup,
	setAdGroup: React.Dispatch<React.SetStateAction<AdMngSetAdGroup>>,
}

function AdGroupSet({adGroup, setAdGroup}: Props) {
	const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
	const params = useParams();
	const [messageApi, contextHolder] = message.useMessage();
	const itemCount = adGroup.agroupItemsCount + "개";

	useEffect(() => {
		updateAdGroup(params.id, setAdGroup);
	}, [params.id, setAdGroup]);

	function handleAdGroupOnOff(checked: boolean) {
		const adGroupIds: (Key | undefined)[] = [params.id];
		updateAdGroupUseConfig(adGroupIds, checked)
			.then(() => updateAdGroupUseConfigSuccess())
			.catch(e => console.log(e));
	}

	function updateAdGroupUseConfigSuccess() {
		updateAdGroup(params.id, setAdGroup)
		return messageApi.success("광고 그룹 사용 설정 여부가 변경되었습니다");
	}

	return (
		<section className="wrap-section wrap-tbl">
			{contextHolder}
			<SectionHeader>
				<h2 className="fz-24 fc-gray-700">{adGroup.agroupName} 설정 및 정보</h2>
			</SectionHeader>
			<SectionBody>
				<dl>
					<Dt title="그룹 명"/>
					<DdTableCell>
						{adGroup.agroupName}
						<Button type="primary" className="pink" style={{marginLeft: "20px"}} onClick={() => setIsModalOpen(true)}>그룹명 변경</Button>
						<SetAdGroupNameModal isModalOpen={isModalOpen} setIsModalOpen={setIsModalOpen} setAdGroup={setAdGroup}/>
					</DdTableCell>
				</dl>
				<dl>
					<Dt title="그룹 ON/OFF"/>
					<Dd><Switch checkedChildren="ON" unCheckedChildren="OFF" size="small" onClick={(checked) => handleAdGroupOnOff(checked)} checked={adGroup.agroupUseConfigYn}/></Dd>
				</dl>
				<dl>
					<Dt title="광고상품 수"/>
					<DdTableCell>
						{itemCount}
						<Button type="primary" className="pink" style={{marginLeft: "20px"}} onClick={() => window.location.href = "/adReg"}>광고 상품 등록</Button>
					</DdTableCell>
				</dl>
				<dl>
					<Dt title="광고 설정"/>
					<DdTableCell>{adGroup.regTime}</DdTableCell>
				</dl>
			</SectionBody>
		</section>
	);
}

export default AdGroupSet;