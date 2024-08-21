package com.example.e_ticaret.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.e_ticaret.R
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import com.example.e_ticaret.presentation.shopping_cart_screen.ShoppingCartViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartComponent(
    productResponseItemDb: ProductResponseItemDb,
    onIncreaseQuantity: (ProductResponseItemDb) -> Unit,
    onDecreaseQuantity: (ProductResponseItemDb) -> Unit,
    onDeleteProduct: (ProductResponseItemDb) -> Unit
) {
    // Ürün kaydırıldığında silinmesini sağlayan bir durum objesi oluşturuyoruz.
    val dismissState = rememberDismissState(
        confirmStateChange = {
            // Ürün sadece sağa kaydırıldığında (DismissedToEnd)
            if (it == DismissValue.DismissedToEnd) {
                onDeleteProduct(productResponseItemDb) // Ürün silme fonksiyonunu çağır
            }
           true
        }
    )

    // Ürünün sağa kaydırılabilir olduğunu belirten bir kapsayıcı (container) oluşturuyoruz.
    SwipeToDismiss(
        state = dismissState,
        background = {
            val color = when (dismissState.dismissDirection) {
                DismissDirection.EndToStart -> Color.Red
                else -> Color.Transparent
            }

            // Eğer kaydırma işlemi aktif değilse arka planı temizle
            if (dismissState.currentValue == DismissValue.Default) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        directions = setOf(DismissDirection.EndToStart), // Ürünün sadece sağa kaydırılabilir olduğunu belirler
        dismissContent = {
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
                    val painter = rememberImagePainter(data = productResponseItemDb.url)

                    AsyncImage(
                        model = productResponseItemDb.url,
                        contentDescription = "",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Log.d("image", painter.toString())
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
    )
}
