package com.example.meditationuicompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditationuicompose.ui.theme.AquaBlue
import com.example.meditationuicompose.ui.theme.Beige1
import com.example.meditationuicompose.ui.theme.Beige2
import com.example.meditationuicompose.ui.theme.Beige3
import com.example.meditationuicompose.ui.theme.BlueViolet1
import com.example.meditationuicompose.ui.theme.BlueViolet2
import com.example.meditationuicompose.ui.theme.BlueViolet3
import com.example.meditationuicompose.ui.theme.ButtonBlue
import com.example.meditationuicompose.ui.theme.DarkerButtonBlue
import com.example.meditationuicompose.ui.theme.DeepBlue
import com.example.meditationuicompose.ui.theme.LightGreen1
import com.example.meditationuicompose.ui.theme.LightGreen2
import com.example.meditationuicompose.ui.theme.LightGreen3
import com.example.meditationuicompose.ui.theme.LightRed
import com.example.meditationuicompose.ui.theme.OrangeYellow1
import com.example.meditationuicompose.ui.theme.OrangeYellow2
import com.example.meditationuicompose.ui.theme.OrangeYellow3
import com.example.meditationuicompose.ui.theme.TextWhite

//comment


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ){
        Column {
            GrettingsSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureScreen(feature = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3,
                ),
                Feature(
                    title = "Calming songs",
                    R.drawable.ic_headphone,
                   Beige1,
                   Beige2,
                   Beige3
                ),
            )
            )
        }
        BottomMenu(items = listOf(
            BottomMenuResource("Home", R.drawable.ic_home),
            BottomMenuResource("Meditate", R.drawable.ic_bubble),
            BottomMenuResource("Sleep", R.drawable.ic_moon),
            BottomMenuResource("Music", R.drawable.ic_music),
            BottomMenuResource("Profile", R.drawable.ic_profile)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuResource>,
    modifier: Modifier = Modifier,
    activeHightLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0//початкове значення вибраного елемента
){
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ){
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHightLightColor = activeHightLightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
                    ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuResource,
    isSelected: Boolean = false,
    activeHightLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: ()-> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHightLightColor else Color.Transparent)
                .padding(10.dp)
            ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
            Text(
                text = item.title,
                color = if (isSelected) activeTextColor else inactiveTextColor
            )
        }
    }

@Composable
fun GrettingsSection(
    name: String = "Olena"
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "We wish you a good day, $name",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon( painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
){

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size){
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top=15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex = it
                }
                .background(
                    if (selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        Column(
        ) {
            Text(
                text = "Daily Thoughts",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Meditation 3-10 min",
                style = MaterialTheme.typography.bodyLarge,
                color = TextWhite
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp),
            contentAlignment = Alignment.Center,
        ){
            Icon(painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureScreen(
    feature: List<Feature>
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns =GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 7.5.dp,
                end = 7.5.dp,
                bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(feature.size){
                FeatureItem(feature = feature[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colors path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width*0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width*0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width*0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width*1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(
                mediumColoredPoint1.x,
                mediumColoredPoint1.y
            )
         standartQuarFromTo(mediumColoredPoint1,mediumColoredPoint2)
         standartQuarFromTo(mediumColoredPoint2,mediumColoredPoint3)
         standartQuarFromTo(mediumColoredPoint3,mediumColoredPoint4)
         standartQuarFromTo(mediumColoredPoint4,mediumColoredPoint5)

            lineTo(width.toFloat()+100f, height.toFloat()+100f)
            lineTo(-100f, height.toFloat()+100f)
            close()
        }
        //Light colors path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width*0.1f, height * 0.4f)
        val lightPoint3 = Offset(width*0.3f, height * 0.35f)
        val lightPoint4 = Offset(width*0.65f, height.toFloat())
        val lightPoint5 = Offset(width*1.4f, height.toFloat()/3)

        val lightColoredPath = Path().apply {
            moveTo(
                lightPoint1.x,
                lightPoint1.y
            )
            standartQuarFromTo(lightPoint1,lightPoint2)
            standartQuarFromTo(lightPoint2,lightPoint3)
            standartQuarFromTo(lightPoint3,lightPoint4)
            standartQuarFromTo(lightPoint4,lightPoint5)

            lineTo(width.toFloat()+100f, height.toFloat()+100f)
            lineTo(-100f, height.toFloat()+100f)
            close()
        }
        Canvas(
            modifier = Modifier
            .fillMaxSize()
        ){
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
           Text(
               text = feature.title,
               lineHeight = 26.sp,
               modifier = Modifier.align(Alignment.TopStart),
               style = MaterialTheme.typography.bodyMedium,
           )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
// TODO:
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}