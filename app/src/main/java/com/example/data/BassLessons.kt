package com.example.data

import com.example.model.*

object BassLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1 (1-5)
            Pair("Anatomia do Contrabaixo", "Conhecer o corpo, braço, captadores (Precision e Jazz Bass), ponte e tarraxas."),
            Pair("Postura, Correia e Ergonomia", "Ajustar a altura do baixo para manter o punho reto em pé ou sentado."),
            Pair("As 4 Cordas e Afinação EADG", "Memorizar as notas das 4 cordas: Mi (E), Lá (A), Ré (D) e Sol (G)."),
            Pair("Técnica de Apoio com Dedos (Pizzicato)", "Alternar indicador e médio (I-M) apoiando o dedo na corda de cima (rest stroke)."),
            Pair("O Primeiro Som Limpo sem Trastejos", "Pressionar as casas com a ponta dos dedos 1, 2 e 4 perto dos trastes."),

            // Módulo 2 (6-15)
            Pair("Notas na 4ª Corda (Mi, Fá, Sol)", "Localizar as primeiras notas fundamentais no Mi grave."),
            Pair("Notas na 3ª Corda (Lá, Si, Dó)", "Explorar a corda Lá e a primeira oitava central."),
            Pair("Oitavas no Baixo (O Formato Universal)", "Aprender a fórmula mágica: 2 cordas acima e 2 casas à frente."),
            Pair("Condução Básica em Semínimas (Walking)", "Tocar notas firmes e contínuas no tempo do bumbo."),
            Pair("Acorde Maior: Tônica e Quinta", "Criar a sustentação harmônica perfeita tocando a tônica e a 5ª justa."),
            Pair("Acorde Menor: A Terça Menor no Baixo", "Diferenciar a terça maior da terça menor na condução harmônica."),
            Pair("Arpejo da Tríade Maior", "Conectar Tônica, 3ª Maior e 5ª Justa em movimento ascendente."),
            Pair("Arpejo da Tríade Menor", "Construir linhas melancólicas e profundas para baladas."),
            Pair("A Sétima Menor (Grau 7)", "Completar a tétrade dominante que conduz com energia."),
            Pair("A Famosa Linha de Blues em 12 Compassos", "Executar a clássica caminhada de baixo do Blues tradicional."),

            // Módulo 3 (16-25)
            Pair("Compasso 4/4 e Sincronia com o Bumbo", "Tocar exatamente junto com a batida do bumbo da bateria."),
            Pair("Uso do Polegar Flutuante (Floating Thumb)", "Mover o polegar direito para abafar cordas graves que não estão soando."),
            Pair("Controle de Abafamento com Ambas as Mãos", "Evitar ressonâncias indesejadas que sujam o som da banda."),
            Pair("Levada Pop Rock em Colcheias Retas", "Manter notas constantes com palhetada ou dedos em andamento firme."),
            Pair("Notas Fantasmas (Ghost Notes)", "Produzir estalos percussivos com a mão esquerda semiapoiada."),
            Pair("Compasso 3/4 e a Valsa no Baixo", "Marcar o tempo 1 com grave e tempos 2 e 3 com acordes ou notas leves."),
            Pair("Levada de Reggae: O Peso dos Graves", "Cortar os agudos no amplificador e tocar com notas longas e profundas."),
            Pair("Levada de Funk Clássico em Semicolcheias", "Inserir síncopes e notas curtas sincopadas cheias de suingue."),
            Pair("Ligados no Baixo (Hammer-on e Pull-off)", "Tirar notas sem palhetar usando a força da mão esquerda."),
            Pair("Resistência Rítmica a 90 BPM", "Sustentar um groove de rock por 3 minutos sem perder a precisão."),

            // Módulo 4 (26-40)
            Pair("Stand By Me (Ben E. King) - Linha Lendária", "Tocar o riff de baixo mais reconhecido da história da música."),
            Pair("Another One Bites the Dust (Queen)", "Dominar o groove seco e marcante de John Deacon."),
            Pair("Billie Jean (Michael Jackson)", "Executar a linha contínua e hipnótica em Fá sustenido menor."),
            Pair("Come Together (The Beatles)", "Tocar a linha melódica e com slides de Paul McCartney."),
            Pair("Samba no Baixo: O Surdo no Tempo 2", "Dar o balanço do samba brasileiro enfatizando o segundo tempo."),
            Pair("Baião no Baixo: O Balanço do Nordeste", "Simular o ritmo sincopado do zabumba com notas staccato."),
            Pair("Bossa Nova: Garota de Ipanema", "Conduzir a harmonia sofisticada de Tom Jobim com suavidade."),
            Pair("Forró Pé de Serra no Baixo", "Acompanhar a sanfona com agilidade e precisão nos baixos."),
            Pair("Balada Country e Sertaneja", "Alternar tônica e quinta com baixo alternado clássico."),
            Pair("Linha de Rockabilly e Walking Rápido", "Subir e descer arpejos em alta velocidade."),
            Pair("Groove de Disco Music dos Anos 70", "Oitavas dançantes no baixo com chimbal aberto."),
            Pair("Hard Rock: Riffs em Uníssono com a Guitarra", "Tocar colado na guitarra para dobrar o peso da música."),
            Pair("Linha de Soul e Motown (James Jamerson)", "Usar apenas o dedo indicador criando melodias sofisticadas."),
            Pair("Melodia Folclórica: Asa Branca no Baixo", "Tocar o tema melódico na região média do instrumento."),
            Pair("Recital de Canções: Apresentação Completa", "Executar 3 clássicos conectados sem interrupção de tempo."),

            // Módulo 5 (41-50)
            Pair("Fundamentos do Slap: O Golpe de Polegar (Thumb)", "Bater com a lateral óssea do polegar contra a escala de metal."),
            Pair("O Puxão do Slap: O Pop com o Indicador", "Puxar a corda aguda soltando-a com estalo cortante."),
            Pair("Combinação Thumb + Pop em Oitavas", "O clássico 'tum-tá' do funk moderno."),
            Pair("Slap com Ghost Notes Duplas", "Adicionar percussão de bateria entre os toques de slap."),
            Pair("Técnica de Palheta no Contrabaixo", "Usar palheta pesada para obter ataque cortante no Punk e Metal."),
            Pair("Palhetada Alternada Rápida", "Mover a palheta para baixo e para cima em semicolcheias."),
            Pair("Harmônicos Naturais no Baixo (Casas 12, 7 e 5)", "Tocar sobre os trastes para tirar sons de sino cristalinos."),
            Pair("Slides Longos e Glissandos", "Deslizar o dedo por todo o braço criando transições dramáticas."),
            Pair("Vibrato Expressivo no Contrabaixo", "Oscilar o dedo paralelamente ao traste para sustentar notas longas."),
            Pair("Técnica de 3 Dedos na Mão Direita", "Usar anelar, médio e indicador para tocar em velocidades extremas."),

            // Módulo 6 (51-60)
            Pair("Escala Maior no Baixo em 1 Oitava", "Dominar o desenho móvel que serve para qualquer tonalidade."),
            Pair("Escala Menor Natural no Baixo", "Aprender a digitação menor com 3ª, 6ª e 7ª menores."),
            Pair("Escala Pentatônica Menor", "O molde definitivo para preenchimentos e solos no baixo."),
            Pair("A Blue Note e Frases de Blues", "Inserir a 4ª aumentada para dar o sotaque bluesy."),
            Pair("Intervalos de Sexta e Décima", "Tocar baixo e nota aguda ao mesmo tempo criando acordes no baixo."),
            Pair("O Campo Harmônico Maior Completo", "Saber os 7 acordes que acompanham qualquer música em tom maior."),
            Pair("Inversões de Baixo (Notas na Barra: C/E, G/B)", "Conduzir a harmonia com baixos que não são a tônica."),
            Pair("Escala Menor Harmônica", "Explorar a sonoridade árabe e neoclássica com a 7ª maior."),
            Pair("Modo Dórico no Funk e Jazz", "Criar grooves modais sobre acordes com sétima menor."),
            Pair("Modo Mixolídio no Rock e Baião", "Tocar sobre acordes dominantes com a 7ª menor."),

            // Módulo 7 (61-75)
            Pair("Walking Bass Tradicional de Jazz", "Caminhar 4 notas por compasso delineando os acordes."),
            Pair("Aproximação Cromática no Walking Bass", "Usar semitons vizinhos para chegar suavemente na tônica."),
            Pair("Choro Brasileiro: O Baixo Contador", "Frases ágeis e contrapontos que dialogam com o solista."),
            Pair("Maracatu e Afoxé no Baixo", "O balanço dos tambores afro-brasileiros traduzidos em cordas."),
            Pair("Linhas de Jaco Pastorius: Harmônicos e Agilidade", "O mestre que transformou o baixo em instrumento solista."),
            Pair("Técnica de Baixo Fretless (Sem Trastes)", "Entonação pura com vibrato vocal expressivo."),
            Pair("Tapping a Duas Mãos no Baixo", "Martelar notas com a mão direita criando polifonia."),
            Pair("Groove em Compasso 5/4 e 7/8", "Conduzir tempos quebrados sem tropeçar no ritmo."),
            Pair("Uso de Efeitos: Envelope Filter (Auto-Wah)", "O clássico som aquático e funky nos graves."),
            Pair("Uso de Efeitos: Drive e Fuzz no Baixo", "Distorcer os médios mantendo os graves intactos."),
            Pair("Uso de Efeitos: Chorus e Octaver", "Engordar o timbre com oitavas abaixo e ambiência estéreo."),
            Pair("Construção de Linhas Autorais", "Como criar um riff de baixo inesquecível para uma nova música."),
            Pair("Preenchimentos (Fills) sem Atrapalhar a Canção", "Saber o momento exato de fazer um floreio no final do compasso."),
            Pair("Afinação Drop D no Baixo", "Abaixar a 4ª corda para Ré e ganhar graves destruidores."),
            Pair("Solo de Baixo Estruturado", "Tocar um solo com começo, meio dramático e desfecho marcante."),

            // Módulo 8 (76-90)
            Pair("Slap Avançado: Double Thumbing (Polegar de Ida e Volta)", "Usar o polegar como uma palheta para velocidade dupla."),
            Pair("Tapping em Acordes de 4 Vozes", "Tocar piano no braço do baixo com as duas mãos."),
            Pair("Arpejos com Saltos de Cordas Rápidos", "Percorrer todo o braço do Mi grave ao Sol agudo."),
            Pair("Improvisação sobre a Cadência II-V-I", "A linguagem universal do Jazz e da MPB moderna no baixo."),
            Pair("Baixo de 5 e 6 Cordas", "Explorar o Si grave (B0) e o Dó agudo (C4) dos baixos modernos."),
            Pair("Coordenação Dinâmica Máxima", "Tocar notas sussurradas e explosões sonoras no mesmo compasso."),
            Pair("Timbre e Equalização para Qualquer Sala", "Ajustar graves, médios-graves, médios-altos e agudos no show."),
            Pair("Gravação de Baixo: Linha Direta (DI) vs Microfone", "Segredos do som profissional de baixo em estúdio de ponta."),
            Pair("Manutenção e Regulagem do Contrabaixo", "Ajustar o tensor, altura das cordas (action) e oitavas na ponte."),
            Pair("Leitura de Clave de Fá à Primeira Vista", "Decifrar partituras orquestrais e de big band sem hesitar."),
            Pair("Liderança Harmônica da Banda", "Ser o maestro silencioso que conduz todos os outros músicos."),
            Pair("Composição de Músicas Instrumentais de Baixo", "Criar peças autorais que exploram todo o potencial do instrumento."),
            Pair("Saúde Muscular e Prevenção de Dores no Baixo", "Técnicas ergonômicas para tocar instrumentos pesados por horas."),
            Pair("Masterclass de Groove e Presença de Palco", "A energia que faz o público dançar e vibrar com os graves."),
            Pair("Apresentação de Formatura do Baixista", "Recital solo virtuoso, entrega do diploma de excelência e aplausos!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 4) {
                1 -> 41.20 // E1
                2 -> 55.00 // A1
                3 -> 73.42 // D2
                else -> 98.00 // G2
            }

            list.add(
                Lesson(
                    id = "baixo_lesson_$lessonNum",
                    instrument = InstrumentType.BAIXO,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Contrabaixo do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Explicação Prática:\n")
                        append("No contrabaixo, cada nota deve ter sustentação e precisão milimétrica. Apoie o polegar no captador ou na corda superior e alterne os dedos indicador e médio com firmeza e suavidade.\n\n")
                        append("Dica do Maestro Pipo:\n")
                        append("Toque pensando no bumbo da bateria! Quando o baixo e o bumbo tocam no mesmo milissegundo, a música ganha um peso e balanço irresistíveis!")
                    },
                    audioNotes = listOf(
                        SoundNote("Fundamental", audioFreq, 500),
                        SoundNote("Quinta Justa", audioFreq * 1.5, 400),
                        SoundNote("Oitava", audioFreq * 2.0, 600)
                    ),
                    practicalExercise = "Pratique a condução e o posicionamento dos dedos por 5 minutos mantendo o som limpo e sem ruídos das outras cordas.",
                    summary = "Parabéns! Você concluiu $title, construindo a base sonora sólida que move toda a música.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.BAIXO, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
