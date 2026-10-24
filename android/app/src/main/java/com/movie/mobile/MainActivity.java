package com.movie.mobile;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.*;
import org.json.*;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class MainActivity extends Activity {
 private LinearLayout panel; private TextView info; private String jwt="";
 private final ExecutorService worker=Executors.newSingleThreadExecutor();
 interface Callback { void done(String body, int status) throws Exception; }
 @Override public void onCreate(Bundle state){super.onCreate(state);jwt=getPreferences(0).getString("jwt","");home();}
 private TextView label(String text){TextView t=new TextView(this);t.setText(text);t.setTextSize(17);t.setTextColor(Color.DKGRAY);t.setPadding(4,10,4,10);return t;}
 private void button(String text,Runnable action){Button b=new Button(this);b.setText(text);b.setOnClickListener(v->action.run());panel.addView(b);}
 private void page(String heading){ScrollView scroll=new ScrollView(this);panel=new LinearLayout(this);panel.setPadding(26,28,26,28);panel.setOrientation(1);scroll.addView(panel);setContentView(scroll);
 TextView h=label("MOVIE — "+heading);h.setTextSize(24);panel.addView(h);info=label("Servidor: "+Config.URL);panel.addView(info);}
 private void message(String s){info.setText(s);}
 private void home(){page("Inicio");panel.addView(label("Sistema móvil de consulta de películas."));button("Consultar películas",()->movies());
 if(jwt.isEmpty())button("Iniciar sesión",()->login()); else {button("Mis favoritos",()->favorites());button("Cerrar sesión",()->{jwt="";getPreferences(0).edit().remove("jwt").apply();home();});}}
 private void request(String method,String path,JSONObject payload,Callback callback){message("Conectando: "+path);
  android.util.Log.d("MOVIE_API", "Petición: " + method + " " + Config.URL + path);
  worker.execute(()->{HttpURLConnection con=null;try{con=(HttpURLConnection)new URL(Config.URL+path).openConnection();con.setRequestMethod(method);con.setConnectTimeout(10000);con.setReadTimeout(10000);
 con.setRequestProperty("Accept","application/json");if(!jwt.isEmpty())con.setRequestProperty("Authorization","Bearer "+jwt);
 if(payload!=null){con.setDoOutput(true);con.setRequestProperty("Content-Type","application/json; charset=utf-8");try(OutputStream o=con.getOutputStream()){o.write(payload.toString().getBytes(StandardCharsets.UTF_8));}}
 int code=con.getResponseCode();InputStream stream=code>=400?con.getErrorStream():con.getInputStream();StringBuilder response=new StringBuilder();
 if(stream!=null)try(BufferedReader reader=new BufferedReader(new InputStreamReader(stream,StandardCharsets.UTF_8))){String line;while((line=reader.readLine())!=null)response.append(line);}
 String body=response.toString();runOnUiThread(()->{if(code>=400){message("HTTP "+code+" — "+body);return;}try{callback.done(body,code);}catch(Exception e){message("Respuesta inesperada: "+e.getMessage());}});
 }catch(Exception e){runOnUiThread(()->message("Falla de conexión: "+e.getMessage()));}finally{if(con!=null)con.disconnect();}});
 }
 private static String val(JSONObject o,String...fields){for(String field:fields){String s=o.optString(field,"");if(!s.isEmpty()&&!s.equals("null"))return s;}return "";}
 private static JSONArray array(String body)throws Exception {String b=body.trim();if(b.startsWith("["))return new JSONArray(b);JSONObject root=new JSONObject(b);
 for(String key:new String[]{"data","peliculas","movies","favoritos","favorites","items","results"}){
 Object v=root.opt(key);if(v instanceof JSONArray)return (JSONArray)v;
 if(v instanceof JSONObject){JSONObject nested=(JSONObject)v;for(String sub:new String[]{"items","peliculas","movies","favoritos"})if(nested.opt(sub) instanceof JSONArray)return nested.getJSONArray(sub);}}
 throw new JSONException("Revisa el formato de datos del backend: "+b);
 }
 private static JSONObject movieObject(JSONObject row){JSONObject m=row.optJSONObject("pelicula");if(m==null)m=row.optJSONObject("movie");return m==null?row:m;}
 private void login(){page("Acceso");EditText email=new EditText(this);email.setHint("Correo");email.setInputType(33);panel.addView(email);
 EditText password=new EditText(this);password.setHint("Contraseña");password.setInputType(129);panel.addView(password);
 button("Entrar",()->{try{JSONObject obj=new JSONObject();obj.put("correo",email.getText().toString().trim());obj.put("password",password.getText().toString());
 request("POST",Config.LOGIN,obj,(s,c)->{JSONObject response=new JSONObject(s);String token=val(response,"token","accessToken");if(token.isEmpty()&&response.optJSONObject("data")!=null)token=val(response.getJSONObject("data"),"token","accessToken");
 if(token.isEmpty()){message("Falta token en respuesta HTTP "+c);return;}jwt=token;getPreferences(0).edit().putString("jwt",jwt).apply();home();});
 }catch(Exception e){message(e.getMessage());}});button("Inicio",()->home());}
 private void movies(){page("Películas");button("Inicio",()->home());request("GET",Config.MOVIES,null,(body,code)->{JSONArray items=array(body);
 for(int i=0;i<items.length();i++){JSONObject item=items.optJSONObject(i);if(item==null)continue;JSONObject m=movieObject(item);
 String title=val(m,"titulo","title","nombre","name");String id=val(m,"id_pelicula","pelicula_id","movie_id","id");
 panel.addView(label((i+1)+". "+title+" (ID "+id+")"));if(!jwt.isEmpty()&&!id.isEmpty())button("Añadir a favoritos",()->request("POST","/api/favorites/"+id,null,(s,c)->message("Favorito registrado: HTTP "+c)));
 }message("Películas recibidas: "+items.length()+" | HTTP "+code);});}
 private void favorites() {
  page("Favoritos");
  button("Inicio", () -> home());

  request("GET", "/api/favorites", null, (body, code) -> {
   JSONArray items = array(body);

   for (int i = 0; i < items.length(); i++) {
    JSONObject item = items.optJSONObject(i);
    if (item == null) continue;

    JSONObject m = movieObject(item);

    String id = val(m, "id_pelicula", "pelicula_id", "movie_id", "id");
    String title = val(m, "titulo", "title", "nombre", "name");

    panel.addView(label((i + 1) + ". " + title + " (ID " + id + ")"));

    if (!id.isEmpty()) {
     button("Quitar favorito", () ->
             request("DELETE", "/api/favorites/" + id, null,
                     (s, c) -> favorites())
     );
    }
   }

   message("Favoritos recibidos: " + items.length() + " | HTTP " + code);
  });
 }
 @Override protected void onDestroy(){worker.shutdownNow();super.onDestroy();}
}
