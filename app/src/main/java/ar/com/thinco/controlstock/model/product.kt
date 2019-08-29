package ar.com.thinco.controlstock.model

class product {
    var id:Long=0
    var categoria:String=""
    var marca:String=""
    var detalles:String=""
    var precio:Float= 0F
    var costo:Float= 0F
    var medidaPeso:Int=0
    var cantidad:Float= 0F

    constructor(){}
    constructor(
        id: Long,
        categoria: String,
        marca: String,
        detalles: String,
        precio: Float,
        costo: Float,
        medidaPeso: Int,
        cantidad: Float
    ) {
        this.id = id
        this.categoria = categoria
        this.marca = marca
        this.detalles = detalles
        this.precio = precio
        this.costo = costo
        this.medidaPeso = medidaPeso
        this.cantidad = cantidad
    }

    constructor(categoria: String, marca: String, detalles: String, precio: Float) {
        this.categoria = categoria
        this.marca = marca
        this.detalles = detalles
        this.precio = precio
    }


}