package com.example.model

data class TunerString(
    val stringNumberPt: String,
    val stringNumberEn: String,
    val stringNumberEs: String,
    val noteLetter: String,
    val noteFullNamePt: String,
    val noteFullNameEn: String,
    val noteFullNameEs: String,
    val frequencyHz: Float,
    val descriptionPt: String,
    val descriptionEn: String,
    val descriptionEs: String
) {
    fun getStringNumber(lang: AppLanguage): String = when (lang) {
        AppLanguage.PT -> stringNumberPt
        AppLanguage.EN -> stringNumberEn
        AppLanguage.ES -> stringNumberEs
    }

    fun getNoteFullName(lang: AppLanguage): String = when (lang) {
        AppLanguage.PT -> noteFullNamePt
        AppLanguage.EN -> noteFullNameEn
        AppLanguage.ES -> noteFullNameEs
    }

    fun getDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.PT -> descriptionPt
        AppLanguage.EN -> descriptionEn
        AppLanguage.ES -> descriptionEs
    }
}

object InstrumentTunerData {
    fun getStringsForInstrument(instrument: InstrumentType): List<TunerString> {
        return when (instrument) {
            InstrumentType.VIOLAO -> listOf(
                TunerString("6ª Corda", "6th String", "6ª Cuerda", "E", "Mi (E2)", "E (E2 - Low E)", "Mi (E2 - Bordón)", 82.41f, "Bordão grave", "Low E bass", "Bordón grave"),
                TunerString("5ª Corda", "5th String", "5ª Cuerda", "A", "Lá (A2)", "A (A2)", "La (A2)", 110.00f, "Bordão médio", "A string bass", "Bordón medio"),
                TunerString("4ª Corda", "4th String", "4ª Cuerda", "D", "Ré (D3)", "D (D3)", "Re (D3)", 146.83f, "Bordão agudo", "D string", "Bordón agudo"),
                TunerString("3ª Corda", "3rd String", "3ª Cuerda", "G", "Sol (G3)", "G (G3)", "Sol (G3)", 196.00f, "Corda média", "G string", "Cuerda media"),
                TunerString("2ª Corda", "2nd String", "2ª Cuerda", "B", "Si (B3)", "B (B3)", "Si (B3)", 246.94f, "Corda aguda", "B string", "Cuerda aguda"),
                TunerString("1ª Corda", "1st String", "1ª Cuerda", "E", "Mi (E4)", "E (E4 - High E)", "Mi (E4 - Prima)", 329.63f, "Mizinha aguda", "High E prima", "Prima aguda")
            )
            InstrumentType.VIOLA_CAIPIRA -> listOf(
                TunerString("5º Par", "5th Course", "5º Par", "E", "Mi (E2)", "E (E2)", "Mi (E2)", 82.41f, "Par mais grave", "Lowest course", "Par más grave"),
                TunerString("4º Par", "4th Course", "4º Par", "B", "Si (B2)", "B (B2)", "Si (B2)", 123.47f, "Par oitavado", "Octaved course", "Par octavado"),
                TunerString("3º Par", "3rd Course", "3º Par", "G#", "Sol# (G#3)", "G# (G#3)", "Sol# (G#3)", 207.65f, "Terça da afinação", "Tuning third", "Tercera de afinación"),
                TunerString("2º Par", "2nd Course", "2º Par", "B", "Si (B3)", "B (B3)", "Si (B3)", 246.94f, "Par uníssono", "Unison course", "Par al unísono"),
                TunerString("1º Par", "1st Course", "1º Par", "E", "Mi (E4)", "E (E4)", "Mi (E4)", 329.63f, "Par uníssono agudo", "High unison course", "Par al unísono agudo")
            )
            InstrumentType.BAIXO -> listOf(
                TunerString("4ª Corda", "4th String", "4ª Cuerda", "E", "Mi (E1)", "E (E1 - Low E)", "Mi (E1 - Grave)", 41.20f, "Subgrave profundo", "Deep sub-bass", "Subgrave profundo"),
                TunerString("3ª Corda", "3rd String", "3ª Cuerda", "A", "Lá (A1)", "A (A1)", "La (A1)", 55.00f, "Grave encorpado", "Punchy bass", "Grave con cuerpo"),
                TunerString("2ª Corda", "2nd String", "2ª Cuerda", "D", "Ré (D2)", "D (D2)", "Re (D2)", 73.42f, "Médio-grave", "Mid-bass punch", "Medio-grave"),
                TunerString("1ª Corda", "1st String", "1ª Cuerda", "G", "Sol (G2)", "G (G2 - High G)", "Sol (G2 - Agudo)", 98.00f, "Corda mais aguda", "Treble string", "Cuerda más aguda")
            )
            InstrumentType.UKULELE -> listOf(
                TunerString("4ª Corda", "4th String", "4ª Cuerda", "G", "Sol (G4)", "G (G4 - High G)", "Sol (G4)", 392.00f, "Sol reentrante", "Re-entrant G", "Sol reentrante"),
                TunerString("3ª Corda", "3rd String", "3ª Cuerda", "C", "Dó (C4)", "C (C4 - Low C)", "Do (C4)", 261.63f, "Corda mais grave", "Lowest string", "Cuerda más grave"),
                TunerString("2ª Corda", "2nd String", "2ª Cuerda", "E", "Mi (E4)", "E (E4)", "Mi (E4)", 329.63f, "Terça do acorde", "Chord third", "Tercera del acorde"),
                TunerString("1ª Corda", "1st String", "1ª Cuerda", "A", "Lá (A4)", "A (A4 - High A)", "La (A4)", 440.00f, "Lá brilhante", "Bright A lead", "La brillante")
            )
            InstrumentType.VIOLINO -> listOf(
                TunerString("4ª Corda", "4th String", "4ª Cuerda", "G", "Sol (G3)", "G (G3 - Low G)", "Sol (G3)", 196.00f, "Corda mais grave", "Warm low G", "Cuerda más grave"),
                TunerString("3ª Corda", "3rd String", "3ª Cuerda", "D", "Ré (D4)", "D (D4)", "Re (D4)", 293.66f, "Quinta intermediária", "Middle D string", "Quinta intermedia"),
                TunerString("2ª Corda", "2nd String", "2ª Cuerda", "A", "Lá (A4)", "A (A4 - Concert pitch)", "La (A4 - Diapasón)", 440.00f, "Diapasão orquestral", "Concert pitch 440 Hz", "Diapasón orquestral"),
                TunerString("1ª Corda", "1st String", "1ª Cuerda", "E", "Mi (E5)", "E (E5 - High E)", "Mi (E5 - Aguda)", 659.25f, "Agudo brilhante", "Bright treble steel", "Agudo brillante")
            )
            InstrumentType.PIANO, InstrumentType.TECLADO -> listOf(
                TunerString("Tecla C", "Key C", "Tecla C", "C", "Dó (C4)", "C (Middle C4)", "Do (Do central C4)", 261.63f, "Dó Central", "Middle C key", "Do Central"),
                TunerString("Tecla D", "Key D", "Tecla D", "D", "Ré (D4)", "D (D4)", "Re (D4)", 293.66f, "Segunda maior", "Major second", "Segunda mayor"),
                TunerString("Tecla E", "Key E", "Tecla E", "E", "Mi (E4)", "E (E4)", "Mi (E4)", 329.63f, "Terça maior", "Major third", "Tercera mayor"),
                TunerString("Tecla F", "Key F", "Tecla F", "F", "Fá (F4)", "F (F4)", "Fa (F4)", 349.23f, "Quarta justa", "Perfect fourth", "Cuarta justa"),
                TunerString("Tecla G", "Key G", "Tecla G", "G", "Sol (G4)", "G (G4)", "Sol (G4)", 392.00f, "Quinta justa", "Perfect fifth", "Quinta justa"),
                TunerString("Tecla A", "Key A", "Tecla A", "A", "Lá (A4)", "A (A4 - 440 Hz)", "La (La4 - 440 Hz)", 440.00f, "Diapasão padrão", "Standard A440", "Diapasón estándar"),
                TunerString("Tecla B", "Key B", "Tecla B", "B", "Si (B4)", "B (B4)", "Si (B4)", 493.88f, "Sétima maior", "Major seventh", "Séptima mayor")
            )
            InstrumentType.BATERIA -> listOf(
                TunerString("Bumbo", "Bass Drum", "Bombo", "C", "Bumbo (Sub)", "Kick Drum (22\")", "Bombo (Sub 22\")", 60.00f, "Ataque grave", "Deep punch", "Golpe grave"),
                TunerString("Surdo", "Floor Tom", "Goliat", "G", "Surdo de Chão", "Floor Tom (16\")", "Tom de suelo", 98.00f, "Pele de 16\"", "Resonant 16\" head", "Parche de 16\""),
                TunerString("Tom 2", "Mid Tom", "Tom 2", "C", "Tom Médio", "Mid Tom (12\")", "Tom medio (12\")", 130.81f, "Pele de 12\"", "12\" rack tom", "Parche de 12\""),
                TunerString("Tom 1", "High Tom", "Tom 1", "E", "Tom Agudo", "High Tom (10\")", "Tom agudo (10\")", 164.81f, "Pele de 10\"", "10\" high rack tom", "Parche de 10\""),
                TunerString("Caixa", "Snare Drum", "Caja", "A", "Caixa Principal", "Snare (14\")", "Caja principal", 220.00f, "Pele porosa 14\"", "Coated batter head", "Parche poroso 14\"")
            )
            InstrumentType.CAVAQUINHO -> listOf(
                TunerString("4ª Corda", "4th String", "4ª Cuerda", "D", "Ré (D4)", "D (D4 - Low D)", "Re (D4 - Bordón)", 293.66f, "Corda mais grave", "Lowest string", "Cuerda más grave"),
                TunerString("3ª Corda", "3rd String", "3ª Cuerda", "G", "Sol (G4)", "G (G4)", "Sol (G4)", 392.00f, "Corda média", "Middle string", "Cuerda media"),
                TunerString("2ª Corda", "2nd String", "2ª Cuerda", "B", "Si (B4)", "B (B4)", "Si (B4)", 493.88f, "Corda aguda", "Treble string", "Cuerda aguda"),
                TunerString("1ª Corda", "1st String", "1ª Cuerda", "D", "Ré (D5)", "D (D5 - High D)", "Re (D5 - Prima)", 587.33f, "Corda mais aguda", "Highest string", "Cuerda más aguda")
            )
        }
    }
}
