package cr.ac.menufracmentcurso.repository

import cr.ac.menufracmentcurso.entity.Empleado
import cr.ac.menufracmentcurso.service.EmpleadoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EmpleadoRepository {

    val empleadosService : EmpleadoService

    companion object{
        @JvmStatic
        val instance by lazy {
            EmpleadoRepository().apply {  }
        }
    }

    constructor(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://etiquicia.click/restAPI/api.php/records/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        empleadosService = retrofit.create(EmpleadoService::class.java)
    }
    fun save(empleado: Empleado){
        empleadosService.create(empleado).execute()
    }
    fun edit(empleado: Empleado){
        empleadosService.update(empleado.idEmpleado, empleado).execute()
    }
    fun delete(empleado: Empleado){
        empleadosService.delete(empleado.idEmpleado).execute()
    }
    fun datos(): List<Empleado>{
       return empleadosService.getEmpleado().execute().body()!!.records
    }
}