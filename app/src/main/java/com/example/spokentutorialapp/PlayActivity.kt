package com.example.spokentutorialapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class PlayActivity : YouTubeBaseActivity() {

    private lateinit var ytPlayer : YouTubePlayerView
    lateinit var ytPlayerInit : YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        var key = "AIzaSyBjfGGONycGEDRfdEQMJVxrGXgv74bnKt8"
        var vID = intent.getStringExtra("VIDEO_ID")
        var vTitle = intent.getStringExtra("VIDEO_TITLE")
        ytPlayer = findViewById<YouTubePlayerView>(R.id.youtube_playerview)
        var titleView = findViewById<TextView>(R.id.titleView)
        var titleView2 = findViewById<TextView>(R.id.titleView2)
        titleView.text=vTitle
        titleView2.text="YOUTUBE PLAYER"
        ytPlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                p1?.loadVideo(vID)
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext, "Failure!", Toast.LENGTH_SHORT).show()
            }

        }
        ytPlayer.initialize(key, ytPlayerInit)
    }
}