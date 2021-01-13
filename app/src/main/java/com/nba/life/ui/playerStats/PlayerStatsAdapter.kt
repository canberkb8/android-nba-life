package com.nba.life.ui.playerStats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nba.life.data.remote.model.players.PlayerModel
import com.nba.life.databinding.CardItemPlayerBinding

class PlayerStatsAdapter : RecyclerView.Adapter<PlayerStatsAdapter.PlayerStatsViewHolder>() {

    private lateinit var playerItemBinding: CardItemPlayerBinding
    private var onItemClickListener: ((PlayerModel.PlayerData?) -> Unit)? = null

    var playerList: MutableList<PlayerModel.PlayerData> = mutableListOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerStatsViewHolder {
        playerItemBinding = CardItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerStatsViewHolder(playerItemBinding)
    }

    override fun onBindViewHolder(holder: PlayerStatsViewHolder, position: Int) {
        val playerItem = playerList[position]
        playerItem.let {
            holder.bind(it, onItemClickListener)
        }
    }

    override fun getItemCount(): Int  = playerList.size

    class PlayerStatsViewHolder(private val playerItemBinding: CardItemPlayerBinding) :
        RecyclerView.ViewHolder(playerItemBinding.root) {

        fun bind(playerItem: PlayerModel.PlayerData, onItemClickListener: ((playerData: PlayerModel.PlayerData) -> Unit)?) {

            playerItemBinding.txtPlayerName.text=playerItem.firstName+" "+playerItem.lastName

            playerItemBinding.cardPlayerItem.setOnClickListener { onItemClickListener?.invoke(playerItem) }
        }
    }
    fun setOnItemClickListener(onItemClickListener: ((PlayerModel.PlayerData?) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }
}