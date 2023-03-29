import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

object Conexion {

     // ********************* Atributos *************************
     var conexion: Connection? = null

     // Atributo a través del cual hacemos la conexión física.
     var sentenciaSQL: Statement? = null


     fun abrirConexion(): Int {
         var cod = 0
         try {
             //Otros posibles controladores.
             // String controlador = "oracle.jdbc.driver.OracleDriver"
             // String controlador = "sun.jdbc.odbc.JdbcOdbcDriver"
             // String URL_BD = "jdbc:oracle:oci:@REPASO";
             // String URL_BD = "jdbc:oracle:oci:@REPASO";
             // String URL_BD = "jdbc:odbc:REPASO";

             // Cargar el driver/controlador JDBC de MySql
             val controlador = "com.mysql.cj.jdbc.Driver"
             val URL_BD = "jdbc:mysql://" + Constantes.servidor + ":" + Constantes.puerto + "/" + Constantes.bbdd

             // Cargar el driver/controlador MariaSQL
//            val controlador = "org.mariadb.jdbc.Driver" // MariaDB la version libre de MySQL (requiere incluir la librería jar correspondiente).
//            val URL_BD = "jdbc:mariadb://" + Constantes.servidor+":"+Constantes.puerto+"/"+Constantes.bbdd+""

             Class.forName(controlador)

             // Realizamos la conexión a una BD con un usuario y una clave.
             conexion = DriverManager.getConnection(URL_BD, Constantes.usuario, Constantes.passwd)
             sentenciaSQL = Conexion.conexion!!.createStatement()
             println("Conexion realizada con éxito")
         } catch (e: Exception) {
             System.err.println("Exception: " + e.message)
             cod = -1
         }
         return cod
     }

     // ------------------------------------------------------
     fun cerrarConexion(): Int {
         var cod = 0
         try {
             conexion!!.close()
             println("Desconectado de la Base de Datos") // Opcional para seguridad
         } catch (ex: SQLException) {
             cod = -1
         }
         return cod
     }

    // ----------------------------------------------------------
    fun insertarPersona(dni: String, nombre: String, clave: String, tfno: Int): Int {
        var cod = 0
        val sentencia = ("INSERT INTO " + Constantes.tablaPersonas + " VALUES ('" + dni + "'," + "'" + nombre + "','"
                + clave + "','" + tfno + "')")
        try {
            abrirConexion()
            sentenciaSQL!!.executeUpdate(sentencia)
        } catch (sq: SQLException) {
            cod = sq.errorCode
        } finally {
            cerrarConexion()
        }
        return cod
    }

    fun insertarPersona2(personaCompleta:String): Int {
        var cod = 0
        val sentencia = personaCompleta
        try {
            abrirConexion()
            sentenciaSQL!!.executeUpdate(sentencia)
        } catch (sq: SQLException) {
            cod = sq.errorCode
        } finally {
            cerrarConexion()
        }
        return cod
    }

    fun modificarNombre(dni: String, nombre:String):Int{
        val sentencia = "UPDATE " + Constantes.tablaPersonas + " SET NOMBRE = " + "'$nombre'" + " WHERE UPPER (DNI) LIKE " + "'$dni'"
        var cod = 0
        try {
            abrirConexion()
            sentenciaSQL!!.executeUpdate(sentencia)
        } catch (sq: SQLException) {
            cod = sq.errorCode
        }
        cerrarConexion()
        return cod
    }

    fun modificarCodigo(dni: String, codigo:String):Int{
        val sentencia = "UPDATE " + Constantes.tablaPersonas + " SET CODIGO = " + "'$codigo'" + " WHERE UPPER (DNI) LIKE " + "'$dni'"
        var cod = 0
        try {
            abrirConexion()
            sentenciaSQL!!.executeUpdate(sentencia)
        } catch (sq: SQLException) {
            cod = sq.errorCode
        }
        cerrarConexion()
        return cod
    }

    fun modificarTelefono(dni: String, telefono:Int):Int{
        val sentencia = "UPDATE " + Constantes.tablaPersonas + " SET TELEFONO = " + "$telefono" + " WHERE UPPER (DNI) LIKE " + "'$dni'"
        var cod = 0
        try {
            abrirConexion()
            sentenciaSQL!!.executeUpdate(sentencia)
        } catch (sq: SQLException) {
            cod = sq.errorCode
        }
        cerrarConexion()
        return cod
    }




    // ----------------------------------------------------------
    fun borrarPersona(dni: String): Int {
        var cuantos = 0
        val sentencia = "DELETE FROM " + Constantes.tablaPersonas + " WHERE dni = '" + dni + "'"
        try {
            abrirConexion()
            cuantos = sentenciaSQL!!.executeUpdate(sentencia)
        } catch (ex: SQLException) {
        } finally {
            cerrarConexion()
        }
        return cuantos
    }

 }