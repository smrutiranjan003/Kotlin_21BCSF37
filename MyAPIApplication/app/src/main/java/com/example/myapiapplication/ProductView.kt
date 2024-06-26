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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductView(products: List<Product>) {
    MyAPIApplicationTheme {
        MyApp {
            ProductList(products = products)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
                            color = OnPrimary, // Use OnPrimary color from theme
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
            containerColor = LightTeal // Set the container color to LightTeal
        ),
        shape = RoundedCornerShape(16.dp) // Optional: round corners of the card
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
                    .aspectRatio(1f) // Make the Box square
                    .clip(RoundedCornerShape(8.dp)) // Clip to rounded corners
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxSize() // Make the image fill the Box
                        .graphicsLayer { shadowElevation = 4.dp.toPx() }, // Add shadow
                    contentScale = ContentScale.Crop // Scale the image to fill the Box
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
