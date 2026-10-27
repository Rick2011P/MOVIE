import {apiRequest} from './apiClient';
export async function signIn(correo,password){const result=await apiRequest('/auth/login',{method:'POST',body:{correo,password},auth:false});const token=result?.token??result?.accessToken??result?.data?.token??result?.data?.accessToken; if(!token)throw new Error('El backend no devolvió un token JWT');const user=result?.usuario??result?.user??result?.data?.user??result?.data?.usuario??null;return {token,user};}
export async function loadProfile(){return apiRequest('/users/me');}
