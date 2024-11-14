package com.raulbarca.portfolio.core.localization.strings

class StringResourcesFr : StringResources {
    override val loading: String = "Chargement en cours"
    override val error: String = "Erreur"
    override val somethingWentWrong: String = "Une erreur s'est produite. Veuillez réessayer plus tard."
    override val back: String = "Retourner"
    override val avatarImage: String = "Image de l'avatar"
    override fun changeUiMode(current: String): String =
        "Changer le mode UI. Valeur actuel : $current"

    override val uiModeDark: String = "Sombre"
    override val uiModeLight: String = "Clair"
    override fun changeLanguage(current: String): String =
        "Changer la langue. Valeur actuel : $current"

    override val selected: String = "Sélectionnée"
    override val contactMe: String = "Contactez-moi"
    override val resume: String = "Mon CV"
    override val whoIAm: String = "Qui je suis"
    override val whatIDo: String = "Ce que je fais"
    override val projects: String = "Projets"
    override val experience: String = "Experience"
    override val education: String = "Education"
    override val selectLanguage: String = "Sélectionnez la langue de l'application"
}