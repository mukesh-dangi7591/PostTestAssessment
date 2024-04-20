package com.posts.test

import PostAdapter
import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.posts.test.activity.fragment.DetailedFragment
import com.posts.test.databinding.ActivityMainBinding
import com.posts.test.models.data_classes.Data
import com.posts.test.network_services.ResponseData
import com.posts.test.view_models.PostsViewModel
import com.posts.test.view_models.view_model_factory.PostViewModelFactory
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
  lateinit var postsViewModel: PostsViewModel
    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var loadingDialog: Dialog
    private var currentPage = 1
    private val itemLimit = 10
    private var postListSize by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        val repository = (application as PostApplication).postRepository


        postsViewModel = ViewModelProvider(
            this,
            PostViewModelFactory(repository)
        ).get(PostsViewModel::class.java)

        loadingDialog = Dialog(this)
        loadingDialog.setContentView(ProgressBar(this))


        adapter = PostAdapter {
                post -> onPostClicked(post)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        postsViewModel.postsData.observe(this, Observer { posts ->
          when(posts){
              is ResponseData.Loading-> {
                  loadingDialog.show()
              }
              is ResponseData.Success-> {
                  loadingDialog.cancel()
                  posts.data?.let {
                      postListSize = posts.data!!.data.size
                    //  print("postTitle:- "+ posts.data!!.data.size.toString())
                      //Toast.makeText(this@MainActivity,posts.data!!.data!!.size.toString(),Toast.LENGTH_SHORT).show()

                      adapter.updatePosts(it.data)
                  }
              }
              is ResponseData.Error-> {
                  loadingDialog.cancel()
              }
          }
        })

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {


                    if (postListSize<itemLimit){

                    }else{
                        loadingDialog.show()
                        currentPage++
                        postsViewModel.getPostsData(currentPage, itemLimit)
                    }

                }
            }
        })
        loadingDialog.show()
        postsViewModel.getPostsData(currentPage,itemLimit)
    }
    private fun onPostClicked(post: Data) {
        val id = post.id
        val fName = post.first_name
        val lName = post.last_name
        val email = post.email

        val fragment = DetailedFragment.newInstance(id.toString(),fName,lName,email)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}