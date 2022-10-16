package dev.campi.apipunksolution.presentation.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.campi.apipunk.domain.model.beer_type.BeerType
import dev.campi.apipunksolution.data.model.mappers.toObjectOrNull
import dev.campi.apipunksolution.ui.theme.*
import dev.campi.apipunksolution.util.intString
import dev.campi.apipunksolution.util.orNotData
import dev.campi.apipunksolution.util.toViewIngredients
import org.intellij.lang.annotations.Language

@ExperimentalMaterialApi
@Composable
fun BeerDetailScreen(
    beer: BeerType,
    close: () -> Unit
) {
    BackHandler { close() }
    Column(
        modifier = Modifier.padding(horizontal = MEDIUM_PADDING)
    ) {
        Row(
            modifier = Modifier
                .padding(top = MEDIUM_PADDING, bottom = SMALL_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        close()
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.Black
            )
            Text(
                modifier = Modifier.padding(start = SMALL_PADDING),
                text = beer.name.orEmpty(),
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = SMALL_PADDING)
                .padding(horizontal = SMALL_PADDING)
                .align(alignment = Alignment.CenterHorizontally),
            text = beer.tagline.orEmpty(),
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Row(
            modifier = Modifier
                .fillMaxHeight(0.26f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(bottom = SMALL_PADDING)
                    .weight(1f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = beer.imageUrl)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(vertical = MEDIUM_PADDING),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Alcohol ${beer.abv}º")
                Text("Bitterness ${beer.ibu.intString} i.b.u.")
                Text("PH ${beer.ph.orNotData}")
                Text("Attenuation Level ${beer.attenuationLevel.orNotData}")
                Text("First brewed ${beer.firstBrewed}")
                Text("${beer.srm.orNotData} srm")
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            TextUnderDescription(beer.description.orEmpty(), paddingBotton = true)
            TextUnderDescription("Ingredients:", fontStyle = FontStyle.Italic)
            TextUnderDescription("Malt:", tabulation = 1)
            beer.ingredients?.malt?.forEach { malt ->
                TextUnderDescription(malt.toViewIngredients(), tabulation = 2)
            }
            TextUnderDescription("Hops:", tabulation = 1)
            beer.ingredients?.hops?.forEach { hop ->
                TextUnderDescription(hop.toViewIngredients(), tabulation = 2)
            }
            TextUnderDescription("Yeast:", tabulation = 1)
            TextUnderDescription(beer.ingredients?.yeast ?: "No data", tabulation = 2, paddingBotton = true)
            beer.foodPairing?.let { foodParingList ->
                if (foodParingList.isNotEmpty()) {
                    TextUnderDescription(value = "Food paring ...", fontStyle = FontStyle.Italic)
                    foodParingList.forEachIndexed { index, s ->
                        TextUnderDescription(value = "${index + 1}. $s", tabulation = 1)
                    }
                }
            }
            beer.brewersTips?.let { tips ->
                TextUnderDescription(value = "Brewers tips ...", fontStyle = FontStyle.Italic)
                TextUnderDescription(value = tips, tabulation = 1, paddingBotton = true)
            }
            beer.contributedBy?.let {
                TextUnderDescription(value = "Contribuited by", fontStyle = FontStyle.Italic)
                TextUnderDescription(value = it, tabulation = 1)
            }
        }
    }
}

@Composable
fun TextUnderDescription(
    value: String,
    tabulation: Int = 0,
    paddingBotton: Boolean = false,
    paddingTop: Boolean = false,
    fontStyle: FontStyle = FontStyle.Normal
) {
    val startPadding = when (tabulation) {
        0 -> ZERO_DP
        1 -> MEDIUM_PADDING
        else -> MEDIUM_PADDING * 2
    }
    val botton = if (paddingBotton) EXTRA_SMALL_PADDING else ZERO_DP
    val top = if (paddingTop) EXTRA_SMALL_PADDING else ZERO_DP
    Text(
        modifier = Modifier.padding(bottom = botton, start = startPadding, top = top),
        text = value,
        color = TextBlackAplhaMedium,
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        fontStyle = fontStyle
    )
}

@ExperimentalMaterialApi
@Preview(name = "Full Preview 2", showSystemUi = true)
@Composable
fun BeerDetailScreenPreview2() {
    @Language("JSON")
    val beer2 = """
       {"id":56,"name":"Black Eyed King Imp","tagline":"Barrel-Aged Prototype Cocoa Psycho.","first_brewed":"11/2012","description":"An early Cocoa Psycho recipe that we loved, but didn't fit what we were looking for. We locked this chocolate coffee stout away in barrels for two years, imparting toasted marshmallow, spicy vanilla, molasses and boozy warmth.","image_url":"https://images.punkapi.com/v2/56.png","abv":9.5,"ibu":85.0,"target_fg":1022.0,"target_og":1095.0,"ebc":250.0,"srm":125.0,"ph":4.4,"attenuation_level":76.8,"volume":{"value":20.0,"unit":"litres"},"boil_volume":{"value":25.0,"unit":"litres"},"method":{"mash_temp":[{"temp":{"value":65.0,"unit":"celsius"},"duration":50.0}],"fermentation":{"temp":{"value":18.0,"unit":"celsius"}},"twist":"Coffee Beans: 12.5g at end, Lactose: 125g"},"ingredients":{"malt":[{"name":"Extra Pale - Spring Blend","amount":{"value":6.25,"unit":"kilograms"}},{"name":"Wheat","amount":{"value":1.25,"unit":"kilograms"}},{"name":"Caramalt","amount":{"value":1.25,"unit":"kilograms"}},{"name":"Crystal","amount":{"value":1.56,"unit":"kilograms"}},{"name":"Dark Crystal","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Amber","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Brown","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Chocolate","amount":{"value":0.63,"unit":"kilograms"}},{"name":"Roasted Barley","amount":{"value":0.31,"unit":"kilograms"}}],"hops":[{"name":"Magnum","amount":{"value":62.5,"unit":"grams"},"add":"start","attribute":"bitter"},{"name":"Willamette","amount":{"value":31.25,"unit":"grams"},"add":"end","attribute":"flavour"},{"name":"First Gold","amount":{"value":31.25,"unit":"grams"},"add":"end","attribute":"flavour"}],"yeast":"Wyeast 1272 - American Ale II™"},"food_pairing":["Beef chilli made with cocoa powder","Dark chocolate covered bacon","Rich espresso tiramisu"],"brewers_tips":"There is a huge amount of roasted malts in this grist. Be careful not to pulverise the malt into powder during the milling process.","contributed_by":"Sam Mason <samjbmason>"} 
""".trimIndent().toObjectOrNull<BeerType>()!!
    BeerDetailScreen(beer = beer2) {
    }
}

@ExperimentalMaterialApi
@Preview(name = "Full Preview 1", showSystemUi = true)
@Composable
fun BeerDetailScreenPreview1() {
    @Language("JSON")
    val beer1 = """
  {
    "id": 1,
    "name": "Buzz  - crisp and bitter IPA brewed with English and American hops. A small batch",
    "tagline": "A Real Bitter Experience. - crisp and bitter IPA brewed with English and American hops. A small batch",
    "first_brewed": "09/2007",
    "description": "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
    "image_url": "https://images.punkapi.com/v2/keg.png",
    "abv": 4.5,
    "ibu": 60,
    "target_fg": 1010,
    "target_og": 1044,
    "ebc": 20,
    "srm": 10,
    "ph": 4.4,
    "attenuation_level": 75,
    "volume": {
      "value": 20,
      "unit": "litres"
    },
    "boil_volume": {
      "value": 25,
      "unit": "litres"
    },
    "method": {
      "mash_temp": [
        {
          "temp": {
            "value": 64,
            "unit": "celsius"
          },
          "duration": 75
        }
      ],
      "fermentation": {
        "temp": {
          "value": 19,
          "unit": "celsius"
        }
      },
      "twist": null
    },
    "ingredients": {
      "malt": [
        {
          "name": "Maris Otter Extra Pale",
          "amount": {
            "value": 3.3,
            "unit": "kilograms"
          }
        },
        {
          "name": "Caramalt",
          "amount": {
            "value": 0.2,
            "unit": "kilograms"
          }
        },
        {
          "name": "Munich",
          "amount": {
            "value": 0.4,
            "unit": "kilograms"
          }
        }
      ],
      "hops": [
        {
          "name": "Fuggles",
          "amount": {
            "value": 25,
            "unit": "grams"
          },
          "add": "start",
          "attribute": "bitter"
        },
        {
          "name": "First Gold",
          "amount": {
            "value": 25,
            "unit": "grams"
          },
          "add": "start",
          "attribute": "bitter"
        },
        {
          "name": "Fuggles",
          "amount": {
            "value": 37.5,
            "unit": "grams"
          },
          "add": "middle",
          "attribute": "flavour"
        },
        {
          "name": "First Gold",
          "amount": {
            "value": 37.5,
            "unit": "grams"
          },
          "add": "middle",
          "attribute": "flavour"
        },
        {
          "name": "Cascade",
          "amount": {
            "value": 37.5,
            "unit": "grams"
          },
          "add": "end",
          "attribute": "flavour"
        }
      ],
      "yeast": "Wyeast 1056 - American Ale™"
    },
    "food_pairing": [
      "Spicy chicken tikka masala",
      "Grilled chicken quesadilla",
      "Caramel toffee cake"
    ],
    "brewers_tips": "The earthy and floral aromas from the hops can be overpowering. Drop a little Cascade in at the end of the boil to lift the profile with a bit of citrus.",
    "contributed_by": "Sam Mason <samjbmason>"
  }
""".trimIndent().toObjectOrNull<BeerType>()!!
    BeerDetailScreen(beer = beer1) {
    }
}
