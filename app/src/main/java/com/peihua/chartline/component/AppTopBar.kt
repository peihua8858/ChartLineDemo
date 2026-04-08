package com.peihua.chartline.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peihua8858.compose.tools.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    title: String,
    elevation: Dp = 1.dp,
    navigateUp: () -> Unit = { },
    navigationIcon: @Composable () -> Unit = {
        NavigationIcon(navigateUp = navigateUp)
    },
    actions: @Composable RowScope.() -> Unit = {},
    hostState: SnackbarHostState = remember { snackbarHostState },
    content: @Composable () -> Unit,
) {
    Toolbar(title, modifier, colors, elevation, navigationIcon, actions, hostState, content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    title: @Composable () -> Unit,
    elevation: Dp = 1.dp,
    navigateUp: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
        NavigationIcon(navigateUp = navigateUp)
    },
    actions: @Composable RowScope.() -> Unit = {},
    hostState: SnackbarHostState = remember { snackbarHostState },
    content: @Composable () -> Unit,
) {
    Toolbar(
        modifier = modifier, colors = colors, title = title,
        elevation = elevation, navigationIcon = navigationIcon,
        actions = actions, hostState = hostState, content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    elevation: Dp = 0.dp,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    hostState: SnackbarHostState = remember { snackbarHostState },
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                elevation = elevation,
                title = title,
                colors = colors,
                navigationIcon = navigationIcon,
                actions = actions
            )
        }, snackbarHost = { SnackbarHost(hostState) }) {
        Box(Modifier
            .padding(it)
            .fillMaxSize()) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    elevation: Dp = 0.dp,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    hostState: SnackbarHostState = remember { snackbarHostState },
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                elevation = elevation,
                title = title,
                colors = colors,
                navigationIcon = navigationIcon,
                actions = actions
            )
        }, snackbarHost = { SnackbarHost(hostState) }) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

internal val snackbarHostState = SnackbarHostState()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val typography = MaterialTheme.typography
    AppTopBar(modifier, colors = colors, elevation = elevation, title = {
        Box(modifier = Modifier.fillMaxWidth()) {
            ScaleText(
                style = typography.titleMedium,
                text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally) // 水平居中
                    .align(Alignment.Center),
                fontSize = 18.sp,
            )
        }
    }, navigationIcon = navigationIcon, actions = actions)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    title: @Composable () -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
        modifier = modifier.surface(0.dp, elevation = elevation),
        colors = colors
    )
}