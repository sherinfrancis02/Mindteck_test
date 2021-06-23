package com.example.android.mindteck

import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.android.mindteck.data.Movie
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


class MainActivity : AppCompatActivity() {

    var sampleImages = intArrayOf(
        R.drawable.images1,
        R.drawable.images2,
        R.drawable.images3
    )
    private lateinit var customAdapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var carouselView: CarouselView
    private lateinit var searchView: SearchView
    private lateinit var movieList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        carouselView = findViewById(R.id.carouselView)
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        movieList = ArrayList<Movie>()
        customAdapter = CustomAdapter()
        recyclerView.adapter = customAdapter
        prepareCarousel()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })


    }

    fun prepareCarousel(){
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(imageListener);
        prepareActionList()
        carouselView.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // Do your desired action
                searchView.setQuery("", false)
                when(position){
                    0 -> prepareActionList()
                    1 -> prepareDramaList()
                    2 -> prepareThrillerList()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            imageView.setImageResource(sampleImages[position])

        }
    }


    fun prepareActionList(){
        movieList.clear()
        //adding some dummy data to the list
        movieList.add(Movie("inception", ""))
        movieList.add(Movie("The Raid", ""))
        movieList.add(Movie("Edge Of Tomorrow", ""))
        movieList.add(Movie("Tenet", ""))
        movieList.add(Movie("Avengers", ""))
        movieList.add(Movie("inception111", ""))
        movieList.add(Movie("The Raid111", ""))
        movieList.add(Movie("Edge Of Tomorrow111", ""))
        movieList.add(Movie("Tenet111", ""))
        movieList.add(Movie("Avengers111", ""))
        movieList.add(Movie("inception555", ""))
        movieList.add(Movie("The Raid555", ""))
        movieList.add(Movie("Edge Of Tomorrow555", ""))
        movieList.add(Movie("Tenet555", ""))
        movieList.add(Movie("Avengers555", ""))
        customAdapter.setMovieList(movieList)
        customAdapter.notifyDataSetChanged()
    }
    fun prepareDramaList(){
        movieList.clear()
        //adding some dummy data to the list
        movieList.add(Movie("Titanic", ""))
        movieList.add(Movie("Modern Times", ""))
        movieList.add(Movie("Roman Holiday", ""))
        movieList.add(Movie("Gladiator", ""))
        movieList.add(Movie("interstellar", ""))
        movieList.add(Movie("Titanic111", ""))
        movieList.add(Movie("Modern Times111", ""))
        movieList.add(Movie("Roman Holiday111", ""))
        movieList.add(Movie("Gladiator111", ""))
        movieList.add(Movie("interstellar111", ""))
        movieList.add(Movie("Titanic555", ""))
        movieList.add(Movie("Modern Times555", ""))
        movieList.add(Movie("Roman Holiday555", ""))
        movieList.add(Movie("Gladiator555", ""))
        movieList.add(Movie("interstellar555", ""))
        customAdapter.setMovieList(movieList)
        customAdapter.notifyDataSetChanged()
    }
    fun prepareThrillerList(){
        movieList.clear()
        //adding some dummy data to the list
        movieList.add(Movie("Parasite", ""))
        movieList.add(Movie("Psycho", ""))
        movieList.add(Movie("The Conjuring", ""))
        movieList.add(Movie("Annabelle", ""))
        movieList.add(Movie("Parasite111", ""))
        movieList.add(Movie("Psycho111", ""))
        movieList.add(Movie("The Conjuring111", ""))
        movieList.add(Movie("Annabelle111", ""))
        movieList.add(Movie("Parasite555", ""))
        movieList.add(Movie("Psycho555", ""))
        movieList.add(Movie("The Conjuring555", ""))
        movieList.add(Movie("Annabelle555", ""))
        customAdapter.setMovieList(movieList)
        customAdapter.notifyDataSetChanged()
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filterList: ArrayList<Movie> = ArrayList()

        // running a for loop to compare elements.
        for (item in movieList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filterList.add(item)
            }
        }
        if (filterList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            customAdapter.filterList(filterList)
        }
    }

}