package ar.com.thinco.controlstock

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_product.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class productActivity : AppCompatActivity() {

    private lateinit var v: ZXingScannerView
    val intent: IntentIntegrator = IntentIntegrator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)

        intent.setPrompt("Escanear")
        intent.setCameraId(0)
        intent.setBeepEnabled(false)
        intent.setBarcodeImageEnabled(false)
    }

    fun searchByCode(view:View){
        intent.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result!=null){
            if (result.contents == null){
                Log.d("ERROR", "algo malo paso")
            }else{
                val intent = Intent(this, listProductActivity::class.java)
                Log.d("RESULT", result.contents.toString())
                intent.putExtra("resultCode", result.contents.toString())
                startActivity(intent)
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun addProductByCode(view: View){

    }
}
