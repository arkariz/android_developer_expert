package com.arrkariz.capstoneprojectmade.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.arrkariz.capstoneprojectmade.R
import com.arrkariz.capstoneprojectmade.databinding.ActivityDetailGameBinding
import com.arrkariz.core.data.source.Resource
import com.arrkariz.core.domain.model.Game
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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

        val gameId = intent.getIntExtra(EXTRA_DATA, 0)
        binding.apply {

            progressBar.visibility = View.VISIBLE
            if (gameId != 0) {
                detailGameViewModel.getDetailGame(gameId)
                    .observe(this@DetailGameActivity, { game ->
                        if (game != null) {
                            when (game) {
                                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                                is Resource.Success -> {
                                    val input = game.data?.get(0)

                                    title.text = input?.name
                                    desc.text = input?.desc
                                    rating.text = input?.rating.toString()
                                    released.text = input?.released

                                    Glide.with(this@DetailGameActivity)
                                        .load(input?.background_image)
                                        .centerCrop()
                                        .into(poster)

                                    var statusFavorite = input?.isFavorite
                                    setStatusFavorite(statusFavorite!!)
                                    binding.fab.setOnClickListener {
                                        statusFavorite = !statusFavorite!!
                                        detailGameViewModel.setFavoriteGame(
                                            input!!,
                                            statusFavorite!!
                                        )
                                        setStatusFavorite(statusFavorite!!)
                                    }

                                    binding.progressBar.visibility = View.GONE
                                }
                                is Resource.Error -> {
                                    binding.progressBar.visibility = View.GONE
                                    binding.viewError.root.visibility = View.VISIBLE
                                    binding.viewError.tvError.text =
                                        game.message ?: getString(R.string.something_wrong)
                                }
                            }
                        }
                    })
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