package com.raulbarca.portfolio.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PortfolioData(
    @SerialName("greeting") val greeting: String,
    @SerialName("headline") val headline: String,
    @SerialName("contactUrl") val contactUrl: String,
    @SerialName("avatarImageUrl") val avatarImageUrl: String,
    @SerialName("aboutMe") val aboutMe: String,
    @SerialName("roles") val roles: List<Role>,
    @SerialName("projects") val projects: List<Project>,
)

@Serializable
data class Role(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
)

@Serializable
data class Project(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("links") val links: List<Link>,
)

@Serializable
data class Link(
    @SerialName("url") val url: String,
    @SerialName("contentDescription") val contentDescription: String,
    @SerialName("image") val image: LinkImage,
)

@Serializable
enum class LinkImage {
    @SerialName("AppStore")
    AppStore,
    @SerialName("Github")
    Github,
    @SerialName("GooglePlay")
    GooglePlay,
    @SerialName("LinkedIn")
    LinkedIn,
    @SerialName("Other")
    Other
}