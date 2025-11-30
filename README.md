# EducaSchool 📚

**Projeto final** — Sistema escolar em Java para gerenciar alunos, disciplinas, matrícula, boletim e finanças.

---

## ✨ Visão geral  

EducaSchool é uma aplicação de console (terminal) em Java que permite:

- Cadastrar alunos, professores e disciplinas.  
- Matricular alunos em disciplinas.  
- Registrar notas e faltas (boletim).  
- Consultar o boletim de um aluno.  
- Gerenciar mensalidades (financeiro).  

O objetivo é simular funcionalidades básicas de um sistema escolar, reforçando conceitos de programação orientada a objetos e organização em camadas (model, controller, service).

---

## 🛠️ Funcionalidades  

- Cadastro de **Professores**.  
- Cadastro de **Disciplinas** vinculadas a um professor.  
- Cadastro de **Alunos** (nome, curso, matrícula automática).  
- Vinculação de disciplinas a alunos (matrícula).  
- Submenu de **Boletim**: lançar nota e faltas / consultar boletim de aluno.  
- Submenu de **Financeiro**: gerar mensalidade, realizar pagamento, consultar histórico.  
- Impressão no console (lista de alunos/disciplina, boletim completo, mensalidades).  

---

## 📂 Estrutura do Projeto  
/src
/controller — controla entrada do usuário e lógica de fluxo
/model — classes de domínio (Aluno, Disciplina, Professor, Mensalidade etc.)
/service — lógica de negócio (armazenamento, busca, manipulação de dados)
Main.java — ponto de entrada do programa (menu principal)
