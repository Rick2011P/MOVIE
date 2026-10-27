import {createContext,useContext,useEffect,useState} from 'react';import {signIn,loadProfile} from '../services/authService';
const Context=createContext(null);const tokenKey='movie_token';
function decodeToken(token){try{const payload=token.split('.')[1].replace(/-/g,'+').replace(/_/g,'/');return JSON.parse(atob(payload));}catch{return {};}}
function normalize(u){if(!u)return null;const x=u?.data?.user??u?.data?.usuario??u?.data??u?.user??u?.usuario??u;return typeof x==='object'?x:null;}
export function AuthProvider({children}){
 const [token,setToken]=useState(()=>sessionStorage.getItem(tokenKey)||'');const [user,setUser]=useState(null);const [loading,setLoading]=useState(Boolean(sessionStorage.getItem(tokenKey)));
 function logout(){sessionStorage.removeItem(tokenKey);setToken('');setUser(null);}
 useEffect(()=>{if(!token){setLoading(false);return;}let active=true;setLoading(true);loadProfile().then(data=>{if(active)setUser(normalize(data));}).catch(error=>{if(!active)return;if(error.status===401||error.status===403){logout();}else{setUser(null);}}).finally(()=>{if(active)setLoading(false);});return()=>{active=false};},[token]);
 async function login(correo,password){const data=await signIn(correo,password);sessionStorage.setItem(tokenKey,data.token);setToken(data.token);setUser(normalize(data.user)||normalize(decodeToken(data.token)));}
 const role=String(user?.rol??user?.role?.name??user?.role??user?.rol_nombre??'').toUpperCase();
 return <Context.Provider value={{token,user,role,loading,login,logout}}>{children}</Context.Provider>;
}
export const useAuth=()=>useContext(Context);
