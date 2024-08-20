package com.example.e_ticaret.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.e_ticaret.R
import com.example.e_ticaret.domain.model.ProductResponseItemDb

@Composable
fun ShoppingCartComponent(
    productResponseItemDb: ProductResponseItemDb,
    onIncreaseQuantity: (ProductResponseItemDb) -> Unit,
    onDecreaseQuantity: (ProductResponseItemDb) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val painter = rememberAsyncImagePainter(model = productResponseItemDb.url)
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = productResponseItemDb.name,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = productResponseItemDb.price,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = Color.Black
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onDecreaseQuantity(productResponseItemDb) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Decrease Quantity"
                        )
                    }
                    Text(
                        text = "Adet: ${productResponseItemDb.quantity}",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = Color.Black
                        )
                    )
                    IconButton(onClick = { onIncreaseQuantity(productResponseItemDb) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Increase Quantity"
                        )
                    }
                }
            }
        }
    }
}
