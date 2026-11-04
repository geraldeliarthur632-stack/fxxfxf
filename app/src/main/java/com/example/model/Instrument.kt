package com.example.model

import com.example.R

enum class InstrumentType(
    val id: String,
    val namePt: String,
    val nameEn: String,
    val nameEs: String,
    val descPt: String,
    val descEn: String,
    val descEs: String,
    val drawableRes: Int,
    val totalModules: Int = 8,
    val totalLessons: Int = 90,
    val tuningInfo: String,
    val category: String
) {
    VIOLAO(
        id = "violao",
        namePt = "Violão",
        nameEn = "Acoustic Guitar",
        nameEs = "Guitarra",
        descPt = "Aprenda acordes, cifras, ritmos, dedilhados e suas músicas favoritas no violão de cordas de nylon e aço.",
        descEn = "Learn chords, tabs, rhythms, fingerpicking and your favorite songs on classical/acoustic guitar.",
        descEs = "Aprende acordes, cifrados, ritmos, arpegios y tus canciones favoritas en guitarra clásica y acústica.",
        drawableRes = R.drawable.instrument_guitar,
        tuningInfo = "E - A - D - G - B - E (Mi, Lá, Ré, Sol, Si, Mi)",
        category = "Cordas"
    ),
    VIOLA_CAIPIRA(
        id = "viola",
        namePt = "Viola Caipira",
        nameEn = "Brazilian Viola",
        nameEs = "Viola Campesina",
        descPt = "Descubra a alma da música caipira brasileira com 10 cordas de puro som, ponteados, afinações Cebolão e Boiadeiro.",
        descEn = "Discover the rich 10-string Brazilian folk guitar traditions, picking techniques and traditional tunings.",
        descEs = "Descubre la rica tradición de la viola campesina brasileña de 10 cuerdas, punteos y afinaciones tradicionales.",
        drawableRes = R.drawable.instrument_viola,
        tuningInfo = "Cebolão em Mi Maior: E-E - B-B - G#-G# - B-B - E-E",
        category = "Cordas Tradicionais"
    ),
    PIANO(
        id = "piano",
        namePt = "Piano",
        nameEn = "Piano",
        nameEs = "Piano",
        descPt = "Desenvolva coordenação motora entre as mãos, leitura na clave de Sol e Fá, dinâmica e clássicos do piano.",
        descEn = "Develop two-hand coordination, Treble and Bass clef reading, dynamics and piano masterpieces.",
        descEs = "Desarrolla la coordinación bimanual, lectura en clave de Sol y Fa, dinámicas y obras para piano.",
        drawableRes = R.drawable.instrument_piano,
        tuningInfo = "88 Teclas temperadas (A0 a C8) com afinação padrão A4 = 440 Hz",
        category = "Teclas e Cordas Percutidas"
    ),
    TECLADO(
        id = "teclado",
        namePt = "Teclado",
        nameEn = "Keyboard / Synth",
        nameEs = "Teclado Electrónico",
        descPt = "Domine acompanhamentos automáticos, timbres sintetizados, split de teclado, efeitos e arranjos modernos.",
        descEn = "Master auto-accompaniments, synth presets, keyboard splitting, effects and modern pop arrangements.",
        descEs = "Domina acompañamientos automáticos, timbres sintéticos, división de teclado y arreglos modernos.",
        drawableRes = R.drawable.instrument_keyboard,
        tuningInfo = "61 Teclas eletrônicas (C2 a C7) com timbres General MIDI e sintetizador",
        category = "Eletrônicos e Teclas"
    );

    fun getName(lang: AppLanguage): String = when (lang) {
        AppLanguage.PT -> namePt
        AppLanguage.EN -> nameEn
        AppLanguage.ES -> nameEs
    }

    fun getDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.PT -> descPt
        AppLanguage.EN -> descEn
        AppLanguage.ES -> descEs
    }
}

data class FutureInstrument(
    val id: String,
    val namePt: String,
    val nameEn: String,
    val nameEs: String,
    val iconName: String
)

val COMING_SOON_INSTRUMENTS = listOf(
    FutureInstrument("bateria", "Bateria", "Drums", "Batería", "Drum"),
    FutureInstrument("baixo", "Contrabaixo", "Bass Guitar", "Bajo", "Bass"),
    FutureInstrument("ukulele", "Ukulele", "Ukulele", "Ukelele", "Ukulele"),
    FutureInstrument("violino", "Violino", "Violin", "Violín", "Violin")
)
