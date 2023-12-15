package com.example.adminmunchmate

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminmunchmate.databinding.ActivityAddItemBinding
import com.example.adminmunchmate.model.AllMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class AddItemActivity : AppCompatActivity() {
    //Food Item Details
    private lateinit var foodName:String
    private lateinit var foodPrice:String
    private lateinit var foodDescription:String
    private lateinit var foodIngredient:String
    private var foodImageuri: Uri?=null
    //firebase
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseDatabase
    private val binding:ActivityAddItemBinding by lazy{
        ActivityAddItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        binding.AddItemButton.setOnClickListener {
            foodName=binding.enterFoodName.text.toString().trim()
            foodPrice=binding.enterFoodPrice.text.toString().trim()
            foodDescription=binding.description.text.toString().trim()
            foodIngredient=binding.ingredient.text.toString().trim()
            if(!(foodName.isBlank()||foodPrice.isBlank()||foodDescription.isBlank()||foodIngredient.isBlank())){
                uploadData()
                Toast.makeText(this,"Item Added Successfully",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Fill all the Details",Toast.LENGTH_SHORT).show()
            }
        }
        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }


        binding.backButton.setOnClickListener {
            finish()
        }

        }

    private fun uploadData() {
        //get a refrence to the menu node in the database
        val menuRef=database.getReference("menu")
        val newItemKey=menuRef.push().key
        if(foodImageuri!=null){
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef=storageRef.child("menu_image${newItemKey}.jpg")
            val uploadTask=imageRef.putFile(foodImageuri!!)
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl->
                    val newItem=AllMenu(
                        foodName=foodName,
                        foodPrice=foodPrice,
                        foodDescription=foodDescription,
                        foodIngredient=foodIngredient,
                        foodImage = downloadUrl.toString()
                    )
                    newItemKey?.let{
                        key ->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this,"Data Uploaded Succesfully",Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this,"data uploaded failed",Toast.LENGTH_SHORT).show()
                            }
                    }
                }

            }.addOnFailureListener {
                Toast.makeText(this,"Image Upload Failed",Toast.LENGTH_SHORT).show()
            }

        }else {
            Toast.makeText(this,"Please Select an Image",Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImage=registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        if(uri!=null){
            binding.selectedImage.setImageURI(uri)
            foodImageuri=uri
        }
    }
}