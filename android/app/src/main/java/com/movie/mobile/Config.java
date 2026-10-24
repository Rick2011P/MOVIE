package com.movie.mobile;
public final class Config {
 private Config() {}
 // 10.0.2.2 es localhost del computador visto desde el emulador.
 public static final String URL="http://10.0.2.2:3000";
 public static final String LOGIN="/api/auth/login";
 public static final String MOVIES="/api/movies"; // PROVISIONAL: revisar ruta en backend original
 public static final String FAVORITES="/api/favoritos";
}
