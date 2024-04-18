package com.teamegg.eggtart.features.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamegg.eggtart.common.feature.theme.ColorKakao
import com.teamegg.eggtart.common.feature.theme.EggtartTheme
import com.teamegg.eggtart.features.login.sideeffect.LoginSideEffect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * Created by 노원진 on 2024.04.10
 */


@Composable
fun LoginScreen(kakaoAccessToken: String? = null, viewModel: LoginViewModel = hiltViewModel(), startKakaoLogin: suspend () -> Unit) {
    val viewModelState = viewModel.collectAsState()

    LaunchedEffect(kakaoAccessToken) {
        if (!kakaoAccessToken.isNullOrEmpty()) {
            viewModel.intentLoginWithKakao(kakaoAccessToken)
        }
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(painter = painterResource(id = R.drawable.bg_login), contentScale = ContentScale.Crop)
                .padding(horizontal = 16.dp)
                .padding(top = 80.dp, bottom = 104.dp),
        ) {
            val coroutine = rememberCoroutineScope()

            Text(text = stringResource(id = R.string.login_start), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.background)

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = stringResource(id = R.string.login_start_des), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.background)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorKakao,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(vertical = 16.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    /*TODO: KakaoLogin*/
                    coroutine.launch {
                        startKakaoLogin()
                    }
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_kakao), contentDescription = "")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.login_continue_with_kakao), style = MaterialTheme.typography.labelLarge, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 16.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    /*TODO: AppleLogin*/
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_apple), contentDescription = "")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.login_continue_with_apple), style = MaterialTheme.typography.labelLarge, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    val des = stringResource(id = R.string.login_terms_des)
                    val policy = stringResource(id = R.string.str_privacy_policy)
                    val terms = stringResource(id = R.string.str_terms)

                    append(stringResource(id = R.string.login_terms_des))

                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), des.indexOf(policy), des.indexOf(policy) + policy.length)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), des.indexOf(terms), des.indexOf(terms) + terms.length)
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.background
            )
        }

        if (viewModelState.value.loginLoading) {
            Dialog(onDismissRequest = { }) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        viewModel.collectSideEffect {
            when (it) {
                is LoginSideEffect.ShowErrorPopup -> {}
            }
        }
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
private fun PreviewLoginScreen() {
    EggtartTheme {
        LoginScreen(startKakaoLogin = {})
    }
}