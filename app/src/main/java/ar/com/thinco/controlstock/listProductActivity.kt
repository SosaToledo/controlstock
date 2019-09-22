package ar.com.thinco.controlstock

import android.database.SQLException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ar.com.thinco.controlstock.DBhelper.DBhelper
import ar.com.thinco.controlstock.model.product
import kotlinx.android.synthetic.main.activity_list_product.*
import kotlinx.android.synthetic.main.activity_product.*
import java.lang.Exception

class listProductActivity : AppCompatActivity() {

    internal lateinit var db:DBhelper
    var exist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        var product:product? = product()
        db = DBhelper(this)

        //Guardamos el codigo enviado desde la activity anterior
        var code = intent.extras["resultCode"]

        //chequea que exista un codigo
        if (code != "000000"){
            //lo busca
            product = db.searchProduct(code.toString().toLong())
            if(product != null){
                //si el producto existe lo carga
                et_id.setText(code.toString())
                et_categoria.setText(product.categoria)
                et_marca.setText(product.marca)
                et_detalles.setText(product.detalles)
                et_precio.setText(product.precio.toString())
                et_costo.setText(product.costo.toString())
                et_cantidad.setText(product.cantidad.toString())

                //modificar boton
                ibtn_productos.setText("Modificar")
                exist = true
            }else{
                et_id.setText(code.toString())
            }
        }

        et_categoria.requestFocus()


    }

    fun saveProduct(view: View) {
        var product: product? = null
        if (et_id.text.toString() == "" || et_id.text.toString() == "identificador" &&
            et_cantidad.text.toString() == "" || et_cantidad.text.toString() == "cantidad" &&
            et_categoria.text.toString() == "" || et_categoria.text.toString() == "Categoría" &&
            et_costo.text.toString() == "" || et_costo.text.toString() == "Costo" &&
            et_detalles.text.toString() == ""|| et_detalles.text.toString() == "Detalles" &&
            et_precio.text.toString() == "" || et_precio.text.toString() == "Precio" &&
            et_marca.text.toString() == "" || et_marca.text.toString() == "Marca")
        {
            Toast.makeText(this, "Falta algun valor", Toast.LENGTH_LONG).show()
        }else{
            product = product(
                et_id.text.toString().toLong(),
                et_categoria.text.toString(),
                et_marca.text.toString(),
                et_detalles.text.toString(),
                et_precio.text.toString().toFloat(),
                et_costo.text.toString().toFloat(),
                0,
                et_cantidad.text.toString().toFloat()
            )

            val builder = AlertDialog.Builder(this)

            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                if (exist == true){
                    db.updateProduct(product)
                    builder.setTitle("Esta modificando los valores")
                    builder.setMessage("¿Seguro desea modificarlos?")
                    Toast.makeText(applicationContext,
                        "ha modificado exitosamente los valores", Toast.LENGTH_SHORT).show()
                }else{
                    db.addProduct(product)
                    builder.setTitle("Esta cargando un nuevo producto")
                    builder.setMessage("¿Seguro desea agregarlo?")
                    Toast.makeText(applicationContext,
                        "Se ha agregado exitosamente", Toast.LENGTH_SHORT).show()
                }

                finish()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext, "Puede seguir modificando", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

    }

    fun cancel(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Esta cancelando")
        builder.setMessage("¿Seguro desea salir sin modificar los valores?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            finish()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()
    }

}
