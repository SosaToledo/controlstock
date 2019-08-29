package ar.com.thinco.controlstock

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.thinco.controlstock.model.product
import kotlinx.android.synthetic.main.activity_list_product.view.*
import kotlinx.android.synthetic.main.cv_product_list.view.*

class productAdapter(private val context: Context, private val productos: ArrayList<product>): RecyclerView.Adapter<productAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cv_product_list, p0, false))
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.categoria?.setText(productos[p1].categoria)
        holder.marca?.setText(productos[p1].marca)
        holder.detalles?.setText(productos[p1].detalles)
        holder.precio?.setText(productos[p1].precio.toString())
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val categoria = itemView.tv_categoria
        val marca = itemView.tv_marca
        val detalles = itemView.tv_detalles
        val precio = itemView.tv_precio
    }
}