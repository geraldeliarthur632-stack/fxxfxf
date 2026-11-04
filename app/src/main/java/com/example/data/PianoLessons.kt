package com.example.data

import com.example.model.*

object PianoLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1: Conhecendo o Instrumento (1-5)
            Pair("O Piano e o Teclado de 88 Teclas", "Compreender a disposição das teclas brancas e pretas e a acústica do piano."),
            Pair("Postura Sentada e Curvatura dos Dedos", "Apoiar os pés no chão, manter os pulsos relaxados e dedos como se segurassem uma maçã."),
            Pair("Localizando o Dó Central (C4)", "Encontrar a tecla de referência Dó logo abaixo das duas teclas pretas centrais."),
            Pair("Numeração Oficial dos Dedos (1 a 5)", "Polegar = 1, Indicador = 2, Médio = 3, Anelar = 4, Mínimo = 5 em ambas as mãos."),
            Pair("Primeiro Toque com Peso do Braço", "Pressionar teclas usando a gravidade do braço sem forçar os tendões."),

            // Módulo 2: Primeiros Acordes e Notas (6-15)
            Pair("A Posição de Dó Maior na Mão Direita", "Colocar os dedos 1 a 5 nas teclas Dó, Ré, Mi, Fá, Sol (C4 a G4)."),
            Pair("A Posição de Dó Maior na Mão Esquerda", "Colocar os dedos 5 a 1 nas teclas Dó, Ré, Mi, Fá, Sol na região grave (C3 a G3)."),
            Pair("Primeiro Acorde: Dó Maior (C)", "Tocar a tríade Dó - Mi - Sol (C - E - G) com dedos 1, 3 e 5 simultaneamente."),
            Pair("Acorde de Sol Maior (G)", "Montar a tríade Sol - Si - Ré (G - B - D) com sonoridade alegre."),
            Pair("Acorde de Fá Maior (F)", "Pressionar Fá - Lá - Dó (F - A - C) mantendo a curvatura dos dedos."),
            Pair("Acorde de Lá Menor (Am)", "Descobrir o acorde menor relativo Lá - Dó - Mi (A - C - E)."),
            Pair("Acorde de Ré Menor (Dm)", "Tocar Ré - Fá - Lá (D - F - A) com o toque expressivo do piano."),
            Pair("Transição Harmônica C - G - C", "Trocar de acordes sem olhar para as mãos, guiando-se pelo tato."),
            Pair("A Progressão I - IV - V - I (C - F - G - C)", "A espinha dorsal da música tonal ocidental no piano."),
            Pair("Acordes Quebrados (Arpejos Simples)", "Tocar as notas da tríade uma a uma de forma fluida e ritmada."),

            // Módulo 3: Ritmos e Exercícios Básicos (16-25)
            Pair("Compasso 4/4 e Semínimas", "Contar 1, 2, 3, 4 tocando uma nota por tempo com precisão de metrônomo."),
            Pair("Mínimas (2 tempos) e Semibreves (4 tempos)", "Sustentar notas longas com contagem mental firme."),
            Pair("Coordenação Bimanual Inicial", "Mão esquerda toca acordes inteiros enquanto a direita toca notas soltas."),
            Pair("Movimento Paralelo das Mãos", "Ambas as mãos subindo a escala em oitavas juntas."),
            Pair("Movimento Contrário das Mãos", "Uma mão se afasta da outra espelhando o movimento dos polegares."),
            Pair("Compasso 3/4 (Ritmo de Valsa)", "Tocar o baixo no tempo 1 e dois acordes nos tempos 2 e 3."),
            Pair("Colcheias e Ritmo Dobrado", "Tocar duas notas por tempo (1 e, 2 e, 3 e, 4 e)."),
            Pair("Pausas Musicais e Silêncio Ativo", "Aprender a levantar os dedos no momento exato da pausa sem barulho mecânico."),
            Pair("Toque Legato (Conectado)", "Conectar uma nota à outra sem deixar buracos de silêncio."),
            Pair("Toque Staccato (Destacado)", "Produzir notas saltadas e leves como gotas de chuva."),

            // Módulo 4: Primeiras Músicas (26-40)
            Pair("Ode à Alegria (Beethoven) - Mão Direita", "Tocar o hino clássico com melodia fluida e expressiva."),
            Pair("Ode à Alegria - Duas Mãos Juntas", "Unir melodia na direita com acordes de sustentação na esquerda."),
            Pair("Brilha, Brilha Estrelinha no Piano", "Canção tradicional com saltos de quinta justa (Dó para Sol)."),
            Pair("Parabéns a Você Completo", "Tocar com harmonia de C, G e F para celebrações familiares."),
            Pair("Canoa Virou (Folclore Brasileiro)", "Canção em compasso binário animado."),
            Pair("Asa Branca - Arranjo Didático para Piano", "O clássico nordestino adaptado com harmonia nos bordões."),
            Pair("Jingle Bells / Bate o Sino", "Praticar notas repetidas com o mesmo dedo sem travar a mão."),
            Pair("Dó-Ré-Mi (A Noviça Rebelde)", "Subir e descer a escala maior com diversão."),
            Pair("Minueto em Sol (Christian Petzold / Bach)", "O primeiro passo no contraponto barroco gracioso."),
            Pair("Für Elise (Beethoven) - O Tema Famoso", "Tocar as notas Mi - Ré# - Mi - Ré# - Mi - Si - Ré - Dó - Lá."),
            Pair("Für Elise - Acompanhamento Arpejado na Esquerda", "Arpejos de Lá Menor e Mi Maior na clave de Fá."),
            Pair("Clair de Lune (Debussy - Tema Simplificado)", "Mergulhar nas harmonias impressionistas suaves."),
            Pair("Greensleeves no Piano", "Compasso 6/8 em Lá Menor com arpejos de harpa."),
            Pair("Cielito Lindo", "Canção popular em ritmo ternário alegre."),
            Pair("Recital de Piano Módulo 4", "Performance com gravação e autoavaliação das primeiras obras."),

            // Módulo 5: Técnicas e Dedilhados (41-50)
            Pair("Exercício de Hanon nº 1", "Fortalecer a independência dos dedos 4 e 5 em ambas as mãos."),
            Pair("Exercício de Hanon nº 2", "Trabalhar abertura e agilidade articular."),
            Pair("Passagem do Polegar por Baixo (Thumb Under)", "O segredo técnico para tocar escalas longas sem interrupção."),
            Pair("Passagem dos Dedos por Cima", "Descer escalas com fluidez sem desequilibrar a mão."),
            Pair("O Pedal de Sustentação (Damper Pedal)", "Pisar e trocar o pedal a cada troca de acorde para não embolar."),
            Pair("O Pedal Una Corda (Pedal Suave)", "Conhecer a alteração tímbrica e redução de volume."),
            Pair("Arpejos de Dó Maior em Duas Oitavas", "Percorrer o teclado em cascata com sincronismo."),
            Pair("Acordes em Bloco vs Acordes Quebrados (Baixo Alberti)", "O clássico acompanhamento do período clássico Mozartiano."),
            Pair("Dinâmica: Pianissimo (pp) a Fortissimo (ff)", "Controlar a velocidade de descida da tecla para modular o volume."),
            Pair("Crescendo e Decrescendo", "Fazer a música respirar com ondas graduais de intensidade."),

            // Módulo 6: Teoria Musical Aplicada ao Piano (51-60)
            Pair("A Clave de Sol e a Linha Suplementar do Dó", "Ler partituras na clave de Sol com segurança."),
            Pair("A Clave de Fá e as Notas Graves", "Desmistificar a leitura da mão esquerda."),
            Pair("Armadura de Clave e Acidentes", "Entender sustenidos e bemóis no início da partitura."),
            Pair("Escala de Dó Maior com Digitação Padrão", "Mão direita: 1-2-3-1-2-3-4-5; Mão esquerda: 5-4-3-2-1-3-2-1."),
            Pair("Escala de Sol Maior (F#)", "Primeira escala com acidente na armadura."),
            Pair("Escala de Fá Maior (Bb)", "A escala com bemol e passagem do 4º dedo."),
            Pair("Inversões de Acordes: Estado Fundamental, 1ª e 2ª Inversão", "Tocar C/E e C/G para conexões suaves."),
            Pair("Acordes com Sétima (C7, G7, Cmaj7)", "Construir acordes de quatro notas (tétrades)."),
            Pair("O Ciclo das Quintas no Teclado", "Compreender como todas as 12 tonalidades se conectam."),
            Pair("Leitura à Primeira Vista no Piano", "Estratégias para ler notas antes mesmo dos dedos tocarem."),

            // Módulo 7: Músicas de Nível Intermediário (61-75)
            Pair("Prelúdio em Dó Maior (J.S. Bach)", "A lendária peça do Cravo Bem Temperado com arpejos hipnóticos."),
            Pair("Prelúdio em Dó Maior - Conclusão e Pedal", "Finalizar a obra de Bach com controle de tempo e rubato."),
            Pair("Sonata ao Luar (Beethoven) - Adagio Sostenuto", "Tocar as tercinas misteriosas em Dó sustenido menor."),
            Pair("Noturno Op. 9 No. 2 (Chopin - Tema Principal)", "Fraseado lírico cantábile imitando uma voz de ópera."),
            Pair("Marcha Turca (Mozart - Tema Didático)", "Articulação brilhante e staccato perolado."),
            Pair("Cânone em Ré (Pachelbel) no Piano", "A famosa linha de baixo com variações melódicas progressivas."),
            Pair("Valsa em Lá Menor (Chopin)", "Equilíbrio entre a melancolia eslava e a elegância francesa."),
            Pair("The Entertainer (Scott Joplin) - Ragtime Básico", "O salto do baixo stride com síncopes divertidas."),
            Pair("Gymnopédie No. 1 (Erik Satie)", "Harmonias flutuantes e atmosfera meditativa modernista."),
            Pair("Ave Maria (Gounod/Bach)", "Sobrepor melodia vocal ao acompanhamento de arpejos."),
            Pair("Rhapsody in Blue (Gershwin - Tema)", "O encontro do piano erudito com o Jazz americano."),
            Pair("Ária na Corda Sol (Bach)", "Condução de vozes polifônicas independentes."),
            Pair("Carmina Burana (O Fortuna - Redução)", "Energia percussiva e acordes com peso total de braço."),
            Pair("Bella Ciao - Arranjo Pianístico Rápido", "Velocidade e precisão nas duas mãos."),
            Pair("Apresentação de Meio de Curso no Piano", "Recital gravado com três peças de estilos contrastantes."),

            // Módulo 8: Técnicas Avançadas (76-90)
            Pair("Polirritmia Básica (2 contra 3)", "Tocar duas notas em uma mão enquanto a outra toca três."),
            Pair("Trinados e Ornamentos Clássicos (Mordentes e Grupetos)", "Velocidade dos dedos 2 e 3 sem tensão."),
            Pair("Escalas em Terças e Sextas Paralelas", "Subir o teclado com duas vozes na mesma mão."),
            Pair("Oitavas Rápidas com Pulso Flexível", "Tocar oitavas sem travar o antebraço."),
            Pair("Técnica de Stride Piano (Salto de Baixo e Acorde)", "A arte dos pianistas do Harlem nos anos 30."),
            Pair("Harmonia Moderna e Jazz Voicings (Rootless Chords)", "Tocar acordes sem a fundamental para dar espaço ao contrabaixo."),
            Pair("Improvisação sobre a Escala Pentatônica e Blues", "Criar solos ao vivo no piano com total liberdade."),
            Pair("Pedal de Meia-Troca e Conexões Tímbricas", "Técnicas refinadas para pianos de cauda."),
            Pair("Interpretação e Rubato Romântico", "Acelerar e desacelerar o andamento com bom gosto artístico."),
            Pair("Como Memorizar Peças Longas", "Memória visual, auditiva, muscular e analítica."),
            Pair("Rotina de Aquecimento e Prevenção de Lesões", "Exercícios de alongamento e cuidados para pianistas."),
            Pair("Regulagem e Afinação Mecânica de Pianos", "Como funcionam martelos, feltros e a tabela de cordas."),
            Pair("Transposição Instantânea ao Teclado", "Mudar uma música de tom em tempo real."),
            Pair("Composição e Arranjo Próprio para Piano Solo", "Escrever sua própria peça pianística."),
            Pair("Diploma de Mestre do Piano", "A consagração do pianista completo: recital e honra ao mérito!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 7) {
                1 -> 261.63 // C4
                2 -> 293.66 // D4
                3 -> 329.63 // E4
                4 -> 349.23 // F4
                5 -> 392.00 // G4
                6 -> 440.00 // A4
                else -> 493.88 // B4
            }

            val pianoDiagram = when (lessonNum % 4) {
                1 -> PianoDiagramData("Acorde de Dó Maior (C)", listOf("C4", "E4", "G4"), "C4")
                2 -> PianoDiagramData("Acorde de Sol Maior (G)", listOf("G3", "B3", "D4"), "G3")
                3 -> PianoDiagramData("Acorde de Fá Maior (F)", listOf("F3", "A3", "C4"), "F3")
                else -> PianoDiagramData("Acorde de Lá Menor (Am)", listOf("A3", "C4", "E4"), "A3")
            }

            list.add(
                Lesson(
                    id = "piano_lesson_$lessonNum",
                    instrument = InstrumentType.PIANO,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Piano do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Instrução Pianística:\n")
                        append("No piano, o som é produzido pelo impacto do martelo nas cordas. A velocidade do seu toque controla a dinâmica: toques mais lentos produzem sons suaves e aveludados, enquanto toques mais rápidos produzem sons brilhantes e fortes.\n\n")
                        append("Conselho do Maestro Pipo:\n")
                        append("Mantenha os ombros relaxados e os pulsos flexíveis. O peso do seu próprio braço é suficiente para fazer o piano cantar sem esforço!")
                    },
                    pianoDiagram = pianoDiagram,
                    audioNotes = listOf(
                        SoundNote("Dó", 261.63, 400),
                        SoundNote("Mi", 329.63, 400),
                        SoundNote("Sol", 392.00, 600)
                    ),
                    practicalExercise = "Toque o acorde ou padrão desta aula 5 vezes seguidas com cada mão separadamente e depois com as duas juntas de forma relaxada.",
                    summary = "Excelente! Você concluiu $title, aprimorando seu toque e percepção no piano.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.PIANO, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
