package com.example.praktika1

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel(){
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareByid(id: Long) = repository.shareByid(id)
}