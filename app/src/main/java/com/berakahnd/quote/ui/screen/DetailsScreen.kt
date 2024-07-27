package com.berakahnd.quote.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berakahnd.quote.data.local.QuoteItem
import com.berakahnd.quote.ui.theme.QuoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    listQuoteItem : List<QuoteItem>,
    onDeleteQuoteClick : (QuoteItem) -> Unit,
    onNavigateBackClick : () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar( title = {Text(text = "Quote Bible")}, navigationIcon = {
                IconButton(onClick = { onNavigateBackClick() }) {
                    Icon(modifier = Modifier, imageVector = Icons.Default.ArrowBack, contentDescription = null)

                }
            })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement =  Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(listQuoteItem){ quoteItem ->
                cardItem(
                    quoteItem = quoteItem,
                    onDeletelick  = { quoteItem ->
                        onDeleteQuoteClick(quoteItem)
                    }
                )
            }
        }
    }
}

@Composable
fun cardItem(
    quoteItem: QuoteItem,
    onDeletelick : (QuoteItem) -> Unit
){
    var checkOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Icon(modifier = Modifier, imageVector = Icons.Default.MenuBook, contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary)
                Text(text = quoteItem.reference, fontWeight = FontWeight.Bold)
                IconButton(onClick = { onDeletelick(quoteItem) } ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
            IconButton(
                onClick = {
                    checkOpen = !checkOpen
                }) {
                Icon(imageVector = if(checkOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown, contentDescription = null)
            }
            if(checkOpen){
                Row (modifier = Modifier.fillMaxWidth()){
                    Text(modifier = Modifier.padding(horizontal = 16.dp),text = quoteItem.text)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    QuoteTheme {
        val quoteItem = QuoteItem(
            reference = "Isaiah 7:13",
            text = "He said, “Listen now, house of David. Is it not enough for you to try the patience of men, that you will try the patience of my God also?\n"
        )
        cardItem(quoteItem = quoteItem, onDeletelick = {})
    }
}