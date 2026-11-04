package com.example.data

import com.example.model.*

object KeyboardLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1: Conhecendo o Instrumento (1-5)
            Pair("O Teclado Eletrônico e Sintetizador", "Conhecer o painel, botões de comando, visor LCD, saídas de áudio e conexões MIDI/USB."),
            Pair("Teclas Sensíveis à Velocidade (Touch Response)", "Entender como a sensibilidade ao toque altera o volume e o timbre dos sintetizadores."),
            Pair("A Roda de Pitch Bend e Modulação", "Usar os controles manuais da mão esquerda para curvar notas e adicionar vibrato."),
            Pair("Navegando pelos Timbres (Voices / Presets)", "Alternar entre sons de Piano, Cordas (Strings), Metais (Brass) e Sintetizadores."),
            Pair("O Volume Geral e Equilíbrio de Fones", "Ajustar ganho correto para ensaiar sem danificar os ouvidos."),

            // Módulo 2: Primeiros Acordes e Notas (6-15)
            Pair("Modo Single Finger vs Fingered Chord", "Descobrir as duas formas de acionar o acompanhamento automático com a mão esquerda."),
            Pair("Acorde de Dó Maior (C) no Teclado", "Tocar a fundamental na mão esquerda e disparar o baixo contínuo."),
            Pair("Acorde de Sol Maior (G)", "Acionar a harmonia com o estilo de ritmo ativo."),
            Pair("Acorde de Fá Maior (F)", "A transição harmônica suave mantendo a batida no compasso."),
            Pair("Acorde de Lá Menor (Am)", "Explorar a sonoridade de balada eletrônica em modo menor."),
            Pair("Acorde de Ré Menor (Dm)", "Enriquecer o arranjo pop moderno."),
            Pair("Acorde de Mi Maior (E) e Sétimas (E7)", "Criar tensão com acompanhamentos automáticos modernos."),
            Pair("Troca de Acordes no Tempo 1 do Compasso", "Sincronizar a mudança exatamente na virada da bateria eletrônica."),
            Pair("Uso da Mão Direita para Frases Melódicas", "Tocar melodias simples enquanto a esquerda comanda os acordes."),
            Pair("O Recurso Hold / Memory de Acordes", "Deixar o som soando enquanto você prepara a próxima posição."),

            // Módulo 3: Ritmos e Exercícios Básicos (16-25)
            Pair("Explorando Estilos de Ritmo (Styles / Rhythms)", "Testar ritmos de 8-Beat, 16-Beat, Pop, Rock, Balada e Dança."),
            Pair("Controle de Andamento (Tempo e BPM)", "Ajustar a velocidade da música pelo display ou botão de rolagem."),
            Pair("O Botão Tap Tempo", "Definir o andamento exato dando toques ritmados no botão."),
            Pair("Os Botões Sync Start e Sync Stop", "Iniciar a bateria automaticamente no instante em que sua mão toca o teclado."),
            Pair("Usando as Viradas (Fill-in A e Fill-in B)", "Dar dinâmica às trocas de parte com viradas automáticas de bateria."),
            Pair("Introduções e Finais (Intro & Ending)", "Começar e finalizar músicas como uma banda completa profissional."),
            Pair("Ritmo de Balada Pop a 75 BPM", "Acompanhar canções lentas com timbres combinados."),
            Pair("Ritmo Dançante Dance/Electronic a 125 BPM", "Tocar em andamento acelerado com pulso constante."),
            Pair("Compasso 3/4 no Teclado (Estilos de Valsa)", "Dominar os estilos ternários com arranjos sinfônicos."),
            Pair("Treino de Resistência com Viradas a Cada 8 Compassos", "Manter a forma musical sem perder o tempo."),

            // Módulo 4: Primeiras Músicas (26-40)
            Pair("Ode à Alegria com Arranjo Orquestral", "Usar o timbre de orquestra de cordas e ritmo clássico."),
            Pair("Ode à Alegria - Versão Synth Pop", "Mudar o timbre para sintetizador retrô anos 80."),
            Pair("Brilha Estrelinha com Som de Caixa de Música (Music Box)", "Explorar timbres de percussão melódica."),
            Pair("Parabéns a Você no Ritmo de Marcha", "Celebrar com acompanhamento animado e metais."),
            Pair("Asa Branca no Ritmo de Baião Eletrônico", "Aproveitar os estilos brasileiros disponíveis no teclado."),
            Pair("Bella Ciao com Timbre de Acordeon", "Tocar o clássico italiano com o fole virtual do teclado."),
            Pair("Greensleeves com Harpa e Flauta", "Arranjo medieval com sons etéreos."),
            Pair("Amazing Grace com Órgão Eletrônico", "Usar som de órgão de igreja com trêmolo."),
            Pair("The Final Countdown (Tema do Teclado)", "O famoso riff de metais sintetizados dos anos 80."),
            Pair("Tema Espacial Futurista em Ré Menor", "Criar atmosferas cósmicas com timbres de Pad."),
            Pair("Clair de Lune com Piano Elétrico (Rhodes / DX7)", "Timbres de piano elétrico com chorus e delay."),
            Pair("Peixe Vivo em Ritmo de Samba Pop", "Divertido arranjo brasileiro com percussão digital."),
            Pair("Scarborough Fair com Sons Étnicos", "Utilizar instrumentos do mundo e flautas andinas."),
            Pair("Pop Balada Autoral", "Criar um tema pop unindo Intro, Estrofe, Refrão e Ending."),
            Pair("Recital de Teclado Módulo 4", "Apresentação com troca de timbres em tempo real."),

            // Módulo 5: Recursos de Sintetizador e Performance (41-50)
            Pair("O Recurso Dual / Layer (Dois Sons Juntos)", "Sobrepor Piano Acústico com Cordas para som de estúdio."),
            Pair("O Recurso Split (Divisão do Teclado)", "Colocar Contrabaixo na mão esquerda e Piano na direita."),
            Pair("Ajuste do Ponto de Divisão (Split Point)", "Mudar a tecla onde o teclado se divide entre baixo e solo."),
            Pair("O Recurso de Transposição (Transpose)", "Subir ou descer tons sem mudar a digitação das mãos."),
            Pair("Oitavação Rápida (Octave Shift)", "Levar o som para registros agudos ou supergraves com um toque."),
            Pair("Uso de Efeitos: Reverb e Chorus", "Dar sensação de catedral e amplitude espacial ao som."),
            Pair("O Arpejador Eletrônico (Arpeggiator)", "Segurar uma nota e deixar o teclado criar cascatas rítmicas."),
            Pair("Curva de Pitch Bend para Solos de Guitarra/Sax", "Adicionar bends de um tom em melodias solistas."),
            Pair("Vibrato Expressivo com a Roda de Modulação", "Fazer a nota tremer com elegância no final das frases."),
            Pair("Uso do Pedal de Sustain no Teclado", "Conectar pedal footswitch e controlar a sustentação."),

            // Módulo 6: Teoria Aplicada ao Teclado Moderno (51-60)
            Pair("A Tabela Geral MIDI (128 Timbres Padronizados)", "Compreender como os sintetizadores organizam os sons de 1 a 128."),
            Pair("Acordes com Baixo Invertido no Teclado", "Indicar ao sintetizador baixos diferentes da fundamental (C/E, G/B)."),
            Pair("Acordes Suspensos (Sus4 e Sus2)", "Criar suspense moderno em introduções e transições."),
            Pair("Acordes com Nona (Add9)", "A assinatura dos tecladistas de pop, gospel e balada."),
            Pair("A Família dos Acordes com Sétima no Display", "Como o teclado reconhece C7, Cmaj7 e Cm7 no visor."),
            Pair("Harmonia em Blocos Modernos (Voicings)", "Distribuir notas para não embolar com o baixo automático."),
            Pair("Escala Pentatônica com Timbres de Lead Synth", "Solos rápidos que cortam a mixagem de uma banda."),
            Pair("A Escala de Blues no Teclado", "Adicionar a Blue Note para riffs vigorosos."),
            Pair("Conexão MIDI e Áudio USB com Celular e PC", "Usar o teclado como controlador para gravar em aplicativos."),
            Pair("Armazenamento em Registros de Memória (Registration Memory)", "Salvar banco completo de timbre, ritmo e volume para o palco."),

            // Módulo 7: Performance Intermediária e Eletrônica (61-75)
            Pair("Estilos de EDM, House e Trance no Teclado", "Tocar ritmos de música eletrônica moderna."),
            Pair("Linhas de Baixo Sintetizado (Synth Bass)", "Executar graves pulsantes com a mão esquerda."),
            Pair("Sons de Lead Synth Monofônicos com Portamento", "O deslize característico da música pop e funk moderno."),
            Pair("Uso de Pads Flutuantes com Sweep de Filtro", "Atmosferas cinematográficas que preenchem o ambiente."),
            Pair("Loops e Disparadores de Pads Percussivos", "Disparar efeitos sonoros, palmas e batidas extras."),
            Pair("Performance de Funk e Soul com Clavinet", "O som percussivo clássico de Stevie Wonder."),
            Pair("Órgão Hammond com Efeito Rotary (Caixa Leslie)", "Acelerar e desacelerar a rotação do som de órgão."),
            Pair("Arranjos para Músicas Pop Atuais", "Como reproduzir os hits do momento no teclado."),
            Pair("Transições Suaves entre Timbres ao Vivo", "Mudar de som sem cortar a cauda do reverb."),
            Pair("Uso de Metrônomo Interno e Treino de Precisão", "Cravado no beat eletrônico."),
            Pair("Acompanhamento sem Ritmo Automático (Piano Bar)", "Tocar apenas com as próprias mãos criando o balanço."),
            Pair("Baladas Gospel e Pop com Piano e Pad", "A combinação mais tocada do mundo."),
            Pair("Música de Cinema e Trilha Sonora no Teclado", "Criar temas emotivos usando orquestra de cordas e trompas."),
            Pair("Cover Completo de Canção Internacional", "Unir bateria, baixo, harmonia e solo."),
            Pair("Apresentação Gravada do Módulo 7", "Executar uma performance eletrônica gravada."),

            // Módulo 8: Técnicas Avançadas e Produção (76-90)
            Pair("O Gravador Interno de Pistas (Song Recorder / Sequencer)", "Gravar bateria na pista 1, baixo na 2, harmonia na 3 e solo na 4."),
            Pair("Edição e Quantização de Notas Gravadas", "Corrigir pequenos atrasos de tempo no próprio teclado."),
            Pair("Criando Seu Próprio Estilo de Ritmo (Style Creator)", "Programar compassos originais de bateria e baixo."),
            Pair("Edição de Filtros: Cutoff e Resonance", "Modificar o brilho e o aspecto metálico de qualquer timbre."),
            Pair("Envelopes de Som (Attack, Decay, Sustain, Release - ADSR)", "Moldar a velocidade de entrada e cauda dos sons."),
            Pair("Modulação com Pedal de Expressão", "Controlar volume e efeitos em tempo real com o pé."),
            Pair("Improvisação Rápida com Duas Mãos", "Duelo de solos e respostas melódicas no teclado."),
            Pair("Harmonia Avançada para Tecladistas: Rearmonização", "Substituir acordes simples por acordes elegantes."),
            Pair("Uso de Apps de Instrumentos Virtuais (VST via MIDI)", "Expandir o teclado com milhares de sons modernos pelo celular."),
            Pair("Cuidados com Fonte de Alimentação, Cabos e Conectores", "Manter o instrumento protegido contra oscilações de energia."),
            Pair("Como Montar Seu Home Studio Básico", "Interface de áudio, monitores de referência e fones."),
            Pair("Produção de uma Faixa Completa no Teclado", "Compor, arranjar e finalizar uma música do zero."),
            Pair("O Tecladista na Banda: Espaço Sonoro e Função", "Saber quando tocar muito e quando apenas segurar o clima."),
            Pair("Ensaio e Checklist para Apresentação ao Vivo", "Configurar memórias para troca rápida em shows."),
            Pair("Diploma de Mestre dos Teclados e Sintetizadores", "A coroação do tecladista completo: diploma e recital final!")
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

            val pianoDiagram = when (lessonNum % 3) {
                1 -> PianoDiagramData("Acorde Pop C Major", listOf("C4", "E4", "G4"), "C4")
                2 -> PianoDiagramData("Acorde Synth F Major", listOf("F3", "A3", "C4"), "F3")
                else -> PianoDiagramData("Acorde Synth G Major", listOf("G3", "B3", "D4"), "G3")
            }

            list.add(
                Lesson(
                    id = "teclado_lesson_$lessonNum",
                    instrument = InstrumentType.TECLADO,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Teclado e Sintetizador do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Fundamento Eletrônico e Musical:\n")
                        append("No teclado eletrônico, você tem uma orquestra inteira nas pontas dos dedos. A mão esquerda controla a harmonia e o ritmo automático, enquanto a mão direita comanda as melodias, timbres principais e expressividade com a roda de pitch bend.\n\n")
                        append("Dica de ouro do Maestro Pipo:\n")
                        append("Sempre acione as trocas de acorde um microssegundo antes do tempo 1 do compasso para que a bateria eletrônica realize as viradas e trocas com perfeição!")
                    },
                    pianoDiagram = pianoDiagram,
                    audioNotes = listOf(
                        SoundNote("Fundamental", audioFreq, 350),
                        SoundNote("Quinta", audioFreq * 1.5, 350),
                        SoundNote("Oitava", audioFreq * 2.0, 500)
                    ),
                    practicalExercise = "Ative o acompanhamento no andamento indicado e pratique mudar os acordes exatamente no início de cada compasso durante 5 minutos.",
                    summary = "Parabéns! Você completou $title, dominando ainda mais a versatilidade do teclado eletrônico.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.TECLADO, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
