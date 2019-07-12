package ar.com.thinco.controlstock

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_product.*
import kotlinx.android.synthetic.main.activity_product.*

class listProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)

        val code = intent.extras["resultCode"]
        tv_code.text = code.toString();
    }
}
