package ar.com.thinco.controlstock

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.thinco.controlstock.model.product
import kotlinx.android.synthetic.main.cv_product_list.view.*

class productAdapter(private val context: productActivity, private val productos: ArrayList<product>): RecyclerView.Adapter<productAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cv_product_list, p0, false))
    }

    override fun getItemCount(): Int {
        if (productos != null) return productos.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        var precio = productos[p1].precio.toString()
        holder.categoria?.setText(productos[p1].categoria)
        holder.marca?.setText(productos[p1].marca)
        holder.detalles?.setText(productos[p1].detalles)
        holder.precio?.setText("Precio: $$precio")
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val categoria = itemView.tv_categoria
        val marca = itemView.tv_marca
        val detalles = itemView.tv_detalles
        val precio = itemView.tv_precio
    }
}