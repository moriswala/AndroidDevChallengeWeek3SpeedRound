package com.example.androiddevchallenge.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.model.Item
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HorizontalListItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(192.dp, 56.dp)
    ) {

        Row(modifier = Modifier.clickable(onClick = { })) {
            Image(
                painter = painterResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(56.dp)
            )

            Box(contentAlignment = Alignment.Center){
                Text(
                    text = item.title,
                    modifier = modifier.fillMaxHeight(),
                    style = MaterialTheme.typography.h3,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}


@Preview
@Composable
fun PreviewHorizontalListItem() {
    val item = DemoDataProvider.item
    MyTheme {
        HorizontalListItem(item = item)
    }
}