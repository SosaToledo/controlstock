package ar.com.thinco.controlstock

import android.database.SQLException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        var product = product()
        db = DBhelper(this)

        val code = intent.extras["resultCode"]
        et_id.setText(code!!.toString())

        if (code != "0000000"){
            product = db.searchProduct(code.toString().toLong())
        }

        et_categoria.setText(product.categoria)
        et_marca.setText(product.marca)
        et_detalles.setText(product.detalles)
        et_costo.setText(product.costo.toString())
        et_precio.setText(product.precio.toString())
        et_medida.setText(product.medidaPeso.toString())
        et_cantidad.setText(product.cantidad.toString())
    }

    fun saveProduct(view: View) {
        val product = product(
            et_id.text.toString().toLong(),
            et_categoria.text.toString(),
            et_marca.text.toString(),
            et_detalles.text.toString(),
            et_costo.text.toString().toFloat(),
            et_precio.text.toString().toFloat(),
            et_medida.text.toString().toInt(),
            et_cantidad.text.toString().toFloat()
        )


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Esta modificando los valores")
        builder.setMessage("¿Seguro desea modificarlos?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            db.updateProduct(product)
            Toast.makeText(applicationContext,
                "ha modificado exitosamente los valores", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext, "Puede seguir modificando", Toast.LENGTH_SHORT).show()
        }
        builder.show()

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
