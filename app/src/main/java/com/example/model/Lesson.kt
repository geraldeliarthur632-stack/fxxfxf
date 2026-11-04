package com.example.model

data class SoundNote(
    val noteName: String,
    val frequencyHz: Double,
    val durationMs: Int = 500
)

data class ChordDiagramData(
    val chordName: String,
    val frets: List<Int>, // -1 for mute (X), 0 for open, 1..5 for fret number
    val fingers: List<Int>, // 0 for none, 1=index, 2=middle, 3=ring, 4=pinky
    val baseFret: Int = 1,
    val barreFret: Int? = null
)

data class PianoDiagramData(
    val chordOrScaleName: String,
    val activeKeys: List<String>, // e.g. listOf("C4", "E4", "G4")
    val rootKey: String = "C4"
)

data class QuizQuestion(
    val id: String,
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val noteAudioPreview: SoundNote? = null
)

data class Lesson(
    val id: String,
    val instrument: InstrumentType,
    val moduleNumber: Int, // 1 to 8
    val moduleTitle: String,
    val lessonNumber: Int, // 1 to 90
    val title: String,
    val objective: String,
    val didacticContent: String,
    val chordDiagram: ChordDiagramData? = null,
    val pianoDiagram: PianoDiagramData? = null,
    val audioNotes: List<SoundNote> = emptyList(),
    val practicalExercise: String,
    val summary: String,
    val questions: List<QuizQuestion> = emptyList(),
    val durationMinutes: Int = 8,
    val xpReward: Int = 25
)

data class ModuleInfo(
    val moduleNumber: Int,
    val titlePt: String,
    val titleEn: String,
    val titleEs: String,
    val descPt: String,
    val descEn: String,
    val descEs: String,
    val startLesson: Int,
    val endLesson: Int,
    val iconName: String
) {
    val totalLessonsInModule: Int get() = (endLesson - startLesson) + 1

    fun getTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PT -> titlePt
        AppLanguage.EN -> titleEn
        AppLanguage.ES -> titleEs
    }
}
