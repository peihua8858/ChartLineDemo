package com.peihua.chartline.screen

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.peihua.chartline.component.Toolbar
import com.peihua.chartline.enums.Functions
import com.peihua8858.compose.tools.Items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Functions> FlowRowScreen(
    navController: NavController, title: String = "Chart Line", entries: List<T>,
    navigateUp: () -> Unit = {
        navController.popBackStack()
    },
) {
    Toolbar(
        title = title,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
        navigateUp = navigateUp
    ) {
        FlowRow(
            modifier = Modifier
                .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
            Items(entries) { item ->
                if (!item.isVisible) {
                    return@Items
                }
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                        .clickable {
                            navController.navigate(item.value)
                        }
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (item.drawableId != 0) {
                        AsyncImage(
                            modifier = Modifier.size(100.dp),
                            model = item.drawableId, contentDescription = item.nickName
                        )
                    }
                    Text(
                        text = item.nickName,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    )
                    if (item.description.isNotEmpty()) {
                        Text(
                            text = item.description,
                            fontSize = 10.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            modifier = Modifier
                                .widthIn(max = 170.dp)
                                .basicMarquee(repeatDelayMillis = 0)
                                .padding(start = 16.dp, end = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
