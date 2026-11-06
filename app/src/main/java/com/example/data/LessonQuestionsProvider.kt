package com.example.data

import com.example.model.InstrumentType
import com.example.model.QuizQuestion

object LessonQuestionsProvider {

    fun createQuestion(
        id: String,
        prompt: String,
        correctOption: String,
        wrongOption1: String,
        wrongOption2: String,
        wrongOption3: String,
        explanation: String
    ): QuizQuestion {
        // Randomly/deterministically distribute the correct answer across options 0 (A), 1 (B), 2 (C), 3 (D)
        val seed = kotlin.math.abs(id.hashCode())
        val targetIndex = seed % 4

        val options = mutableListOf(wrongOption1, wrongOption2, wrongOption3)
        options.add(targetIndex, correctOption)

        return QuizQuestion(
            id = id,
            prompt = prompt,
            options = options,
            correctIndex = targetIndex,
            explanation = explanation
        )
    }

    fun getQuestionsForLesson(
        instrument: InstrumentType,
        lessonNumber: Int,
        lessonTitle: String
    ): List<QuizQuestion> {
        return when (instrument) {
            InstrumentType.VIOLAO -> getGuitarQuestions(lessonNumber, lessonTitle)
            InstrumentType.VIOLA_CAIPIRA -> getViolaQuestions(lessonNumber, lessonTitle)
            InstrumentType.PIANO -> getPianoQuestions(lessonNumber, lessonTitle)
            InstrumentType.TECLADO -> getKeyboardQuestions(lessonNumber, lessonTitle)
            InstrumentType.BATERIA -> getDrumsQuestions(lessonNumber, lessonTitle)
            InstrumentType.BAIXO -> getBassQuestions(lessonNumber, lessonTitle)
            InstrumentType.UKULELE -> getUkuleleQuestions(lessonNumber, lessonTitle)
            InstrumentType.VIOLINO -> getViolinQuestions(lessonNumber, lessonTitle)
        }
    }

    private fun getGuitarQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1: QuizQuestion
        val q2: QuizQuestion

        when (lessonNumber) {
            1 -> {
                q1 = createQuestion(
                    id = "q_violao_1_1",
                    prompt = "Qual parte do violão é responsável por amplificar acusticamente o som das cordas?",
                    correctOption = "O tampo harmônico e o corpo de ressonância",
                    wrongOption1 = "O tensor de metal dentro do braço",
                    wrongOption2 = "As tarraxas na mão do violão",
                    wrongOption3 = "O rastilho de plástico",
                    explanation = "O tampo de madeira vibra em conjunto com as cordas, projetando o som para fora da boca."
                )
                q2 = createQuestion(
                    id = "q_violao_1_2",
                    prompt = "Qual é a função dos trastes de metal na escala do violão?",
                    correctOption = "Dividir o braço em intervalos de semitom exatos",
                    wrongOption1 = "Servir de enfeite decorativo",
                    wrongOption2 = "Segurar as cordas presas ao cavalete",
                    wrongOption3 = "Amortecer o impacto das palhetas",
                    explanation = "Cada traste delimita o comprimento vibrante da corda, definindo a altura exata da nota em semitons."
                )
            }
            2 -> {
                q1 = createQuestion(
                    id = "q_violao_2_1",
                    prompt = "Como deve permanecer a coluna e os ombros ao tocar violão?",
                    correctOption = "Coluna ereta e ombros relaxados sem tensão",
                    wrongOption1 = "Ombro esquerdo tensionado perto da orelha",
                    wrongOption2 = "Tronco inclinado e curvado para frente",
                    wrongOption3 = "Com o violão completamente deitado no colo",
                    explanation = "Uma boa ergonomia previne lesões e dá amplitude total de movimento aos braços."
                )
                q2 = createQuestion(
                    id = "q_violao_2_2",
                    prompt = "Onde o polegar da mão esquerda deve ficar apoiado idealmente?",
                    correctOption = "No centro da parte traseira do braço do violão",
                    wrongOption1 = "Envolvendo o braço e abafando as cordas agudas",
                    wrongOption2 = "Escondido na palma da mão",
                    wrongOption3 = "Apontando para a mão do instrumento",
                    explanation = "O polegar atrás do braço serve de ponto de apoio suave, liberando os 4 dedos para alcançar as casas."
                )
            }
            3 -> {
                q1 = createQuestion(
                    id = "q_violao_3_1",
                    prompt = "Qual é a ordem correta das 6 cordas do violão da 6ª (mais grossa) para a 1ª (mais fina)?",
                    correctOption = "Mi (E), Lá (A), Ré (D), Sol (G), Si (B), Mi (E)",
                    wrongOption1 = "Dó, Ré, Mi, Fá, Sol, Lá",
                    wrongOption2 = "Mi, Si, Sol, Ré, Lá, Mi",
                    wrongOption3 = "Lá, Ré, Sol, Dó, Mi, Lá",
                    explanation = "A afinação padrão ocidental (Standard EADGBE) vai do Mi grave (6ª) até o Mi agudo (1ª)."
                )
                q2 = createQuestion(
                    id = "q_violao_3_2",
                    prompt = "Como é carinhosamente chamada a 1ª corda mais aguda do violão?",
                    correctOption = "Mizinha",
                    wrongOption1 = "Bordão",
                    wrongOption2 = "Prima de nylon",
                    wrongOption3 = "Baixão",
                    explanation = "A 1ª corda é chamada de 'mizinha', enquanto a 6ª corda grave é o 'mizão'."
                )
            }
            4 -> {
                q1 = createQuestion(
                    id = "q_violao_4_1",
                    prompt = "Na mão esquerda do violonista, qual dedo é representado pelo número 1?",
                    correctOption = "O dedo indicador",
                    wrongOption1 = "O dedo polegar",
                    wrongOption2 = "O dedo médio",
                    wrongOption3 = "O dedo anelar",
                    explanation = "A numeração clássica da mão esquerda é 1=Indicador, 2=Médio, 3=Anelar, 4=Mínimo."
                )
                q2 = createQuestion(
                    id = "q_violao_4_2",
                    prompt = "Quais letras representam os dedos da mão direita nos dedilhados?",
                    correctOption = "P, I, M, A (Polegar, Indicador, Médio, Anelar)",
                    wrongOption1 = "1, 2, 3, 4",
                    wrongOption2 = "A, B, C, D",
                    wrongOption3 = "X, Y, Z, W",
                    explanation = "A mão direita usa as iniciais dos nomes dos dedos para indicar o dedilhado."
                )
            }
            5 -> {
                q1 = createQuestion(
                    id = "q_violao_5_1",
                    prompt = "Qual é o segredo para a nota soar limpa e sem trastejar no violão?",
                    correctOption = "Apertar a corda bem próximo ao traste de metal",
                    wrongOption1 = "Apertar no meio da casa com força excessiva",
                    wrongOption2 = "Deitar o dedo cobrindo as cordas vizinhas",
                    wrongOption3 = "Tocar sem afinar o instrumento",
                    explanation = "Apertar perto do traste metálico exige menos esforço muscular e gera a vibração mais pura."
                )
                q2 = createQuestion(
                    id = "q_violao_5_2",
                    prompt = "Em que formato os dedos da mão esquerda devem tocar as cordas?",
                    correctOption = "Em formato de arco (martelinho) com a ponta dos dedos",
                    wrongOption1 = "Completamente esticados e rígidos",
                    wrongOption2 = "Deitados de lado encostando nas outras cordas",
                    wrongOption3 = "Com a palma colada na madeira",
                    explanation = "Dedos arqueados tocam na ponta e impedem o abafamento acidental das cordas vizinhas."
                )
            }
            6 -> {
                q1 = createQuestion(
                    id = "q_violao_6_1",
                    prompt = "Quais casas e cordas são pressionadas no acorde de Mi Menor (Em)?",
                    correctOption = "2ª casa da 5ª corda e 2ª casa da 4ª corda",
                    wrongOption1 = "1ª casa da 2ª corda",
                    wrongOption2 = "3ª casa da 6ª corda",
                    wrongOption3 = "Pestana em todas as casas",
                    explanation = "O Em é montado com dedos 2 e 3 na 2ª casa das cordas 5 (Lá) e 4 (Ré)."
                )
                q2 = createQuestion(
                    id = "q_violao_6_2",
                    prompt = "Quantas cordas continuam soando soltas no acorde de Mi Menor (Em)?",
                    correctOption = "Quatro cordas (6ª, 3ª, 2ª e 1ª)",
                    wrongOption1 = "Nenhuma corda solta",
                    wrongOption2 = "Apenas uma corda",
                    wrongOption3 = "Todas as 6 cordas",
                    explanation = "Como prendemos apenas 2 cordas, 4 cordas vibram soltas, dando amplitude ao acorde."
                )
            }
            7 -> {
                q1 = createQuestion(
                    id = "q_violao_7_1",
                    prompt = "Qual é a relação visual entre os formatos de Mi Maior (E) e Lá Menor (Am)?",
                    correctOption = "Possuem o mesmo formato físico, apenas descendo uma corda abaixo",
                    wrongOption1 = "São completamente opostos",
                    wrongOption2 = "O Lá Menor usa pestana na 5ª casa",
                    wrongOption3 = "O Lá Menor não usa o dedo indicador",
                    explanation = "Ao descer o bloco de dedos de Mi Maior uma corda para baixo, formamos Lá Menor (Am)!"
                )
                q2 = createQuestion(
                    id = "q_violao_7_2",
                    prompt = "A partir de qual corda devemos tocar o acorde de Lá Menor (Am)?",
                    correctOption = "A partir da 5ª corda solta (nota Lá)",
                    wrongOption1 = "A partir da 6ª corda grave",
                    wrongOption2 = "Apenas na 1ª corda",
                    wrongOption3 = "A partir da 3ª corda",
                    explanation = "A 5ª corda solta fornece a tônica fundamental (Lá) para o acorde de Am."
                )
            }
            14 -> {
                q1 = createQuestion(
                    id = "q_violao_14_1",
                    prompt = "Qual é a sequência da batida universal Pop/Rock mais famosa no violão?",
                    correctOption = "Baixo, Baixo, Cima, Cima, Baixo, Cima (↓ ↓ ↑ ↑ ↓ ↑)",
                    wrongOption1 = "Apenas batidas para baixo o tempo todo",
                    wrongOption2 = "Cima, Cima, Cima, Cima",
                    wrongOption3 = "Baixo, Cima sem nenhuma pausa",
                    explanation = "A levada ↓ ↓ ↑ ↑ ↓ ↑ cria a síncope perfeita presente em milhares de sucessos pop/rock."
                )
                q2 = createQuestion(
                    id = "q_violao_14_2",
                    prompt = "O que a mão direita deve fazer nos tempos em que não toca as cordas?",
                    correctOption = "Continuar o movimento de pêndulo no ar mantendo a pulsação",
                    wrongOption1 = "Parar imóvel e congelar no ar",
                    wrongOption2 = "Largar a palheta",
                    wrongOption3 = "Bater no tampo com força",
                    explanation = "O movimento fantasma de pêndulo mantém o relógio interno do ritmo firme e constante."
                )
            }
            else -> {
                val mod = CourseRepository.getModuleForLesson(lessonNumber)
                q1 = createQuestion(
                    id = "q_violao_${lessonNumber}_1",
                    prompt = "Na aula '$title', qual princípio é indispensável para a sua evolução no violão?",
                    correctOption = "Praticar a digitação com precisão rítmica e clareza sonora",
                    wrongOption1 = "Tocar o mais rápido possível mesmo errando as notas",
                    wrongOption2 = "Ignorar a afinação do instrumento",
                    wrongOption3 = "Apertar as cordas com o dedo deitado",
                    explanation = "A clareza de som e a regularidade do tempo são os alicerces pedagógicos desta lição."
                )
                q2 = createQuestion(
                    id = "q_violao_${lessonNumber}_2",
                    prompt = "Como o conteúdo do Módulo ${mod.moduleNumber} (${mod.titlePt}) te ajuda musicalmente?",
                    correctOption = "Desenvolve memória motora, agilidade e fluência interpretativa",
                    wrongOption1 = "Substitui a necessidade de estudar teoria",
                    wrongOption2 = "Serve apenas para uma única canção",
                    wrongOption3 = "Não tem aplicação prática em outros estilos",
                    explanation = "Cada exercício fortalece a musculatura das mãos e expande o vocabulário musical do aluno."
                )
            }
        }

        return listOf(q1, q2)
    }

    private fun getViolaQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_viola_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "Quantas cordas possui uma viola caipira tradicional?" else "Na aula '$title', qual cuidado garante a sonoridade típica da viola?",
            correctOption = if (lessonNumber == 1) "10 cordas de aço agrupadas em 5 pares" else "Tocar ambas as cordas do par simultaneamente com clareza",
            wrongOption1 = "6 cordas de nylon",
            wrongOption2 = "12 cordas em trios",
            wrongOption3 = "4 cordas simples",
            explanation = "A viola caipira possui 10 cordas organizadas em 5 pares que devem soar juntos em harmonia."
        )
        val q2 = createQuestion(
            id = "q_viola_${lessonNumber}_2",
            prompt = if (lessonNumber == 2) "Qual é a afinação mais celebrada pelos violeiros onde as cordas soltas soam Mi Maior?" else "Qual ritmo tradicional foi consagrado pelo mestre Tião Carreiro na viola?",
            correctOption = if (lessonNumber == 2) "Cebolão em Mi Maior" else "O Pagode de Viola caipira",
            wrongOption1 = "Afinação Drop D",
            wrongOption2 = "Afinação Jazz Standard",
            wrongOption3 = "Valsa vienense",
            explanation = "Cebolão em Mi e o Pagode de Viola são marcos fundamentais da identidade musical caipira."
        )
        return listOf(q1, q2)
    }

    private fun getPianoQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_piano_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "Qual dedo corresponde ao número 1 na técnica tradicional de piano?" else "Na aula '$title', qual postura das mãos garante agilidade e dinâmica?",
            correctOption = if (lessonNumber == 1) "O Polegar em ambas as mãos" else "Mãos relaxadas em formato de concha suave",
            wrongOption1 = "O Dedo Indicador",
            wrongOption2 = "O Dedo Mínimo",
            wrongOption3 = "O Dedo Médio",
            explanation = "No piano o polegar é sempre o dedo 1 e a mão em concha transmite o peso do braço com precisão."
        )
        val q2 = createQuestion(
            id = "q_piano_${lessonNumber}_2",
            prompt = if (lessonNumber == 1) "Onde se localiza a nota Dó no teclado do piano?" else "Qual é a função do pedal de sustentação (sustain/damper)?",
            correctOption = if (lessonNumber == 1) "Na tecla branca imediatamente à esquerda das duas teclas pretas" else "Permitir que as cordas continuem vibrando ao levantar as mãos",
            wrongOption1 = "À direita das três teclas pretas",
            wrongOption2 = "No centro das teclas pretas",
            wrongOption3 = "Apenas na última tecla do teclado",
            explanation = "O Dó fica sempre antes do par de teclas pretas, e o pedal de sustain sustenta as notas harmoniosamente."
        )
        return listOf(q1, q2)
    }

    private fun getKeyboardQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_teclado_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "O que diferencia um Teclado Arranjador de um Piano Acústico tradicional?" else "Na aula '$title', qual recurso tecnológico potencializa seu arranjo?",
            correctOption = if (lessonNumber == 1) "Ritmos eletrônicos automáticos, timbres variados e acompanhamento inteligente" else "Ajuste de timbres combinados (Dual/Layer) e metrônomo digital",
            wrongOption1 = "Não possui teclas pretas",
            wrongOption2 = "Funciona sem energia elétrica",
            wrongOption3 = "Não tem controle de volume",
            explanation = "O teclado arranjador possui biblioteca sonora ampla e gerador de ritmos para criar bandas completas."
        )
        val q2 = createQuestion(
            id = "q_teclado_${lessonNumber}_2",
            prompt = "O que significa a função 'Split Point' em um teclado eletrônico?",
            correctOption = "A tecla que divide o teclado em duas regiões com timbres diferentes",
            wrongOption1 = "O botão de volume mestre",
            wrongOption2 = "A saída para fones de ouvido",
            wrongOption3 = "A gravação de voz",
            explanation = "O ponto de divisão permite tocar contrabaixo na mão esquerda e piano/cordas na mão direita."
        )
        return listOf(q1, q2)
    }

    private fun getDrumsQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_bateria_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "Quais são as três peças fundamentais que compõem o groove básico da bateria?" else "Na aula de Bateria '$title', qual o papel da mão condutora?",
            correctOption = if (lessonNumber == 1) "O Bumbo (pé direito), a Caixa (mão esquerda) e o Chimbal (mão direita)" else "Manter a subdivisão rítmica no chimbal ou prato de condução",
            wrongOption1 = "Apenas os pratos de ataque",
            wrongOption2 = "Os três tons sem usar pedais",
            wrongOption3 = "O banco e a estante de partitura",
            explanation = "A trindade Bumbo, Caixa e Chimbal é a matriz do pop, rock e quase todos os ritmos modernos."
        )
        val q2 = createQuestion(
            id = "q_bateria_${lessonNumber}_2",
            prompt = "O que é o conceito de 'independência dos membros' na bateria?",
            correctOption = "A capacidade de controlar mãos e pés executando ritmos diferentes sem travar",
            wrongOption1 = "Tocar apenas com uma mão",
            wrongOption2 = "Tocar sem ouvir a música",
            wrongOption3 = "Tocar sem baquetas",
            explanation = "A independência motora permite que cada pé e mão execute sua função no compasso com precisão."
        )
        return listOf(q1, q2)
    }

    private fun getBassQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_baixo_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "Qual é a afinação padrão das 4 cordas do contrabaixo elétrico?" else "Na aula de Contrabaixo '$title', qual é a missão principal do baixista?",
            correctOption = if (lessonNumber == 1) "E - A - D - G (Mi, Lá, Ré, Sol) em registro grave" else "Conectar a harmonia com o ritmo do bumbo da bateria",
            wrongOption1 = "Dó, Ré, Mi, Fá",
            wrongOption2 = "Lá, Si, Dó, Ré",
            wrongOption3 = "Afinação de ukulele",
            explanation = "O baixo de 4 cordas segue as notas E-A-D-G, exatamente como as 4 cordas mais graves do violão."
        )
        val q2 = createQuestion(
            id = "q_baixo_${lessonNumber}_2",
            prompt = "O que caracteriza a famosa técnica de 'Slap' no contrabaixo?",
            correctOption = "Bater com o polegar nas cordas graves e puxar as agudas com o indicador",
            wrongOption1 = "Usar um arco de violoncelo",
            wrongOption2 = "Tocar apenas com palheta de ferro",
            wrongOption3 = "Abafar com fita adesiva",
            explanation = "O slap combina o golpe percussivo do polegar (thumb) com o puxão (pop) das cordas agudas."
        )
        return listOf(q1, q2)
    }

    private fun getUkuleleQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_ukulele_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "Qual é a afinação clássica do ukulele soprano e concerto?" else "Na aula de Ukulele '$title', qual o segredo da batida leve e alegre?",
            correctOption = if (lessonNumber == 1) "G - C - E - A (Sol, Dó, Mi, Lá com Sol reentrante mais agudo)" else "Relaxar o pulso e rasguear com a lateral do dedo indicador",
            wrongOption1 = "Mi, Lá, Ré, Sol, Si, Mi",
            wrongOption2 = "Dó, Ré, Mi, Fá",
            wrongOption3 = "Afinação de violoncelo",
            explanation = "A afinação tradicional havaiana possui a 4ª corda Sol reentrante (mais aguda que a 3ª corda Dó)."
        )
        val q2 = createQuestion(
            id = "q_ukulele_${lessonNumber}_2",
            prompt = "Quantos dedos são necessários para montar o clássico acorde de Dó Maior (C) no ukulele?",
            correctOption = "Apenas um dedo (dedo 3 na 3ª casa da 1ª corda)",
            wrongOption1 = "Quatro dedos com pestana",
            wrongOption2 = "Três dedos em formato de triângulo",
            wrongOption3 = "Nenhum dedo (cordas soltas)",
            explanation = "O acorde de C no ukulele é superfácil e usa apenas um dedo na 1ª corda, 3ª casa."
        )
        return listOf(q1, q2)
    }

    private fun getViolinQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1 = createQuestion(
            id = "q_violino_${lessonNumber}_1",
            prompt = if (lessonNumber == 1) "Qual é a afinação padrão das 4 cordas do violino da mais grave à mais aguda?" else "Na aula de Violino '$title', como o violinista afina cada nota sem trastes?",
            correctOption = if (lessonNumber == 1) "G - D - A - E (Sol, Ré, Lá, Mi em quintas justas)" else "Desenvolvendo memória muscular e ouvindo os harmônicos com precisão",
            wrongOption1 = "Mi, Lá, Ré, Sol",
            wrongOption2 = "Dó, Ré, Mi, Fá",
            wrongOption3 = "Afinação de violão",
            explanation = "As 4 cordas do violino são afinadas em intervalos de quintas justas: Sol3, Ré4, Lá4 e Mi5."
        )
        val q2 = createQuestion(
            id = "q_violino_${lessonNumber}_2",
            prompt = "Qual substância é aplicada na crina do arco para que ele faça as cordas vibrarem e produzirem som?",
            correctOption = "O breu (resina natural)",
            wrongOption1 = "Óleo vegetal",
            wrongOption2 = "Água destilada",
            wrongOption3 = "Grafite em pó",
            explanation = "O breu cria o atrito microscópico necessário entre a crina do arco e as cordas de aço/nylon do violino."
        )
        return listOf(q1, q2)
    }
}
