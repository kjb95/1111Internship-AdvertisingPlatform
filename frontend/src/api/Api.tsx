import axios, {InternalAxiosRequestConfig} from "axios";
import {JWT_TOKEN} from "../constants/Constant";

function addJwtTokenToHeader(config: InternalAxiosRequestConfig<any>) {
	const jwtToken = sessionStorage.getItem(JWT_TOKEN);
	config.headers.Authorization = `Bearer ${jwtToken}`;
	return config;
}

export const api = axios.create({headers: {"Content-Type": "application/json"}});

api.interceptors.request.use(addJwtTokenToHeader);

export const multipartFormDataApi = axios.create({headers: {"Content-Type": "multipart/form-data"}});

multipartFormDataApi.interceptors.request.use(addJwtTokenToHeader);