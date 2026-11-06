package com.example.data

import com.example.model.*

object ViolinLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1 (1-5)
            Pair("Anatomia do Violino e do Arco", "Conhecer tampo, espelho, cavalete, voluta, alma, queixeira, crina e talão."),
            Pair("Empunhadura do Arco (Bow Hold)", "Posicionar o polegar curvado no talão e dedos relaxados em formato redondo."),
            Pair("Postura, Espaleira e Apoio no Ombro", "Equilibrar o violino entre a clavícula e o queixo sem segurar com a mão esquerda."),
            Pair("Aplicação do Breu e Tensão da Crina", "Ajustar o parafuso do arco e passar breu com movimentos suaves e uniformes."),
            Pair("O Primeiro Som com Cordas Soltas", "Passar o arco paralelo ao cavalete mantendo o peso do braço sobre a corda Lá."),

            // Módulo 2 (6-15)
            Pair("A Afinação G - D - A - E", "Memorizar as 4 cordas em quintas justas: Sol3, Ré4, Lá4 e Mi5."),
            Pair("Arco Para Baixo e Arco Para Cima", "Controlar os sinais de arcada: ∏ (Down Bow) e V (Up Bow)."),
            Pair("Divisão do Arco (Talão, Meio e Ponta)", "Aprender as três regiões sonoras do arco e suas características."),
            Pair("O Primeiro Dedo na Corda Lá (Nota Si)", "Posicionar o dedo indicador curvado na primeira fita guia."),
            Pair("O Segundo Dedo Alto (Nota Dó Sustenido)", "Espaçar um tom inteiro do primeiro dedo criando o intervalo maior."),
            Pair("O Terceiro Dedo (Nota Ré)", "Aproximar o terceiro dedo do segundo dedo (meio tom) afinando com a corda Ré solta."),
            Pair("O Quarto Dedo (Dedo Mínimo - Nota Mi)", "Fortalecer o quarto dedo afinando em uníssono com a corda Mi solta."),
            Pair("A Escala de Lá Maior na 1ª Posição", "Tocar a escala mais natural do violino unindo as cordas Lá e Mi."),
            Pair("Pizzicato com a Mão Direita", "Puxar as cordas com o dedo indicador criando som percussivo."),
            Pair("Transição Entre Cordas com o Arco", "Mudar o nível do cotovelo para tocar suavemente nas 4 cordas."),

            // Módulo 3 (16-25)
            Pair("Compasso 4/4 e Velocidade de Arco Constante", "Manter a velocidade de passagem do arco uniforme por 4 tempos."),
            Pair("Articulação Détaché Básica", "Tocar notas separadas e articuladas no meio do arco."),
            Pair("Ligaduras de Duas e Quatro Notas", "Tocar múltiplas notas na mesma passada de arco."),
            Pair("Staccato com o Arco", "Parar o arco bruscamente sobre a corda para notas curtas e secas."),
            Pair("Compasso 3/4 e a Valsa no Violino", "Contar 1-2-3 usando mais arco no tempo 1 e menos nos tempos 2 e 3."),
            Pair("Escala de Ré Maior na 1ª Posição", "Percorrer as notas D-E-F#-G-A-B-C#-D nas cordas Ré e Lá."),
            Pair("O Segundo Dedo Baixo (Nota Dó Natural)", "Colocar o dedo colado no primeiro dedo criando o semitom menor."),
            Pair("Escala de Sol Maior em Duas Oitavas", "Percorrer todo o violino desde a 4ª corda Sol solta até a 1ª corda."),
            Pair("Dinâmica: Piano (Suave) e Forte (Potente)", "Controlar o ponto de contato e velocidade do arco para variar o volume."),
            Pair("Resistência de Postura por 5 Minutos", "Tocar sem abaixar o instrumento nem tensionar o pescoço."),

            // Módulo 4 (26-40)
            Pair("Brilha, Brilha Estrelinha (Tema de Mozart)", "O primeiro clássico do método Suzuki na corda Lá e Mi."),
            Pair("Ode à Alegria (Beethoven)", "Executar o hino da 9ª sinfonia com arcadas nobres e expressivas."),
            Pair("Canção do Vento (Suzuki Livro 1)", "Praticar staccatos rápidos com paradas precisas de arco."),
            Pair("Minueto em Sol (Bach / Petzold)", "O charme do período barroco com ligaduras e dança."),
            Pair("Asa Branca no Violino", "Interpretar o clássico nordestino com o toque lírico das cordas."),
            Pair("Parabéns a Você no Violino", "Cantarolar a melodia festiva em Ré Maior."),
            Pair("Amazing Grace nas Cordas do Violino", "Sustentar notas longas e expressivas com arco contínuo."),
            Pair("Greensleeves (Canção Tradicional Inglesa)", "Melodia melancólica em Lá Menor em compasso 6/8."),
            Pair("Peixe Vivo e Cantigas Tradicionais", "Trabalhar agilidade de dedos com melodias folclóricas."),
            Pair("Bella Ciao no Violino", "Ritmo enérgico com acentos marcantes no arco."),
            Pair("Cai Cai Balão para Iniciantes", "Trocas de cordas divertidas e fáceis."),
            Pair("Largo da Sinfonia do Novo Mundo (Dvořák)", "Sustentação pura de som e beleza lírica."),
            Pair("Ária na Corda Sol (J. S. Bach)", "Explorar a profundidade aveludada da 4ª corda grave."),
            Pair("Scarborough Fair no Violino", "Arco suave com clima medieval encantador."),
            Pair("Recital Suzuki: Apresentação Completa", "Tocar três peças memorizadas com postura impecável e reverência."),

            // Módulo 5 (41-50)
            Pair("Iniciação ao Vibrato de Pulso", "Oscilar suavemente o punho esquerdo para dar vida e calor ao som."),
            Pair("Vibrato de Braço para Notas Graves", "Usar o antebraço para oscilações amplas e profundas."),
            Pair("Mudança para a 3ª Posição (Shift)", "Deslizar o polegar e a mão esquerda até encostar no corpo do violino."),
            Pair("Afinação na 3ª Posição", "Usar harmônicos e cordas soltas para conferir a altura exata das notas."),
            Pair("Golpe de Arco Martelé", "Ataque seco e acentuado no talão e na ponta."),
            Pair("Golpe de Arco Spiccato (Arco Saltado)", "Deixar o arco quicar naturalmente no ponto de equilíbrio."),
            Pair("Harmônicos Naturais no Violino (Meio da Corda)", "Tocar levemente na 4ª casa imaginária para soar a oitava cristalina."),
            Pair("Cordas Duplas (Double Stops) Básicas", "Passar o arco em duas cordas adjacentes simultaneamente."),
            Pair("Controle de Arco no Talão (Frog)", "Evitar o som áspero perto da mão mantendo os dedos flexíveis."),
            Pair("Controle de Arco na Ponta (Tip)", "Manter o volume constante mesmo quando o arco estiver esticado."),

            // Módulo 6 (51-60)
            Pair("A Escala Menor Natural, Harmônica e Melódica", "Compreender as três formas da escala menor no violino."),
            Pair("Leitura de Clave de Sol Avançada", "Decifrar acidentes (#, b, bequadro), armaduras de clave e pausas."),
            Pair("Intervalos e Ressonância Simpática", "Sentir as outras cordas vibrando sozinhas quando a nota está afinada."),
            Pair("Escala de Si Bemol Maior na 1ª Posição", "Trabalhar o primeiro dedo recuado (baixo) em semitom."),
            Pair("Escala de Fá Maior", "Dominar o Si bemol na 1ª corda e o Fá natural na corda Mi."),
            Pair("O Trinado (Trill) no Violino", "Alternar rapidamente dois dedos criando ornamentos barrocos."),
            Pair("Mordentes e Apojaturas", "Embelezar frases musicais com notas de adorno velozes."),
            Pair("Compassos Compostos (6/8, 9/8, 12/8)", "Sentir a pulsação ternária das danças europeias."),
            Pair("Uso da Surdina de Estudo e Orquestra", "Abafe o som do violino para praticar à noite com timbre doce."),
            Pair("Afinação de Ouvido por Batimentos de Quintas", "Afinar o violino ouvindo os batimentos acústicos entre duas cordas."),

            // Módulo 7 (61-75)
            Pair("Primavera das Quatro Estações (Vivaldi)", "Tocar o tema imortal do barroco italiano com brilho e virtuosismo."),
            Pair("O Inverno de Vivaldi: O Pizzicato da Chuva", "Pizzicatos rápidos e precisos simulando gotas de chuva no gelo."),
            Pair("Meditação de Thaïs (Massenet)", "A peça lírica mais emocionante do repertório para violino e piano."),
            Pair("Dança Húngara Nº 5 (Brahms)", "Trocas bruscas de andamento e paixão cigana."),
            Pair("Canon em Ré Maior (Pachelbel)", "Conduzir a melodia polifônica com sustentação nobre."),
            Pair("Música Cigana e Klezmer no Violino", "Glissandos expressivos e ornamentos tradicionais do leste europeu."),
            Pair("Violino Sertanejo e Fiddle Tradicional", "O violino caipira com cordas duplas e ritmo dançante."),
            Pair("Choro no Violino: Carinhoso (Pixinguinha)", "Interpretar a obra-prima brasileira com suingue e sentimento."),
            Pair("Mudança para a 2ª Posição", "Usar a posição intermediária para passagens rápidas sem saltos grandes."),
            Pair("Mudança para a 4ª e 5ª Posições", "Alcançar os agudos celestiais no topo do espelho do violino."),
            Pair("Golpe de Arco Sautillé", "Spiccato ultrarrápido controlado pela elasticidade da madeira do arco."),
            Pair("Cordas Duplas em Terças e Sextas", "Harmonia rica a duas vozes executada por um único violinista."),
            Pair("Harmônicos Artificiais (Dedo 1 Fixo + Dedo 4 Leve)", "Tocar notas flautadas em qualquer altura da escala."),
            Pair("Ricochet: O Arco Saltando Três Vezes", "Jogar o arco para produzir cascatas de notas descendentes."),
            Pair("Apresentação de Concerto Intermediário", "Executar um movimento de concerto barroco com cadência."),

            // Módulo 8 (76-90)
            Pair("Caprichos de Paganini: Estudo do Virtuosismo", "Inspirar-se nas técnicas revolucionárias do lendário violinista."),
            Pair("Pizzicato com a Mão Esquerda (Paganini)", "Beliscar as cordas com a mão esquerda enquanto o arco toca."),
            Pair("Escalas em Três Oitavas a 120 BPM", "Percorrer todo o comprimento do espelho com velocidade cristalina."),
            Pair("Arpejos em Oitavas Dedilhadas", "Deslocamentos ágeis de mão com precisão milimétrica."),
            Pair("Interpretação e Rubato Romântico", "Flexibilizar o tempo com maturidade artística sem perder o pulso."),
            Pair("O Violino Elétrico e Processadores de Efeito", "Explorar o som moderno com distorção, delay e pedais de loop."),
            Pair("Improvisação no Jazz Manouche (Estilo Stéphane Grappelli)", "O swing parisiense no violino acústico."),
            Pair("Técnicas de Fiddle Folk Americano e Irlandês", "Ornamentos de rolls e cuts na música celta tradicional."),
            Pair("Gravação de Violino em Estúdio", "Posicionamento do microfone de fita e controle de ruídos de arco."),
            Pair("Regulagem do Cavalete, Alma e Cordas", "Como manter a distância perfeita das cordas e a projeção sonora."),
            Pair("Prevenção de Lesões: Cuidados com o Ombro e Punho", "Alongamentos fundamentais para a saúde de longo prazo do músico."),
            Pair("Liderança de Orquestra: O Papel do Spalla (Concertmaster)", "Afinar a orquestra e liderar o naipe de primeiros violinos."),
            Pair("Composição Autoral para Violino Solo", "Escrever frases originais que exploram todas as cores tímbricas."),
            Pair("Masterclass Final de Interpretação e Presença Cênica", "A postura triunfal de um verdadeiro concertista no palco."),
            Pair("Grande Concerto de Formatura do Violinista", "Execução magnífica do repertório clássico, diploma de honra e ovação!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 4) {
                1 -> 196.00 // G3
                2 -> 293.66 // D4
                3 -> 440.00 // A4
                else -> 659.25 // E5
            }

            list.add(
                Lesson(
                    id = "violino_lesson_$lessonNum",
                    instrument = InstrumentType.VIOLINO,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Violino do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Explicação Prática:\n")
                        append("No violino, a pureza do som nasce do ponto de contato do arco entre o espelho e o cavalete. Mantenha o arco reto e deixe o peso natural do braço fluir através do dedo indicador, sem apertar a mão!\n\n")
                        append("Dica do Maestro Pipo:\n")
                        append("Ouça com atenção a ressonância das cordas soltas! Quando sua nota está perfeitamente afinada, o violino inteiro vibra em simpatia!")
                    },
                    audioNotes = listOf(
                        SoundNote("Sol (G3)", audioFreq * 0.44, 400),
                        SoundNote("Ré (D4)", audioFreq * 0.66, 400),
                        SoundNote("Lá (A4)", audioFreq, 500),
                        SoundNote("Mi (E5)", audioFreq * 1.5, 600)
                    ),
                    practicalExercise = "Pratique a passagem de arco e a afinação dos dedos por 5 minutos mantendo o violino apoiado sem usar as mãos.",
                    summary = "Parabéns! Você concluiu $title, elevando sua afinação e elegância sonora no violino.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.VIOLINO, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
