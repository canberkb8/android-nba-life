package com.nba.life.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nba.life.data.remote.model.games.GameModel
import com.nba.life.databinding.CardItemGameBinding

/**
 * It holds the list of NBA games.
 */
class GameListAdapter : RecyclerView.Adapter<GameListAdapter.GameListViewHolder>() {

    private lateinit var gameItemBinding: CardItemGameBinding

    private var onItemClickListener: ((GameModel.GameData?) -> Unit)? = null

    var gameList: MutableList<GameModel.GameData> = mutableListOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        gameItemBinding = CardItemGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameListViewHolder(gameItemBinding)
    }

    override fun getItemCount(): Int = gameList.size

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        val gameItem = gameList[position]
        gameItem.let {
            holder.bind(it, onItemClickListener)
        }
    }

    class GameListViewHolder(private val gameItemBinding: CardItemGameBinding) :
        RecyclerView.ViewHolder(gameItemBinding.root) {

        fun bind(gameItem: GameModel.GameData, onItemClickListener: ((gameData: GameModel.GameData) -> Unit)?) {

            gameItemBinding.txtHomeTeam.text=gameItem.homeTeam?.fullName
            gameItemBinding.txtVisitorTeam.text=gameItem.visitorTeam?.fullName

            itemView.setOnClickListener { onItemClickListener?.invoke(gameItem) }
        }

    }

    fun setOnItemClickListener(onItemClickListener: (( GameModel.GameData?) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }


}