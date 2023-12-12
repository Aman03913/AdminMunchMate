package com.example.adminmunchmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminmunchmate.adapter.AddItemAdapter
import com.example.adminmunchmate.databinding.ActivityAddItemBinding
import com.example.adminmunchmate.databinding.ActivityAllItemBinding

class AllItemActivity : AppCompatActivity() {
    private val binding:ActivityAllItemBinding by lazy{
        ActivityAllItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val menuFoodName= listOf("Burger","Sandwich","MOMO","Sandwich","Chowmin")
        val menuItemPrice= listOf("100Rs","50Rs","120Rs","150Rs","110Rs")
        val menuImage= listOf(R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5
        )
        binding.backButton.setOnClickListener {
            finish()
        }
        val adapter=AddItemAdapter(ArrayList(menuFoodName),ArrayList(menuItemPrice),ArrayList(menuImage))
        binding.MenuRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter=adapter
    }
}