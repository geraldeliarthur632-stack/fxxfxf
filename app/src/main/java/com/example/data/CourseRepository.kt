package com.example.data

import com.example.audio.AudioSynthesizer
import com.example.model.*

object CourseRepository {

    val MODULE_DEFINITIONS = listOf(
        ModuleInfo(
            moduleNumber = 1,
            titlePt = "1. Conhecendo o Instrumento",
            titleEn = "1. Knowing the Instrument",
            titleEs = "1. Conociendo el Instrumento",
            descPt = "Partes do instrumento, postura correta, afinação e primeiros toques.",
            descEn = "Instrument anatomy, correct posture, tuning and first sounds.",
            descEs = "Partes del instrumento, postura correcta, afinación y primeros toques.",
            startLesson = 1,
            endLesson = 5,
            iconName = "info"
        ),
        ModuleInfo(
            moduleNumber = 2,
            titlePt = "2. Primeiros Acordes e Notas",
            titleEn = "2. First Chords & Notes",
            titleEs = "2. Primeros Acordes y Notas",
            descPt = "Posicionamento das mãos, diagrama de cifras/teclas e primeiras harmonias.",
            descEn = "Hand positioning, chord charts/keyboard layout and first harmonies.",
            descEs = "Posición de manos, diagramas y primeras armonías.",
            startLesson = 6,
            endLesson = 15,
            iconName = "music_note"
        ),
        ModuleInfo(
            moduleNumber = 3,
            titlePt = "3. Ritmos e Exercícios Básicos",
            titleEn = "3. Rhythms & Basic Drills",
            titleEs = "3. Ritmos y Ejercicios Básicos",
            descPt = "Compasso, sincronismo, levadas rítmicas e exercícios de independência.",
            descEn = "Time signatures, timing, rhythm strumming and independence drills.",
            descEs = "Compás, sincronismo, patrones rítmicos y ejercicios de coordinación.",
            startLesson = 16,
            endLesson = 25,
            iconName = "timer"
        ),
        ModuleInfo(
            moduleNumber = 4,
            titlePt = "4. Primeiras Músicas",
            titleEn = "4. First Songs",
            titleEs = "4. Primeras Canciones",
            descPt = "Repertório folclórico e clássico em domínio público para tocar do início ao fim.",
            descEn = "Folk and classical public-domain repertoire to play from start to finish.",
            descEs = "Repertorio folclórico y clásico de dominio público para tocar de principio a fin.",
            startLesson = 26,
            endLesson = 40,
            iconName = "library_music"
        ),
        ModuleInfo(
            moduleNumber = 5,
            titlePt = "5. Técnicas e Dedilhados",
            titleEn = "5. Techniques & Arpeggios",
            titleEs = "5. Técnicas y Arpegios",
            descPt = "Articulações, dinâmica, dedilhados alternados e digitação ágil.",
            descEn = "Articulations, dynamics, fingerpicking patterns and agile phrasing.",
            descEs = "Articulaciones, dinámicas, arpegios y digitación ágil.",
            startLesson = 41,
            endLesson = 50,
            iconName = "touch_app"
        ),
        ModuleInfo(
            moduleNumber = 6,
            titlePt = "6. Teoria Musical Aplicada",
            titleEn = "6. Applied Music Theory",
            titleEs = "6. Teoría Musical Aplicada",
            descPt = "Escalas maiores, menores, intervalos, campo harmônico e formação de tríades.",
            descEn = "Major & minor scales, intervals, harmonic fields and triad formations.",
            descEs = "Escalas mayores, menores, intervalos, campo armónico y formación de tríadas.",
            startLesson = 51,
            endLesson = 60,
            iconName = "school"
        ),
        ModuleInfo(
            moduleNumber = 7,
            titlePt = "7. Músicas de Nível Intermediário",
            titleEn = "7. Intermediate Songs",
            titleEs = "7. Canciones Intermedias",
            descPt = "Peças mais desafiadoras com mudanças rápidas de acordes e nuances expressivas.",
            descEn = "Challenging pieces with fast transitions and expressive nuances.",
            descEs = "Piezas más complejas con cambios de acordes rápidos y matices expresivos.",
            startLesson = 61,
            endLesson = 75,
            iconName = "star"
        ),
        ModuleInfo(
            moduleNumber = 8,
            titlePt = "8. Técnicas Avançadas",
            titleEn = "8. Advanced Techniques",
            titleEs = "8. Técnicas Avanzadas",
            descPt = "Improvisação, harmonia moderna, velocidade, ornamentos e maestria no instrumento.",
            descEn = "Improvisation, modern harmony, speed, ornaments and instrument mastery.",
            descEs = "Improvisación, armonía moderna, velocidad, adornos y maestría musical.",
            startLesson = 76,
            endLesson = 90,
            iconName = "workspace_premium"
        )
    )

    fun getModules(): List<ModuleInfo> = MODULE_DEFINITIONS

    fun getModuleForLesson(lessonNumber: Int): ModuleInfo {
        return MODULE_DEFINITIONS.firstOrNull { lessonNumber in it.startLesson..it.endLesson }
            ?: MODULE_DEFINITIONS.first()
    }

    fun getLessonsForInstrument(instrument: InstrumentType): List<Lesson> {
        return when (instrument) {
            InstrumentType.VIOLAO -> GuitarLessons.allLessons
            InstrumentType.VIOLA_CAIPIRA -> ViolaLessons.allLessons
            InstrumentType.PIANO -> PianoLessons.allLessons
            InstrumentType.TECLADO -> KeyboardLessons.allLessons
            InstrumentType.BATERIA -> DrumsLessons.allLessons
            InstrumentType.BAIXO -> BassLessons.allLessons
            InstrumentType.UKULELE -> UkuleleLessons.allLessons
            InstrumentType.VIOLINO -> ViolinLessons.allLessons
            InstrumentType.CAVAQUINHO -> CavaquinhoLessons.allLessons
        }
    }

    private val allLessonsMap: Map<String, Lesson> by lazy {
        val map = HashMap<String, Lesson>(810)
        GuitarLessons.allLessons.forEach { map[it.id] = it }
        ViolaLessons.allLessons.forEach { map[it.id] = it }
        PianoLessons.allLessons.forEach { map[it.id] = it }
        KeyboardLessons.allLessons.forEach { map[it.id] = it }
        DrumsLessons.allLessons.forEach { map[it.id] = it }
        BassLessons.allLessons.forEach { map[it.id] = it }
        UkuleleLessons.allLessons.forEach { map[it.id] = it }
        ViolinLessons.allLessons.forEach { map[it.id] = it }
        CavaquinhoLessons.allLessons.forEach { map[it.id] = it }
        map
    }

    fun getLessonById(id: String): Lesson? = allLessonsMap[id]
}
