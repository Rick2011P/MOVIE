class HttpError extends Error { constructor(status,message){super(message);this.status=status;} }
const fail=(status,message)=>{throw new HttpError(status,message);};
const id=(v)=>{const n=Number(v);if(!Number.isSafeInteger(n)||n<1)fail(400,'Identificador inválido');return n;};
module.exports={HttpError,fail,id};
