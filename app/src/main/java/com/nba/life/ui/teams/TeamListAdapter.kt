package com.nba.life.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nba.life.data.remote.model.teams.TeamModel
import com.nba.life.databinding.CardItemTeamBinding

class TeamListAdapter : RecyclerView.Adapter<TeamListAdapter.TeamListViewHolder>() {

    private lateinit var teamItemBinding: CardItemTeamBinding

    private var onItemClickListener: ((TeamModel.TeamData?) -> Unit)? = null

    var teamList: MutableList<TeamModel.TeamData> = mutableListOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        teamItemBinding = CardItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeamListViewHolder(teamItemBinding)
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        val teamItem = teamList[position]
        teamItem.let {
            holder.bind(it, onItemClickListener)
        }
    }

    class TeamListViewHolder(private val teamItemBinding: CardItemTeamBinding) :
        RecyclerView.ViewHolder(teamItemBinding.root) {

        fun bind(teamItem: TeamModel.TeamData, onItemClickListener: ((teamData: TeamModel.TeamData) -> Unit)?) {

            teamItemBinding.txtTeam.text=teamItem.fullName

            teamItemBinding.cardTeamItem.setOnClickListener { onItemClickListener?.invoke(teamItem) }
        }

    }

    fun setOnItemClickListener(onItemClickListener: ((TeamModel.TeamData?) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }


}