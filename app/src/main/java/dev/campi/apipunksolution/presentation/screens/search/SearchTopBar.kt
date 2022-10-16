package dev.campi.apipunksolution.presentation.screens.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import dev.campi.apipunk.domain.accesories.BeerFilter
import dev.campi.apipunksolution.R
import dev.campi.apipunksolution.ui.theme.STANDARD_ALPHA
import dev.campi.apipunksolution.ui.theme.TOP_APP_BAR_HEIGHT
import dev.campi.apipunksolution.ui.theme.topAppBarBackgroundColor
import dev.campi.apipunksolution.ui.theme.topAppBarContentColor
import dev.campi.apipunksolution.usecases.allBeerFilters

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onFilterSelected: (BeerFilter) -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onFilterSelected = onFilterSelected
    )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onFilterSelected: (BeerFilter) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    text = stringResource(R.string.search_a_beer),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                Row {
                    IconButton(
                        onClick = {
                            onTextChange("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colors.topAppBarContentColor.copy(alpha = STANDARD_ALPHA)
                        )
                    }
                    IconButton(
                        onClick = {
                            expanded = !expanded
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_filter_list_24),
                            contentDescription = "",
                            tint = MaterialTheme.colors.topAppBarContentColor.copy(alpha = STANDARD_ALPHA)
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            allBeerFilters.forEach { filter ->
                                DropdownMenuItem(
                                    onClick = {
                                        expanded = false
                                        onFilterSelected(filter)
                                    }
                                ) {
                                    Text(text = filter.name)
                                }
                            }
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Default
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            )
        )
    }
}

@Composable
@Preview
fun SearchWidgetPreview() {
    SearchWidget(
        text = "",
        onTextChange = {},
        onSearchClicked = {},
        onFilterSelected = {}
    )
}