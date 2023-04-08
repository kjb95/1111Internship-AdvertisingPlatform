import React, {useEffect, useState} from 'react';
import {updateAdGroups} from "../../../../constants/Function";
import {AdMngAdGroupListAdGroup} from "../../../../constants/Interface";
import AdPageBody from "../../AdPageBody";
import AdvAccountSet from "./contentBody/AdvAccountSet";
import GroupList from "./contentBody/GroupList";
import GroupSearch from "./contentBody/GroupSearch";

function AdMngGroupsContent() {
	const [adGroups, setAdGroups] = useState<AdMngAdGroupListAdGroup[]>([]);
	const [adGroupNameSearchKeyword, setAdGroupNameSearchKeyword] = useState<string>("");

	useEffect(() => {
		updateAdGroups("", setAdGroups);
	}, [setAdGroups]);

	return (
		<AdPageBody title="광고 관리 / 광고그룹">
			<AdvAccountSet/>
			<GroupSearch adGroupNameSearchKeyword={adGroupNameSearchKeyword} setAdGroupNameSearchKeyword={setAdGroupNameSearchKeyword} setAdGroups={setAdGroups}/>
			<GroupList adGroupNameSearchKeyword={adGroupNameSearchKeyword} adGroups={adGroups} setAdGroups={setAdGroups}/>
		</AdPageBody>
	);
}

export default AdMngGroupsContent;