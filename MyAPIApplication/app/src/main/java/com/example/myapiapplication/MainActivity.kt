package com.example.myapiapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(ProductRepository(RetrofitClient.service))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val products by productViewModel.products.observeAsState(emptyList())
                ProductList(products)
            }
        }
    }
}
