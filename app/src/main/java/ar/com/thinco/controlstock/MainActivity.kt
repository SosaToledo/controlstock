package ar.com.thinco.controlstock

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toProduct(view: View){
        val intent = Intent(this@MainActivity, productActivity::class.java)
        startActivity(intent)
    }

}
