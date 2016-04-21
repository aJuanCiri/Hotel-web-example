package userServlet;

/*
* Clase que contiene las operaciones que se pueden realizar
* sobre un dato de tipo ResultadoBusqueda.
*/
public class ResultadoBusqueda implements Comparable<Object>{
	private int id;
	private String nombre;
	private int estrellas;
	private String descripcion;
	private String ciudad;
	private String fecha;
	private String tipoHabitacion;
	private int habDisponibles;
	private double precio;
	
	/*
	* Constructor encargado de actualizar las variables y darles nuevo valor
	*/
	public ResultadoBusqueda(String _nombre, int _estrellas, String _descripcion,
			String _ciudad, String _fecha, String _tipoHabitacion, int _habDisponibles, double _precio, int _id){
			nombre = _nombre;
			estrellas = _estrellas;
			descripcion = _descripcion;
			ciudad = _ciudad;
			fecha = _fecha;
			tipoHabitacion = _tipoHabitacion;
			habDisponibles = _habDisponibles;
			precio = _precio;
			id = _id;
	}
	
	/*
	* Devuelve el valor de [nombre]
	*/
	public String getNombre(){
		return nombre;
	}
	
	/*
	* Devuelve el valor de [estrellas]
	*/
	public int getEstrellas(){
		return estrellas;
	}
	
	/*
	* Devuelve el valor de [descripcion]
	*/
	public String getDescripcion(){
		return descripcion;
	}
	
	/*
	* Devuelve el valor de [ciudad]
	*/
	public String getCiudad(){
		return ciudad;
	}
	
	/*
	* Devuelve el valor de [fecha]
	*/
	public String getFecha(){
		return fecha;
	}
	
	/*
	* Devuelve el valor de [tipoHabitacion]
	*/
	public String getTipoHabitacion(){
		return tipoHabitacion;
	}
	
	/*
	* Devuelve el valor de [habDisponibles]
	*/
	public int getHabDisponibles(){
		return habDisponibles;
	}
	
	/*
	* Devuelve el valor de [precio]
	*/
	public double getPrecio(){
		return precio;
	}
	
	/*
	* Devuelve el valor de [id]
	*/
	public int getId(){
		return id;
	}
	
	/*
	* Sobreescibe el metodo compareTo para que pueda organizar
	* los resultados de la busqueda de hoteles segun el precio de estos.
	*/
	@Override
	public int compareTo(Object rb) {
		if (precio < ((ResultadoBusqueda) rb).getPrecio()) {
			return -1;
		}
		if (precio > ((ResultadoBusqueda) rb).getPrecio()) {
			return 1;
		} return 0;
	}
}
