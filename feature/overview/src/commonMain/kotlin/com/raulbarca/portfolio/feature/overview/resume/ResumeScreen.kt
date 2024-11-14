package com.raulbarca.portfolio.feature.overview.resume

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.raulbarca.portfolio.core.designsystem.component.BackButton
import com.raulbarca.portfolio.core.designsystem.component.PortfolioError
import com.raulbarca.portfolio.core.designsystem.component.PortfolioLoading
import com.raulbarca.portfolio.core.localization.locale.CurrentAppLocale
import com.raulbarca.portfolio.core.localization.locale.Resources
import com.raulbarca.portfolio.core.model.EducationEntry
import com.raulbarca.portfolio.core.model.ExperienceEntry
import com.raulbarca.portfolio.core.model.ResumeData
import com.raulbarca.portfolio.feature.overview.resume.model.ResumeUiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResumeScreenRoute(
    viewModel: ResumeViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(CurrentAppLocale) {
        viewModel.load()
    }

    ResumeScreen(
        uiState = uiState,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ResumeScreen(
    uiState: ResumeUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BackButton(onClick = onBackClick)
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        when (uiState) {
            is ResumeUiState.Error -> PortfolioError(modifier = Modifier.padding(paddingValues))

            is ResumeUiState.Loading -> PortfolioLoading(modifier = Modifier.padding(paddingValues))

            is ResumeUiState.Success -> ResumeSuccess(
                modifier = Modifier.padding(paddingValues),
                resumeData = uiState.data,
            )
        }
    }
}

@Composable
private fun Line(modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(MaterialTheme.colorScheme.primary)
            .width(2.dp)
    )
}

@Composable
private fun ResumeSuccess(
    modifier: Modifier = Modifier,
    resumeData: ResumeData,
) {
    val uriHandler = LocalUriHandler.current

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Spacer(Modifier.size(48.dp))
        Text(
            text = Resources.strings.experience,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(horizontal = 24.dp).semantics { heading() }
        )
        Spacer(Modifier.size(24.dp))

        Column(
            Modifier.semantics {
                collectionInfo = CollectionInfo(
                    rowCount = resumeData.experience.size,
                    columnCount = 1
                )
            }
        ) {
            resumeData.experience.forEachIndexed { index, experienceEntry ->
                ResumeRow(
                    index = index,
                    lastIndex = resumeData.experience.lastIndex,
                    url = experienceEntry.companyUrl,
                    imageUrl = experienceEntry.imageUrl,
                    place = experienceEntry.companyName,
                    location = experienceEntry.location,
                    title = experienceEntry.title,
                    start = experienceEntry.start,
                    end = experienceEntry.end,
                    uriHandler = uriHandler,
                    modifier = Modifier.semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = index,
                            rowSpan = 1,
                            columnIndex = 0,
                            columnSpan = 1
                        )
                    }
                )
            }
        }

        Spacer(Modifier.size(24.dp))
        Text(
            text = Resources.strings.education,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(horizontal = 24.dp).semantics { heading() }
        )
        Spacer(Modifier.size(24.dp))

        Column(
            Modifier.semantics {
                collectionInfo = CollectionInfo(
                    rowCount = resumeData.education.size,
                    columnCount = 1
                )
            }
        ) {
            resumeData.education.forEachIndexed { index, educationEntry ->
                ResumeRow(
                    index = index,
                    lastIndex = resumeData.education.lastIndex,
                    url = educationEntry.institutionUrl,
                    imageUrl = educationEntry.imageUrl,
                    place = educationEntry.institutionName,
                    location = educationEntry.location,
                    title = educationEntry.title,
                    start = educationEntry.start,
                    end = educationEntry.end,
                    uriHandler = uriHandler,
                    modifier = Modifier.semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = index,
                            rowSpan = 1,
                            columnIndex = 0,
                            columnSpan = 1
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ResumeRow(
    index: Int,
    lastIndex: Int,
    url: String,
    imageUrl: String,
    place: String,
    location: String,
    title: String,
    start: String,
    end: String,
    uriHandler: UriHandler,
    modifier: Modifier = Modifier,
) {
    val imageSize = 48.dp
    val circleIndicatorDiameter = 12.dp
    val rippleExtraPadding = 16.dp

    Row(
        modifier
            .height(IntrinsicSize.Min)
            .padding(horizontal = 24.dp)
            .semantics(mergeDescendants = true) {}
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (index == 0) {
                Spacer(Modifier.height(imageSize - (circleIndicatorDiameter + rippleExtraPadding) / 2))
            } else {
                Line(Modifier.height(imageSize - (circleIndicatorDiameter + rippleExtraPadding) / 2))
            }
            Box(
                Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(circleIndicatorDiameter)
            )
            if (index == lastIndex) {
                Spacer(Modifier.weight(1f))
            } else {
                Line(Modifier.weight(1f))
            }
        }
        Box(Modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { uriHandler.openUri(url) }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(rippleExtraPadding)
            ) {
                RoundedImage(
                    model = imageUrl,
                    contentDescription = place,
                    modifier = Modifier.size(imageSize)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = place,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "$start - $end",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun RoundedImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) = AsyncImage(
    model = model,
    contentDescription = contentDescription,
    contentScale = ContentScale.Crop,
    alignment = Alignment.Center,
    modifier = modifier.clip(MaterialTheme.shapes.large)
)

@Preview
@Composable
fun ResumeScreenPreview() {
    val success = ResumeUiState.Success(
        ResumeData(
            experience = listOf(
                ExperienceEntry(
                    imageUrl = "https://media.licdn.com/dms/image/v2/C4E0BAQErzXWSFkn9tQ/company-logo_100_100/company-logo_100_100/0/1663664110809/cgi_logo?e=1739404800&v=beta&t=9SKGkVBifCIIjdGOeOViiVBhfn4MGYXmY74iF4b5wP8",
                    title = "Android Developer",
                    companyName = "CGI",
                    companyUrl = "https://www.cgi.com/en",
                    location = "Sherbrooke, Québec, Canada",
                    start = "Sep 2021",
                    end = "now"
                ),
                ExperienceEntry(
                    imageUrl = "https://media.licdn.com/dms/image/v2/D4D0BAQGwhrn49xXUiw/company-logo_100_100/company-logo_100_100/0/1684433959108/zupinnovation_logo?e=1739404800&v=beta&t=UtXHdL8VJyt_nzz6_oZ1nZ-Qa7fLOcXPSvS2S3Sf-iw",
                    title = "Android Developer",
                    companyName = "Zup Innovation",
                    companyUrl = "https://zupinnovation.com",
                    location = "Campinas, São Paulo, Brazil",
                    start = "Dec 2019",
                    end = "Jul 2021"
                ),
                ExperienceEntry(
                    imageUrl = "https://media.licdn.com/dms/image/v2/C4E0BAQHbAWs02xP3ww/company-logo_100_100/company-logo_100_100/0/1630602647189/enel_brasil_logo?e=1739404800&v=beta&t=Zb4jEg6e9KIWQNhp2d5QmLeYkYm9mSSd0rgpGTdDp4o",
                    title = "Systems Analyst",
                    companyName = "Enel",
                    companyUrl = "https://www.enel.com.br",
                    location = "Goiânia, Goiás, Brazil",
                    start = "Mar 2015",
                    end = "Dec 2019"
                ),
                ExperienceEntry(
                    imageUrl = "https://media.licdn.com/dms/image/v2/D4D0BAQGw3BVYHzaBwQ/company-logo_100_100/company-logo_100_100/0/1720207931733/decisaosistemas_logo?e=1739404800&v=beta&t=uMMpXkUBBFuspkIU_SsNqi-RLZ2JDh74-NS5m0ygvfc",
                    title = "Tests Analyst",
                    companyName = "Decisão Sistemas",
                    companyUrl = "https://decisaosistemas.com.br/",
                    location = "Aparecida de Goiânia, Goiás, Brazil",
                    start = "Oct 2012",
                    end = "Feb 2015"
                )
            ),
            education = listOf(
                EducationEntry(
                    imageUrl = "https://media.licdn.com/dms/image/v2/D4D0BAQE92sjYGQ9eqQ/company-logo_100_100/company-logo_100_100/0/1728258542573/faculdade_senai_fatesg_logo?e=1739404800&v=beta&t=0s_j-jxfT7J4QEigUMlZEKzABJ3iFHxb_F1RpE_Kvkw",
                    title = "Software Project and Development for Mobile Devices",
                    institutionName = "SENAI Fatesg",
                    institutionUrl = "https://senaigoias.com.br/faculdade-fatesg",
                    location = "Goiânia, Goiás, Brazil",
                    start = "2014",
                    end = "2016"
                ),
                EducationEntry(
                    imageUrl = "https://media.licdn.com/dms/image/v2/C4D0BAQEfBMvGBE7yZg/company-logo_100_100/company-logo_100_100/0/1630536419962/universidade_federal_de_goias_logo?e=1739404800&v=beta&t=6uSvS264zp-8-41SKe4KgmS8S-nA_M9lhjqiHsK5LEs",
                    title = "Software Engineering",
                    institutionName = "Universidade Federal de Goiás",
                    institutionUrl = "https://ufg.br",
                    location = "Goiânia, Goiás, Brazil",
                    start = "2010",
                    end = "2014"
                ),
            ),
        )
    )
    ResumeScreen(
        uiState = success,
    )
}