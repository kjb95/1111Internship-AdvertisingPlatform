import {Button, Input} from "antd";
import React, {useState} from 'react';
import {jwtAuthenticate} from "../api/jwt/JwtApi";
import {findAuthorities} from "../api/member/MemberApi";
import {AUTHENTICATED_MEMBER_ID, JWT_TOKEN, ROLE, ROLE_ADV} from "../constants/Constant";
import {onPressEnter} from "../constants/Function";

function findAuthoritiesSuccess(roles: string[]) {
	sessionStorage.setItem(ROLE, roles.join(','));
	const nextHref = roles.includes(ROLE_ADV) ? "/adReg" : "/checkKwd";
	window.location.href = nextHref;
}

function loginSuccess(username: string, password: string, token: string) {
	sessionStorage.setItem(JWT_TOKEN, token);
	sessionStorage.setItem(AUTHENTICATED_MEMBER_ID, username);
	findAuthorities(username)
		.then((res) => findAuthoritiesSuccess(res.data.roles))
		.catch((e) => console.log(e));
}

function loginFail(e: unknown, setIsLoginFail: React.Dispatch<React.SetStateAction<boolean>>) {
	setIsLoginFail(true);
	console.log(e);
}

function Login() {
	const [isLoginFail, setIsLoginFail] = useState<boolean>(false);
	const [username, setUsername] = useState<string>("");
	const [password, setPassword] = useState<string>("");

	function onClick() {
		return jwtAuthenticate(username, password)
			.then((res) => loginSuccess(username, password, res.data.token))
			.catch((e) => loginFail(e, setIsLoginFail));
	}

	return (
		<div className="wrap login">
			<div className="box-inner">
				<div className="box-left">
					<div className="box-top">
						<div className="logo">NHNAD Bidding Solution</div>
						<div className="logo-sub">NHNAD 자동입찰 솔루션 로그인</div>
					</div>
					<div className="box-middle">
						<div className="company-name">
							<i className="ico ico-check"/>
							<span className="txt-company">DB손해보험</span>
						</div>
						<Input name="email" placeholder="아이디를 입력해주세요." size="large" prefix={<i className="ico ico-id"/>} value={username}
						       onChange={(e) => setUsername(e.target.value)}
						/>
						<Input.Password name={"password"} placeholder="비밀번호를 입력해주세요." size="large" prefix={<i className="ico ico-pw"/>} value={password}
						                onChange={(e) => setPassword(e.target.value)}
						                onPressEnter={(e) => onPressEnter(e, onClick)}
						/>
						{isLoginFail && <p className="txt-error show">아이디 또는 비밀번호가 일치하지 않습니다.</p>}
					</div>
					<div className="box-bottom">
						<Button type="primary" className="pink" size="large" block onClick={onClick}>로그인</Button>
					</div>
				</div>
				<div className="box-right">
					<img src={require('../images/img-login-object.jpg')} alt="NBS 솔루션 화면 이미지"/>
				</div>
			</div>
		</div>);
}

export default Login;