import {apiRequest,unwrapList} from './apiClient';
// Los métodos de usuarios siguen la Guía 02. Confirmar contratos/permisos en MOVIE/backend/src/modules/users/user.routes.js.
export const listUsers=async()=>unwrapList(await apiRequest('/users'));
export const getUser=id=>apiRequest(`/users/${encodeURIComponent(id)}`);
export const createUser=data=>apiRequest('/users',{method:'POST',body:data});
export const updateUser=(id,data)=>apiRequest(`/users/${encodeURIComponent(id)}`,{method:'PUT',body:data});
export const changeUserStatus=(id,data)=>apiRequest(`/users/${encodeURIComponent(id)}/status`,{method:'PATCH',body:data});
export const deleteUser=id=>apiRequest(`/users/${encodeURIComponent(id)}`,{method:'DELETE'});
