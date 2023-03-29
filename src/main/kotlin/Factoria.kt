import kotlin.random.Random

object Factoria {

    fun factoriaPersona():String{
        var cad:String = ""
        var dni = Random.nextInt(100000000,999999999).toString()
        val nombres = arrayOf("María", "Juan", "Lucía", "Pablo", "Lola", "Javier", "Ana", "Pedro", "Carmen", "Sergio", "Sara", "Ricardo", "Alicia", "Diego", "Laura", "Miguel", "Elena", "Carlos", "Raquel", "Jorge")
        var nombre = nombres[Random.nextInt(0,nombres.size-1)]
        var clave = Random.nextInt(1000,9999).toString()
        var telefono = Random.nextInt(100000,999999)

        cad = ("INSERT INTO " + Constantes.tablaPersonas + " VALUES ('" + dni + "'," + "'" + nombre + "','"
                + clave + "','" + telefono + "')")
        return  cad
    }



}