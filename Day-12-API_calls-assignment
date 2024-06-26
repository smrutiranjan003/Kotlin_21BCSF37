Here's a revised version of the provided project setup, formatted as a README file. The code has been adjusted for better clarity and usability.

# My API Application

This project demonstrates how to create an Android application using Jetpack Compose, Retrofit for networking, Gson for JSON parsing, Coil for image loading, and LiveData for observing data changes.

## Setup

### Start a new Android Studio project with a Compose activity.

### Add dependencies to your `build.gradle` file

```kotlin
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.0")
}
```

These dependencies include Retrofit for networking, Gson converter for JSON parsing, Coil-Compose for image loading in Jetpack Compose, and LiveData integration for observing data changes.

## Data Models

Define a Kotlin data class `Product` to represent the structure of product data fetched from the API. The data model should match the structure of the API response.

```kotlin
// Product.kt

package com.example.myapiapplication

data class Rating(
    val rate: Double,
    val count: Int
)

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)
```

## Networking

### Setting up Retrofit to communicate with APIs

Define API endpoints using an interface annotated with Retrofit annotations. Define a method `getProducts()` to fetch a list of products.

### Configure Retrofit with RetrofitClient

Create a singleton `RetrofitClient` that builds a Retrofit instance with a base URL and Gson converter factory.

```kotlin
// RetrofitClient.kt

package com.example.myapiapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://fakestoreapi.com/"

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

object RetrofitClient {
    val service: ProductService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }
}
```

## Repository

Create a repository that acts as an intermediary between data sources and the rest of the application.

```kotlin
// ProductRepository.kt

package com.example.myapiapplication

class ProductRepository(private val apiService: ProductService) {
    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
}
```

## ViewModel

Implement `ProductViewModel` to manage UI-related data. Use `ViewModel` and `LiveData` to hold and update the list of products fetched from the repository.

```kotlin
// ProductViewModel.kt

package com.example.myapiapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val productList = repository.getProducts()
                _products.postValue(productList)
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}

class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```

## Design UI Components with Jetpack Compose

Implement Jetpack Compose functions for UI components like `MyApp`, `ProductList`, and `ProductCard`.

```kotlin
// ProductView.kt

package com.example.myapiapplication

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapiapplication.ui.theme.MyAPIApplicationTheme
import com.example.myapiapplication.ui.theme.Green
import com.example.myapiapplication.ui.theme.LightTeal
import com.example.myapiapplication.ui.theme.OnPrimary
import com.example.myapiapplication.ui.theme.OnSecondary

@Composable
fun ProductView(products: List<Product>) {
    MyAPIApplicationTheme {
        MyApp {
            ProductList(products = products)
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Products",
                            color = OnPrimary,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green
                )
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    )
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(animationSpec = spring()),
        colors = CardDefaults.cardColors(
            containerColor = LightTeal
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { shadowElevation = 4.dp.toPx() },
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(2f)) {
                Text(text = product.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.Bold
                )
                if (expanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product.description)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rating: ${product.rating.rate} (${product.rating.count} reviews)",
                        color = OnSecondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(onClick = { /* Handle add to cart action here */ }) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Add to Cart")
            }
        }
    }
}
```

## MainActivity

Set up the main activity to use Jetpack Compose.

```kotlin
// MainActivity.kt

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
```

### OUTPUT

![Output Image](../screenshots/list_products_api.png)

### [Check source code of the application](https://github.com/smrutiranjan003/Kotlin_21BCSF37/blob/android/day_12/API_calls/MyAPIApplication)
