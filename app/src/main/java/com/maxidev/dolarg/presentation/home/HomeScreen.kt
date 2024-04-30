package com.maxidev.dolarg.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxidev.dolarg.domain.model.Dollars
import com.maxidev.dolarg.presentation.components.StatusScreen
import com.maxidev.dolarg.presentation.components.TopBarItem
import com.maxidev.dolarg.utils.DateTimeUtils

@Composable
fun HomeScreen(
    viewModel: DollarsViewModel = viewModel()
) {
    val state by viewModel.remoteStatus.collectAsStateWithLifecycle()

    ResponseState(status = state)
}

@Composable
private fun ResponseState(status: RemoteStatus) {

    when (status) {
        is RemoteStatus.Error -> StatusScreen(status = "Connection fail.")
        RemoteStatus.Loading -> StatusScreen(status = "Loading...")
        is RemoteStatus.Success -> ContentItem(model = status.onSuccess)
    }
}

@Composable
private fun ContentItem(
    model: List<Dollars>
) {
    val lazyState = rememberLazyListState()
    val rememberModel = remember(model) { model }

    Scaffold(
        topBar = {
            TopBarItem()
        },
        bottomBar = {
            DateTimeItem(dateTime = DateTimeUtils.formattedDate)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            state = lazyState,
            contentPadding = paddingValues
        ) {
            items(
                items = rememberModel,
                key = { it.name!! },
                contentType = { it }
            ) { data ->
                InformationItem(
                    name = data.name,
                    buy = data.buy.toString(),
                    sale = data.sale.toString()
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun InformationItem(
    name: String?,
    buy: String?,
    sale: String?
) {
    val annotatedStringBuy = buildStringWithStyle(text = "Compra", param = buy ?: "")
    val annotatedStringSale = buildStringWithStyle(text = "Venta", param = sale ?: "")
    val replaceName = name?.replace("Contado con liquidación", "CCL")

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp),
            shape = RoundedCornerShape(5),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                Text(
                    text = replaceName ?: "",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = annotatedStringBuy
                )
                Text(
                    text = annotatedStringSale
                )
            }
        }
    }
}

@Composable
private fun DateTimeItem(
    dateTime: String
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Last update: $dateTime",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun buildStringWithStyle(
    text: String,
    param: String
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
            withStyle(
                SpanStyle(
                    fontSize = 18.sp
                )
            ) { append("$text\n") }
            withStyle(
                SpanStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            ) { append(param) }
        }
    }
}