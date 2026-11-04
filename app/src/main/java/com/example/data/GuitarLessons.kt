package com.example.data

import com.example.model.*

object GuitarLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1 (1-5)
            Pair("Anatomia do Violão", "Conhecer o tampo, braço, trastes, tarraxas, cavalete e boca do violão."),
            Pair("Postura e Ergonomia", "Aprender a sentar com a coluna ereta e apoiar o violão sem forçar o corpo."),
            Pair("As Cordas e Afinação EADGBE", "Memorizar os nomes das 6 cordas: Mi, Lá, Ré, Sol, Si, Mi."),
            Pair("Numeração dos Dedos", "Entender a mão esquerda (1, 2, 3, 4) e a mão direita (P, I, M, A)."),
            Pair("O Primeiro Som Limpo", "Tocar cordas soltas e pressionadas perto dos trastes sem trastejar."),

            // Módulo 2 (6-15)
            Pair("Acorde de Mi Menor (Em)", "Montar o primeiro acorde de dois dedos com precisão e clareza."),
            Pair("Acorde de Lá Menor (Am)", "Posicionar os dedos 1, 2 e 3 na primeira posição do braço."),
            Pair("Transição Entre Em e Am", "Trocar entre Mi Menor e Lá Menor no tempo sem pausar o ritmo."),
            Pair("Acorde de Dó Maior (C)", "Abrir a mão para alcançar a quinta corda na terceira casa."),
            Pair("Acorde de Sol Maior (G)", "Aprender o clássico acorde de Sol com dedos 1, 2 e 3."),
            Pair("Acorde de Ré Maior (D)", "Formar o triângulo nas três primeiras cordas."),
            Pair("Progressão Pop: G - Em - C - D", "Unir os quatro acordes mais famosos da música ocidental."),
            Pair("Acorde de Lá Maior (A)", "Encaixar três dedos na segunda casa sem esbarrar nas cordas vizinhas."),
            Pair("Acorde de Mi Maior (E)", "Sentir a diferença brilhante entre o acorde maior e o menor."),
            Pair("Acorde de Ré Menor (Dm)", "Dominar o formato melancólico do Ré Menor na 1ª casa."),

            // Módulo 3 (16-25)
            Pair("Compasso 4/4 e Contagem de Tempo", "Marcar os quatro tempos do compasso com o pé de forma estável."),
            Pair("Levada Básica com Polegar", "Tocar para baixo com suavidade mantendo o volume constante."),
            Pair("Levada Alternada Baixo e Cima", "Utilizar indicador e polegar ou palheta em movimentos contínuos."),
            Pair("A Batida Universal Pop/Rock", "Executar a famosa levada: Baixo, Baixo, Cima, Cima, Baixo, Cima."),
            Pair("Abafamento com a Palma (Palm Mute)", "Produzir o efeito percussivo apoiando a lateral da mão na ponte."),
            Pair("Compasso 3/4 e a Valsa Caipira", "Contar 1-2-3 e executar o baixo no tempo 1 e acordes nos tempos 2 e 3."),
            Pair("Levada Sincopada de Reggae", "Destacar os contratempos abafando as cordas com a mão esquerda."),
            Pair("Ritmo de Toada Sertaneja", "Acompanhar baladas acústicas com o toque tradicional brasileiro."),
            Pair("Batida de Samba Leve", "Coordenar o polegar nos baixos e dedos nas cordas agudas."),
            Pair("Resistência Rítmica a 80 BPM", "Manter uma levada constante por 2 minutos seguidos sem errar."),

            // Módulo 4 (26-40)
            Pair("Ode à Alegria (Beethoven) - Melodia", "Tocar a famosa melodia na primeira e segunda cordas."),
            Pair("Ode à Alegria com Harmonia", "Acompanhar a melodia utilizando os acordes C e G."),
            Pair("Brilha, Brilha Estrelinha", "Praticar saltos de cordas com uma melodia tradicional."),
            Pair("Parabéns a Você no Tom de Sol", "Cantar e tocar a música mais cantada do mundo com G, D7 e C."),
            Pair("Cai, Cai Balão", "Controlar o andamento em compasso binário alegre."),
            Pair("Ciranda Cirandinha", "Trocar de acorde exatamente na sílaba tônica das frases."),
            Pair("Asa Branca - Introdução Melódica", "Tocar o tema histórico de Luiz Gonzaga na primeira posição."),
            Pair("Asa Branca - Acompanhamento Rítmico", "Executar o baião clássico com os acordes G, C e D7."),
            Pair("Peixe Vivo", "Acompanhar uma cantiga rápida com trocas ágeis."),
            Pair("Greensleeves - Parte 1", "Entrar no clima medieval da canção tradicional inglesa em Am."),
            Pair("Greensleeves - Parte 2 e Conclusão", "Dominar o compasso 6/8 e as resoluções em Mi Maior."),
            Pair("Amazing Grace em Ré Maior", "Tocar a clássica melodia com acordes D, G e A7."),
            Pair("Scarborough Fair - Dedilhado", "Unir acordes menores com arpejos suaves."),
            Pair("Bella Ciao - O Canto Popular", "Aprender o tema em Lá Menor com ritmo enérgico."),
            Pair("Recital das Primeiras Canções", "Tocar uma apresentação completa com 3 músicas sem interrupção."),

            // Módulo 5 (41-50)
            Pair("Dedilhado Fundamental P-I-M-A", "Dedos Polegar, Indicador, Médio e Anelar em cada corda correspondente."),
            Pair("Dedilhado Contínuo P-I-M-A-M-I", "Criar um mar de notas arpejadas para baladas acústicas."),
            Pair("Técnica de Baixo Alternado", "Alternar a tônica e a quinta no polegar enquanto os dedos dedilham."),
            Pair("Ligados Ascendentes (Hammer-on)", "Tirar som com a força do dedo da mão esquerda batendo na casa."),
            Pair("Ligados Descendentes (Pull-off)", "Puxar a corda com o dedo da mão esquerda para soar a nota de trás."),
            Pair("Slides Elegantes no Braço", "Deslizar o dedo de uma casa para outra mantendo a sustentação da nota."),
            Pair("Harmônicos Naturais (Casa 12 e 7)", "Tocar de leve sobre os trastes de metal para soar notas cristalinas."),
            Pair("Exercício de Agilidade 'A Aranha'", "Trabalhar independência dos dedos 1-2-3-4 em todas as cordas."),
            Pair("Diferenças de Timbres (Junto à Ponte vs Boca)", "Explorar sons metálicos (sul ponticello) e aveludados (sul tasto)."),
            Pair("Expressão e Dinâmica Musical", "Tocar trechos suaves (piano) e fortes (forte) para emocionar o ouvinte."),

            // Módulo 6 (51-60)
            Pair("Notas Naturais e Acidentes (# e b)", "Compreender que entre Mi-Fá e Si-Dó não existem sustenidos."),
            Pair("A Fórmula da Escala Maior", "Dominar a sequência de intervalos: Tom - Tom - Semitom - Tom - Tom - Tom - Semitom."),
            Pair("Escala de Dó Maior em Duas Oitavas", "Percorrer as notas C-D-E-F-G-A-B-C com a digitação recomendada."),
            Pair("Intervalos Musicais na Prática", "Identificar de ouvido terças maiores, terças menores e quintas justas."),
            Pair("Como São Formados os Acordes Maiores", "Descobrir a fórmula da tríade maior: Tônica + 3ª Maior + 5ª Justa."),
            Pair("Como São Formados os Acordes Menores", "Entender o papel da 3ª Menor que dá o caráter melancólico ao acorde."),
            Pair("O Campo Harmônico de Dó Maior", "Conhecer a família de acordes: C, Dm, Em, F, G, Am e Bm(b5)."),
            Pair("Funções Harmônicas (Tônica, Subdominante, Dominante)", "Saber quando uma harmonia descansa ou pede resolução urgente."),
            Pair("Acordes com Sétima Dominante (X7)", "Adicionar a 7ª menor para criar a tensão perfeita que resolve na tônica."),
            Pair("O Uso do Capotraste", "Mudar o tom das músicas mantendo os mesmos formatos de acordes familiares."),

            // Módulo 7 (61-75)
            Pair("A Famosa Pestana de Fá Maior (F)", "Apoiar a lateral do dedo 1 reto sobre todas as 6 cordas na casa 1."),
            Pair("Técnica Sem Esforço para Pestanas", "Usar o peso do braço e o ponto de apoio ao invés de apertar o polegar."),
            Pair("Pestana na Quinta Corda: Si Menor (Bm)", "Formato essencial para centenas de canções populares."),
            Pair("Acorde de Dó Sustenido Menor (C#m)", "Deslocar o formato de Si Menor duas casas à frente."),
            Pair("Acorde de Si Maior (B)", "Encaixar a tríade na 4ª casa com pestana na 2ª casa."),
            Pair("Romance Anônimo (Tema Clássico)", "Tocar o lendário solo dedilhado da música clássica espanhola."),
            Pair("House of the Rising Sun - Dedilhado Completo", "Dominar a famosa sequência Am - C - D - F - Am - E7."),
            Pair("Fundamentos da Bossa Nova", "Aprender a levada clássica criada por João Gilberto no violão."),
            Pair("Acordes com Sétima Maior (7M)", "Enriquecer a sonoridade com acordes sofisticados como C7M e F7M."),
            Pair("Acordes Menores com Sétima (m7)", "Suavizar a harmonia com Dm7, Em7 e Am7."),
            Pair("Choro no Violão: O Baixaria", "Conectar acordes com frases melódicas executadas pelo polegar."),
            Pair("Minueto em Sol (Bach)", "Executar polifonia tocando melodia e baixo simultaneamente."),
            Pair("Inversões de Baixo (C/E, G/B, D/F#)", "Deixar a linha de baixo com condução suave e contínua."),
            Pair("Afinação Alternativa Drop D", "Baixar a 6ª corda de Mi para Ré e ganhar graves poderosos."),
            Pair("Interpretação e Fluência Intermediária", "Apresentar uma canção instrumental com interpretação madura."),

            // Módulo 8 (76-90)
            Pair("A Escala Pentatônica Menor", "O molde definitivo para improvisação no Rock, Blues e MPB."),
            Pair("Bends e Vibratos com Cordas de Nylon/Aço", "Adicionar expressão vocal puxando a corda para subir o tom."),
            Pair("A Famosa Blue Note", "Inserir a 4ª aumentada para dar aquele sabor inconfundível de Blues."),
            Pair("Estrutura do Blues Tradicional em 12 Compassos", "Tocar a sequência padrão com I7, IV7 e V7."),
            Pair("Técnicas Percussivas no Corpo do Violão", "Usar o polegar no tampo para simular bumbo e caixa."),
            Pair("Tapping a Duas Mãos no Braço", "Martelar notas com a mão direita diretamente na escala do instrumento."),
            Pair("Modo Dórico Aplicado", "Construir solos com a sonoridade modal moderna de Carlos Santana."),
            Pair("Modo Mixolídio e Baião Elétrico", "Explorar a 7ª menor sobre acordes maiores no estilo nordestino."),
            Pair("Acordes com Nona (add9 e 9)", "Dar brilho moderno aos acordes básicos adicionando o 2º grau acima da oitava."),
            Pair("Harmônicos Artificiais Octavados", "Tocar harmônicos em qualquer casa usando indicador e polegar direitos."),
            Pair("Rasgueado Flamenco Espanhol", "Abrir os dedos em leque sucessivo criando cascatas percussivas."),
            Pair("Leitura de Tablatura e Partitura", "Decifrar arranjos complexos publicados em livros e partituras."),
            Pair("Improvisação sobre a Cadência II-V-I", "A fórmula dourada da harmonia e jazz aplicada com criatividade."),
            Pair("Como Compor Sua Própria Música", "Estruturar introdução, estrofe, refrão, ponte e final marcante."),
            Pair("Masterclass Final do Violonista", "A celebração do curso completo: repertório, postura e diploma de honra!")
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

            val chord = when (lessonNum % 5) {
                1 -> ChordDiagramData("Em", listOf(0, 2, 2, 0, 0, 0), listOf(0, 2, 3, 0, 0, 0))
                2 -> ChordDiagramData("Am", listOf(-1, 0, 2, 2, 1, 0), listOf(0, 0, 2, 3, 1, 0))
                3 -> ChordDiagramData("C", listOf(-1, 3, 2, 0, 1, 0), listOf(0, 3, 2, 0, 1, 0))
                4 -> ChordDiagramData("G", listOf(3, 2, 0, 0, 0, 3), listOf(2, 1, 0, 0, 0, 3))
                else -> ChordDiagramData("D", listOf(-1, -1, 0, 2, 3, 2), listOf(0, 0, 0, 1, 3, 2))
            }

            list.add(
                Lesson(
                    id = "violao_lesson_$lessonNum",
                    instrument = InstrumentType.VIOLAO,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Violão do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Explicação Prática:\n")
                        append("Nesta aula focamos em precisão e sonoridade. No violão, a pressão das pontas dos dedos deve ser sempre perpendicular à escala, próxima ao traste de metal, garantindo que o som saia nítido e sem trastejos.\n\n")
                        append("Dica do Maestro Pipo:\n")
                        append("Mantenha o polegar atrás do braço do violão, na altura média, sem subir demais o polegar. Isso dará maior alcance e agilidade para todos os seus dedos!")
                    },
                    chordDiagram = chord,
                    audioNotes = listOf(
                        SoundNote("Nota Base", audioFreq, 400),
                        SoundNote("Terça", audioFreq * 1.25, 400),
                        SoundNote("Quinta", audioFreq * 1.5, 600)
                    ),
                    practicalExercise = "Pratique a transição e a digitação indicada nesta aula durante 5 minutos, tocando cada corda individualmente para conferir a clareza sonora.",
                    summary = "Você aprendeu $title com sucesso, aprimorando sua mecânica motora e percepção musical.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.VIOLAO, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
