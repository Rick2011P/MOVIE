# Trazabilidad de la evidencia GA7-220501096-AA2-EV01

| Criterio oficial | Implementación | Evidencia |
|---|---|---|
| JDBC | `DriverManager`, `Connection`, `PreparedStatement`, `ResultSet` | `config/DatabaseConnection.java`, `repository/MovieRepository.java`, `pom.xml` |
| CRUD | Crear, listar, consultar ID, actualizar, eliminar | `service/MovieService.java`, `app/MovieApplication.java` |
| Versionamiento | Historial de commits, `.gitignore` | repositorio Git local, `docs/git-log.txt` |
| Estándar | Arquitectura por capas y convenciones | `docs/ESTANDAR_CODIFICACION.md` |

**Contexto de clases:** las guías SICE del docente emplean Node.js/Express/MySQL y el flujo Route → Validator → Controller → Service → Repository → MySQL. La presente implementación usa Java/JDBC para cumplir literalmente la rúbrica, conservando la separación de capas aplicable al proyecto MOVIE.
