const API = import.meta.env.VITE_API_URL || 'http://localhost:3000/api';
export async function apiRequest(path,{method='GET',body,signal,auth=true}={}){
 const token=sessionStorage.getItem('movie_token');
 const response=await fetch(`${API}${path}`,{method,signal,headers:{Accept:'application/json',...(body!==undefined?{'Content-Type':'application/json'}:{}),...(auth&&token?{Authorization:`Bearer ${token}`}:{})},...(body!==undefined?{body:JSON.stringify(body)}:{})});
 const text=await response.text();let data=null;try{data=text?JSON.parse(text):null;}catch{throw new Error('El servidor no devolvió JSON válido');}
 if(!response.ok||data?.success===false){const error=new Error(data?.message||data?.error||`HTTP ${response.status}`);error.status=response.status;throw error;}
 return data;
}
export function unwrapList(data){if(Array.isArray(data))return data;for(const x of [data?.data,data?.items,data?.movies,data?.peliculas,data?.favorites,data?.favoritos,data?.users,data?.usuarios,data?.data?.items])if(Array.isArray(x))return x;throw new Error('El formato de lista no coincide con la API MOVIE');}
