package ar.com.thinco.controlstock

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import ar.com.thinco.controlstock.DBhelper.DBhelper
import kotlinx.android.synthetic.main.activity_list_product.*
import kotlinx.android.synthetic.main.activity_product.*

class listProductActivity : AppCompatActivity() {

    internal lateinit var db:DBhelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)

        db = DBhelper(this)

        val code = intent.extras["resultCode"]
        et_id.setText(code.toString())
        val product = db.searchProduct(code.toString().toLong())

        if (product != null){
            et_categoria.setText(product.categoria)
            et_detalles.setText(product.detalles)
            et_costo.setText(product.costo.toString())
            et_precio.setText(product.precio.toString())
            et_medida.setText(product.medidaPeso.toString())
            et_cantidad.setText(product.cantidad.toString())
        }
    }
}
