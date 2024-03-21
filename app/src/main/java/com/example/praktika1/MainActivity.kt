package com.example.praktika1

import adapter.PostsAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktika1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val viewModel: PostViewModel by viewModels()
            val adapter = PostsAdapter ({
                viewModel.likeById(it.id)},
                {
                    viewModel.shareByid(it.id)
                })
            binding.list.adapter = adapter
            viewModel.data.observe(this) { posts ->
                adapter.submitList(posts)
            }
        }
    }

