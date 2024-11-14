package com.raulbarca.portfolio.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeData(
    @SerialName("experience") val experience: List<ExperienceEntry>,
    @SerialName("education") val education: List<EducationEntry>,
)

@Serializable
data class ExperienceEntry(
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("title") val title: String,
    @SerialName("companyName") val companyName: String,
    @SerialName("companyUrl") val companyUrl: String,
    @SerialName("location") val location: String,
    @SerialName("start") val start: String,
    @SerialName("end") val end: String,
)

@Serializable
data class EducationEntry(
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("title") val title: String,
    @SerialName("institutionName") val institutionName: String,
    @SerialName("institutionUrl") val institutionUrl: String,
    @SerialName("location") val location: String,
    @SerialName("start") val start: String,
    @SerialName("end") val end: String,
)