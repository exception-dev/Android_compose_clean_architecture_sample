package com.ex.punk.ui.beer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ex.domain.model.Beer
import com.ex.punk.R
import com.ex.punk.ui.beer.vm.BeerListViewModel
import com.ex.punk.ui.theme.DividerColor
import com.ex.punk.ui.theme.DividerSectionColor
import com.ex.punk.ui.theme.NoImage
import com.ex.punk.ui.theme.SectionColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerListScreen(viewModel : BeerListViewModel = hiltViewModel(), onClick: (Beer) -> Unit){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.splash_00)) },
            )
        }
    ) {
        padding ->

        Column(Modifier.padding(padding)){
            Divider(color = DividerColor, thickness = 1.dp)
            val dataList = viewModel.items.collectAsLazyPagingItems()

            BeerList(dataList, onClick)
        }

    }
}

@Composable
fun BeerList(items: LazyPagingItems<Beer>, onClick: (Beer) -> Unit){

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {

            items(items.itemCount) { index ->
                BeerListItem(items[index]!!, onClick)
            }

        }
    )


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BeerListItem(beer: Beer, onClick : (Beer) -> Unit ){
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth().clickable {
                            onClick(beer)
                        }
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Box(modifier = Modifier.size(60.dp, 70.dp)){
                    if(beer.imageUrl.isNullOrEmpty()){

                        Text(
                            text = stringResource(id = com.ex.punk.R.string.beer_00),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = NoImage,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .border(2.dp, color = NoImage)
                                .fillMaxSize()
                                .wrapContentHeight(),
                        )

                    }else{
                        GlideImage(
                            model = beer.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0x40000000))
                                .padding(vertical = 3.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = beer.name,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = beer.description,
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

        }

        Divider(color = DividerSectionColor, thickness = 0.5.dp)
        Divider(color = SectionColor, thickness = 5.dp)
        Divider(color = DividerSectionColor, thickness = 0.5.dp)


    }


}