
package com.example.expandableList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandableList.data.User
import com.example.expandableList.data.Users
import com.example.expandableList.ui.theme.ExpandableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandableListTheme {
                ExpandableListApp()
            }
        }
    }
}

@Composable
fun ExpandableListApp() {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(Users) {
                UserItem(User = it)
            }
        }
    }
}

@Composable
fun UserItem(User: User, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = if (expanded) Color.Green
    else MaterialTheme.colors.surface,
    )

    Card(
        elevation = 4.dp,
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            .background(color = color)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                UserIcon(User.imageResourceId)
                UserInformation(User.name, User.age)
                Spacer(Modifier.weight(1f))
                UserItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                UserHobby(User.hobbies)
            }
        }
    }
}


@Composable
private fun UserItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(R.string.expand_button_content_description),
        )
    }
}


@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop,
            painter = painterResource(android.R.drawable.ic_menu_save),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h2
        )
    }
}


@Composable
fun UserIcon(@DrawableRes UserIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(96.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(25)),
        contentScale = ContentScale.Crop,
        painter = painterResource(android.R.drawable.ic_menu_myplaces),
        contentDescription = null
    )
}

@Composable
fun UserInformation(@StringRes UserName: Int, UserAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(UserName),
            style = MaterialTheme.typography.h2,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(R.string.years_old, UserAge),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun UserHobby(@StringRes UserHobby: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(UserHobby),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun Preview() {
    ExpandableListTheme(darkTheme = false) {
        ExpandableListApp()
    }
}

@Preview
@Composable
fun DarkThemePreview() {
    ExpandableListTheme(darkTheme = true) {
        ExpandableListApp()
    }
}
