package com.raulbarca.portfolio.feature.overview.overview

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.raulbarca.portfolio.core.designsystem.component.PortfolioError
import com.raulbarca.portfolio.core.designsystem.component.PortfolioLoading
import com.raulbarca.portfolio.core.designsystem.icon.AppIcons
import com.raulbarca.portfolio.core.designsystem.icon.brand.AppStore
import com.raulbarca.portfolio.core.designsystem.icon.brand.Github
import com.raulbarca.portfolio.core.designsystem.icon.brand.GooglePlay
import com.raulbarca.portfolio.core.designsystem.icon.brand.LinkedIn
import com.raulbarca.portfolio.core.designsystem.theme.AppThemeUiMode
import com.raulbarca.portfolio.core.localization.locale.AppLocale
import com.raulbarca.portfolio.core.localization.locale.CurrentAppLocale
import com.raulbarca.portfolio.core.localization.locale.Resources
import com.raulbarca.portfolio.core.model.Link
import com.raulbarca.portfolio.core.model.LinkImage
import com.raulbarca.portfolio.core.model.PortfolioData
import com.raulbarca.portfolio.core.model.Project
import com.raulbarca.portfolio.core.model.Role
import com.raulbarca.portfolio.feature.overview.component.LanguageChooserDialog
import com.raulbarca.portfolio.feature.overview.overview.model.OverviewUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OverviewScreenRoute(
    viewModel: OverviewViewModel,
    onUiModeChange: (AppThemeUiMode) -> Unit,
    onAppLocaleChange: (AppLocale) -> Unit,
    onResumeClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(CurrentAppLocale) {
        viewModel.load()
    }

    OverviewScreen(
        uiState = uiState,
        onUiModeChange = onUiModeChange,
        onAppLocaleChange = onAppLocaleChange,
        onResumeClick = onResumeClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OverviewScreen(
    uiState: OverviewUiState,
    modifier: Modifier = Modifier,
    onUiModeChange: (AppThemeUiMode) -> Unit = {},
    onAppLocaleChange: (AppLocale) -> Unit = {},
    onResumeClick: () -> Unit = {},
) {
    val strings = Resources.strings

    val isSystemInDarkTheme = isSystemInDarkTheme()
    var uiMode by rememberSaveable {
        mutableStateOf(if (isSystemInDarkTheme) AppThemeUiMode.Dark else AppThemeUiMode.Light)
    }

    val uiModeButtonContentDescription = rememberSaveable(uiMode) {
        strings.changeUiMode(
            if (uiMode == AppThemeUiMode.Dark) strings.uiModeDark else strings.uiModeLight
        )
    }
    val appLocaleButtonContentDescription = rememberSaveable(CurrentAppLocale) {
        strings.changeLanguage(CurrentAppLocale.text)
    }

    var isAppLocaleDialogExpanded by remember { mutableStateOf(false) }
    if (isAppLocaleDialogExpanded) {
        LanguageChooserDialog(
            onAppLocaleChosen = {
                isAppLocaleDialogExpanded = false
                onAppLocaleChange(it)
            },
            onDismissRequest = {
                isAppLocaleDialogExpanded = false
            },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(
                        onClick = {
                            val newUiMode =
                                if (uiMode == AppThemeUiMode.Dark) AppThemeUiMode.Light else AppThemeUiMode.Dark
                            onUiModeChange(newUiMode)
                            uiMode = newUiMode
                        }
                    ) {
                        Icon(
                            imageVector = if (uiMode == AppThemeUiMode.Dark) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = uiModeButtonContentDescription,
                            modifier = Modifier.semantics {
                                liveRegion = LiveRegionMode.Polite
                            }
                        )
                    }
                    IconButton(onClick = {
                        isAppLocaleDialogExpanded = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = appLocaleButtonContentDescription,
                            modifier = Modifier.semantics {
                                liveRegion = LiveRegionMode.Polite
                            }
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        when (uiState) {
            is OverviewUiState.Error -> PortfolioError(modifier = Modifier.padding(paddingValues))

            is OverviewUiState.Loading -> PortfolioLoading(modifier = Modifier.padding(paddingValues))

            is OverviewUiState.Success -> OverviewSuccess(
                modifier = Modifier.padding(paddingValues),
                portfolioData = uiState.data,
                onResumeClick = onResumeClick,
            )
        }
    }
}

@Composable
private fun OverviewSuccess(
    modifier: Modifier = Modifier,
    portfolioData: PortfolioData,
    onResumeClick: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current

    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.verticalScroll(scrollState)
    ) {
        OverviewHeader(
            portfolioData = portfolioData,
            uriHandler = uriHandler,
            onResumeClick = onResumeClick
        )
        OverviewAboutMe(portfolioData = portfolioData)
        OverviewWhatIDo(portfolioData = portfolioData)
        OverviewMyProjects(portfolioData = portfolioData, uriHandler = uriHandler)
    }
}

@Composable
private fun OverviewHeader(
    portfolioData: PortfolioData,
    uriHandler: UriHandler,
    onResumeClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(24.dp)
    ) {
        Spacer(Modifier.size(48.dp))
        AvatarImage(avatarImageUrl = portfolioData.avatarImageUrl)
        Text(
            text = portfolioData.greeting,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.semantics { heading() }
        )
        Text(text = portfolioData.headline, textAlign = TextAlign.Center)
        Spacer(Modifier.size(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {
                    uriHandler.openUri(portfolioData.contactUrl)
                }
            ) {
                Text(Resources.strings.contactMe)
            }
            OutlinedButton(onClick = onResumeClick) {
                Text(Resources.strings.resume)
            }
        }
    }
}

@Composable
private fun OverviewAboutMe(portfolioData: PortfolioData) {
    Section(
        title = Resources.strings.whoIAm,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = portfolioData.aboutMe)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun OverviewWhatIDo(portfolioData: PortfolioData) {
    Section(
        title = Resources.strings.whatIDo,
        containerColor = MaterialTheme.colorScheme.background,
        titleColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    collectionInfo = CollectionInfo(
                        rowCount = 1,
                        columnCount = portfolioData.roles.size
                    )
                }
        ) {
            portfolioData.roles.forEachIndexed { index, role ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .semantics(mergeDescendants = true) {
                            collectionItemInfo = CollectionItemInfo(
                                rowIndex = 0,
                                rowSpan = 1,
                                columnIndex = index,
                                columnSpan = 1,
                            )
                        }
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 24.dp
                        )
                    ) {
                        Text(
                            text = role.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(text = role.description)
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun OverviewMyProjects(
    portfolioData: PortfolioData,
    uriHandler: UriHandler
) {
    Section(
        title = Resources.strings.projects,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    collectionInfo = CollectionInfo(
                        rowCount = 1,
                        columnCount = portfolioData.projects.size
                    )
                }
        ) {
            portfolioData.projects.forEachIndexed { index, project ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .semantics(mergeDescendants = true) {
                            collectionItemInfo = CollectionItemInfo(
                                rowIndex = 0,
                                rowSpan = 1,
                                columnIndex = index,
                                columnSpan = 1,
                            )
                        }
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        Text(
                            text = project.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(text = project.description)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            project.links.forEach { link ->
                                IconButton(
                                    onClick = { uriHandler.openUri(link.url) },
                                    modifier = Modifier.padding(top = 8.dp),
                                ) {
                                    Icon(
                                        imageVector = link.image.toImageVector(),
                                        contentDescription = link.contentDescription,
                                        modifier = Modifier.size(40.dp).padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun LinkImage.toImageVector(): ImageVector =
    when (this) {
        LinkImage.AppStore -> AppIcons.Brand.AppStore
        LinkImage.Github -> AppIcons.Brand.Github
        LinkImage.GooglePlay -> AppIcons.Brand.GooglePlay
        LinkImage.LinkedIn -> AppIcons.Brand.LinkedIn
        LinkImage.Other -> Icons.Default.Link
    }


@Composable
private fun Section(
    title: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    titleColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 48.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = titleColor,
                modifier = Modifier.semantics { heading() }
            )
            content()
        }
    }
}

@Composable
private fun AvatarImage(avatarImageUrl: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val extraPadding by animateDpAsState(targetValue = if (isHovered) 80.dp else 40.dp)
    val xPosition by animateDpAsState(targetValue = if (isHovered) 40.dp else 20.dp)
    val yPosition by animateDpAsState(targetValue = if (isHovered) 40.dp else 20.dp)

    Layout(
        modifier = Modifier,
        content = {
            val cardModifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .size(300.dp)
            Box(
                modifier = cardModifier.background(MaterialTheme.colorScheme.primary)
            )
            AsyncImage(
                model = avatarImageUrl,
                contentDescription = Resources.strings.avatarImage,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = cardModifier.hoverable(interactionSource)
            )
        }
    ) { measurables, constraints ->
        val placeables =
            measurables.map { measurable ->
                measurable.measure(constraints)
            }

        val height =
            if (placeables.isNotEmpty())
                placeables.first().height + (extraPadding.value.toInt() * placeables.size)
            else 0

        val width =
            if (placeables.isNotEmpty()) placeables.first().width
            else 0

        layout(width = width, height = height) {
            placeables.mapIndexed { index, placeable ->
                placeable.place(
                    x = if (index % 2 == 0) xPosition.value.toInt() / 2 else -xPosition.value.toInt() / 2,
                    y = if (index % 2 == 0) yPosition.value.toInt() / 2 else -yPosition.value.toInt() / 2,
                )
            }
        }
    }
}

@Preview
@Composable
fun OverviewScreenPreview() {
    val success = OverviewUiState.Success(
        PortfolioData(
            greeting = "Hey, I'm Raul",
            headline = "A Software Engineer creating the best user experience on mobile apps",
            contactUrl = "https://www.linkedin.com/in/raul-barca-522a16a0",
            avatarImageUrl = "https://picsum.photos/id/883/800/800",
            aboutMe =
            "I’m an Android Developer with a strong foundation in Kotlin, Jetpack Compose, and accessibility.\n" +
                    "Since late 2019, I’ve worked as a full-time Android developer, and I’ve been freelancing in this field since early 2018.\n" +
                    "My journey in IT began in 2012, covering diverse domains like finance, energy, banking, and well-being.\n" +
                    "I’m passionate about building user-centered mobile applications that are both high-quality and accessible.\n\n" +
                    "Beyond my professional work, I volunteer in STEM programs, introducing young students to programming and robotics,\n" +
                    "as I believe in the power of tech education and giving back to the community.",
            roles = listOf(
                Role(
                    title = "Android App Development",
                    description = "I create modern Android apps using Kotlin and Jetpack Compose.",
                ),
                Role(
                    title = "Multiplatform Development",
                    description = "I create responsive multiplatform apps and websites with Compose Multiplatform.",
                ),
                Role(
                    title = "Accessibility",
                    description = "Design projects thinking of WCAG principles.",
                ),
            ),
            projects = listOf(
                Project(
                    title = "Github Project",
                    description = "A placeholder project to preview data on screen.",
                    links = listOf(
                        Link(
                            url = "https://apps.apple.com/us/app/github/id1477376905",
                            contentDescription = "App Store",
                            image = LinkImage.AppStore,
                        ),
                        Link(
                            url = "https://play.google.com/store/apps/details?id=com.github.android",
                            contentDescription = "Google Play",
                            image = LinkImage.GooglePlay,
                        ),
                        Link(
                            url = "https://github.com/",
                            contentDescription = "Github",
                            image = LinkImage.Github,
                        ),
                    ),
                ),
            )
        )
    )
    OverviewScreen(
        uiState = success,
    )
}