package ar.com.thinco.controlstock

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import ar.com.thinco.controlstock.DBhelper.DBhelper
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_product.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class productActivity : AppCompatActivity() {

    private lateinit var v: ZXingScannerView
    val intent: IntentIntegrator = IntentIntegrator(this)
    private lateinit var db:DBhelper
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        db = DBhelper(this)

        layoutManager = LinearLayoutManager(this)
        val context = this

        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)

        intent.setPrompt("Escanear")
        intent.setCameraId(0)
        intent.setBeepEnabled(false)
        intent.setBarcodeImageEnabled(false)

        et_search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count==0) Log.d("ERROR", "evitar error")
                if (count>=3){
                    val product = db.searchProduct(s.toString())
                    rv_searchproduct.layoutManager = layoutManager
                    rv_searchproduct.adapter = productAdapter(context, product)
                }
            }

        })

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

    fun addProductWithoutCode(view: View){
        val intent = Intent(this, listProductActivity::class.java)
        intent.putExtra("resultCode", "0000000")
        startActivity(intent)
    }


}
