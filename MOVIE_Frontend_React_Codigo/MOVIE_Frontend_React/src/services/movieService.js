import {apiRequest,unwrapList} from './apiClient';
export async function getMovies(signal){return unwrapList(await apiRequest('/movies',{signal,auth:false}));}
export const getFavorites=async()=>unwrapList(await apiRequest('/favorites'));
export const addFavorite=id=>apiRequest(`/favorites/${encodeURIComponent(id)}`,{method:'POST'});
export const removeFavorite=id=>apiRequest(`/favorites/${encodeURIComponent(id)}`,{method:'DELETE'});
