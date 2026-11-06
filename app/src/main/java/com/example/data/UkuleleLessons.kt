package com.example.data

import com.example.model.*

object UkuleleLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1 (1-5)
            Pair("Anatomia do Ukulele", "Conhecer o corpo pequeno, braço, 4 cordas de nylon, ponte e tarraxas."),
            Pair("Tamanhos de Ukulele (Soprano, Concerto, Tenor, Barítono)", "Descobrir as diferenças de tamanho, escala e sonoridade entre os tipos."),
            Pair("A Afinação Havaiana GCEA (Sol Reentrante)", "Compreender porque a 4ª corda (Sol) é afinada mais aguda que a 3ª (Dó)."),
            Pair("Postura e Apoio do Ukulele no Peito", "Segurar o instrumento junto ao corpo com o antebraço direito sem escorregar."),
            Pair("O Primeiro Rasgueio Suave com Indicador", "Descer com as costas da unha do indicador e subir com a polpa do dedo."),

            // Módulo 2 (6-15)
            Pair("Acorde de Dó Maior (C)", "Montar o primeiro acorde de apenas um dedo na 1ª corda, 3ª casa."),
            Pair("Acorde de Lá Menor (Am)", "Posicionar o dedo 2 na 4ª corda, 2ª casa."),
            Pair("Transição Suave Entre C e Am", "Trocar entre Dó e Lá Menor no tempo sem parar a batida."),
            Pair("Acorde de Fá Maior (F)", "Adicionar o dedo 1 na 2ª corda, 1ª casa mantendo o formato alegre."),
            Pair("Acorde de Sol Maior (G)", "Encaixar os dedos 1, 2 e 3 no triângulo clássico havaiano."),
            Pair("A Progressão Mágica C - G - Am - F", "Tocar os quatro acordes que formam 90% das músicas pop e havaianas."),
            Pair("Acorde de Ré Menor (Dm)", "Deslocar o dedo 3 para a 3ª corda formando o Dm melancólico."),
            Pair("Acorde de Mi Menor (Em)", "Subir a escadinha de três dedos nas cordas 1, 2 e 3."),
            Pair("Acorde de Lá Maior (A)", "Montar o Lá Maior com dedos 1 e 2 na 3ª e 4ª cordas."),
            Pair("Acorde de Sol com Sétima (G7)", "Dominar a tensão que pede resolução imediata de volta no Dó Maior."),

            // Módulo 3 (16-25)
            Pair("Compasso 4/4 e Batida Para Baixo Contínua", "Manter a pulsação constante com o polegar ou indicador."),
            Pair("A Famosa Batida Havaiana (Ilha Strum)", "Executar a levada: Baixo, Baixo-Cima, Cima-Baixo-Cima (↓ ↓↑ ↑↓↑)."),
            Pair("Abafamento com a Mão Direita (Chunk / Chnk)", "Produzir o estalo percussivo característico abafando com a palma."),
            Pair("Levada com Chunk Sincopado", "Inserir o chunk nos tempos 2 e 4 simulando a caixa da bateria."),
            Pair("Compasso 3/4 no Ukulele (Valsa Havaiana)", "Contar 1-2-3 com baixo no tempo 1 e batidas nos tempos 2 e 3."),
            Pair("Batida de Calipso e Reggae", "Acentuar os contratempos com toque seco e solto."),
            Pair("Dedilhados com Polegar e Três Dedos", "Designar o polegar para cordas 4 e 3, e dedos 1 e 2 para cordas 2 e 1."),
            Pair("Dedilhado Circular Havaiano (4-3-2-1)", "Criar cascatas de notas dedilhadas para baladas suaves."),
            Pair("Dedilhado de Balada (4-2-3-1)", "Arpejar os acordes criando atmosfera relaxante de praia."),
            Pair("Resistência Rítmica a 90 BPM", "Manter o Island Strum por 3 minutos sem perder a leveza."),

            // Módulo 4 (26-40)
            Pair("Somewhere Over the Rainbow (Israel Kamakawiwo'ole)", "A canção definitiva do ukulele havaiano com a batida clássica."),
            Pair("Riptide (Vance Joy)", "Aprender o sucesso pop moderno com acordes Am, G, C e F."),
            Pair("I'm Yours (Jason Mraz)", "Groove com chunk sincopado nos acordes C, G, Am e F."),
            Pair("Aloha 'Oe (Canção Tradicional do Havaí)", "Melodia e harmonia tradicional da realeza havaiana."),
            Pair("Lava (Tema da Disney / Pixar)", "Tocar a doce melodia sobre amor e vulcões tropicais."),
            Pair("Can't Help Falling in Love (Elvis Presley)", "Balada romântica dedilhada em compasso 6/8."),
            Pair("Brilha, Brilha Estrelinha no Ukulele", "Tocar a melodia na 1ª e 2ª cordas para treinar digitação."),
            Pair("Parabéns a Você no Ukulele", "Cantar e tocar a música festiva com C, G7 e F."),
            Pair("Asa Branca Adaptada para Ukulele", "O clássico do baião com o timbre brilhante das 4 cordas."),
            Pair("Cai Cai Balão e Cantigas Populares", "Transições rápidas e alegres para tocar em família."),
            Pair("La Bamba no Ukulele", "Ritmo latino rápido com acordes C, F e G."),
            Pair("Stand By Me em Versão Havaiana", "Caminhada de acordes com swing descontraído."),
            Pair("Don't Worry, Be Happy (Bobby McFerrin)", "Sorrir e tocar a progressão positiva C, Dm e F."),
            Pair("Count on Me (Bruno Mars)", "Música pop animada perfeita para cantar junto com amigos."),
            Pair("Recital Havaiano das Primeiras Músicas", "Apresentar 3 canções sem pausas com introdução e finalização."),

            // Módulo 5 (41-50)
            Pair("Dedilhado Alternado com Polegar (Travis Picking)", "Técnica folk adaptada para as 4 cordas do ukulele."),
            Pair("Ligados Ascendentes (Hammer-on) no Ukulele", "Tirar notas com a força do dedo caindo na casa."),
            Pair("Ligados Descendentes (Pull-off)", "Puxar a corda suavemente para fazer soar a nota de trás."),
            Pair("Slides Elegantes pelas Casas", "Deslizar o dedo de uma casa para outra mantendo o sustain."),
            Pair("Harmônicos Naturais na 12ª Casa", "Toques de sino cristalinos sobre os trastes de metal."),
            Pair("Técnica de Trêmolo Havaiano", "Mover o indicador para cima e para baixo em velocidade rápida contínua."),
            Pair("Rasgueado Rápido com Três Dedos", "Abrir os dedos em leque sucessivo para criar volume festivo."),
            Pair("Abafamento com a Mão Esquerda (Fret Mute)", "Aliviar a pressão dos dedos para estalar acordes de ska/reggae."),
            Pair("Timbres Junto ao Cavalete vs Junto ao Braço", "Explorar sons metálicos (sul ponticello) e aveludados no bocal."),
            Pair("Dinâmica Expressiva: Tocar Suave e Tocar Forte", "Trazer emoção variando a intensidade do rasgueio."),

            // Módulo 6 (51-60)
            Pair("Escala de Dó Maior no Ukulele", "Tocar C-D-E-F-G-A-B-C aproveitando as cordas soltas."),
            Pair("Fórmula da Escala Maior no Ukulele", "Entender a sequência de tons e semitons pelo braço."),
            Pair("Intervalos Musicais de Terça e Quinta", "Reconhecer de ouvido os intervalos que formam os acordes."),
            Pair("Como São Formados os Acordes Maiores no Ukulele", "A fórmula Tônica + 3ª Maior + 5ª Justa nas 4 cordas."),
            Pair("Como São Formados os Acordes Menores", "O papel da 3ª Menor que dá o toque nostálgico."),
            Pair("O Campo Harmônico de Dó Maior no Ukulele", "A família de acordes: C, Dm, Em, F, G, Am e Bdim."),
            Pair("Acordes com Sétima Dominante (C7, D7, E7, A7)", "Adicionar a sétima para dar o balanço do Blues no ukulele."),
            Pair("Acordes com Sétima Maior (C7M, F7M)", "A sonoridade sonhadora e poética da Bossa Nova no ukulele."),
            Pair("Acordes Menores com Sétima (Am7, Dm7, Em7)", "Suavizar a harmonia moderna com digitações de 1 ou 2 dedos."),
            Pair("O Uso do Capotraste no Ukulele", "Subir o tom das músicas mantendo os mesmos formatos familiares."),

            // Módulo 7 (61-75)
            Pair("A Pestana no Ukulele: Acorde de Si Bemol (Bb)", "Apoiar o dedo 1 nas duas primeiras cordas com firmeza."),
            Pair("Acorde de Si Menor (Bm) com Pestana", "Pestana completa na 2ª casa para centenas de canções."),
            Pair("Acorde de Ré Maior (D) Fechado", "Digitar três dedos juntos na 2ª casa sem esbarrar na 1ª corda."),
            Pair("Chord Melody: Tocando Melodia e Acorde Juntos", "A arte de tocar a canção completa sozinho no ukulele."),
            Pair("Ode à Alegria em Chord Melody", "Harmonizar a melodia clássica de Beethoven nas 4 cordas."),
            Pair("Bossa Nova no Ukulele: Desafinado", "Levada sofisticada com acordes de 7ª e notas abafadas."),
            Pair("Blues no Ukulele: Turnaround Clássico", "Frases de blues com pestanas e arpejos sincopados."),
            Pair("Fingerstyle Solo: Canção de Ninar Havaiana", "Arranjo instrumental com sustentação e dinâmica doce."),
            Pair("Inversões de Acordes pelo Braço", "Montar o acorde de C em três posições diferentes na escala."),
            Pair("Escala Pentatônica no Ukulele", "O mapa definitivo para improvisar solos em qualquer tom."),
            Pair("Frases Rápidas com Palheta de Feltro", "Usar a tradicional palheta macia de feltro para solo ágil."),
            Pair("Choro Brasileiro no Ukulele", "Tocar o ritmo brasileiro com precisão rítmica das cordas."),
            Pair("Afinação Alternativa Low G (Sol Grave)", "Substituir o Sol reentrante por uma corda grave encorpada."),
            Pair("Vamp Havaiano Tradicional (D7 - G7 - C)", "A famosa finalização havaiana que encerra as músicas."),
            Pair("Apresentação Intermediária: Show Solo", "Tocar duas músicas complexas em Chord Melody com fluência."),

            // Módulo 8 (76-90)
            Pair("Técnicas de Jake Shimabukuro: Velocidade e Ataque", "Inspirar-se no virtuoso do ukulele moderno mundial."),
            Pair("Rasgueado Flamenco Quádruplo", "Explosão de quatro batidas sucessivas em fração de segundo."),
            Pair("Harmônicos Artificiais Octavados", "Tocar harmônicos em qualquer casa usando indicador direito."),
            Pair("Modo Dórico e Mixolídio no Ukulele", "Escalas modais para improvisar em jazz e MPB."),
            Pair("Tapping com Duas Mãos no Braço do Ukulele", "Martelar notas agudas diretamente nos trastes."),
            Pair("Arpejos Estendidos com Nona e Décima Primeira", "Enriquecer canções autorais com acordes de jazz moderno."),
            Pair("Bending de Cordas de Nylon", "Puxar a corda para subir o tom com expressividade vocal."),
            Pair("Composição no Ukulele: Da Ideia à Canção", "Como criar introduções marcantes, estrofes e refrão memorável."),
            Pair("Leitura de Tablatura Avançada de Ukulele", "Decifrar arranjos complexos publicados por mestres internacionais."),
            Pair("Uso de Captadores e Pedais de Efeito no Ukulele", "Ligar em amplificadores com Reverb, Delay e Chorus."),
            Pair("Microfonação e Gravação em Home Studio", "Técnicas para capturar o som doce e natural do ukulele acústico."),
            Pair("Troca de Cordas e Manutenção do Instrumento", "Como amarrar as cordas no cavalete e manter a afinação estável."),
            Pair("Saúde e Relaxamento ao Tocar", "Prevenir tensões nos pulsos para tocar por horas com conforto."),
            Pair("Masterclass de Performance e Canto Simultâneo", "A harmonia perfeita entre voz e o som caloroso do ukulele."),
            Pair("Recital de Graduação do Ukulelista", "Apresentação solo triunfal, entrega do diploma de mestre e aplausos!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 4) {
                1 -> 392.00 // G4
                2 -> 261.63 // C4
                3 -> 329.63 // E4
                else -> 440.00 // A4
            }

            val chord = when (lessonNum % 4) {
                1 -> ChordDiagramData("C", listOf(0, 0, 0, 3), listOf(0, 0, 0, 3))
                2 -> ChordDiagramData("Am", listOf(2, 0, 0, 0), listOf(2, 0, 0, 0))
                3 -> ChordDiagramData("F", listOf(2, 0, 1, 0), listOf(2, 0, 1, 0))
                else -> ChordDiagramData("G", listOf(0, 2, 3, 2), listOf(0, 1, 3, 2))
            }

            list.add(
                Lesson(
                    id = "ukulele_lesson_$lessonNum",
                    instrument = InstrumentType.UKULELE,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Ukulele do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Explicação Prática:\n")
                        append("O segredo do ukulele é a leveza! Mantenha o pulso solto e flexível. Deixe as pontas dos dedos da mão esquerda pressionarem bem próximas aos trastes para que as notas soem límpidas e cantem com alegria!\n\n")
                        append("Dica do Maestro Pipo:\n")
                        append("Sorria ao tocar! O ukulele tem um som acolhedor e solar que conecta as pessoas instantaneamente!")
                    },
                    chordDiagram = chord,
                    audioNotes = listOf(
                        SoundNote("Sol (G4)", audioFreq, 350),
                        SoundNote("Dó (C4)", audioFreq * 0.66, 350),
                        SoundNote("Mi (E4)", audioFreq * 0.84, 350),
                        SoundNote("Lá (A4)", audioFreq * 1.12, 500)
                    ),
                    practicalExercise = "Pratique a batida e os acordes desta lição por 5 minutos mantendo o ritmo fluido e relaxado.",
                    summary = "Parabéns! Você concluiu $title, trazendo alegria e ritmo solar ao seu aprendizado no ukulele.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.UKULELE, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
