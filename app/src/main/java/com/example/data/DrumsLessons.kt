package com.example.data

import com.example.model.*

object DrumsLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1 (1-5)
            Pair("Anatomia da Bateria", "Conhecer as peças: bumbo, caixa, chimbal, tons, surdo e pratos."),
            Pair("Empunhadura das Baquetas (Grip)", "Aprender o Matched Grip equilibrando o fulcro com polegar e indicador."),
            Pair("Postura no Banco e Pedais", "Ajustar a altura do banco formando ângulo de 90° e posicionar os pés nos pedais."),
            Pair("O Primeiro Toque na Caixa", "Tirar som consistente tocando no centro da pele com rebote natural."),
            Pair("Controle de Rebote e Toques Simples", "Executar toques alternados direita-esquerda (R L R L) relaxadamente."),

            // Módulo 2 (6-15)
            Pair("O Pulso do Chimbal", "Marcar semínimas constantes abrindo e fechando o pedal do chimbal."),
            Pair("Coordenação Bumbo e Caixa", "Alternar o bumbo no tempo 1 e a caixa no tempo 2 com precisão."),
            Pair("O Primeiro Ritmo Básico de Rock", "Unir bumbo nos tempos 1 e 3, caixa nos 2 e 4, e chimbal em colcheias."),
            Pair("Variações de Bumbo no Pop", "Inserir o bumbo no contratempo (tempo 3 e) mantendo o pulso."),
            Pair("Toques Duplos (R R L L)", "Praticar o rudimento de toque duplo deixando a baqueta quicar duas vezes."),
            Pair("O Paradiddle Básico (R L R R L R L L)", "Dominar o rudimento mais famoso da percussão mundial."),
            Pair("Dinâmica: Toques Acentuados e Ghost Notes", "Diferenciar notas suaves fantasmas de acentos explosivos na caixa."),
            Pair("Prato de Condução (Ride)", "Transferir a condução do chimbal para a cúpula e corpo do Ride."),
            Pair("O Prato de Ataque (Crash)", "Acentuar o tempo 1 com crash e bumbo simultâneos nas viradas."),
            Pair("Coordenação dos 4 Membros", "Manter o pé esquerdo marcando o chimbal enquanto toca o groove."),

            // Módulo 3 (16-25)
            Pair("Compasso 4/4 a 80 BPM", "Manter a consistência rítmica sem oscilar o andamento."),
            Pair("Subdivisão de Semicolcheias", "Contar 1-e-&-a no chimbal com duas mãos alternadas."),
            Pair("Viradas Básicas de 1 Compasso", "Descer pelos tons 1, 2 e surdo finalizando no prato de ataque."),
            Pair("Viradas em Colcheias e Semicolcheias", "Combinar toques rápidos nos tambores antes do refrão."),
            Pair("Ritmo de Disco e Funk", "Abrir o chimbal nos contratempos criando o efeito de 'abertura'."),
            Pair("Compasso 3/4 e 6/8", "Tocar baladas ternárias em 6/8 com condução swingada."),
            Pair("Uso da Aro da Caixa (Cross-Stick / Rim Click)", "Produzir o som de estalo de madeira apoiando a baqueta na pele."),
            Pair("Técnica de Pedal: Heel-Down vs Heel-Up", "Explorar o toque com calcanhar apoiado versus calcanhar levantado."),
            Pair("Pivô e Slide no Pedal do Bumbo", "Executar dois toques rápidos com um único pé no bumbo."),
            Pair("Resistência Rítmica a 100 BPM", "Tocar 3 minutos de groove sem interrupções nem perda de pulso."),

            // Módulo 4 (26-40)
            Pair("Groove de 'We Will Rock You' (Queen)", "Tocar o clássico 'pé-pé-mão' com bumbo duplo e caixa."),
            Pair("Groove de 'Billie Jean' (Michael Jackson)", "Manter a precisão milimétrica do groove pop mais famoso do mundo."),
            Pair("Groove de 'Come As You Are' (Nirvana)", "Acompanhar a levada grunge com chimbal marcante."),
            Pair("Baião na Bateria Brasileira", "Simular o triângulo no chimbal e o zabumba no bumbo."),
            Pair("Samba Básico na Bateria", "Conduzir o telecoteco no prato com bumbo em dois tempos."),
            Pair("Bossa Nova: O Toque Suave", "Tocar o aro da caixa com bumbo suave e chimbal no pé."),
            Pair("Reggae: O One Drop", "Deixar o tempo 1 vazio e acentuar o tempo 3 com bumbo e caixa juntos."),
            Pair("Balada Pop Lenta a 65 BPM", "Controlar a calma e o espaço entre as notas em andamentos lentos."),
            Pair("Rock Enérgico a 120 BPM", "Tocar com pressão e volume constante em andamento acelerado."),
            Pair("Viradas com Flams na Caixa", "Adicionar apojaturas com as duas mãos quase juntas."),
            Pair("Groove com Abertura Sincopada de Chimbal", "Coordenar o pé esquerdo para abrir o chimbal no contratempo."),
            Pair("Levada Soul e R&B", "Adicionar suingue e notas fantasmas entre os tempos fortes."),
            Pair("Groove de Hard Rock com Bumbo Duplo", "Sincronizar bumbo rápido com condução potente."),
            Pair("Ritmo de Blues Shuffle", "Aprender a tercina de swing com balanço característico."),
            Pair("Apresentação de Repertório Completo", "Tocar uma faixa do início ao fim com introdução, estrofes, refrão e viradas."),

            // Módulo 5 (41-50)
            Pair("Técnica Moeller: Movimento de Chicote", "Usar o movimento ondulatório do braço para multiplicar toques sem esforço."),
            Pair("Rulo Duplo Aberto e Fechado (Press Roll)", "Criar a sustentação contínua de som na caixa para introduções orquestrais."),
            Pair("Rudimento Drag / Ruff", "Inserir duas notas fantasmas de apoio antes do toque principal."),
            Pair("Rudimento Flam Tap e Flam Accent", "Combinar flams com alternância de mãos em alta velocidade."),
            Pair("Independência do Pé Esquerdo no Chimbal", "Marcar semínimas no pé esquerdo enquanto a mão direita sola."),
            Pair("Deslocamento Rítmico de Acentos", "Mudar os acentos de lugar criando ilusões de tempo."),
            Pair("Técnica de Bumbo com Pivô Lateral (Swivel)", "Aumentar a velocidade do pé com o movimento do calcanhar."),
            Pair("Uso das Cúpulas dos Pratos (Bell)", "Explorar os timbres cortantes no centro dos pratos de condução."),
            Pair("Dinâmica Pianíssimo ao Fortíssimo", "Controlar a altura da baqueta para variar o volume de 1 a 10."),
            Pair("Exercício de Coordenação Linear", "Tocar padrões onde nenhum membro atinge a peça ao mesmo tempo."),

            // Módulo 6 (51-60)
            Pair("Leitura de Partitura de Bateria", "Identificar a linha do bumbo, espaço da caixa e cruz do chimbal na pauta."),
            Pair("Pausas e Silêncios Rítmicos", "Saber quando NÃO tocar para dar impacto à entrada da banda."),
            Pair("Tercinas em 4/4 (Triplets)", "Dividir cada tempo em 3 partes iguais criando balanço."),
            Pair("Sextinas nas Viradas", "Executar 6 notas por tempo com velocidade nos tambores."),
            Pair("Polirritmia 3 contra 2", "Coordenar 3 toques em uma mão contra 2 na outra simultaneamente."),
            Pair("Polirritmia 4 contra 3", "Desenvolver independência mental avançada entre mãos e pés."),
            Pair("Métricas Ímpares: Compasso 5/4", "Contar 1-2-3, 1-2 como no clássico 'Take Five' do jazz."),
            Pair("Métricas Ímpares: Compasso 7/8", "Dividir o compasso em 2+2+3 ou 3+2+2 no rock progressivo."),
            Pair("Afinação dos Tambores", "Afinar peles batedeiras e de resposta em intervalos harmoniosos."),
            Pair("Uso de Abafadores e Acústica da Sala", "Controlar sobras e harmônicos com géis e anéis abafadores."),

            // Módulo 7 (61-75)
            Pair("Groove de Funk Linear (David Garibaldi)", "Tocar grooves complexos sem repetições sobrepostas de membros."),
            Pair("Samba Cruzado com Vassourinhas", "Explorar o som aveludado e contínuo das vassourinhas de aço."),
            Pair("Baião Nordestino com Prato de Ataque Conduzido", "Adicionar suingue brasileiro com acentos cruzados."),
            Pair("Maracatu de Baque Virado na Bateria", "Transferir o peso dos tambores de alfaia para o bumbo e surdo."),
            Pair("Choro Rápido e Maxixe", "Precisão cirúrgica em andamentos rápidos brasileiros."),
            Pair("Reggae Steppers (Four on the Floor)", "Bumbo em todos os 4 tempos com chimbal sincopado."),
            Pair("Jazz Tradicional: O Ride em Swing", "Executar a famosa condução de swing jazzística com independência de caixa."),
            Pair("Compasso Afro-Cubano 6/8", "Tocar a clave afrocubana com independência rítmica total."),
            Pair("Bossa Nova em Andamento Rápido", "Fluidez perfeita sem rigidez no aro da caixa."),
            Pair("Heavy Metal: Pedal Duplo Básico", "Alternar os dois pés em semicolcheias constantes."),
            Pair("Heavy Metal: Blast Beats", "Velocidade extrema de bumbo e caixa alternados."),
            Pair("Viradas com Quebra de Tempo", "Surpreender a banda com pausas inesperadas antes do refrão."),
            Pair("Uso do Prato China e Splash", "Colorir a condução com efeitos exóticos e curtos."),
            Pair("Coordenação com Baixista (Pocket Groove)", "Travar perfeitamente com a linha de baixo para criar 'groove gordo'."),
            Pair("Solo de Bateria de 16 Compassos", "Estruturar introdução, desenvolvimento dinâmico e clímax percussivo."),

            // Módulo 8 (76-90)
            Pair("Pedal Duplo Avançado a 160 BPM", "Resistência de pernas para tocar minutos de pedal duplo rápido."),
            Pair("Métricas Moduladas (Metric Modulation)", "Trocar a percepção de andamento sem alterar o metrônomo real."),
            Pair("Improvisação Livre em Quintinas e Septinas", "Dividir o tempo em 5 e 7 notas nos solos de fusão."),
            Pair("Fusão Latina e Clave 3:2 / 2:3", "Manter a clave no bloco enquanto sola com os outros 3 membros."),
            Pair("Vassourinhas no Jazz: O Círculo na Caixa", "Manter a vassourinha arrastando na pele em círculos contínuos."),
            Pair("Rudos Rudimentares Híbridos (Herta, Cheese)", "Dominar os rudimentos modernos do Drum Corps internacional."),
            Pair("Técnica Heel-Toe para Bumbo Ultrarrápido", "Tirar dois toques de bumbo por movimento usando calcanhar e ponta."),
            Pair("Grooves em Tempos Mistos (11/8 e 13/8)", "Navegar em compassos complexos com musicalidade e naturalidade."),
            Pair("Dinâmica Extrema: O Poder do Silêncio", "Usar pausas dramáticas e decrescendos para prender a atenção do público."),
            Pair("Uso de Triggers e Pads Eletrônicos", "Integrar tecnologia digital ao kit acústico no palco."),
            Pair("Criação de Grooves Originais", "Compor levadas exclusivas para canções autorais."),
            Pair("Gravação de Bateria em Estúdio", "Tocar com clique no fone, microfonação e consistência dinâmica de estúdio."),
            Pair("Saúde do Baterista: Prevenção de Tendinites", "Exercícios de alongamento, relaxamento e respiração para longevidade."),
            Pair("Masterclass de Performance ao Vivo", "Presença de palco, liderança rítmica da banda e comunicação visual."),
            Pair("Apresentação de Graduação do Baterista", "Execução impecável do repertório avançado, solo autoral e certificado de honra!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 5) {
                1 -> 80.0  // Kick
                2 -> 200.0 // Snare
                3 -> 400.0 // Tom
                4 -> 120.0 // Floor Tom
                else -> 800.0 // Hi-Hat / Cymbal
            }

            list.add(
                Lesson(
                    id = "bateria_lesson_$lessonNum",
                    instrument = InstrumentType.BATERIA,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Bateria do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Explicação Prática:\n")
                        append("Na bateria, a consistência temporal e a dinâmica são fundamentais. Mantenha os ombros e punhos soltos. A força vem do movimento fluído do antebraço e do controle do rebote, nunca de tensão nos músculos!\n\n")
                        append("Dica do Maestro Pipo:\n")
                        append("Conte o tempo em voz alta ('1, 2, 3, 4') enquanto toca! Isso conecta seu cérebro ao ritmo e acelera sua independência dos 4 membros!")
                    },
                    audioNotes = listOf(
                        SoundNote("Bumbo", audioFreq, 250),
                        SoundNote("Caixa", audioFreq * 2.5, 200),
                        SoundNote("Chimbal", audioFreq * 4.0, 150)
                    ),
                    practicalExercise = "Pratique o padrão desta aula com metrônomo por 5 minutos diários, começando devagar e aumentando a velocidade apenas quando estiver perfeito.",
                    summary = "Excelente! Você concluiu $title, aprimorando seu senso rítmico e independência na bateria.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.BATERIA, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
