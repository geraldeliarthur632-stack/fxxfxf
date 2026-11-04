package com.example.data

import com.example.model.InstrumentType
import com.example.model.QuizQuestion

object LessonQuestionsProvider {

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
        }
    }

    private fun getGuitarQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1: QuizQuestion
        val q2: QuizQuestion

        when (lessonNumber) {
            1 -> {
                q1 = QuizQuestion(
                    id = "q_violao_1_1",
                    prompt = "Qual elemento do violão é o principal responsável por amplificar acusticamente o som das cordas?",
                    options = listOf("O tampo harmônico e a caixa de ressonância", "O tensor de metal dentro do braço", "As tarraxas na mão do instrumento", "O rastilho de plástico"),
                    correctIndex = 0,
                    explanation = "O tampo harmônico de madeira vibra em conjunto com as cordas, amplificando o som dentro da caixa de ressonância."
                )
                q2 = QuizQuestion(
                    id = "q_violao_1_2",
                    prompt = "Qual é a função dos trastes de metal incrustados na escala do violão?",
                    options = listOf("Servir apenas de enfeite visual", "Dividir o braço em semitons precisos ao encurtar a corda", "Segurar as cordas na afinação", "Amortecer a vibração dos dedos"),
                    correctIndex = 1,
                    explanation = "Cada traste delimita a corda em distâncias que correspondem exatamente a intervalos de semitom."
                )
            }
            2 -> {
                q1 = QuizQuestion(
                    id = "q_violao_2_1",
                    prompt = "Como deve permanecer a coluna e os ombros ao praticar violão?",
                    options = listOf("Coluna ereta e ombros relaxados sem tensão", "Inclinado para a frente encostado no tampo", "Com o ombro esquerdo levantado perto do queixo", "Com o violão deitado totalmente no colo"),
                    correctIndex = 0,
                    explanation = "Uma postura relaxada e ereta previne dores lombares e lesões por esforço repetitivo."
                )
                q2 = QuizQuestion(
                    id = "q_violao_2_2",
                    prompt = "Onde o polegar da mão esquerda deve ficar posicionado idealmente?",
                    options = listOf("Envolvendo todo o braço e abafando as cordas", "No centro da parte traseira do braço, sem força excessiva", "Apontando para a mão do violão", "Escondido dentro da palma da mão"),
                    correctIndex = 1,
                    explanation = "O polegar atrás do braço serve de apoio suave, dando liberdade para os outros 4 dedos alcançarem as casas."
                )
            }
            3 -> {
                q1 = QuizQuestion(
                    id = "q_violao_3_1",
                    prompt = "Qual é a ordem correta das 6 cordas do violão da mais grossa (6ª) para a mais fina (1ª)?",
                    options = listOf("Mi (E), Lá (A), Ré (D), Sol (G), Si (B), Mi (E)", "Dó, Ré, Mi, Fá, Sol, Lá", "Mi, Si, Sol, Ré, Lá, Mi", "Lá, Ré, Sol, Dó, Mi, Lá"),
                    correctIndex = 0,
                    explanation = "A afinação padrão (Standard EADGBE) vai do Mi grave (6ª) até o Mi agudo (1ª)."
                )
                q2 = QuizQuestion(
                    id = "q_violao_3_2",
                    prompt = "Como os músicos costumam chamar carinhosamente a 1ª corda do violão?",
                    options = listOf("Bordão", "Mizinha", "Prima de aço", "Baixão"),
                    correctIndex = 1,
                    explanation = "A 1ª corda é chamada de 'mizinha', enquanto a 6ª corda mais grossa é chamada de 'mizão' ou bordão."
                )
            }
            4 -> {
                q1 = QuizQuestion(
                    id = "q_violao_4_1",
                    prompt = "Na mão esquerda do violonista, que dedo é representado pelo número 1?",
                    options = listOf("O dedo polegar", "O dedo indicador", "O dedo médio", "O dedo anelar"),
                    correctIndex = 1,
                    explanation = "A numeração da mão esquerda na partitura e métodos é: 1=Indicador, 2=Médio, 3=Anelar, 4=Mínimo."
                )
                q2 = QuizQuestion(
                    id = "q_violao_4_2",
                    prompt = "Quais letras representam os dedos da mão direita usados nos dedilhados?",
                    options = listOf("P, I, M, A (Polegar, Indicador, Médio, Anelar)", "1, 2, 3, 4", "A, B, C, D", "X, Y, Z, W"),
                    correctIndex = 0,
                    explanation = "Utiliza-se as iniciais em português/espanhol: P (Polegar), I (Indicador), M (Médio) e A (Anelar)."
                )
            }
            5 -> {
                q1 = QuizQuestion(
                    id = "q_violao_5_1",
                    prompt = "Qual é o segredo para a nota soar limpa e sem zumbido no violão?",
                    options = listOf("Pressionar a corda bem próximo ao traste de metal", "Pressionar no meio da casa com força excessiva", "Deitar os dedos cobrindo as cordas vizinhas", "Tocar o mais rápido possível"),
                    correctIndex = 0,
                    explanation = "Apertar a corda próxima ao traste metálico requer o mínimo de força e evita o trastejamento indesejado."
                )
                q2 = QuizQuestion(
                    id = "q_violao_5_2",
                    prompt = "Em que formato os dedos da mão esquerda devem tocar as cordas?",
                    options = listOf("Formato de arco ou martelinho com as pontas", "Completamente retos e esticados", "Deitados de lado", "Apoiando a palma colada no braço"),
                    correctIndex = 0,
                    explanation = "Os dedos em formato de arco (martelinho) impedem que a polpa do dedo encoste nas cordas abaixo e as abafe."
                )
            }
            6 -> {
                q1 = QuizQuestion(
                    id = "q_violao_6_1",
                    prompt = "Quais dedos e casas são utilizados para formar o acorde de Mi Menor (Em)?",
                    options = listOf("Dedos 2 e 3 na 2ª casa das cordas 5 (Lá) e 4 (Ré)", "Dedo 1 na 1ª casa da corda 2", "Dedos 1, 2 e 3 na 3ª casa", "Pestana completa na 2ª casa"),
                    correctIndex = 0,
                    explanation = "O Em é montado colocando os dedos na 2ª casa da 5ª e da 4ª corda, tocando todas as 6 cordas."
                )
                q2 = QuizQuestion(
                    id = "q_violao_6_2",
                    prompt = "Quantas cordas soam soltas no acorde de Mi Menor (Em)?",
                    options = listOf("Quatro cordas (6ª, 3ª, 2ª e 1ª)", "Nenhuma corda", "Apenas uma corda", "Todas as cordas são presas"),
                    correctIndex = 0,
                    explanation = "Como prendemos apenas 2 cordas, 4 cordas continuam vibrando soltas, criando um som encorpado."
                )
            }
            7 -> {
                q1 = QuizQuestion(
                    id = "q_violao_7_1",
                    prompt = "Qual é a relação visual entre o acorde de Mi Maior (E) e o de Lá Menor (Am)?",
                    options = listOf("Possuem exatamente o mesmo formato físico, apenas descendo uma corda abaixo", "São totalmente diferentes e invertidos", "O Lá Menor usa pestana na 5ª casa", "O Lá Menor não usa o dedo indicador"),
                    correctIndex = 0,
                    explanation = "Ao montar Mi Maior e descer o bloco de dedos uma corda para baixo, obtemos o acorde de Lá Menor (Am)!"
                )
                q2 = QuizQuestion(
                    id = "q_violao_7_2",
                    prompt = "A partir de qual corda devemos tocar o acorde de Lá Menor (Am) para que a nota tônica seja ouvida?",
                    options = listOf("A partir da 5ª corda solta (Lá)", "A partir da 6ª corda mais grave", "Apenas na 1ª corda", "A partir da 3ª corda"),
                    correctIndex = 0,
                    explanation = "O baixo do acorde de Am é a 5ª corda (Lá solta). A 6ª corda pode ser evitada ou levemente abafada."
                )
            }
            8 -> {
                q1 = QuizQuestion(
                    id = "q_violao_8_1",
                    prompt = "Qual técnica facilita a troca rápida entre os acordes Em e Am?",
                    options = listOf("Manter os dedos agrupados em bloco e usar o dedo guia para deslizar", "Tirar a mão inteira do violão e recomeçar do zero", "Parar a batida rítmica a cada troca", "Olhar apenas para a mão direita"),
                    correctIndex = 0,
                    explanation = "Mover os dedos em conjunto sem espalhá-los pelo ar reduz o tempo de transição entre os acordes."
                )
                q2 = QuizQuestion(
                    id = "q_violao_8_2",
                    prompt = "O que deve ter prioridade máxima durante o treino de troca de acordes?",
                    options = listOf("Manter o tempo rítmico contínuo sem parar a levada", "Fazer força máxima na mão esquerda", "Tocar apenas quando tiver certeza absoluta", "Tocar com volume excessivo"),
                    correctIndex = 0,
                    explanation = "A consistência rítmica é a prioridade: o ritmo não deve congelar enquanto a mão esquerda se posiciona."
                )
            }
            9 -> {
                q1 = QuizQuestion(
                    id = "q_violao_9_1",
                    prompt = "Qual dedo pressiona a tônica Dó na 5ª corda (3ª casa) no acorde de Dó Maior (C)?",
                    options = listOf("Dedo 3 (anelar)", "Dedo 1 (indicador)", "Dedo 4 (mínimo)", "Dedo polegar"),
                    correctIndex = 0,
                    explanation = "O dedo 3 da mão esquerda pressiona a 5ª corda na 3ª casa, tocando a nota Dó fundamental."
                )
                q2 = QuizQuestion(
                    id = "q_violao_9_2",
                    prompt = "Qual corda NÃO deve ser tocada como baixo no acorde de Dó Maior aberto tradicional?",
                    options = listOf("A 6ª corda (Mi grave)", "A 5ª corda", "A 1ª corda", "A 3ª corda"),
                    correctIndex = 0,
                    explanation = "A 6ª corda solta é um Mi, que conflita com a tônica do Dó se não for abafada pela ponta do dedo 3."
                )
            }
            10 -> {
                q1 = QuizQuestion(
                    id = "q_violao_10_1",
                    prompt = "Onde é posicionado o dedo 3 (ou dedo 4) no acorde de Sol Maior (G) clássico?",
                    options = listOf("Na 1ª corda, 3ª casa (Sol agudo)", "Na 6ª corda, 1ª casa", "Na 4ª corda solta", "Na 2ª casa da 3ª corda"),
                    correctIndex = 0,
                    explanation = "No Sol Maior tradicional, um dos dedos prende a 1ª corda na 3ª casa para soar a nota Sol aguda."
                )
                q2 = QuizQuestion(
                    id = "q_violao_10_2",
                    prompt = "Qual nota da 6ª corda na 3ª casa dá nome ao acorde de Sol Maior?",
                    options = listOf("Nota Sol (G)", "Nota Fá (F)", "Nota Dó (C)", "Nota Ré (D)"),
                    correctIndex = 0,
                    explanation = "Na 6ª corda, a 3ª casa é a nota Sol (G), a nota tônica do acorde."
                )
            }
            11 -> {
                q1 = QuizQuestion(
                    id = "q_violao_11_1",
                    prompt = "Quantas cordas são tocadas no acorde de Ré Maior (D) padrão?",
                    options = listOf("As 4 primeiras cordas, partindo da 4ª solta (Ré)", "Todas as 6 cordas", "Apenas 2 cordas", "Somente as cordas graves"),
                    correctIndex = 0,
                    explanation = "O Ré Maior soa a partir da 4ª corda solta (Ré), que fornece o baixo perfeito para o acorde."
                )
                q2 = QuizQuestion(
                    id = "q_violao_11_2",
                    prompt = "Que figura geométrica os dedos 1, 2 e 3 formam no braço do violão no acorde de D?",
                    options = listOf("Um triângulo nas três primeiras cordas", "Uma linha reta horizontal", "Um quadrado de 4 casas", "Um semicírculo"),
                    correctIndex = 0,
                    explanation = "A digitação do Ré Maior forma um triângulo bem característico nas cordas 1, 2 e 3."
                )
            }
            12 -> {
                q1 = QuizQuestion(
                    id = "q_violao_12_1",
                    prompt = "A progressão G - Em - C - D é conhecida popularmente como a base de centenas de músicas de qual gênero?",
                    options = listOf("Música Pop e Rock acústico mundial", "Música clássica barroca", "Choro instrumental e maxixe", "Valsa austríaca"),
                    correctIndex = 0,
                    explanation = "Essa progressão é a lendária 'progressão de 4 acordes' presente em incontáveis sucessos mundiais."
                )
                q2 = QuizQuestion(
                    id = "q_violao_12_2",
                    prompt = "Nessa sequência em Sol Maior, qual acorde exerce o papel de 'relativa menor'?",
                    options = listOf("Mi Menor (Em)", "Dó Maior (C)", "Ré Maior (D)", "Lá Menor (Am)"),
                    correctIndex = 0,
                    explanation = "O acorde do sexto grau de Sol Maior é o Mi Menor (Em), sua relativa menor natural."
                )
            }
            14 -> {
                q1 = QuizQuestion(
                    id = "q_violao_14_1",
                    prompt = "Qual é a sequência rítmica da batida universal Pop/Rock mais famosa no violão?",
                    options = listOf("Baixo, Baixo, Cima, Cima, Baixo, Cima", "Apenas batidas para baixo o tempo todo", "Cima, Cima, Cima, Cima", "Baixo, Cima, Baixo, Cima sem pausas"),
                    correctIndex = 0,
                    explanation = "A sequência ↓ ↓ ↑ ↑ ↓ ↑ cria a síncope perfeita que dá o balanço do pop e rock acústico."
                )
                q2 = QuizQuestion(
                    id = "q_violao_14_2",
                    prompt = "O que a mão direita deve fazer no movimento fantasma (ghost stroke) quando não toca as cordas?",
                    options = listOf("Continuar balançando no tempo como um pêndulo sem tocar as cordas", "Parar completamente no ar e esperar", "Soltar a palheta", "Bater no tampo com força"),
                    correctIndex = 0,
                    explanation = "O braço age como um pêndulo contínuo; nos tempos vazios, ele passa sem tocar as cordas, mantendo o pulso."
                )
            }
            16 -> {
                q1 = QuizQuestion(
                    id = "q_violao_16_1",
                    prompt = "Em um compasso 4/4, quantas batidas principais (tempos) existem em cada compasso?",
                    options = listOf("4 tempos", "3 tempos", "2 tempos", "6 tempos"),
                    correctIndex = 0,
                    explanation = "A fórmula de compasso 4/4 significa quatro tempos por compasso, a pulsação mais comum da música."
                )
                q2 = QuizQuestion(
                    id = "q_violao_16_2",
                    prompt = "Qual tempo do compasso 4/4 tradicionalmente recebe o apoio ou acento forte?",
                    options = listOf("O 1º tempo", "O 2º tempo", "O 4º tempo", "Nenhum tempo"),
                    correctIndex = 0,
                    explanation = "O tempo 1 é o tempo forte do compasso 4/4, onde inicia cada ciclo musical."
                )
            }
            20 -> {
                q1 = QuizQuestion(
                    id = "q_violao_20_1",
                    prompt = "Como é executado o efeito percussivo de Palm Mute (abafamento com a palma)?",
                    options = listOf("Apoiando a lateral da palma da mão direita suavemente sobre as cordas junto à ponte", "Apertando as cordas no 12º traste", "Tirando as cordas do violão", "Colocando um pano dentro da boca do violão"),
                    correctIndex = 0,
                    explanation = "A lateral carnosa da mão direita pousada junto ao cavalete abafa os agudos e cria um som rítmico pesado."
                )
                q2 = QuizQuestion(
                    id = "q_violao_20_2",
                    prompt = "Em qual estilo musical o Palm Mute é largamente utilizado?",
                    options = listOf("No Rock, Heavy Metal e Baladas acústicas modernas", "Na ópera barroca clássica", "Em cantigas de ninar exclusivamente", "No canto gregoriano"),
                    correctIndex = 0,
                    explanation = "O Palm Mute confere peso rítmico e dinamismo essencial no rock e pop moderno."
                )
            }
            26 -> {
                q1 = QuizQuestion(
                    id = "q_violao_26_1",
                    prompt = "Quem compôs a famosa melodia do tema 'Ode à Alegria'?",
                    options = listOf("Ludwig van Beethoven (9ª Sinfonia)", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "Antonio Vivaldi"),
                    correctIndex = 0,
                    explanation = "A Ode à Alegria é o tema imortal do movimento final da Nona Sinfonia de Beethoven."
                )
                q2 = QuizQuestion(
                    id = "q_violao_26_2",
                    prompt = "Nas primeiras notas da Ode à Alegria, a melodia começa em qual nota na 1ª corda solta?",
                    options = listOf("Nota Mi (E)", "Nota Dó (C)", "Nota Sol (G)", "Nota Ré (D)"),
                    correctIndex = 0,
                    explanation = "A melodia inicia com a nota Mi (1ª corda solta) tocada duas vezes: Mi - Mi - Fá - Sol..."
                )
            }
            32 -> {
                q1 = QuizQuestion(
                    id = "q_violao_32_1",
                    prompt = "Quem compôs a lendária canção 'Asa Branca', um dos hinos da música brasileira?",
                    options = listOf("Luiz Gonzaga e Humberto Teixeira", "Tom Jobim e Vinicius de Moraes", "Pixinguinha", "Cartola"),
                    correctIndex = 0,
                    explanation = "Asa Branca foi composta em 1947 por Luiz Gonzaga (o Rei do Baião) e Humberto Teixeira."
                )
                q2 = QuizQuestion(
                    id = "q_violao_32_2",
                    prompt = "Qual é o ritmo nordestino característico usado no acompanhamento de Asa Branca?",
                    options = listOf("O Baião com acento sincopado", "A Valsa austríaca", "O Samba-canção lento", "O Blues americano"),
                    correctIndex = 0,
                    explanation = "O baião com sua batida sincopada e marcante no baixo é o coração do ritmo de Asa Branca."
                )
            }
            41 -> {
                q1 = QuizQuestion(
                    id = "q_violao_41_1",
                    prompt = "No dedilhado padrão P-I-M-A, qual dedo da mão direita é responsável pelas cordas graves (bordões 6, 5 e 4)?",
                    options = listOf("O Polegar (P)", "O Indicador (I)", "O Médio (M)", "O Anelar (A)"),
                    correctIndex = 0,
                    explanation = "O polegar (P) cuida dos bordões graves, enquanto os dedos I, M e A cuidam das cordas 3, 2 e 1."
                )
                q2 = QuizQuestion(
                    id = "q_violao_41_2",
                    prompt = "Qual é a postura correta da mão direita ao executar um arpejo dedilhado?",
                    options = listOf("Punho levemente arqueado e dedos relaxados caindo para dentro da palma", "Punho dobrado encostando na madeira", "Dedos totalmente esticados para cima", "Polegar escondido sob a palma"),
                    correctIndex = 0,
                    explanation = "Um punho arqueado com folga permite que os dedos toquem de forma independente sem esbarrar nas cordas."
                )
            }
            51 -> {
                q1 = QuizQuestion(
                    id = "q_violao_51_1",
                    prompt = "Entre quais pares de notas naturais NÃO existe intervalo de sustenido (#) na escala cromática?",
                    options = listOf("Entre Mi-Fá e entre Si-Dó", "Entre Dó-Ré e entre Sol-Lá", "Entre Ré-Mi e entre Lá-Si", "Entre Fá-Sol e entre Dó-Mi"),
                    correctIndex = 0,
                    explanation = "Na escala natural ocidental, os semitons naturais ocorrem entre Mi-Fá e Si-Dó (não há Mi# ou Si# nas notas naturais)."
                )
                q2 = QuizQuestion(
                    id = "q_violao_51_2",
                    prompt = "Quantos semitons (casas no violão) equivalem a 1 Tom inteiro?",
                    options = listOf("2 casas (2 semitons)", "1 casa", "3 casas", "12 casas"),
                    correctIndex = 0,
                    explanation = "Cada casa no braço do violão é 1 semitom. Logo, andar 2 casas para frente equivale a 1 Tom inteiro."
                )
            }
            61 -> {
                q1 = QuizQuestion(
                    id = "q_violao_61_1",
                    prompt = "Qual é o segredo mecânico para montar a pestana de Fá Maior (F) sem machucar a mão?",
                    options = listOf("Usar a lateral mais óssea do dedo indicador levemente curvada e o peso do braço", "Apertar o polegar com força extrema no braço", "Usar a almofada macia e frouxa do dedo", "Colocar o dedo indicador no meio da casa 2"),
                    correctIndex = 0,
                    explanation = "A borda lateral do indicador é mais firme que a polpa carnosa, exigindo menos força muscular."
                )
                q2 = QuizQuestion(
                    id = "q_violao_61_2",
                    prompt = "No acorde de Fá Maior (F), em qual casa a pestana do dedo 1 é aplicada cobrindo todas as cordas?",
                    options = listOf("Na 1ª casa", "Na 3ª casa", "Na 5ª casa", "Na 12ª casa"),
                    correctIndex = 0,
                    explanation = "O acorde de Fá Maior tradicional tem sua pestana de dedo 1 na 1ª casa do violão."
                )
            }
            76 -> {
                q1 = QuizQuestion(
                    id = "q_violao_76_1",
                    prompt = "Quantas notas diferentes compõem uma escala pentatônica?",
                    options = listOf("5 notas", "7 notas", "12 notas", "8 notas"),
                    correctIndex = 0,
                    explanation = "O prefixo 'penta' significa cinco notas, eliminando notas de tensão para soar sempre musical."
                )
                q2 = QuizQuestion(
                    id = "q_violao_76_2",
                    prompt = "Qual é a escala de improvisação mais utilizada no Blues, Rock e solos de violão?",
                    options = listOf("A Escala Pentatônica Menor", "A Escala Pentatônica Hexagonal", "A Escala Cômica", "Apenas a escala de fá sustenido"),
                    correctIndex = 0,
                    explanation = "A pentatônica menor com ou sem a Blue Note é a espinha dorsal de quase todos os grandes solos da história."
                )
            }
            else -> {
                // Generates dynamic topic-specific questions based on lesson index and module
                val mod = CourseRepository.getModuleForLesson(lessonNumber)
                q1 = QuizQuestion(
                    id = "q_violao_${lessonNumber}_1",
                    prompt = "Na aula '$title', qual conceito é o mais importante para o seu domínio no violão?",
                    options = listOf(
                        "Praticar a digitação com precisão rítmica e clareza sonora",
                        "Tocar o mais rápido possível mesmo errando as notas",
                        "Ignorar a afinação do instrumento",
                        "Pressionar as cordas no meio da casa sem postura"
                    ),
                    correctIndex = 0,
                    explanation = "A clareza sonora e o controle do tempo rítmico são os pilares essenciais ensinados nesta etapa do curso."
                )
                q2 = QuizQuestion(
                    id = "q_violao_${lessonNumber}_2",
                    prompt = "Como o estudo desta técnica do Módulo ${mod.moduleNumber} (${mod.titlePt}) contribui para a sua evolução?",
                    options = listOf(
                        "Desenvolve memória muscular, agilidade e fluência interpretativa",
                        "Substitui a necessidade de afinar o violão",
                        "Serve apenas para tocar um acorde específico",
                        "Não tem aplicação em outros estilos musicais"
                    ),
                    correctIndex = 0,
                    explanation = "Cada técnica exercitada cria caminhos neurais e destreza motora que serão aproveitados em todo o repertório futuro."
                )
            }
        }

        return listOf(q1, q2)
    }

    private fun getViolaQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1: QuizQuestion
        val q2: QuizQuestion

        when (lessonNumber) {
            1 -> {
                q1 = QuizQuestion(
                    id = "q_viola_1_1",
                    prompt = "Quantas cordas possui uma viola caipira tradicional brasileira?",
                    options = listOf("10 cordas agrupadas em 5 pares", "6 cordas individuais", "12 cordas em trios", "4 cordas de nylon"),
                    correctIndex = 0,
                    explanation = "A viola caipira é célebre por suas 10 cordas de aço organizadas em 5 ordens ou pares."
                )
                q2 = QuizQuestion(
                    id = "q_viola_1_2",
                    prompt = "Como são divididos os 5 pares de cordas da viola caipira?",
                    options = listOf("Os dois primeiros pares são uníssonos e os três últimos são oitavados", "Todos os 5 pares são afinados em uníssono", "Todos os pares são afinados em quintas", "São 10 cordas soltas sem pares"),
                    correctIndex = 0,
                    explanation = "Nas afinações clássicas como o Cebolão, os pares 1 e 2 soam em uníssono e os pares 3, 4 e 5 têm uma corda grave e outra uma oitava acima."
                )
            }
            2 -> {
                q1 = QuizQuestion(
                    id = "q_viola_2_1",
                    prompt = "Qual é o nome da afinação mais tradicional e emblemática da viola caipira?",
                    options = listOf("Cebolão em Mi Maior", "Drop D", "Open G americana", "Afinação Dó Maior"),
                    correctIndex = 0,
                    explanation = "O Cebolão em Mi é a afinação mais cultuada pelos grandes mestres da música caipira brasileira."
                )
                q2 = QuizQuestion(
                    id = "q_viola_2_2",
                    prompt = "O que acontece ao tocar todas as cordas soltas na afinação Cebolão em Mi?",
                    options = listOf("Soa imediatamente um acorde completo de Mi Maior com ressonância rica", "Soa uma nota desafinada dissonante", "Soa um acorde menor melancólico", "Não produz som audível"),
                    correctIndex = 0,
                    explanation = "Por ser uma afinação aberta, as cordas soltas soam o acorde de Mi Maior perfeitamente consonante."
                )
            }
            3 -> {
                q1 = QuizQuestion(
                    id = "q_viola_3_1",
                    prompt = "Qual acessório é frequentemente utilizado no polegar direito pelo violeiro tradicional?",
                    options = listOf("A dedeira de chifre, osso ou plástico", "Um anel de ferro pesado", "Uma luva de lã", "Uma correia de borracha"),
                    correctIndex = 0,
                    explanation = "A dedeira no polegar permite ferir os baixos com ataque firme, clareza e o timbre brilhante característico da viola."
                )
                q2 = QuizQuestion(
                    id = "q_viola_3_2",
                    prompt = "Como o violeiro deve apoiar os dedos da mão esquerda para não abafar os pares de cordas?",
                    options = listOf("Manter os dedos arredondados em martelo tocando ambas as cordas do par simultaneamente", "Deitar os dedos cobrindo vários pares", "Tocar apenas a corda mais fina de cada par", "Apertar com as unhas"),
                    correctIndex = 0,
                    explanation = "O dedo deve prender com precisão ambas as cordas do mesmo par ao mesmo tempo sem tocar nos pares adjacentes."
                )
            }
            16 -> {
                q1 = QuizQuestion(
                    id = "q_viola_16_1",
                    prompt = "Quem criou e consagrou o lendário ritmo do 'Pagode de Viola' brasileiro?",
                    options = listOf("Tião Carreiro e Pardinho", "Luiz Gonzaga", "Chiquinha Gonzaga", "Heitor Villa-Lobos"),
                    correctIndex = 0,
                    explanation = "Tião Carreiro inventou o Pagode de Viola em 1959 misturando elementos de cururu com catira e bate-pé."
                )
                q2 = QuizQuestion(
                    id = "q_viola_16_2",
                    prompt = "O que caracteriza a levada rítmica do Pagode de Viola de Tião Carreiro?",
                    options = listOf("O corte ou abafamento com a lateral da mão direita gerando efeito de caixa percussiva", "Uma levada de valsa vienense lenta", "Apenas dedilhado suave sem batida", "Uso exclusivo de arco de violino"),
                    correctIndex = 0,
                    explanation = "O som percussivo característico do pagode de viola vem da batida seca abafada com a mão direita no tampo."
                )
            }
            41 -> {
                q1 = QuizQuestion(
                    id = "q_viola_41_1",
                    prompt = "O que significa a técnica do 'Ponteado' na viola caipira?",
                    options = listOf("Tocar frases melódicas soladas em pares de cordas com dedilhado rápido ou palhetadas", "Tocar apenas o acorde final da música", "Apertar a ponte de madeira do instrumento", "Trocar as cordas de aço"),
                    correctIndex = 0,
                    explanation = "O ponteado é a arte de solar na viola, criando introduções e duetos marcantes nos pares de cordas."
                )
                q2 = QuizQuestion(
                    id = "q_viola_41_2",
                    prompt = "Na afinação Cebolão, os ponteados tradicionais frequentemente caminham em qual intervalo harmônico?",
                    options = listOf("Em terças ou sextas paralelas nos dois primeiros pares", "Em segundas menores dissonantes", "Apenas em oitavas graves", "Em acordes diminutos"),
                    correctIndex = 0,
                    explanation = "Caminhar em terças e sextas nos pares 1 e 2 produz a sonoridade caipira lírica e inconfundível."
                )
            }
            else -> {
                val mod = CourseRepository.getModuleForLesson(lessonNumber)
                q1 = QuizQuestion(
                    id = "q_viola_${lessonNumber}_1",
                    prompt = "Na aula '$title', qual cuidado essencial garante a sonoridade tradicional da viola?",
                    options = listOf(
                        "Garantir que as duas cordas de cada par soem juntas e afinadas com clareza",
                        "Tocar apenas uma das cordas de cada par",
                        "Ignorar a afinação dos pares oitavados",
                        "Substituir as cordas de aço por cordas de violoncelo"
                    ),
                    correctIndex = 0,
                    explanation = "A magia da viola vem da ressonância combinada de ambos os fios de aço vibrando em perfeita harmonia."
                )
                q2 = QuizQuestion(
                    id = "q_viola_${lessonNumber}_2",
                    prompt = "Qual elemento cultural brasileiro é celebrado no repertório do Módulo ${mod.moduleNumber} (${mod.titlePt})?",
                    options = listOf(
                        "As raízes da música sertaneja de raiz, cururu, cateretê e toadas",
                        "Música eletrônica de sintetizadores",
                        "Reggae jamaicano acústico",
                        "Valsas barrocas alemãs"
                    ),
                    correctIndex = 0,
                    explanation = "A viola caipira é patrimônio cultural do Brasil e preserva os ritmos mais puros da terra e do interior."
                )
            }
        }

        return listOf(q1, q2)
    }

    private fun getPianoQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1: QuizQuestion
        val q2: QuizQuestion

        when (lessonNumber) {
            1 -> {
                q1 = QuizQuestion(
                    id = "q_piano_1_1",
                    prompt = "Qual dedo corresponde ao número 1 na técnica tradicional de piano para ambas as mãos?",
                    options = listOf("O Polegar", "O Indicador", "O Dedo Médio", "O Dedo Mínimo"),
                    correctIndex = 0,
                    explanation = "No piano, a numeração universal dos dedos é: 1=Polegar, 2=Indicador, 3=Médio, 4=Anelar, 5=Mínimo."
                )
                q2 = QuizQuestion(
                    id = "q_piano_1_2",
                    prompt = "Como identificar visualmente a nota Dó (C) no teclado do piano?",
                    options = listOf("É a tecla branca imediatamente à esquerda do grupo de 2 teclas pretas", "Fica à direita do grupo de 3 teclas pretas", "Fica no centro do grupo de 3 teclas pretas", "É qualquer tecla preta"),
                    correctIndex = 0,
                    explanation = "O teclado alterna grupos de 2 e 3 teclas pretas. O Dó fica sempre antes das 2 teclas pretas."
                )
            }
            2 -> {
                q1 = QuizQuestion(
                    id = "q_piano_2_1",
                    prompt = "Qual é a postura correta das mãos sobre o teclado do piano?",
                    options = listOf("Mãos em formato de concha suave, como se segurasse uma maçã ou bola de tênis", "Dedos totalmente retos e rígidos", "Com a palma colada na madeira da frente", "Com os dedos hiperestendidos para trás"),
                    correctIndex = 0,
                    explanation = "A mão relaxada em concha distribui o peso natural do braço através das pontas dos dedos nas teclas."
                )
                q2 = QuizQuestion(
                    id = "q_piano_2_2",
                    prompt = "O que é o Dó Central (C4) e por que ele é a referência principal no piano?",
                    options = listOf("É o Dó situado no meio do instrumento que separa a Clave de Fá (mão esquerda) da Clave de Sol (mão direita)", "É a tecla mais aguda do piano", "É o botão de ligar o piano acústico", "É a tecla do pedal direito"),
                    correctIndex = 0,
                    explanation = "O Dó Central serve de ponto de partida didático conectando o registro grave e o registro agudo."
                )
            }
            6 -> {
                q1 = QuizQuestion(
                    id = "q_piano_6_1",
                    prompt = "Quais três notas compõem o acorde básico de Dó Maior (C) no piano?",
                    options = listOf("Dó (C), Mi (E) e Sol (G)", "Dó (C), Ré (D) e Mi (E)", "Dó (C), Fá (F) e Lá (A)", "Dó (C), Mi (E) e Si (B)"),
                    correctIndex = 0,
                    explanation = "A tríade de Dó Maior é formada pela fundamental (Dó), terça maior (Mi) e quinta justa (Sol)."
                )
                q2 = QuizQuestion(
                    id = "q_piano_6_2",
                    prompt = "Quais dedos da mão direita costumam ser utilizados para tocar a tríade de C na posição fundamental?",
                    options = listOf("Dedos 1 (polegar), 3 (médio) e 5 (mínimo)", "Dedos 1, 2 e 3", "Dedos 2, 3 e 4", "Dedos 3, 4 e 5"),
                    correctIndex = 0,
                    explanation = "A digitação 1-3-5 é a formação padrão ergonômica para tríades na posição fundamental."
                )
            }
            26 -> {
                q1 = QuizQuestion(
                    id = "q_piano_26_1",
                    prompt = "Ao tocar com as duas mãos juntas, qual papel a mão esquerda costuma desempenhar na música popular e clássica?",
                    options = listOf("Tocar os baixos fundamentais e harmonia enquanto a direita canta a melodia", "Tocar a melodia oitavada no topo", "Ficar imóvel sem tocar nada", "Apenas apertar o pedal"),
                    correctIndex = 0,
                    explanation = "A mão esquerda dá a sustentação harmônica e rítmica (baixo), liberando a mão direita para conduzir a melodia."
                )
                q2 = QuizQuestion(
                    id = "q_piano_26_2",
                    prompt = "Qual é a melhor dica para conseguir tocar com as duas mãos coordenadas?",
                    options = listOf("Dominar cada mão separadamente e lentamente antes de tentar juntar", "Tentar tocar muito rápido logo na primeira tentativa", "Olhar apenas para uma das mãos o tempo todo", "Nunca contar o tempo"),
                    correctIndex = 0,
                    explanation = "Praticar cada mão isoladamente cria independência motora, facilitando a sincronia das duas mãos juntas."
                )
            }
            51 -> {
                q1 = QuizQuestion(
                    id = "q_piano_51_1",
                    prompt = "Qual é a função do pedal de sustentação (sustain/damper pedal), o mais à direita no piano?",
                    options = listOf("Levantar os abafadores permitindo que as cordas continuem soando após soltar as teclas", "Deixar o som mais baixo e abafado", "Mudar a afinação das cordas em 1 tom", "Desligar o som do piano"),
                    correctIndex = 0,
                    explanation = "O pedal de sustain libera as cordas para ressoarem livremente, criando ligaduras e sonoridade ampla."
                )
                q2 = QuizQuestion(
                    id = "q_piano_51_2",
                    prompt = "Quando o pianista deve 'trocar' o pedal de sustain para evitar que os acordes virem uma confusão sonora?",
                    options = listOf("Exatamente no momento em que um novo acorde é acionado (pedal sincopado)", "Nunca, o pedal deve ficar sempre abaixado", "Apenas no final da música", "A cada nota individualmente rápida"),
                    correctIndex = 0,
                    explanation = "A troca sincopada do pedal limpa a ressonância do acorde anterior sem deixar buracos de silêncio."
                )
            }
            else -> {
                val mod = CourseRepository.getModuleForLesson(lessonNumber)
                q1 = QuizQuestion(
                    id = "q_piano_${lessonNumber}_1",
                    prompt = "Na aula de Piano '$title', como você deve articular os dedos para obter expressividade?",
                    options = listOf(
                        "Com toque firme e relaxado a partir das pontas dos dedos e peso do braço",
                        "Batendo as mãos espalmadas com força nas teclas",
                        "Usando apenas os polegares para todas as notas",
                        "Tensionando os pulsos e os ombros"
                    ),
                    correctIndex = 0,
                    explanation = "A dinâmica pianística (piano e forte) decorre da velocidade e peso com que a tecla é descida, não de tensão."
                )
                q2 = QuizQuestion(
                    id = "q_piano_${lessonNumber}_2",
                    prompt = "Como os exercícios práticos do Módulo ${mod.moduleNumber} (${mod.titlePt}) ajudam seu desenvolvimento pianístico?",
                    options = listOf(
                        "Fortalecem a independência dos dedos 4 e 5 e a leitura harmônica",
                        "Eliminam a necessidade de praticar escalas",
                        "Servem apenas para tocar em pianos elétricos",
                        "Evitam que você precise usar a mão esquerda"
                    ),
                    correctIndex = 0,
                    explanation = "O desenvolvimento do dedo 4 (anelar) e 5 (mínimo) é crucial para o equilíbrio e brilho no piano."
                )
            }
        }

        return listOf(q1, q2)
    }

    private fun getKeyboardQuestions(lessonNumber: Int, title: String): List<QuizQuestion> {
        val q1: QuizQuestion
        val q2: QuizQuestion

        when (lessonNumber) {
            1 -> {
                q1 = QuizQuestion(
                    id = "q_teclado_1_1",
                    prompt = "Qual é a principal diferença funcional entre um Teclado Arranjador e um Piano Acústico tradicional?",
                    options = listOf("O Teclado Arranjador possui ritmos eletrônicos automáticos, timbres variados e acompanhamento inteligente", "O teclado arranjador não possui teclas brancas e pretas", "O piano acústico funciona com bateria 9V", "Não existe nenhuma diferença"),
                    correctIndex = 0,
                    explanation = "O teclado arranjador inclui gerador de som com centenas de timbres, ritmos de acompanhamento e arpejadores."
                )
                q2 = QuizQuestion(
                    id = "q_teclado_1_2",
                    prompt = "O que significa o termo 'Split Point' (ponto de divisão) em um teclado?",
                    options = listOf("A tecla que divide o teclado em duas regiões com timbres ou funções diferentes (ex: baixo na esquerda e piano na direita)", "O botão de volume mestre", "A entrada do cabo de energia", "A saída de fone de ouvido"),
                    correctIndex = 0,
                    explanation = "O Split permite tocar um instrumento na mão esquerda (como cordas ou baixo) e outro na mão direita (como flauta ou solo)."
                )
            }
            2 -> {
                q1 = QuizQuestion(
                    id = "q_teclado_2_1",
                    prompt = "O que faz o botão 'Touch Response' (sensibilidade ao toque) nos teclados modernos?",
                    options = listOf("Faz o volume e timbre variarem conforme a intensidade com que você aperta a tecla, como em um piano acústico", "Desliga o som do teclado se tocar devagar", "Muda o tom da música em 1 oitava", "Acende luzes coloridas"),
                    correctIndex = 0,
                    explanation = "O Touch Response simula a sensibilidade dinâmica real dos instrumentos acústicos."
                )
                q2 = QuizQuestion(
                    id = "q_teclado_2_2",
                    prompt = "Qual recurso permite alterar o tom da música inteira sem precisar mudar as posições dos dedos?",
                    options = listOf("O recurso de Transpose (Transposição)", "O botão de liga/desliga", "O metrônomo", "A roda de modulação"),
                    correctIndex = 0,
                    explanation = "O Transpose desloca digitalmente os semitons, permitindo acompanhar cantores em qualquer tom com facilidade."
                )
            }
            16 -> {
                q1 = QuizQuestion(
                    id = "q_teclado_16_1",
                    prompt = "Como funciona o modo de Acompanhamento Automático (Auto Accompaniment / Style) no teclado?",
                    options = listOf("A mão esquerda toca o acorde e o teclado gera bateria, baixo e instrumentos de apoio automaticamente no ritmo", "O teclado toca a música sozinho sem nenhum comando", "Ele apenas grava a voz do cantor", "Ele serve de afinador de violão"),
                    correctIndex = 0,
                    explanation = "O processador arranjador reconhece a harmonia tocada pela mão esquerda e orquestra uma banda completa em tempo real."
                )
                q2 = QuizQuestion(
                    id = "q_teclado_16_2",
                    prompt = "O que é a função 'Fill-In' (Virada de Bateria) disponível nos teclados?",
                    options = listOf("Uma variação rítmica temporária da bateria para anunciar a transição entre estrofe e refrão", "Um botão que zera o volume", "Um efeito que desafina as teclas", "A gravação de voz"),
                    correctIndex = 0,
                    explanation = "O Fill-In executa viradas de bateria clássicas enriquecendo a dinâmica musical nas transições."
                )
            }
            else -> {
                val mod = CourseRepository.getModuleForLesson(lessonNumber)
                q1 = QuizQuestion(
                    id = "q_teclado_${lessonNumber}_1",
                    prompt = "Na aula de Teclado '$title', qual recurso tecnológico potencializa a sua performance?",
                    options = listOf(
                        "Configurar timbres combinados (Layer/Dual) e sincronizar o tempo com o metrônomo interno",
                        "Usar apenas o som padrão sem explorar outros timbres",
                        "Desligar o teclado durante a execução",
                        "Tocar sem prestar atenção ao andamento (BPM)"
                    ),
                    correctIndex = 0,
                    explanation = "O domínio dos recursos sonoros e do andamento digital do teclado amplia incrivelmente as possibilidades artísticas."
                )
                q2 = QuizQuestion(
                    id = "q_teclado_${lessonNumber}_2",
                    prompt = "Como o estudo dos temas do Módulo ${mod.moduleNumber} (${mod.titlePt}) te prepara para tocar em bandas e eventos?",
                    options = listOf(
                        "Desenvolve versatilidade para atuar como tecladista solo, arranjador ou apoio harmônico",
                        "Limita sua atuação a tocar apenas músicas infantis",
                        "Impede que você aprenda a ler partituras",
                        "Serve apenas para tocar um único instrumento virtual"
                    ),
                    correctIndex = 0,
                    explanation = "Tecladistas completos entendem de sintetizadores, arranjos rítmicos, timbres orquestrais e harmonia aplicada."
                )
            }
        }

        return listOf(q1, q2)
    }
}
