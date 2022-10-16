package dev.campi.apipunksolution.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.data.model.mappers.toObjectOrNull
import dev.campi.apipunksolution.ui.theme.*
import org.intellij.lang.annotations.Language

@Composable
fun BeerItem(
    beerType: BeerType,
    beerSelected: (BeerType) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MEDIUM_PADDING,
                vertical = HALF_MEDIUM_PADDING
            )
            .height(BEER_ITEM_HEIGHT)
            .clickable {
                beerSelected(beerType)
            },
        elevation = CARD_ITEM_ELEVATION,
        shape = RoundedCornerShape(SMALL_PADDING)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White),
            contentAlignment = Alignment.TopEnd
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .padding(MEDIUM_PADDING)
                            .weight(7f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = LARGE_PADDING),
                            text = beerType.description.orEmpty(),
                            color = TextBlackAplhaMedium,
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    AsyncImage(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxHeight(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data = beerType.imageUrl)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Inside
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.40f)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(ItemBeerHeader, Color.White.copy(alpha = 0.1f))
                        ),
                    )

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = MEDIUM_PADDING, start = MEDIUM_PADDING, bottom = SMALL_PADDING),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = beerType.name.orEmpty(),
                        color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    listOf(
                        "${beerType.abv} º Alcohol",
                        "Bitterness ${beerType.ibu} i.b.u."
                    ).forEach { textValue ->
                        Text(
                            text = textValue,
                            color = Color.White.copy(alpha = ContentAlpha.high),
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun BeerItemPreview() {
    @Language("JSON")
    val mock = """
    {"id":56,"name":"Black Eyed King Imp","tagline":"Barrel-Aged Prototype Cocoa Psycho.","first_brewed":"11/2012","description":"An early Cocoa Psycho recipe that we loved, but didn't fit what we were looking for. We locked this chocolate coffee stout away in barrels for two years, imparting toasted marshmallow, spicy vanilla, molasses and boozy warmth.","image_url":"https://images.punkapi.com/v2/56.png","abv":9.5,"ibu":85.0,"target_fg":1022.0,"target_og":1095.0,"ebc":250.0,"srm":125.0,"ph":4.4,"attenuation_level":76.8,"volume":{"value":20.0,"unit":"litres"},"boil_volume":{"value":25.0,"unit":"litres"},"method":{"mash_temp":[{"temp":{"value":65.0,"unit":"celsius"},"duration":50.0}],"fermentation":{"temp":{"value":18.0,"unit":"celsius"}},"twist":"Coffee Beans: 12.5g at end, Lactose: 125g"},"ingredients":{"malt":[{"name":"Extra Pale - Spring Blend","amount":{"value":6.25,"unit":"kilograms"}},{"name":"Wheat","amount":{"value":1.25,"unit":"kilograms"}},{"name":"Caramalt","amount":{"value":1.25,"unit":"kilograms"}},{"name":"Crystal","amount":{"value":1.56,"unit":"kilograms"}},{"name":"Dark Crystal","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Amber","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Brown","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Chocolate","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Roasted Barley","amount":{"value":0.31,"unit":"kilograms"}}],"hops":[{"name":"Magnum","amount":{"value":62.5,"unit":"grams"},"add":"start","attribute":"bitter"},{"name":"Willamette","amount":{"value":31.25,"unit":"grams"},"add":"end","attribute":"flavour"},{"name":"First Gold","amount":{"value":31.25,"unit":"grams"},"add":"end","attribute":"flavour"}],"yeast":"Wyeast 1272 - American Ale II™"},"food_pairing":["Beef chilli made with cocoa powder","Dark chocolate covered bacon","Rich espresso tiramisu"],"brewers_tips":"There is a huge amount of roasted malts in this grist. Be careful not to pulverise the malt into powder during the milling process.","contributed_by":"Sam Mason <samjbmason>"}
""".trimIndent().toObjectOrNull<BeerType>()
    BeerItem(
        beerType = mock!!
    ){}
}
