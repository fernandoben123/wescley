import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ator ator = new Ator("", "", "", 0);
        Filme filme = new Filme(0, "", 0, new Genero(0, "", ""), "");
        FilmeAtor filmeAtor = new FilmeAtor(0, null, null, "", false);
        Genero genero = new Genero(0, "", "");
        Funcionario funcionario = new Funcionario(null, null, null, 0, null);
        Sessao sessao = new Sessao(0, filme, null, funcionario, null, null);
        Sala sala = new Sala(0, 0, null, null);
        TipoAssento tipoAssento = new TipoAssento(0, null, null);
        Assento assento = new Assento(0, null);
        SalaAssento salaAssento = new SalaAssento(0, null,null);
        Ingresso ingresso = new Ingresso(0, 0, salaAssento, sessao);

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gerenciar Atores");
            System.out.println("2. Gerenciar Filmes");
            System.out.println("3. Gerenciar Filme-Ator");
            System.out.println("4. Gerenciar Gêneros");
            System.out.println("5. Gerenciar Funcionarios");
            System.out.println("6. Gerenciar Sessões");
            System.out.println("7. Gerenciar Salas");
            System.out.println("8. Gerenciar Tipo Assentos");
            System.out.println("9. Gerenciar Assentos");
            System.out.println("10. Gerenciar sala assento");
            System.out.println("11. Gerenciar Ingressos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> gerenciarAtores(scanner, ator);
                case 2 -> gerenciarFilmes(scanner, filme);
                case 3 -> gerenciarFilmeAtor(scanner, filmeAtor, ator, filme);
                case 4 -> gerenciarGeneros(scanner, genero);
                case 5 -> gerenciarFuncionarios(scanner, funcionario);
                case 6 -> gerenciarSessoes(scanner, sessao);
                case 7 -> gerenciarSalas(scanner, sala);
                case 8 -> gerenciarTipoAssentos(scanner, tipoAssento);
                case 9 -> gerenciarAssentos(scanner, assento, tipoAssento);
                case 10 -> gerenciarsalaAssento(scanner, salaAssento, sala, assento);
                case 11 -> gerenciarIngressos(scanner, ingresso);
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void gerenciarAtores(Scanner scanner, Ator ator) {
        while (true) {
            System.out.println("\n--- Gerenciar Atores ---");
            System.out.println("1. Cadastrar Ator");
            System.out.println("2. Listar Atores");
            System.out.println("3. Editar Ator");
            System.out.println("4. Consultar Ator");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o número de registro: ");
                    int registro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do ator: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do ator: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite o email do ator: ");
                    String email = scanner.nextLine();

                    Ator novoAtor = new Ator(cpf, nome, email, registro);
                    if (ator.cadastrar(novoAtor)) {
                        System.out.println("Ator cadastrado com sucesso.");
                    }
                }
                case 2 -> {
                    List<Ator> atores = ator.listar();
                    if (atores.isEmpty()) {
                        System.out.println("Nenhum ator cadastrado.");
                    } else {
                        System.out.println("\n--- Lista de Atores ---");
                        for (Ator a : atores) {
                            a.exibirInfo();
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Digite o número de registro do ator para editar: ");
                    int registro = scanner.nextInt();
                    scanner.nextLine();
                    Ator atorConsultado = ator.consultar(registro);
                    if (atorConsultado != null) {
                        System.out.print("Digite o novo nome: ");
                        atorConsultado.setNome(scanner.nextLine());
                        System.out.print("Digite o novo CPF: ");
                        atorConsultado.setCpf(scanner.nextLine());
                        System.out.print("Digite o novo email: ");
                        atorConsultado.setEmail(scanner.nextLine());

                        if (ator.editar(atorConsultado)) {
                            System.out.println("Ator editado com sucesso.");
                        }
                    } else {
                        System.out.println("Ator não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Digite o número de registro do ator: ");
                    int registro = scanner.nextInt();
                    Ator atorConsultado = ator.consultar(registro);
                    if (atorConsultado != null) {
                        atorConsultado.exibirInfo();
                    } else {
                        System.out.println("Ator não encontrado.");
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void gerenciarFilmes(Scanner scanner, Filme filme) {
        while (true) {
            System.out.println("\n--- Gerenciar Filmes ---");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Listar Filmes");
            System.out.println("3. Editar Filme");
            System.out.println("4. Consultar Filme");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID do filme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o título do filme: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite a classificação: ");
                    int classificacao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o gênero: ");
                    String generoDesc = scanner.nextLine();
                    System.out.print("Digite o status: ");
                    String status = scanner.nextLine();

                    Genero genero = new Genero(0, generoDesc, "Ativo");
                    Filme novoFilme = new Filme(idFilme, titulo, classificacao, genero, status);
                    if (filme.cadastrar(novoFilme)) {
                        System.out.println("Filme cadastrado com sucesso.");
                    }
                }
                case 2 -> {
                    List<Filme> filmes = filme.listar();
                    if (filmes.isEmpty()) {
                        System.out.println("Nenhum filme cadastrado.");
                    } else {
                        System.out.println("\n--- Lista de Filmes ---");
                        for (Filme f : filmes) {
                            f.exibirInfo();
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID do filme para editar: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();
                    Filme filmeConsultado = filme.consultar(idFilme);
                    if (filmeConsultado != null) {
                        System.out.print("Digite o novo título: ");
                        filmeConsultado.setTitulo(scanner.nextLine());
                        System.out.print("Digite a nova classificação: ");
                        filmeConsultado.setClassificacao(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Digite o novo gênero: ");
                        String generoDesc = scanner.nextLine();
                        Genero genero = new Genero(0, generoDesc, "Ativo");
                        filmeConsultado.setGenero(genero);
                        System.out.print("Digite o novo status: ");
                        filmeConsultado.setStatus(scanner.nextLine());

                        if (filme.editar(filmeConsultado)) {
                            System.out.println("Filme editado com sucesso.");
                        }
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Digite o ID do filme: ");
                    int idFilme = scanner.nextInt();
                    Filme filmeConsultado = filme.consultar(idFilme);
                    if (filmeConsultado != null) {
                        filmeConsultado.exibirInfo();
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void gerenciarFilmeAtor(Scanner scanner, FilmeAtor filmeAtor, Ator ator, Filme filme) {
        while (true) {
            System.out.println("\n--- Gerenciar Filme-Ator ---");
            System.out.println("1. Cadastrar FilmeAtor");
            System.out.println("2. Listar FilmeAtore");
            System.out.println("3. Editar FilmeAtor");
            System.out.println("4. Consultar FilmeAtor");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID da relação: ");
                    int idRelacao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o registro do ator: ");
                    int registroAtor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o ID do filme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o personagem: ");
                    String personagem = scanner.nextLine();
                    System.out.print("É um personagem principal? (true/false): ");
                    boolean principal = scanner.nextBoolean();

                    Ator atorRelacionado = ator.consultar(registroAtor);
                    Filme filmeRelacionado = filme.consultar(idFilme);

                    if (atorRelacionado != null && filmeRelacionado != null) {
                        FilmeAtor novaRelacao = new FilmeAtor(idRelacao, atorRelacionado, filmeRelacionado, personagem, principal);
                        if (filmeAtor.cadastrar(novaRelacao)) {
                            System.out.println("Relação cadastrada com sucesso.");
                        }
                    } else {
                        System.out.println("Ator ou filme não encontrado.");
                    }
                }
                case 2 -> {
                    List<FilmeAtor> relacoes = filmeAtor.listar();
                    if (relacoes.isEmpty()) {
                        System.out.println("Nenhuma relação cadastrada.");
                    } else {
                        System.out.println("\n--- Lista de Relações ---");
                        for (FilmeAtor fa : relacoes) {
                            fa.exibirInfo();
                        }
                    }
                }

                case 3 -> {
                    System.out.print("ID FilmeAtor a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo registro do Ator: ");
                    int novoRegistroAtor = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo ID do Filme: ");
                    int novoIdFilme = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Nome do Personagem: ");
                    String novoPersonagem = scanner.nextLine();

                    System.out.print("É principal? (Sim/Não): ");
                    boolean novoPrincipal = scanner.nextLine().equalsIgnoreCase("Sim");

                    Ator novoAtor = new Ator("", "", "", novoRegistroAtor);
                    Filme novoFilme = new Filme(novoIdFilme, "", 0, new Genero(0, "", ""), "");

                    FilmeAtor filmeAtorEditar = new FilmeAtor(idEditar, novoAtor, novoFilme, novoPersonagem, novoPrincipal);
                    if (filmeAtor.editar(filmeAtorEditar)) {
                        System.out.println("FilmeAtor editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar FilmeAtor.");
                    }
                    break;
                }
                
                case 4 ->{
                    System.out.println("\nConsulta de FilmeAtor:");
                                System.out.print("Digite o ID do FilmeAtor para consultar: ");
                                int idConsultar = scanner.nextInt();
                                FilmeAtor faConsulta = filmeAtor.consultar(idConsultar);
                                if (faConsulta != null) {
                                    faConsulta.exibirInfo();
                                } else {
                                    System.out.println("FilmeAtor não encontrado.");
                                }                
                                break;
                }
                
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void gerenciarGeneros(Scanner scanner, Genero genero) {
        while (true) {
            System.out.println("\n--- Gerenciar Gêneros ---");
            System.out.println("1. Cadastrar Gênero");
            System.out.println("2. Listar Gêneros");
            System.out.println("3. Editar Gênero");
            System.out.println("4. Consultar Gênero");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite a descrição do gênero: ");
                    String descricao = scanner.nextLine();
                        
                    System.out.println("Digite o ID do gênero: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                        
                    Genero novoGenero = new Genero(id, descricao, "A");
                    novoGenero.cadastrar();
                        
              
                    break;

                }
                case 2 -> {
                    System.out.println("\nLista de gêneros:");
                        List<Genero> generos = genero.listar();
                        for (Genero g : generos) {
                            g.detalhes();
                            }    
                        break;
                }
                case 3 -> {
                    System.out.println("\nEdição de gênero:");
                        System.out.print("Digite o ID do gênero para editar: ");
                        int idEditarGen = scanner.nextInt();
                        scanner.nextLine();
                        List<Genero> gen = genero.listar();
                        Genero generoEditar = null;
                        for (Genero g : gen) {
                            if (g.getId() == idEditarGen) {
                                generoEditar = g;
                                break;
                            }
                        }
                
                        if (generoEditar != null) {
                            System.out.print("Digite a nova descrição do gênero: ");
                            String novoDescricao = scanner.nextLine();
                            System.out.print("Digite o novo status do gênero: ");
                            String novoStatus = scanner.nextLine();
                
                            generoEditar.setDescricao(novoDescricao);
                            generoEditar.setStatus(novoStatus);
                
                            if (genero.editar(generoEditar)) {
                                System.out.println("Gênero editado com sucesso.");
                            }
                        } else {
                            System.out.println("Gênero não encontrado.");
                        }
                        break;
                }
                case 4 -> {
                    System.out.println("\nConsulta de gênero:");
                    System.out.print("Digite a descrição do gênero para consultar: ");
                    String descricaoConsultar = scanner.nextLine();
                    Genero generoConsulta = genero.buscarGenero(descricaoConsultar);
                        if (generoConsulta != null) {
                            generoConsulta.detalhes();
                            } else {
                            System.out.println("Gênero não encontrado.");
                            }
                            break;
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void gerenciarFuncionarios(Scanner scanner, Funcionario funcionario){
        while (true) {
            System.out.println("\n--- Gerenciar Funcionarios ---");
            System.out.println("1. Cadastrar Funcionario");
            System.out.println("2. Listar Funcionarios");
            System.out.println("3. Editar Funcionario");
            System.out.println("4. Consultar Funcionario");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Cadastro de funcionário:");
                    System.out.print("Digite o número de matrícula: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do funcionário: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite o email do funcionário: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite o horário de trabalho (HH:mm): ");
                    String horario = scanner.nextLine();
            
                    Funcionario funcionarioNovo = new Funcionario(nome, cpf, email, matricula, horario);
                    if (funcionarioNovo .cadastrar()) {
                        System.out.println("Funcionário cadastrado com sucesso.");
                    } else {
                        System.out.println("Erro ao cadastrar funcionário.");
                    }
                    break;

                }
                case 2 -> {
                    System.out.println("\nLista de funcionários:");
                        List<Funcionario> funcionarios = funcionario.listar();
                        for (Funcionario f : funcionarios) {
                            f.detalhes();
                        }
                        break;   
                }
                case 3 -> {
                   
                    System.out.println("\nEdição de funcionário:");
                    System.out.print("Digite o número de matrícula do funcionário para editar: ");
                    int matriculaEditar = scanner.nextInt();
                    scanner.nextLine();
                    Funcionario funcionarioConsultado = funcionario.consultar(matriculaEditar);
                    if (funcionarioConsultado != null) {
                        System.out.print("Digite o novo nome do funcionário: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Digite o novo CPF do funcionário: ");
                        String novoCpf = scanner.nextLine();
                        System.out.print("Digite o novo email do funcionário: ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Digite o novo horário de trabalho: ");
                        String novoHorario = scanner.nextLine();
            
                        funcionarioConsultado.setNome(novoNome);
                        funcionarioConsultado.setCpf(novoCpf);
                        funcionarioConsultado.setEmail(novoEmail);
                        funcionarioConsultado.setHorarioTrabalho(novoHorario);
            
                        if (funcionarioConsultado.editar()) {
                            System.out.println("Funcionário editado com sucesso.");
                        } else {
                            System.out.println("Erro ao editar funcionário.");
                        }
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;
                }
                case 4 -> {
                    System.out.println("\nConsulta de funcionário:");
                        System.out.print("Digite o número de matrícula para consultar: ");
                        int matriculaConsultar = scanner.nextInt();
                        Funcionario funcionarioConsulta = funcionario.consultar(matriculaConsultar);
                        if (funcionarioConsulta != null) {
                            funcionarioConsulta.detalhes();
                        } else {
                            System.out.println("Funcionário não encontrado.");
                        }
                        break;
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void gerenciarSessoes(Scanner scanner, Sessao sessao){
        while (true) {
            System.out.println("\n--- Gerenciar Sessões ---");
            System.out.println("1. Cadastrar Sessão");
            System.out.println("2. Listar Sessões");
            System.out.println("3. Editar Sessão");
            System.out.println("4. Consultar Sessão");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Sala sala = new Sala(opcao, opcao, null, null);
                    Filme filme = new Filme(opcao, null, opcao, null, null);
                    Funcionario funcionario = new Funcionario(null, null, null, opcao, null);
                    
                    System.out.println("Cadastro de Sessão:");
                    System.out.print("Digite o ID da Sessão: ");
                    int idSessao = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite o IDFilme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o ID da Sala: ");
                    int idSala = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite o ID do Funcionario: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite a data e hora da sessão (dd - mm - AAAA / HH:mm): ");
                    String dataHora = scanner.nextLine();
                    System.out.print("Digite o status da sessão: ");
                    String status = scanner.nextLine();

                    Sala salaRelacionado = sala.consultar(idSala);
                    Filme filmeRelacionado = filme.consultar(idFilme);
                    Funcionario funcionarioRelacionado = funcionario.consultar(matricula);

                    if (salaRelacionado != null && filmeRelacionado != null && funcionarioRelacionado != null) {
                        Sessao novaRelacao = new Sessao(idSessao, filmeRelacionado, salaRelacionado, funcionarioRelacionado, dataHora, status);
                        if (novaRelacao.cadastrar()) {
                            System.out.println("Relação cadastrada com sucesso.");
                        }
                    } else {
                        System.out.println("Ator ou filme ou funcionario não encontrado.");
                    }

                }
                case 2 -> {
                    System.out.println("\nLista de sessões:");
                                    List<Sessao> sessoes = sessao.listar();
                                    for (Sessao s : sessoes) {
                                        s.detalhes();
                                    }
                }
                case 3 -> {
                   
                    System.out.println("\nEdição de Sessão:");
                                    System.out.print("Digite o ID da Sessão para editar: ");
                                    int idSessaoEditar = scanner.nextInt();
                                    Sessao sessaoConsultada = sessao.consultar(idSessaoEditar);
                                    if (sessaoConsultada != null) {
                                        System.out.print("Digite o novo status da sessão: ");
                                        scanner.nextLine(); 
                                        String novoStatus = scanner.nextLine();
                                        sessaoConsultada.setStatus(novoStatus);
                                        if (sessaoConsultada.editar()) {
                                            System.out.println("Sessão editada com sucesso.");
                                        }
                                    } else {
                                        System.out.println("Sessão não encontrada.");
                                    }
                                break;
                }
                case 4 -> {
                    System.out.println("\nConsulta de Sessão:");
                                    System.out.print("Digite o ID da Sessão para consultar: ");
                                    int idSessaoConsultar = scanner.nextInt();
                                    Sessao sessaoConsulta = sessao.consultar(idSessaoConsultar);
                                    if (sessaoConsulta != null) {
                                        sessaoConsulta.detalhes();
                                    } else {
                                        System.out.println("Sessão não encontrada.");
                                    }
                            
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");

            }
        }
    }


    public static void gerenciarSalas(Scanner scanner, Sala sala){
        while (true) {
            System.out.println("\n--- Gerenciar Salas ---");
            System.out.println("1. Cadastrar Sala");
            System.out.println("2. Listar Salas");
            System.out.println("3. Editar Sala");
            System.out.println("4. Consultar Sala");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Cadastro de Sala:");
                    System.out.print("Digite o ID da sala: ");
                    int idSala = scanner.nextInt();
                    System.out.print("Digite a capacidade da sala: ");
                    int capacidadeSala = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite a descrição da sala: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite o status da sala: ");
                    String status = scanner.nextLine();

                    Sala salanova = new Sala(idSala, capacidadeSala, descricao, status);
                    if (salanova.cadastrar()) {
                        System.out.println("Sala cadastrada com sucesso.");
        }

                }
                case 2 -> {
                    System.out.println("\nLista de salas:");
                List<Sala> salas = sala.listar();
                for (Sala s : salas) {
                    s.detalhes();
                }

                }
                case 3 -> {
                   
                    System.out.println("\nEdição de Sala:");
                    System.out.print("Digite o ID da sala para editar: ");
                    int idSalaEditar = scanner.nextInt();
                    scanner.nextLine(); 
                    Sala salaConsultada = sala.consultar(idSalaEditar);
                    if (salaConsultada != null) {
                        System.out.print("Digite a nova capacidade da sala: ");
                        int novaCapacidade = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite a nova descrição da sala: ");
                        String novaDescricao = scanner.nextLine();
                        System.out.print("Digite o novo status da sala: ");
                        String novoStatus = scanner.nextLine();

                        salaConsultada.setCapacidadeSala(novaCapacidade);
                        salaConsultada.setDescricao(novaDescricao);
                        salaConsultada.setStatus(novoStatus);

                        if (salaConsultada.editar()) {
                            System.out.println("Sala editada com sucesso.");
                        }
                    } else {
                        System.out.println("Sala não encontrada.");
                    }


                }
                case 4 -> {
                    System.out.println("\nConsulta de Sala:");
                System.out.print("Digite o ID da sala para consultar: ");
                int idSalaConsultar = scanner.nextInt();
                Sala salaConsulta = sala.consultar(idSalaConsultar);
                if (salaConsulta != null) {
                    salaConsulta.detalhes();
                } else {
                    System.out.println("Sala não encontrada.");
                }
                
            }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            
            }
        }
        }

        public static void gerenciarTipoAssentos(Scanner scanner, TipoAssento tipoAssento){
            while (true) {
                System.out.println("\n--- Gerenciar tipo Assentos ---");
                System.out.println("1. Cadastrar tipo Assento");
                System.out.println("2. Listar tipo Assentos");
                System.out.println("3. Editar tipo Assento");
                System.out.println("4. Consultar tipo Assento");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
    
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite o ID do Tipo de Assento: ");
                    int idCadastro = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite a Descrição do Tipo de Assento: ");
                    String descricaoCadastro = scanner.nextLine();
                    System.out.print("Digite o Status do Tipo de Assento (Ativo/Inativo): ");
                    String statusCadastro = scanner.nextLine();
                    tipoAssento = new TipoAssento(idCadastro, descricaoCadastro, statusCadastro);
                    if (tipoAssento.cadastrar(tipoAssento)) {
                        System.out.println("Tipo de Assento cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar Tipo de Assento.");
                    }
                    break;
    
                    }
                    case 2 -> {
                        System.out.println("Listando todos os Tipos de Assento:");
                        for (TipoAssento ta : tipoAssento.listar()) {
                            System.out.println(ta);
                        }
                        break;
    
                    }
                    case 3 -> {
                       
                        System.out.print("Digite o ID do Tipo de Assento que deseja editar: ");
                    int idEdicao = scanner.nextInt();
                    scanner.nextLine(); 
                    TipoAssento tipoExistente = tipoAssento.consultar(idEdicao);
                    if (tipoExistente != null) {
                        System.out.print("Digite a nova Descrição do Tipo de Assento: ");
                        String descricaoEdicao = scanner.nextLine();
                        System.out.print("Digite o novo Status do Tipo de Assento (Ativo/Inativo): ");
                        String statusEdicao = scanner.nextLine();
                        tipoExistente.setDescricao(descricaoEdicao);
                        tipoExistente.setStatus(statusEdicao);
                        if (tipoAssento.editar(tipoExistente)) {
                            System.out.println("Tipo de Assento editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar Tipo de Assento.");
                        }
                    } else {
                        System.out.println("Tipo de Assento não encontrado.");
                    }
                    break;
    
    
                    }
                    case 4 -> {
                        System.out.print("Digite o ID do Tipo de Assento que deseja consultar: ");
                    int idConsulta = scanner.nextInt();
                    scanner.nextLine(); 
                    tipoAssento = tipoAssento.consultar(idConsulta);
                    if (tipoAssento != null) {
                        tipoAssento.exibirInfo();
                    } else {
                        System.out.println("Tipo de Assento não encontrado.");
                    }
                    break;
                    
                }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                
                }
            }
            }
            
            public static void gerenciarAssentos(Scanner scanner, Assento assento, TipoAssento tipoAssento) {
                while (true) {
                    System.out.println("\n--- Gerenciar Assentos ---");
                    System.out.println("1. Cadastrar Assento");
                    System.out.println("2. Listar Assentos");
                    System.out.println("3. Editar Assento");
                    System.out.println("4. Consultar Assento");
                    System.out.println("0. Voltar");
                    System.out.print("Escolha uma opção: ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine(); 
            
                    switch (opcao) {
                        case 1: {
                            
                            System.out.print("Digite o ID do Assento: ");
                            int idCadastro = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Digite o ID do Tipo de Assento: ");
                            int idTipoAssentoCadastro = scanner.nextInt();
                            scanner.nextLine(); 
            
                            tipoAssento = new TipoAssento(idTipoAssentoCadastro, "", "");
                            tipoAssento = tipoAssento.consultar(idTipoAssentoCadastro); 
            
                            if (tipoAssento != null) {
                                assento = new Assento(idCadastro, tipoAssento);
                                if (assento.cadastrar(assento)) {
                                    System.out.println("Assento cadastrado com sucesso!");
                                } else {
                                    System.out.println("Erro ao cadastrar Assento.");
                                }
                            } else {
                                System.out.println("Tipo de Assento não encontrado.");
                            }
                            break;
                        }
            
                        case 2: {
                            System.out.println("Listando todos os Assentos:");
                            for (Assento a : assento.listar()) {
                                a.exibirInfo();
                            }
                            break;
                        }
            
                        case 3: {
                            System.out.print("Digite o ID do Assento que deseja editar: ");
                            int idEdicao = scanner.nextInt();
                            scanner.nextLine();
            
                            Assento assentoExistente = assento.consultar(idEdicao);
                            if (assentoExistente != null) {
                                System.out.print("Digite o novo ID do Tipo de Assento: ");
                                int idTipoAssentoEdicao = scanner.nextInt();
                                scanner.nextLine();
                                tipoAssento = new TipoAssento(idTipoAssentoEdicao, "", "");
                                tipoAssento = tipoAssento.consultar(idTipoAssentoEdicao); 
            
                                if (tipoAssento != null) {
                                    assentoExistente.setTipoAssento(tipoAssento);
                                    if (assento.editar(assentoExistente)) {
                                        System.out.println("Assento editado com sucesso!");
                                    } else {
                                        System.out.println("Erro ao editar Assento.");
                                    }
                                } else {
                                    System.out.println("Tipo de Assento não encontrado.");
                                }
                            } else {
                                System.out.println("Assento não encontrado.");
                            }
                            break;
                        }
            
                        case 4: {
                            System.out.print("Digite o ID do Assento que deseja consultar: ");
                            int idConsulta = scanner.nextInt();
                            scanner.nextLine(); 
            
                            assento = assento.consultar(idConsulta);
                            if (assento != null) {
                                assento.exibirInfo();
                            } else {
                                System.out.println("Assento não encontrado.");
                            }
                            break;
                        }
            
                        case 0: {

                            return;
                        }
            
                        default:
                            System.out.println("Opção inválida! Tente novamente.");
                            break;
                    }
                }
            }

            public static void gerenciarsalaAssento(Scanner scanner, SalaAssento salaAssento, Sala sala, Assento assento) {
                while (true) {
                    System.out.println("\n--- Gerenciar sala assentos ---");
                    System.out.println("1. Cadastrar sala assento");
                    System.out.println("2. Listar sala assentos");
                    System.out.println("3. Editar sala assento");
                    System.out.println("4. Consultar sala assento");
                    System.out.println("0. Voltar");
                    System.out.print("Escolha uma opção: ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine(); 
            
                    switch (opcao) {
                        case 1: {
                            
                            System.out.println("Cadastro SalaAssento:");
                            System.out.print("Digite o ID de SalaAssento: ");
                            int idSalaAssento = scanner.nextInt();
                            System.out.print("Digite o ID da sala: ");
                            int idAssento = scanner.nextInt();
                            System.out.print("Digite o ID do assento: ");
                            int idSala = scanner.nextInt();

                            Assento assentoRelacionado = assento.consultar(idAssento);
                            Sala salaRelacionada = sala.consultar(idSala);

                            if (assentoRelacionado != null && salaRelacionada != null) {
                            SalaAssento novaRelacao = new SalaAssento(idSalaAssento, salaRelacionada, assentoRelacionado);
                            if (novaRelacao.cadastrar()) {
                                System.out.println("Relação cadastrada com sucesso.");
                            }
                        } else {
                            System.out.println("Sala ou assento não encontrado.");
                        }
        }
            
                        case 2: {
                            System.out.println("\nLista SalaAssento:");
                            List<SalaAssento> salaAssentos = salaAssento.listar();
                            for (SalaAssento sa : salaAssentos) {
                                sa.detalhes();
        }
                        }
            
                        case 3: {
                            System.out.println("\nEdição de SalaAssento:");
                            System.out.print("Digite o ID de SalaAssento para editar: ");
                            int idEditar = scanner.nextInt();
                            SalaAssento salaAssentoEditar = salaAssento.consultar(idEditar);
                            if (salaAssentoEditar != null) {
                                System.out.print("Digite o novo ID da sala: ");
                                int novoIdSala = scanner.nextInt();
                                System.out.print("Digite o novo ID do assento: ");
                                int novoIdAssento = scanner.nextInt();
                                salaAssentoEditar.setSala(new Sala(novoIdSala, 0, "", ""));
                                salaAssentoEditar.setAssento(new Assento(novoIdAssento, null));
                                if (salaAssentoEditar.editar()) {
                                    System.out.println("SalaAssento editado com sucesso.");
                                }
                            } else {
                                System.out.println("IdSalaAssento não encontrado.");
                            }

                        }
            
                        case 4: {
                            System.out.println("\nConsulta de SalaAssento:");
                            System.out.print("Digite o ID de SalaAssento para consultar: ");
                            int idConsultar = scanner.nextInt();
                            SalaAssento salaAssentoConsulta = salaAssento.consultar(idConsultar);
                            if (salaAssentoConsulta != null) {
                                salaAssentoConsulta.detalhes();
                            } else {
                                System.out.println("IdSalaAssento não encontrado.");
                            }
                        }
            
                        case 0: {

                            return;
                        }
            
                        default:
                            System.out.println("Opção inválida! Tente novamente.");
                            break;
                    }
                }
            }


            public static void gerenciarIngressos(Scanner scanner, Ingresso ingresso) {
                while (true) {
                    System.out.println("\n--- Gerenciar Ingressos ---");
                    System.out.println("1. Cadastrar ingresso");
                    System.out.println("2. Listar ingressos");
                    System.out.println("3. Editar ingresso");
                    System.out.println("4. Consultar ingressos");
                    System.out.println("0. Voltar");
                    System.out.print("Escolha uma opção: ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine(); 
            
                    switch (opcao) {
                        case 1: {
                            
                            System.out.println("Cadastro de Ingresso:");
                            System.out.print("Digite o ID do Ingresso: ");
                            int idIngresso = scanner.nextInt();
                            System.out.print("Digite o valor pago: ");
                            double valorPago = scanner.nextDouble();
                            System.out.print("Digite o ID da SalaAssento: ");
                            int idSalaAssento= scanner.nextInt();
                            System.out.print("Digite o ID da Sessão: ");
                            int idSessao = scanner.nextInt();
                            
                            Sessao sessao = new Sessao(idSessao, null, null, null, null, null);
                            SalaAssento salaAssento = new SalaAssento(idSalaAssento, null, null);
                            SalaAssento salaAssentoRelacionado = salaAssento.consultar(idSalaAssento);
                            Sessao sessaoRelacionada = sessao.consultar(idSessao);
                            

                            if (salaAssentoRelacionado != null && sessaoRelacionada != null) {
                                Ingresso novaRelacao = new Ingresso(idIngresso, valorPago, salaAssentoRelacionado, sessaoRelacionada);
                                if (novaRelacao.cadastrar()) {
                                    System.out.println("Ingresso cadastrado com sucesso.");
                                }
                            } else {
                                System.out.println("IdSalaAssento ou idSessao não encontrada.");
                            }
        }
            
                        case 2: {
                            System.out.println("\nLista de ingressos:");
                            List<Ingresso> ingressos = ingresso.listar();
                            for (Ingresso ing : ingressos) {
                                ing.detalhes();
                            }
                        }
            
                        case 3: {
                            System.out.println("\nEdição de Ingresso:");
                            System.out.print("Digite o ID do Ingresso para editar: ");
                            int idIngressoEditar = scanner.nextInt();
                            Ingresso ingressoConsultado = ingresso.consultar(idIngressoEditar);
                            if (ingressoConsultado != null) {
                                System.out.print("Digite o novo valor pago: ");
                                double novoValorPago = scanner.nextDouble();
                                ingressoConsultado.setValorPago(novoValorPago);
                                if (ingressoConsultado.editar()) {
                                    System.out.println("Ingresso editado com sucesso.");
                                }
                            } else {
                                System.out.println("Ingresso não encontrado.");
                            }


                        }
            
                        case 4: {
                            System.out.println("\nConsulta de Ingresso:");
                            System.out.print("Digite o ID do Ingresso para consultar: ");
                            int idIngressoConsultar = scanner.nextInt();
                            Ingresso ingressoConsulta = ingresso.consultar(idIngressoConsultar);
                            if (ingressoConsulta != null) {
                                ingressoConsulta.detalhes();
                            } else {
                                System.out.println("Ingresso não encontrado.");
                            }

                        }
            
                        case 0: {

                            return;
                        }
            
                        default:
                            System.out.println("Opção inválida! Tente novamente.");
                            break;
                    }
                }
            }
}