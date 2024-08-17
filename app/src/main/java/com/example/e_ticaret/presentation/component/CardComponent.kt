package com.example.e_ticaret.presentation.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.e_ticaret.domain.model.ProductResponseItem
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import com.example.e_ticaret.presentation.product_screen.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardComponent(product: ProductResponseItem, productViewModel: ProductViewModel) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            val painter = rememberAsyncImagePainter(model = product.url)
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = product.price,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Button(
                onClick = {
                    val productItem = ProductResponseItemDb(0,product.name,product.price,product.price)
                    productViewModel.addProductDb(productItem)
                    Log.d("added",productItem.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE2771F)
                )
            ) {
                Text(
                    text = "Sepete Ekle",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color.White
                    )
                )
            }
        }
    }
}