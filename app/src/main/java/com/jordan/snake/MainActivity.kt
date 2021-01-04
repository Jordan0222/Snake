package com.jordan.snake

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val snakeViewModel = ViewModelProvider(this).get(SnakeViewModel::class.java)
        snakeViewModel.body.observe(this, Observer {
            main_game_view.snakeBody = it
            main_game_view.invalidate()
        })

        snakeViewModel.gameState.observe(this, Observer {
            if (it == GameState.GAME_OVER) {
                AlertDialog.Builder(this@MainActivity)
                        .setTitle("Game")
                        .setMessage("Game Over")
                        .setPositiveButton("OK", null)
                        .show()
            }
        })

        snakeViewModel.apple.observe(this, Observer {
            main_game_view.apple = it
            main_game_view.invalidate()
        })

        snakeViewModel.score.observe(this, Observer {

        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        snakeViewModel.start()

        main_top.setOnClickListener { snakeViewModel.move(Direction.TOP) }
        main_left.setOnClickListener { snakeViewModel.move(Direction.LEFT) }
        main_right.setOnClickListener { snakeViewModel.move(Direction.RIGHT) }
        main_down.setOnClickListener { snakeViewModel.move(Direction.DOWN) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}