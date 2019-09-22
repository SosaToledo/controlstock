package ar.com.thinco.controlstock.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class product {
    @Expose
    @SerializedName("id")
    var id:Long=0
    @Expose
    @SerializedName("categoria")
    var categoria:String=""
    @Expose
    @SerializedName("marca")
    var marca:String=""
    @Expose
    @SerializedName("detalles")
    var detalles:String=""
    @Expose
    @SerializedName("precio")
    var precio:Float= 0F
    @Expose
    @SerializedName("costo")
    var costo:Float= 0F
    var medidaPeso:Int=0
    @Expose
    @SerializedName("cantidad")
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