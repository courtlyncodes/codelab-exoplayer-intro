/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
* limitations under the License.
 */
package com.example.exoplayer

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import com.example.exoplayer.databinding.ActivityPlayerBinding

/**
 * A fullscreen activity to play audio or video streams.
 */
class PlayerActivity : AppCompatActivity() {

  private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
    ActivityPlayerBinding.inflate(layoutInflater)
  }



  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(viewBinding.root)
  }

  private var player: ExoPlayer? = null

  private fun initializePlayer() {
    player = ExoPlayer.Builder(this)
      .build()
      .also { exoplayer ->
        viewBinding.videoView.player = exoplayer

        val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
        exoplayer.setMediaItem(mediaItem)
        exoplayer.playWhenReady = true
        exoplayer.seekTo(0, 0)
        exoplayer.prepare()
      }
  }

  @OptIn(UnstableApi::class)
  public override fun onStart() {
    super.onStart()
    if(Util.SDK_INT > 23) {
      initializePlayer()
    }
  }

  override fun onStop() {
    super.onStop()
  }
  override fun onPause() {
    super.onPause()
  }

  @OptIn(UnstableApi::class)
  public override fun onResume() {
    super.onResume()
    if(Util.SDK_INT <= 23 || player == null) {
      initializePlayer()
    }
  }

  private fun hideSystemUi() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
      
    }
  }
}