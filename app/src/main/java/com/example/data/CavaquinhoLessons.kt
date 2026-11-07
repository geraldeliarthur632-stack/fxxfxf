package com.example.data

import com.example.model.*

object CavaquinhoLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1 (1-5)
            Pair("Anatomia do Cavaquinho", "Conhecer o corpo, tampo de pinho, escala, trastes, cavalete e tarraxas do cavaquinho."),
            Pair("Postura e Posicionamento do Instrumento", "Apoiar o cavaquinho no peito e antebraço sem tensionar o punho ou o ombro."),
            Pair("Afinação Tradicional DGBD (Ré, Sol, Si, Ré)", "Memorizar as 4 cordas de aço da 4ª (Ré grave) até a 1ª (Ré agudo)."),
            Pair("Empunhadura da Palheta", "Segurar a palheta entre polegar e indicador com firmeza e flexibilidade."),
            Pair("A Primeira Palhetada Limpa", "Tocar cordas soltas com movimento de pêndulo do punho sem travar o braço."),

            // Módulo 2 (6-15)
            Pair("Acorde de Sol Maior (G)", "Montar o clássico acorde de Sol aberto no cavaquinho com sonoridade brilhante."),
            Pair("Acorde de Ré com Sétima (D7)", "Posicionar os dedos para a preparação harmônica dominante no tom de Sol."),
            Pair("Transição Rítmica entre G e D7", "Trocar os acordes no tempo 1 sem pausar a batida de palheta."),
            Pair("Acorde de Dó Maior (C)", "Montar a subdominante fundamental com dedos 1, 2 e 3."),
            Pair("Acorde de Lá Menor (Am)", "Aprender a sonoridade menor melancólica tão usada nas rodas de samba."),
            Pair("Acorde de Mi Menor (Em)", "Encaixar os dedos na segunda e terceira casas com firmeza."),
            Pair("Primeira Cadência de Pagode: G - Em - Am - D7", "Dominar o famoso 'quadrado de Sol', base de centenas de sambas."),
            Pair("Acorde de Ré Maior (D)", "Montar o acorde no formato triangular característico."),
            Pair("Acorde de Lá com Sétima (A7)", "Preparar a cadência para o tom de Ré Maior."),
            Pair("Quadrado de Ré: D - Bm - Em - A7", "Executar a segunda cadência mais tocada do samba."),

            // Módulo 3 (16-25)
            Pair("O Compasso 2/4 do Samba", "Compreender a métrica binária do samba e o tempo forte no tempo 2."),
            Pair("Palhetada Básica de Samba (Baixo e Cima)", "Desenvolver o movimento contínuo de leque com o punho relaxado."),
            Pair("A Batida Sincopada de Partido Alto", "Marcar a síncope com toques acentuados nos contratempos."),
            Pair("Palhetada de Pagode Romântico", "Executar a levada suave com abafamentos rítmicos na mão esquerda."),
            Pair("Levada Rápida de Samba-Enredo", "Treinar velocidade e resistência a 110 BPM mantendo o pique."),
            Pair("O Balanço do Choro Tradicional", "Intercalar baixos marcados e acordes staccato com elegância."),
            Pair("Abafamento Rítmico (Rasqueado e Mute)", "Soltar a pressão da mão esquerda para criar o efeito percussivo."),
            Pair("Dinâmica: Toques Fortes e Fracos", "Colorir a levada com acentos dinâmicos que dão o suingue brasileiro."),
            Pair("Palhetada de Marchinha e Frevo", "Acelerar a palhetada alternada em andamentos festivos e carnavalescos."),
            Pair("Resistência Rítmica a 100 BPM", "Manter a levada de samba constante por 3 minutos sem perder a precisão."),

            // Módulo 4 (26-40)
            Pair("O Que É, O Que É? (Gonzaguinha)", "Acompanhar o clássico hino da alegria com o quadrado de Sol."),
            Pair("Trem das Onze (Adoniran Barbosa) - Parte 1", "Tocar a estrofe do samba paulista mais famoso com Am e E7."),
            Pair("Trem das Onze - Refrão e Conclusão", "Completar a música com as transições de Dm, Am e E7 no tempo certo."),
            Pair("Não Deixe o Samba Morrer", "Tocar a introdução e harmonia marcante imortalizada por Alcione."),
            Pair("Canto das Três Raças (Clara Nunes)", "Trabalhar o peso e a emoção do samba de raiz em tom menor."),
            Pair("Aquarela Brasileira (Silas de Oliveira)", "Executar o samba-enredo imortal com levada vibrante e contínua."),
            Pair("Vou Festejar (Beth Carvalho)", "Tocar o refrão mais cantado do pagode com G, Bm e E7."),
            Pair("Foi Um Rio Que Passou em Minha Vida", "Acompanhar a obra-prima de Paulinho da Viola com acordes líricos."),
            Pair("As Rosas Não Falam (Cartola) - Harmonia", "Entrar na genialidade de Cartola com acordes de passagem em Ré Menor."),
            Pair("As Rosas Não Falam - Solo da Introdução", "Pontear as notas agudas da introdução clássica nas cordas 1 e 2."),
            Pair("Carinhoso (Pixinguinha) - Primeira Parte", "Harmonizar a introdução do hino do choro com acordes cheios."),
            Pair("Carinhoso - Segunda Parte e Modulação", "Acompanhar a mudança harmônica expressiva para o tom relativo."),
            Pair("O Mundo É Um Moinho (Cartola)", "Dominar a levada serena e os acordes com baixo invertido."),
            Pair("Brasileirinho (Waldir Azevedo) - Tema Principal", "Tocar o primeiro tema do choro mais virtuoso do cavaquinho."),
            Pair("Apresentação de Repertório Completo", "Apresentar um pot-pourri de 3 sambas sem interrupção nem perda de andamento."),

            // Módulo 5 (41-50)
            Pair("Pestanas no Cavaquinho", "Pressionar as 4 cordas com o dedo indicador sem travar nem cansar a mão."),
            Pair("Acordes Diminutos de Passagem (º)", "Utilizar acordes diminutos simétricos para ligar acordes vizinhos."),
            Pair("Acordes com Sétima Maior (7M)", "Enriquecer a sonoridade com a sofisticação da Bossa Nova e do Choro."),
            Pair("Acordes Meio-Diminutos (m7b5)", "Formar a preparação clássica II - V - I em tonalidades menores."),
            Pair("Ponteados Rápidos nas Cordas Agudas", "Alternar palheta estritamente para cima e para baixo em frases melódicas."),
            Pair("Frases de Resposta entre Versos", "Preencher as pausas do cantor com pequenos bordões e licks de cavaquinho."),
            Pair("Técnica de Trêmolo com a Palheta", "Produzir a nota contínua e rápida vibrando o punho sobre uma corda."),
            Pair("Uso de Harmônicos Naturais", "Tocar os harmônicos brilhantes no 12º e 7º trastes para finalizar frases."),
            Pair("Arpejos com Palhetada Sweep", "Deslizar a palheta continuamente pelas quatro cordas com precisão."),
            Pair("Velocidade e Agilidade na Escala Cromática", "Exercitar os quatro dedos em todas as 12 casas mantendo a limpeza sonora."),

            // Módulo 6 (51-60)
            Pair("Formação da Escala Maior no Cavaquinho", "Visualizar o mapa de intervalos de tom e semitom na escala de Ré Maior."),
            Pair("Campo Harmônico Maior em Tétrades", "Entender os 7 acordes do campo harmônico com sétimas (7M, m7, 7, m7b5)."),
            Pair("O Ciclo de Quintas no Cavaquinho", "Mapear as tonalidades vizinhas e antecipar modulações harmônicas."),
            Pair("Cadência II - V - I Maior", "Dominar a progressão harmônica mais importante do choro e do jazz."),
            Pair("Cadência II - V - I Menor", "Encadear m7(b5) - 7(b9) - m6 com condução melódica de vozes."),
            Pair("Substitutos de Acordes e Dominantes Secundários", "Inserir acordes dominantes transitórios antes de qualquer grau."),
            Pair("Inversões de Acordes: Baixos na 4ª e 3ª Cordas", "Evitar saltos longos na escala usando as diferentes inversões do mesmo acorde."),
            Pair("Escala Pentatônica Maior no Cavaquinho", "Tocar as 5 notas mágicas para improvisar sobre o samba e o pagode."),
            Pair("Escala Pentatônica Menor e Blue Note", "Inserir a nota de blues (b5) para criar solos cheios de pegada e tensão."),
            Pair("Leitura Básica de Partitura na Clave de Sol", "Ler notas na pauta e associar imediatamente às casas do cavaquinho."),

            // Módulo 7 (61-75)
            Pair("Noites Cariocas (Jacob do Bandolim)", "Tocar o choro saltitante com acordes em staccato e suingue clássico."),
            Pair("Apanhei-te, Cavaquinho (Ernesto Nazareth)", "Encarar o clássico histórico com síncopes refinadas e andamento vivo."),
            Pair("Tico-Tico no Fubá (Zequinha de Abreu)", "Praticar a agilidade de palheta e os arpejos rápidos na tonalidade de Lá Menor."),
            Pair("Disritmia (Martinho da Vila)", "Acompanhar o balanço carioca com acordes cheios e pausas expressivas."),
            Pair("Chega de Saudade (Tom Jobim) - Parte A", "Dominar os acordes sofisticados de Bossa Nova adaptados ao cavaquinho."),
            Pair("Chega de Saudade - Parte B em Maior", "Mudar da atmosfera menor para o brilho maior com transições perfeitas."),
            Pair("Flor Amorosa (Joaquim Callado)", "Interpretar o choro pioneiro com ornamentos e dinâmica elegante."),
            Pair("Pedacinhos do Céu (Waldir Azevedo)", "Tocar a melodia doce e comovente com vibrato suave na mão esquerda."),
            Pair("Samba da Minha Terra (Dorival Caymmi)", "Capturar o suingue baiano com levada de partido alto contagiante."),
            Pair("Na Baixa do Sapateiro (Ary Barroso)", "Explorar modulações ricas com acordes de passagem em compasso sincopado."),
            Pair("Doce de Coco (Jacob do Bandolim)", "Sensibilidade harmônica com acordes invertidos e arpejos aveludados."),
            Pair("Choro Negro (Paulinho da Viola)", "Executar o choro moderno com acordes densos e condução impecável."),
            Pair("Feitiço da Vila (Noel Rosa)", "Acompanhar a poesia de Vila Isabel com harmonia precisa e tradicional."),
            Pair("Manias da Minha Gente", "Tocar o samba acelerado com corte de palheta e acentos percussivos."),
            Pair("Recital Intermediário do Cavaquinista", "Tocar 4 temas instrumentais de choro e samba com afinação impecável."),

            // Módulo 8 (76-90)
            Pair("Improvisação sobre o Quadrado de Samba", "Construir solos melódicos conectando notas-alvo dos acordes."),
            Pair("Uso de Cromatismos e Notas de Passagem", "Criar fluência no solo conectando tons da escala com notas cromáticas."),
            Pair("Palhetada Dupla Contínua em Alta Velocidade", "Superar os 130 BPM com resistência física, sem dor e sem perder o tempo."),
            Pair("Frases em Terças e Sextas Paralelas", "Tocar duas cordas simultâneas criando harmonias brilhantes de cordas duplas."),
            Pair("Acordes com Tensões: 9ª, 11ª e 13ª", "Embelezar os arranjos de samba com acordes modernos de 4 sons ricos."),
            Pair("Harmonização de Melodias (Chord Melody)", "Tocar a melodia e o acompanhamento ao mesmo tempo no cavaquinho."),
            Pair("Arranjo Solo de 'Asa Branca' em Chord Melody", "Construir a obra completa unindo linha melódica e harmonia simultâneas."),
            Pair("Técnicas de Repique de Mão no Tampo", "Usar as unhas e a lateral do dedo para estalar no tampo simulando o tamborim."),
            Pair("Roda de Samba Real: Como se Portar e Segurar o Centro", "Manter a afinação, o andamento e a condução firme junto ao pandeiro e surdo."),
            Pair("A Arte da Introdução Espontânea", "Criar introduções marcantes de 4 compassos para chamar o cantor no tom certo."),
            Pair("Modulações Harmônicas em Rodas de Samba", "Mudar de tom no meio da roda sem engasgar o ritmo nem os cantores."),
            Pair("Choro Moderno e Linguagem Contemporânea", "Explorar harmonias inspiradas em Hamilton de Holanda e Waldir Azevedo."),
            Pair("Gravação em Estúdio: Timbre e Microfonação", "Escolher cordas, palhetas e posicionar o microfone para obter som profissional."),
            Pair("Grand Finale: Concerto Solo do Cavaquinho", "Executar uma suíte completa de Choro e Samba demonstrando todo o domínio."),
            Pair("Diploma de Mestre do Cavaquinho", "Revisão geral das 90 aulas, comemoração e conquista do troféu de mestre do samba!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 4) {
                1 -> 293.66 // D4
                2 -> 392.00 // G4
                3 -> 493.88 // B4
                else -> 587.33 // D5
            }

            val chord = when (lessonNum % 5) {
                1 -> ChordDiagramData("G", listOf(0, 0, 0, 0), listOf(0, 0, 0, 0)) // Sol aberto tradicional D-G-B-D
                2 -> ChordDiagramData("D7", listOf(0, 2, 1, 0), listOf(0, 2, 1, 0))
                3 -> ChordDiagramData("C", listOf(2, 0, 1, 2), listOf(2, 0, 1, 3))
                4 -> ChordDiagramData("Em", listOf(2, 0, 0, 2), listOf(1, 0, 0, 2))
                else -> ChordDiagramData("Am", listOf(2, 2, 1, 2), listOf(2, 3, 1, 4))
            }

            list.add(
                Lesson(
                    id = "cavaquinho_lesson_$lessonNum",
                    instrument = InstrumentType.CAVAQUINHO,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso completo de Cavaquinho do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico:\n$objective\n\n")
                        append("Instrução Didática Prática:\n")
                        append("No cavaquinho, o som característico do samba e do choro nasce do movimento solto e pendular do punho direito. Não tencione o braço; a palheta deve tocar as cordas com leveza e elasticidade, produzindo o estalo cristalino típico das cordas de aço.\n\n")
                        append("Dica do Maestro Pipo:\n")
                        append("Mantenha as pontas dos dedos da mão esquerda perpendiculares à escala para que nenhuma corda soe abafada involuntariamente. Pratique com metrônomo aumentando a velocidade aos poucos!")
                    },
                    chordDiagram = chord,
                    audioNotes = listOf(
                        SoundNote("Nota Raiz", audioFreq, 350),
                        SoundNote("Terça", audioFreq * 1.25, 350),
                        SoundNote("Quinta", audioFreq * 1.5, 500)
                    ),
                    practicalExercise = "Pratique a digitação e a levada indicada nesta aula durante 5 a 10 minutos diários com o instrumento afinado em DGBD.",
                    summary = "Você concluiu a aula '$title' com excelência, dominando novos fundamentos do cavaquinho.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.CAVAQUINHO, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
