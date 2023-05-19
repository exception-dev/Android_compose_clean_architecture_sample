package com.ex.punk.ui.beer

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ex.domain.model.Beer
import com.ex.punk.R
import com.ex.punk.ui.beer.vm.BeerDetailViewModel
import com.ex.punk.ui.common.ErrorMessage
import com.ex.punk.ui.common.LoadingProgress
import com.ex.punk.ui.theme.NoImage
import com.ex.punk.ui.theme.ValueFontColor

@Composable
fun BeerDetailScreen(viewModel : BeerDetailViewModel = hiltViewModel()){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        viewModel.getData()
        DetailScreen()
    }
}

@Composable
fun DetailScreen(viewModel : BeerDetailViewModel = hiltViewModel()){

    Box(modifier = Modifier.fillMaxSize()){
        var beer by remember<MutableState<Beer?>> { mutableStateOf(null) }
        BeerInfo(beer)

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when (uiState) {
            is BeerDetailViewModel.UiState.Loading -> {
                LoadingProgress()
            }
            is BeerDetailViewModel.UiState.Success -> {
                beer = (uiState as BeerDetailViewModel.UiState.Success).beer
            }
            is BeerDetailViewModel.UiState.Error -> {
                //실제 로직에서 서버에서 준 메시지나 throw 처리시 메시지 넘겨줄수 있도록 작업후 해당 메시지 표시
                ErrorMessage("잠시후 다시 시도 부탁드립니다.")
            }
        }

        /**
         * 아래 주석 처럼 로직을 짜면 로딩 되는 동안 화면을 그리고 있지 않아
         * (예전 setContentView 시절 화면을 그려놓고 바뀐 데이터 영역만 바뀌는게
         * 유저 입장에서는 더 빠른거처럼 느껴짐)
         * state 를 여러번 쓴거 아닌가 생각이 들긴 하지만 위에 실행 로직 처럼 작업함
         */
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//        when (uiState) {
//            is BeerDetailViewModel.UiState.Loading -> {
//                LoadingProgress()
//            }
//            is BeerDetailViewModel.UiState.Success -> {
//                val beer = (uiState as BeerDetailViewModel.UiState.Success).beer
//                BeerInfo(beer)
//            }
//            is BeerDetailViewModel.UiState.Error -> {
//
//            }
//        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BeerInfo(beer : Beer?){
    Column(modifier = Modifier
        .padding(bottom = 20.dp)
        .verticalScroll(rememberScrollState())) {
        
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)){
            if(beer?.imageUrl.isNullOrEmpty()){

                Text(
                    text = stringResource(id = R.string.beer_00),
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
                    model = beer!!.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x40000000))
                        .padding(vertical = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = beer?.name?:"",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ){
            BeerIndex(stringResource(id = R.string.beer_11), beer?.abv?:"")
            BeerIndex(stringResource(id = R.string.beer_12), beer?.ibu?:"")
            BeerIndex(stringResource(id = R.string.beer_13), beer?.srm?:"")
            BeerIndex(stringResource(id = R.string.beer_14), beer?.ebc?:"")
            BeerIndex(stringResource(id = R.string.beer_15), beer?.ph?:"")
            
        }

        Spacer(modifier = Modifier.height(30.dp))
        
        Column(modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .background(Color(0xFF000320))
            .padding(16.dp)) {

            BeerInfoLabelValue(stringResource(id = R.string.beer_21), beer?.tagline?:"")
            BeerInfoLabelValue(stringResource(id = R.string.beer_22), beer?.firstBrewed?:"")
            BeerInfoLabelValue(stringResource(id = R.string.beer_23), beer?.description?:"")
            BeerInfoLabelValue(stringResource(id = R.string.beer_24), beer?.foodPairing?.joinToString ("\n")?:"")
            BeerInfoLabelValue(stringResource(id = R.string.beer_25), beer?.brewersTips?:"")
            BeerInfoLabelValue(stringResource(id = R.string.beer_26), beer?.contributedBy?:"")
        }


    }
}


@Composable
fun BeerIndex(label : String, value : String ){
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4)),
        modifier = Modifier.size(100.dp, 80.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = label,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .weight(1f),
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = value,
                modifier = Modifier.align(alignment = Alignment.End),
                style = MaterialTheme.typography.labelSmall.copy(color = ValueFontColor, fontSize = 15.sp)
            )
        }
        
    }

    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
fun BeerInfoLabelValue(label : String, value : String){

    Text(
        text = label,
        style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFF999999))
    )


    Text(
        text = value,
        style = MaterialTheme.typography.labelSmall.copy(color = Color.White, fontSize = 14.sp)
    )
    
    Spacer(modifier = Modifier.height(20.dp))
}