package ar.com.thinco.controlstock.DBhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ar.com.thinco.controlstock.model.product

class DBhelper(context:Context):SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VER) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_CATEGORIA TEXT, $COL_MARCA TEXT, $COL_DETALLES TEXT, $COL_COSTO REAL, $COL_PRECIO REAL, $COL_MEDIDAPESO INTEGER, $COL_CANTIDAD REAL)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "negocioAPP.db"

        //table
        private val TABLE_NAME = "product"
        private val COL_ID = "id"
        private val COL_CATEGORIA= "categoria"
        private val COL_MARCA = "marca"
        private val COL_DETALLES = "detalles"
        private val COL_COSTO = "costo"
        private val COL_PRECIO = "precio"
        private val COL_MEDIDAPESO = "medidaPeso"
        private val COL_CANTIDAD = "cantidad"
    }

    //CRUD
    val allProduct:List<product>
        get(){
            val lstProduct = ArrayList<product>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()){
                do {
                    val product = product()
                    product.id = cursor.getLong(cursor.getColumnIndex(COL_ID))
                    product.categoria = cursor.getString(cursor.getColumnIndex(COL_CATEGORIA))
                    product.marca = cursor.getString(cursor.getColumnIndex(COL_MARCA))
                    product.detalles = cursor.getString(cursor.getColumnIndex(COL_DETALLES))
                    product.costo = cursor.getFloat(cursor.getColumnIndex(COL_COSTO))
                    product.precio = cursor.getFloat(cursor.getColumnIndex(COL_PRECIO))
                    product.medidaPeso = cursor.getInt(cursor.getColumnIndex(COL_MEDIDAPESO))
                    product.cantidad = cursor.getFloat(cursor.getColumnIndex(COL_CANTIDAD))

                    lstProduct.add(product)
                }while (cursor.moveToNext())
            }
            db.close()
            return lstProduct
        }

    fun addProduct(product: product){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, product.id)
        values.put(COL_CATEGORIA, product.categoria)
        values.put(COL_MARCA, product.marca)
        values.put(COL_DETALLES, product.detalles)
        values.put(COL_COSTO, product.costo)
        values.put(COL_PRECIO, product.precio)
        values.put(COL_MEDIDAPESO, product.medidaPeso)
        values.put(COL_CANTIDAD, product.cantidad)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateProduct(product: product){
        val db = this.writableDatabase
        val updatetable = "UPDATE $TABLE_NAME " +
                "SET $COL_CATEGORIA = '${product.categoria}', " +
                "$COL_MARCA = '${product.marca}', " +
                "$COL_DETALLES = '${product.detalles}', " +
                "$COL_PRECIO = ${product.precio}, " +
                "$COL_COSTO = ${product.costo}, " +
                "$COL_MEDIDAPESO = ${product.medidaPeso}, " +
                "$COL_CANTIDAD = ${product.cantidad} " +
                "WHERE $COL_ID = ${product.id};"
        db.execSQL(updatetable)
        db.close()
    }

    fun deleteProduct(product: product){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(product.id.toString()))
        db.close()
    }

    fun searchProduct(id: Long):product?{
        val producto = product()
        val searchQuery = "SELECT * FROM $TABLE_NAME WHERE id = $id"
        val db = this.writableDatabase
        val cursor = db.rawQuery(searchQuery, null)
        if (cursor.moveToFirst()){
            producto.categoria = cursor.getString(cursor.getColumnIndex(COL_CATEGORIA))
            producto.marca = cursor.getString(cursor.getColumnIndex(COL_MARCA))
            producto.detalles = cursor.getString(cursor.getColumnIndex(COL_DETALLES))
            producto.costo = cursor.getFloat(cursor.getColumnIndex(COL_COSTO))
            producto.precio = cursor.getFloat(cursor.getColumnIndex(COL_PRECIO))
            producto.medidaPeso = cursor.getInt(cursor.getColumnIndex(COL_MEDIDAPESO))
            producto.cantidad = cursor.getFloat(cursor.getColumnIndex(COL_CANTIDAD))

            return producto
        }

        return  null
    }

    fun searchProduct(cadena: String):ArrayList<product>{
        val lstProduct = ArrayList<product>()
        val searchQuery = "SELECT * FROM $TABLE_NAME WHERE categoria LIKE '%$cadena%' OR marca LIKE '%$cadena%' OR detalles LIKE '%$cadena%'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(searchQuery, null)
        if (cursor.moveToFirst()){
            do {
                val product = product()
                product.id = cursor.getLong(cursor.getColumnIndex(COL_ID))
                product.categoria = cursor.getString(cursor.getColumnIndex(COL_CATEGORIA))
                product.marca = cursor.getString(cursor.getColumnIndex(COL_MARCA))
                product.detalles = cursor.getString(cursor.getColumnIndex(COL_DETALLES))
                product.costo = cursor.getFloat(cursor.getColumnIndex(COL_COSTO))
                product.precio = cursor.getFloat(cursor.getColumnIndex(COL_PRECIO))
                product.medidaPeso = cursor.getInt(cursor.getColumnIndex(COL_MEDIDAPESO))
                product.cantidad = cursor.getFloat(cursor.getColumnIndex(COL_CANTIDAD))

                lstProduct.add(product)
            }while (cursor.moveToNext())
        }
        db.close()
        return lstProduct
    }

}