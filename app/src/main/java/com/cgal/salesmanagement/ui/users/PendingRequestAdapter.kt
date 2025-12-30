package com.cgal.salesmanagement.ui.users

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cgal.salesmanagement.R
import com.cgal.salesmanagement.data.model.RegisterRequest

class PendingRequestAdapter(
    private val requests: List<RegisterRequest>,
    private val onViewClick: (RegisterRequest) -> Unit
) : RecyclerView.Adapter<PendingRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pending_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = requests[position]

        holder.tvSerial.text = (position + 1).toString()
        holder.tvName.text = request.name


        holder.btnView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PendingRequestDetailsActivity::class.java)
            intent.putExtra("USER_ID", request.contact)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = requests.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSerial: TextView = itemView.findViewById(R.id.tvSerial)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val btnView: Button = itemView.findViewById(R.id.btnView)
    }
}
