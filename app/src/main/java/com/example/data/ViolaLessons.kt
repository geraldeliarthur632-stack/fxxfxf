package com.example.data

import com.example.model.*

object ViolaLessons {
    val allLessons: List<Lesson> by lazy {
        val list = mutableListOf<Lesson>()

        val lessonTitles = listOf(
            // Módulo 1: Conhecendo o Instrumento (1-5)
            Pair("A Alma da Viola Caipira", "Compreender as 10 cordas divididas em 5 pares e a história da viola caipira no Brasil."),
            Pair("Pares Uníssonos e Oitavados", "Entender a diferença entre os 2 primeiros pares (uníssonos) e os 3 últimos (oitavados)."),
            Pair("Afinação Tradicional Cebolão em Mi", "Afinar a viola em Mi Maior aberto: Mi-Mi, Si-Si, Sol#-Sol#, Si-Si, Mi-Mi."),
            Pair("A Afinação Boiadeiro", "Afinar a viola em Sol Maior para toadas e modas com ressonância profunda."),
            Pair("Uso da Dedeira e Postura do Violeiro", "Posicionar o polegar com dedeira plástica ou unha natural com apoio correto."),

            // Módulo 2: Primeiros Acordes e Notas (6-15)
            Pair("Acorde de Mi Maior (E) no Cebolão", "Tocar o acorde mais natural da viola com cordas soltas ressonando."),
            Pair("Acorde de Si com Sétima (B7)", "Montar a dominante do tom com dois dedos precisos nos pares centrais."),
            Pair("Acorde de Lá Maior (A)", "Completar a primeira tríade da moda caipira com o subdominante Lá."),
            Pair("Acorde de Fá Sustenido Menor (F#m)", "Explorar acordes menores no braço da viola com sonoridade nostálgica."),
            Pair("Acorde de Dó Sustenido Menor (C#m)", "Posicionar os dedos para melodias reflexivas do sertão."),
            Pair("Transições Rápidas E - B7 - E", "Ganhar agilidade na mudança sem abafar os pares de aço."),
            Pair("Sequência Caipira Clássica: E - A - B7 - E", "O ciclo harmônico fundamental de 90% das modas de viola."),
            Pair("Acorde de Sol Sustenido Menor (G#m)", "Alcançar a região média da escala da viola."),
            Pair("Acorde de Ré Maior (D) no Cebolão", "Formação com uso de dedilhado aberto."),
            Pair("Exercício de Toque Limpo nos 5 Pares", "Tocar simultaneamente os dois fios de cada par com ataque firme."),

            // Módulo 3: Ritmos e Exercícios Básicos (16-25)
            Pair("O Ritmo de Cururu", "Executar o compasso binário com batida para baixo e corte com o polegar."),
            Pair("O Cateretê Caipira", "Aprender o ritmo dançante tradicional do interior paulista e mineiro."),
            Pair("A Toada Romântica", "Tocar baladas calmas e líricas com dedilhado suave em 4/4."),
            Pair("Introdução ao Pagode de Viola", "O ritmo virtuoso imortalizado por Tião Carreiro."),
            Pair("O Rasqueado de Mão Aberta", "Abrir os dedos indicador, médio e anelar num golpe percussivo brilhante."),
            Pair("Controle da Batida com o Polegar", "Usar o dorso e a polpa do polegar para alternar tons graves e agudos."),
            Pair("Abafamento Seco no Ritmo de Polca", "Criar o balanço paraguaio e pantaneiro com corte súbito de som."),
            Pair("Valsa Sertaneja em 3/4", "Contar 1-2-3 com o baixo no 1º par grave e batidas leves nos pares 2 e 3."),
            Pair("Exercício de Rítmica Contínua", "Manter a levada de Cururu estável sem oscilar o andamento."),
            Pair("Sincronismo Rítmico de Viola e Voz", "Tocar a levada enquanto pronuncia versos da moda."),

            // Módulo 4: Primeiras Músicas (26-40)
            Pair("Chico Mulato - Introdução Tradicional", "Pontear as primeiras frases da clássica canção folclórica."),
            Pair("Chico Mulato - Acompanhamento", "Cantar e tocar a harmonia completa em Mi Maior."),
            Pair("Menino da Porteira - O Toque Raiz", "Aprender os acordes e a levada da toada mais célebre do cancioneiro."),
            Pair("Menino da Porteira - Ponteado das Terças", "Executar a célebre introdução em terças nos dois primeiros pares."),
            Pair("Tristeza do Jeca - Melodia", "Interpretar o clássico lírico de Angelino de Oliveira."),
            Pair("Tristeza do Jeca - Harmonia em Lá Maior", "Usar a transposição para deixar a voz confortável."),
            Pair("Boiadeiro Errante - Ritmo de Cururu", "Acompanhar a história do peão de boiadeiro com batida firme."),
            Pair("Cabocla Teresa - Introdução Dramática", "Ponteado expressivo com dinâmica e pausas teatrais."),
            Pair("Cabocla Teresa - Condução Rítmica", "Transições com acordes menores e preparação dominante."),
            Pair("Canoeiro - O Ritmo do Pagode", "Primeira experiência tocando o pagode caipira com velocidade moderada."),
            Pair("Moda das Almas", "Tocar canções tradicionais em afinação aberta."),
            Pair("Saudade de Matão", "Valsa clássica caipira com dedilhados em arpejo contínuo."),
            Pair("Luar do Sertão (Catulo da Paixão Cearense)", "Interpretação poética nas noites de luar."),
            Pair("O Violeiro e o Rio", "Composição didática para testar independência de mãos."),
            Pair("Recital de Viola Caipira Módulo 4", "Apresentação completa das 3 modas mais marcantes."),

            // Módulo 5: Técnicas e Dedilhados (41-50)
            Pair("Terças Caipiras no 1º e 2º Pares", "Deslizar os dedos 1 e 2 ao longo do braço criando o dueto sertanejo."),
            Pair("Sextas Caipiras no 1º e 3º Pares", "A sonoridade aberta das sextas que ecoa pelos campos."),
            Pair("O Ponteado com Polegar e Indicador", "A pinça tradicional que constrói os grandes solos caipiras."),
            Pair("Ligados Caipiras com Resonância Dupla", "Puxar as duas cordas simultâneas no traste anterior."),
            Pair("O Glissando Rústico", "Arrastar os dedos pela escala com pressão constante das cordas de aço."),
            Pair("O Trinado e Tremolo de Viola", "Repetir notas rapidamente com alternância de dedos I-M-A."),
            Pair("Harmônicos Naturais no Cebolão", "Fazer soar a 12ª casa que ecoa como um sino de capela."),
            Pair("Dedilhado de Toada 5-3-2-1", "Saltos precisos do polegar entre os bordões e as primas."),
            Pair("Controle da Dinâmica em Cordas Duplas", "Tocar sem ferir os dedos e mantendo equilíbrio de volume."),
            Pair("Uso da Pestana Curta na Viola", "Prender apenas 2 ou 3 pares para facilitar vozes agudas."),

            // Módulo 6: Teoria Musical Aplicada à Viola (51-60)
            Pair("A Escala Aberta de Mi Maior", "Mapear todas as notas no braço afinado em Cebolão."),
            Pair("Intervalos de Terça e Sexta Explicados", "Por que esses dois intervalos definem o som da música sertaneja."),
            Pair("O Campo Harmônico de Mi Maior", "E - F#m - G#m - A - B7 - C#m - D#dim."),
            Pair("Afinação Rio Abaixo (Ré Maior)", "Conhecer a lendária afinação mitológica dos violeiros."),
            Pair("Transposição de Modas com Capotraste", "Subir tons para duetos vocais sem mudar a digitação dos ponteios."),
            Pair("Formação dos Baixos Conduzidos", "Criar linhas de baixo que preparam a entrada de cada estrofe."),
            Pair("Acordes com Dissonâncias Caipiras", "Usar a 9ª adicionada (Eadd9) para brilho cristalino."),
            Pair("Diferença entre Moda de Viola, Toada e Cururu", "A métrica dos versos e a estrutura formal de cada gênero."),
            Pair("A Relação da Viola com a Afinação Natural", "Entender como os harmônicos afinam a viola por ressonância."),
            Pair("Como Escrever Tablatura para 5 Pares", "Ler e registrar ponteios com a notação de cinco linhas duplas."),

            // Módulo 7: Músicas de Nível Intermediário (61-75)
            Pair("Pagode em Brasília - Introdução e Frases", "Dominar o ponteado rápido com sincronismo perfeito de palheta/dedos."),
            Pair("Pagode em Brasília - A Levada Completa", "Executar a batida completa sem perder o fôlego."),
            Pair("A Vaca Já Foi Pro Brejo - Ponteio Raiz", "Estilo incisivo com acentuações no contratempo."),
            Pair("Amargurado - O Dueto de Terças Perfeito", "Tocar uma das mais belas introduções do sertanejo raiz."),
            Pair("Cabelo Loiro - Introdução Dinâmica", "Velocidade e clareza no toque dos dois primeiros pares."),
            Pair("Chico Mineiro - Arranjo Instrumental", "Criar um arranjo onde a viola sola e acompanha ao mesmo tempo."),
            Pair("O Poder da Viola no Cateretê Rápido", "Dobrar a velocidade dos passos e batidas."),
            Pair("Valsa das Flores Sertaneja", "Arranjo delicado explorando toda a extensão das 10 cordas."),
            Pair("Moda do Peão Valente", "História contada em acordes e ponteados expressivos."),
            Pair("Ponteado em Afinação Rio Abaixo", "Executar uma canção completa com a afinação em Ré."),
            Pair("Recortado Paulista", "O ritmo contagiante das festas de São Gonçalo e mutirões."),
            Pair("Polca Pantaneira na Viola", "Balanço fronteiriço com tercinas e notas pontuadas."),
            Pair("Chorinho Caipira no Braço da Viola", "O encontro da música caipira com o chorinho brasileiro."),
            Pair("A Dança de São Gonçalo", "Música cerimonial e sagrada do folclore brasileiro."),
            Pair("Apresentação de Meio de Curso", "Executar 3 estilos diferentes com autoridade."),

            // Módulo 8: Técnicas Avançadas (76-90)
            Pair("Virtuosismo no Pagode de Viola", "Atingir 110 BPM com clareza cristalina em cada nota."),
            Pair("Ponteado a Três Dedos (P-I-M)", "Técnica de velocidade para escalas caipiras vertiginosas."),
            Pair("Dueto Instrumental com Duas Violas", "Dividir as vozes de Terça Superior e Terça Inferior."),
            Pair("O Toque Repicado", "Bater e puxar cordas produzindo efeito de eco natural."),
            Pair("Improvisação no Ritmo de Cururu", "Responder versos cantados com floreios espontâneos no braço."),
            Pair("Harmônicos Artificiais na Viola Caipira", "Expandir os limites do instrumento para timbres etéreos."),
            Pair("Técnica de Polegar Solto e Apoiado", "Dominar o ataque percussivo que não desafina a viola de aço."),
            Pair("A Viola de 12 Cordas e Outras Variações", "Conhecer a viola de cocho pantaneira e violas regionais."),
            Pair("Afinação Boiadeira em Sol: Domínio Total", "Ponteios graves e melodias solenes na afinação em Sol."),
            Pair("Construção de Introduções Originais", "Criar sua própria assinatura como violeiro compositor."),
            Pair("Como Regular e Trocar os 5 Pares de Cordas", "Evitar quebras, manter oitavas afinadas e cuidar das tarraxas."),
            Pair("Preservação do Tampo e Madeiras Nobres", "Cuidados com umidade, cedro, jacarandá e calosidades."),
            Pair("O Violeiro de Roda e Palco", "Presença cênica, afinação rápida ao vivo e diálogo com a plateia."),
            Pair("Obras Clássicas Adaptadas à Viola Caipira", "Tocar peças eruditas como Bach e Villa-Lobos na viola."),
            Pair("Consagração do Mestre Violeiro", "A conclusão do curso: o legado da música caipira em suas mãos!")
        )

        lessonTitles.forEachIndexed { index, (title, objective) ->
            val lessonNum = index + 1
            val mod = CourseRepository.getModuleForLesson(lessonNum)
            val audioFreq = when (lessonNum % 6) {
                1 -> 329.63 // E4 (Cebolão aberto)
                2 -> 246.94 // B3
                3 -> 207.65 // G#3
                4 -> 164.81 // E3
                5 -> 440.00 // A4
                else -> 493.88 // B4
            }

            val chord = when (lessonNum % 4) {
                1 -> ChordDiagramData("E (Cebolão)", listOf(0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0))
                2 -> ChordDiagramData("B7", listOf(2, 0, 2, 0, 2), listOf(1, 0, 2, 0, 3))
                3 -> ChordDiagramData("A", listOf(0, 2, 2, 2, 0), listOf(0, 1, 2, 3, 0))
                else -> ChordDiagramData("F#m", listOf(2, 2, 2, 4, 4), listOf(1, 1, 1, 3, 4))
            }

            list.add(
                Lesson(
                    id = "viola_lesson_$lessonNum",
                    instrument = InstrumentType.VIOLA_CAIPIRA,
                    moduleNumber = mod.moduleNumber,
                    moduleTitle = mod.titlePt,
                    lessonNumber = lessonNum,
                    title = title,
                    objective = objective,
                    didacticContent = buildString {
                        append("Bem-vindo à aula $lessonNum do curso de Viola Caipira do Mundo da Música!\n\n")
                        append("Objetivo Pedagógico: $objective\n\n")
                        append("Fundamento Tradicional:\n")
                        append("A viola caipira possui 10 cordas agrupadas em 5 pares. No ponteado, os pares devem ser atacados juntos com firmeza, seja utilizando a dedeira ou a unha, para que ambas as cordas vibrem em perfeita sincronia e ressonância.\n\n")
                        append("Segredo do Maestro Pipo:\n")
                        append("Na afinação Cebolão em Mi Maior, ao tocar todas as cordas soltas você já tem um acorde de Mi Maior perfeito! Use isso para criar ressonâncias ricas e envolventes!")
                    },
                    chordDiagram = chord,
                    audioNotes = listOf(
                        SoundNote("Par 1 (Mi)", 329.63, 400),
                        SoundNote("Par 2 (Si)", 246.94, 400),
                        SoundNote("Par 3 (Sol#)", 207.65, 400),
                        SoundNote("Par 5 (Mi Baixo)", 164.81, 600)
                    ),
                    practicalExercise = "Toque cada par individualmente e verifique se as duas cordas estão soando juntas sem abafamento. Pratique por 5 minutos.",
                    summary = "Parabéns! Você concluiu $title, aprofundando suas raízes na rica arte da viola caipira brasileira.",
                    questions = LessonQuestionsProvider.getQuestionsForLesson(InstrumentType.VIOLA_CAIPIRA, lessonNum, title),
                    durationMinutes = 8,
                    xpReward = 25
                )
            )
        }
        list
    }
}
