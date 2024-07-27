package com.berakahnd.quote.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berakahnd.quote.R
import com.berakahnd.quote.data.local.QuoteItem
import com.berakahnd.quote.ui.theme.QuoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    quoteItem: QuoteItem,
    onRemoteQuoteClick : () -> Unit,
    onShowListQuoteClick : () -> Unit
) {
    val height = 16.dp
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Quote Bible")
            }, actions = {
                IconButton(onClick = { onShowListQuoteClick() } ) {
                    Icon(imageVector = Icons.Default.List, contentDescription = null)
                }
            })
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), contentAlignment = Alignment.Center){
            Card(modifier = Modifier.padding(16.dp), onClick = { /*TODO*/ }) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    , verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(modifier = Modifier.size(32.dp), painter = painterResource(id = R.drawable.menu_book), contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.size(height))
                    Text(text = quoteItem.reference, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(height))
                    Text(text = quoteItem.text)
                    Spacer(modifier = Modifier.size(height))
                    Button(onClick = { onRemoteQuoteClick() } ) {
                        Text(text = "generate new verse")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    QuoteTheme {
        val quoteItem = QuoteItem(
            reference = "Isaiah 7:13",
            text = "He said, “Listen now, house of David. Is it not enough for you to try the patience of men, that you will try the patience of my God also?\n"
        )
        //MainScreen(quoteItem = quoteItem, onRemoteQuoteClick = {})
    }
}