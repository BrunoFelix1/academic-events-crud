# ProjetoEventosP2
Cadastro de Eventos
U.I:

Menu de cadastro/login de usuário
Menu de acesso para o tipo de usuário:
Selecionar para logar como USUÁRIO/ADMIN/PALESTRANTE:

Menu Usuário:
	Participar de Evento
	Participar de trilha
	Emitir Certificado
	Cancelar Inscrição
	Visualizar inscrições
	
Menu Admin:
	CRUD de Evento, Subevento, Seção, Trilha
Menu Palestrante:
	Submeter conteúdo
	Apagar conteúdo
	Atualizar conteúdo
	Listar conteúdo


Persistência:
Tem que ter uma classe de manipulação de csv
Tudo csv, pra cada classe:
Usuários.csv
 Eventos.csv
Subeventos.csv
Trilhas.csv
Seções.csv
Submissões.csv (Contéudo, palestras, artigos, workshops etc.)

Controladores:
ControladorCertificados
ControladorMenu (Validações do login etc.)
ControladorUsuário
ControladorAdmin
ControladorPalestrante

Interfaces:
U.I/Controlador
Controlador/Persistência
	

