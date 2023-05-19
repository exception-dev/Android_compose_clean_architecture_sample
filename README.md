# Android Compose Clean Architecture 샘플

[Brewdog 의 PunkApi](https://punkapi.com/documentation/v2)를 이용하여 
compose clean archtecture 샘플작성

## 사용한기술
- Clean Architecture - app(presentation/ui), data, domain
- Kotlin
- Retrofit + okHttp
- Coroutine
- flow
- Jetpack compose
- Paging
- hilt
- Glide(glide:compose)

## Clean Architechture 구성
- app - presetation layout
- data - data layout
- domain - domain layout 

domain layout은 android 의존성이 없어야 해서 일반 java / kotlin library 로 추가 하였는데
의존성(hilt)과 paging3 관련 처리때문에 이것저것 찾아 보다가
hilt 는 dagger추가
```groovy
implementation 'com.google.dagger:dagger:2.45'
kapt 'com.google.dagger:dagger-compiler:2.45'
```
paging은 다음과 같이 안드로이드 의존성이 없는
```groovy
implementation "androidx.paging:paging-common:$paging_version"
```
를 이용하여 처리
