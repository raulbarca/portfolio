package com.raulbarca.portfolio.core.localization.strings

class StringResourcesPt : StringResources {
    override val loading: String = "Carregando"
    override val error: String = "Erro"
    override val somethingWentWrong: String = "Ocorreu um erro. Tente novamente mais tarde."
    override val back: String = "Retornar"
    override val avatarImage: String = "Imagem do avatar"
    override fun changeUiMode(current: String): String =
        "Alterar o modo da UI. Valor atual: $current"

    override val uiModeDark: String = "Escuro"
    override val uiModeLight: String = "Claro"
    override fun changeLanguage(current: String): String = "Alterar idioma. Valor atual: $current"
    override val selected: String = "Selecionado"
    override val contactMe: String = "Entre em contato"
    override val resume: String = "Currículo"
    override val whoIAm: String = "Quem sou eu"
    override val whatIDo: String = "O que faço"
    override val projects: String = "Projetos"
    override val experience: String = "Experiência"
    override val education: String = "Educação"
    override val selectLanguage: String = "Selecione o idioma do aplicativo"
}