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
        category = "Cordas Acústicas"
    ),
    VIOLA_CAIPIRA(
        id = "viola",
        namePt = "Viola Caipira",
        nameEn = "Brazilian Viola",
        nameEs = "Viola Campesina",
        descPt = "Descubra a alma da música de raiz brasileira com 10 cordas de aço, ponteados, afinação Cebolão e toadas.",
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
        category = "Teclas e Acústico"
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
    ),
    BATERIA(
        id = "bateria",
        namePt = "Bateria",
        nameEn = "Drums",
        nameEs = "Batería",
        descPt = "Domine independência dos 4 membros, condução de chimbal, bumbo, caixa, viradas dinâmicas e grooves de todos os estilos.",
        descEn = "Master four-limb independence, hi-hat grooves, kick, snare, drum fills and diverse musical beats.",
        descEs = "Domina la independencia de las 4 extremidades, grooves de charles, bombo, caja y redobles dinámicos.",
        drawableRes = R.drawable.instrument_drums,
        tuningInfo = "Kit padrão: Bumbo, Caixa, Chimbal, Tons 1 e 2, Surdo e Pratos de Condução/Ataque",
        category = "Percussão e Ritmo"
    ),
    BAIXO(
        id = "baixo",
        namePt = "Contrabaixo",
        nameEn = "Bass Guitar",
        nameEs = "Bajo Eléctrico",
        descPt = "A espinha dorsal da banda: aprenda condução de baixo, slap, pizzicato, arpejos, escalas e conexão rítmica com a bateria.",
        descEn = "The groove foundation: learn walking bass, slap, fingerstyle, arpeggios and lock-in timing with drums.",
        descEs = "La base del ritmo: aprende walking bass, slap, técnica de dedos, arpegios y sincronización musical.",
        drawableRes = R.drawable.instrument_bass,
        tuningInfo = "E - A - D - G (Mi, Lá, Ré, Sol) em 4 cordas de aço",
        category = "Cordas Graves"
    ),
    UKULELE(
        id = "ukulele",
        namePt = "Ukulele",
        nameEn = "Ukulele",
        nameEs = "Ukelele",
        descPt = "Leve, alegre e portátil: toque suas primeiras canções em minutos com batidas havaianas, dedilhados e acordes simples.",
        descEn = "Fun, portable and light: play your first songs in minutes with Hawaiian strums, fingerpicking and chords.",
        descEs = "Alegre, portátil y accesible: toca tus primeras canciones con rasgueos hawaianos y acordes sencillos.",
        drawableRes = R.drawable.instrument_ukulele,
        tuningInfo = "G - C - E - A (Sol reentrante, Dó, Mi, Lá)",
        category = "Cordas Havaianas"
    ),
    VIOLINO(
        id = "violino",
        namePt = "Violino",
        nameEn = "Violin",
        nameEs = "Violín",
        descPt = "A nobreza do arco: desenvolva empunhadura do arco, afinação precisa sem trastes, vibrato, postura e obras clássicas e populares.",
        descEn = "The art of the bow: master bow hold, fretless intonation, vibrato, posture and classical melodies.",
        descEs = "El arte del arco: domina la sujeción del arco, afinación precisa sin trastes, vibrato y melodías clásicas.",
        drawableRes = R.drawable.instrument_violin,
        tuningInfo = "G - D - A - E (Sol, Ré, Lá, Mi) afinado em quintas justas",
        category = "Cordas Friccionadas"
    ),
    CAVAQUINHO(
        id = "cavaquinho",
        namePt = "Cavaquinho",
        nameEn = "Cavaquinho",
        nameEs = "Cavaquinho",
        descPt = "A essência do samba, choro e pagode: domine palhetadas rápidas, levadas percussivas, acordes com pestana e solos brilhantes.",
        descEn = "The soul of Brazilian Samba and Choro: master fast strumming, syncopated rhythms, bar chords and bright melodic solos.",
        descEs = "El alma del samba y choro brasileño: domina rasgueos rápidos, ritmos sincopados, cejillas y solos brillantes.",
        drawableRes = R.drawable.instrument_cavaquinho,
        tuningInfo = "D - G - B - D (Ré, Sol, Si, Ré) afinação tradicional",
        category = "Cordas Brasileiras"
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
