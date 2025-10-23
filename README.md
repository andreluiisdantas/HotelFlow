# 🏨 HotelFlow

Um moderno **Sistema de Gerenciamento de Hotel (PMS)** web-based, construído com **Django** e **React**.

Este repositório contém o código-fonte do **HotelFlow**, um projeto de **PMS V1.0** focado em cobrir o ciclo de vida completo do hóspede: da **Reserva ao Check-out**.

---

## 🎯 Visão do Produto

O **HotelFlow** visa atender **pequenos e médios hotéis e pousadas** que atualmente sofrem com sistemas de desktop antigos ou pagam caro por soluções em nuvem complexas demais.

A **V1.0** foca em fornecer ao staff do hotel (**Recepcionistas e Gerentes**) as ferramentas essenciais para gerenciar:

- Reservas  
- Disponibilidade de quartos  
- Faturamento de hóspedes  
- Operações de governança  

---

## 🛠️ Stack Tecnológica

- **Backend:** Django  
- **API:** Django Rest Framework (DRF)  
- **Frontend:** React  
- **Banco de Dados:** PostgreSQL  
- **Autenticação:** JWT (JSON Web Tokens)

---

## 🏛️ Arquitetura do Backend (Apps Django)

O projeto Django é organizado em *apps* desacoplados, cada um com uma responsabilidade de negócio clara:

- **core:** Configurações globais do Django (`settings.py`, `urls.py` principal).  
- **users:** Gerencia os funcionários do hotel (Recepcionistas, Gerentes), autenticação via JWT e permissões de acesso.  
- **guests:** Funciona como o "CRM" do hotel, gerenciando o perfil dos hóspedes (distintos dos funcionários).  
  - Modelos: `Hospede`  
- **property:** Gerencia o "inventário" físico do hotel.  
  - Modelos: `TipoQuarto`, `Quarto`, `BloqueioQuarto`  
- **rates:** Motor de precificação do hotel. Define quanto custa cada tipo de quarto.  
  - Modelos: `PlanoTarifa`, `Tarifa`  
- **reservations:** O “coração” do sistema. Orquestra a reserva, conectando `guests`, `property` e `rates`.  
  - Modelos: `Reserva`  
- **folio:** Gerencia a parte financeira da estadia (“conta corrente” do hóspede).  
  - Modelos: `Folio`, `TransacaoFolio`  

---

## 🚀 Começando (Setup e Instalação)

Siga estes passos para configurar o ambiente de desenvolvimento.

### 🧩 Pré-requisitos

- Python 3.10+  
- Node.js 18+  
- PostgreSQL em execução  

---

### ⚙️ 1. Configurando o Backend (Django)

Assumindo que o backend está na pasta `/backend`:

#### Navegue até a pasta do backend:
```bash
cd backend/
Crie e ative um ambiente virtual:
bash
Copiar código
# Linux/macOS
python -m venv venv
source venv/bin/activate

# Windows
python -m venv venv
.\venv\Scripts\activate
Instale as dependências:
bash
Copiar código
pip install -r requirements.txt
Configure as variáveis de ambiente:
(Copie .env.example para .env e preencha com seus dados do PostgreSQL)

bash
Copiar código
cp .env.example .env
Depois, edite o arquivo .env com suas credenciais do banco.

Rode as migrações do banco:
bash
Copiar código
python manage.py migrate
Crie um superusuário (exemplo: Gerente "Maria"):
bash
Copiar código
python manage.py createsuperuser
Inicie o servidor do backend:
bash
Copiar código
python manage.py runserver
O backend estará rodando em:
👉 http://localhost:8000

💻 2. Configurando o Frontend (React)
Assumindo que o frontend está na pasta /frontend:

Em um NOVO terminal, navegue até a pasta do frontend:
bash
Copiar código
cd frontend/
Instale as dependências:
bash
Copiar código
npm install
Inicie o servidor de desenvolvimento:
bash
Copiar código
npm start
O frontend estará rodando em:
👉 http://localhost:3000

🔖 Hashtags / Tópicos
#Django #React #PostgreSQL #JWT #PMS #HotelFlow #Backend #Frontend #FullStack #HotelManagement

yaml
Copiar código

---

Quer que eu adicione uma seção de **estrutura de pastas (tree)** ou **roadmap de versões (V1, V2 etc.)** também? Isso deixaria 
