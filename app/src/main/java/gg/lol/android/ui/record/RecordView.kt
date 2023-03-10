package gg.lol.android.ui.record

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import gg.lol.android.R
import gg.lol.android.data.search.SearchHistory
import gg.lol.android.ui.theme.BackgroundPrimaryColor
import gg.lol.android.ui.theme.ButtonTextColor
import gg.lol.android.ui.theme.LightGray
import gg.lol.android.ui.theme.MultiKillBackgroundColor
import gg.lol.android.ui.theme.PrimaryColor
import gg.lol.android.ui.theme.SeasonInformationTextColor

@Composable
fun RecordScreen(
    viewModel: RecordViewModel = hiltViewModel(), navController: NavHostController
) {
    val searchHistories = viewModel.searchHistories.observeAsState(emptyList()).value
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopView()
        RecordUpdateAndInGame()
        SeasonInformation()
        TierInformation()
        if (searchHistories.isEmpty()) {
            Text(text = "No items to display")
        } else {
            LazyColumn(
                modifier = Modifier
            ) {
                items(searchHistories) { item ->
                    SearchHistoryCard(item)
                }
            }
        }
    }
}

@Composable
fun TopView() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(start = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.BottomStart)
        ) {
            Box(modifier = Modifier.align(Alignment.Bottom)) {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(35.dp))
                        .width(80.dp)
                        .height(80.dp),
                    painter = painterResource(R.drawable.summoner_icon_test),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 2.dp, end = 2.dp)
                        .background(color = Color.Gray),
                    text = "642",
                    style = TextStyle(color = Color.White)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier, text = "Hide On Bush", style = TextStyle(
                        color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 22.sp
                    )
                )
                Text(
                    modifier = Modifier, text = "T1[Faker]", style = TextStyle(
                        color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp
                    )
                )
                Text(
                    modifier = Modifier, text = "?????? ?????? 514???", style = TextStyle(
                        color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
fun RecordUpdateAndInGame() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Button(colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            shape = RoundedCornerShape(10),
            content = {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.record_update),
                    style = TextStyle(
                        textAlign = TextAlign.Center, color = Color.White, fontSize = 12.sp
                    )
                )
            },
            onClick = { /* TODO */ })
    }
}

@Composable
fun SeasonInformation() {
    // TODO
    val items = listOf(mapOf(Pair("S2022", "DIAMOND 1"), Pair("S2021", "DIAMOND 2")))
    LazyRow(modifier = Modifier) {
        itemsIndexed(items) { index, item ->
            Card(
                modifier = Modifier.padding(8.dp),
//                    .background(color = LightGray),
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.cardColors(containerColor = LightGray)
            ) {
                Row(
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp)
                ) {
                    val season = item.keys.elementAt(index)
                    val tier = item.getValue(season)
                    Text(
                        text = season, style = TextStyle(
                            color = SeasonInformationTextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                    Text(
                        text = tier,
                        style = TextStyle(color = SeasonInformationTextColor, fontSize = 12.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun TierInformation() {
    LazyRow() {
        items(listOf("", "")) {
            TierItem()
        }
    }
}

@Composable
fun TierItem() {
//    Card(
//        modifier = Modifier
//            .padding(4.dp)
//            .border(width = 2.dp, color = LightGray, RoundedCornerShape(4.dp))
//            .fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//    ) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 2.dp, color = LightGray, RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .background(color = Color.White)
            .height(100.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://opgg-static.akamaized.net/images/medals_new/diamond.png?image=q_auto,f_webp,w_144&v=1678675410621"),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
//                    .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(color = BackgroundPrimaryColor)
                    .padding(2.dp),
                text = "??????/2??? ??????",
                style = TextStyle(color = ButtonTextColor, fontSize = 12.sp)
            )
            Text(
                text = "Master 1", style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black
                )
            )
            Text(
                text = "111LP", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = SeasonInformationTextColor
                )
            )
            Text(
                text = "274??? 269??? (50%)",
                style = TextStyle(fontSize = 12.sp, color = SeasonInformationTextColor)
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SearchHistoryCard(item: SearchHistory) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        ResultRecord(
            Modifier
                .background(color = Color.Red)
                .weight(1.5f)
                .padding(top = 24.dp, bottom = 24.dp, start = 4.dp, end = 4.dp)
                .align(Alignment.CenterVertically)
        )
        ResultInformation(
            Modifier
                .weight(9f)
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun RoundImage(
    @DrawableRes imageRes: Int, imageSize: Dp, cornerRadius: Dp, contentDescription: String? = null
) {
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .size(imageSize),
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription
    )
}

@Composable
fun ResultInformation(modifier: Modifier) {
    Column(modifier) {
        ResultInformationTop()
        ResultInformationBottom()
    }
}

@Composable
fun ResultRecord(modifier: Modifier) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "???",
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center),
        )
        Divider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp), color = Color.White
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "15:57",
            style = TextStyle(color = Color.White, textAlign = TextAlign.Center),
        )
    }
}

@Composable
fun ResultInformationTop() {
    Row(
        modifier = Modifier.fillMaxWidth()
//            .fillMaxHeight()
    ) {
        RoundImage(
            imageRes = R.drawable.champion_leblanc, imageSize = 50.dp, cornerRadius = 10.dp
        )
        Column(
            modifier = Modifier
//                .fillMaxHeight()
                .align(Alignment.CenterVertically)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 25.dp)
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundImage(
                    imageRes = R.drawable.summoner_spell_ignite,
                    imageSize = 20.dp,
                    cornerRadius = 5.dp
                )
                RoundImage(
                    imageRes = R.drawable.summoner_rune_electrocute,
                    imageSize = 20.dp,
                    cornerRadius = 10.dp
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "10/14/12",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "??????/2??? ??????",
                    style = TextStyle(
                        color = Color.Gray, fontSize = 11.sp, textAlign = TextAlign.End
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 25.dp)
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundImage(
                    imageRes = R.drawable.summoner_spell_flash,
                    imageSize = 20.dp,
                    cornerRadius = 5.dp
                )
                RoundImage(
                    imageRes = R.drawable.summoner_rune_sorcery,
                    imageSize = 20.dp,
                    cornerRadius = 10.dp
                )
                Text(
                    modifier = Modifier,
                    text = "??? ?????? 58%",
                    style = TextStyle(color = Color.Gray, fontSize = 11.sp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "1??????",
                    style = TextStyle(
                        color = Color.Gray, fontSize = 11.sp, textAlign = TextAlign.End
                    ),
                )
            }
        }
    }
}

@Composable
fun ResultInformationBottom() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            RoundImage(
                imageRes = R.drawable.item_everfrost, imageSize = 20.dp, cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost, imageSize = 20.dp, cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost, imageSize = 20.dp, cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost, imageSize = 20.dp, cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost, imageSize = 20.dp, cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.item_everfrost, imageSize = 20.dp, cornerRadius = 5.dp
            )
            Spacer(modifier = Modifier.size(4.dp))
            RoundImage(
                imageRes = R.drawable.accessories_farsight_alteration,
                imageSize = 20.dp,
                cornerRadius = 5.dp
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(color = MultiKillBackgroundColor)
                .padding(
                    top = 2.dp, bottom = 2.dp, start = 4.dp, end = 4.dp
                ), text = "?????????", style = TextStyle(fontSize = 10.sp, color = Color.Red)
        )
    }
}

@Preview
@Composable
fun SearchPreview() {
//    RecordScreen()
}
