package com.arrkariz.capstoneprojectmade.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.arrkariz.capstoneprojectmade.R
import com.arrkariz.capstoneprojectmade.databinding.ActivityDetailGameBinding
import com.arrkariz.core.domain.model.Game
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailGameViewModel: DetailGameViewModel by viewModel()
    private lateinit var binding: ActivityDetailGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        detailGame?.let {
            binding.apply {

                progressBar.visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.Main) {
                    detailGameViewModel.setDescGame(it.id).observe(this@DetailGameActivity, {
                        desc.text = it.desc
                        progressBar.visibility = View.GONE
                    })
                }

                title.text = it.name
                rating.text = it.rating.toString()
                released.text = it.released

                Glide.with(this@DetailGameActivity)
                    .load(it.background_image)
                    .into(poster)
            }

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteTourism(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_border))
        }
    }
}