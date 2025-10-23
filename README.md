HotelFlow
Um moderno Sistema de Gerenciamento de Hotel (PMS) web-based, construído com Django e React.

Este repositório contém o código-fonte do "HotelFlow", um projeto de PMS V1.0 focado em cobrir o ciclo de vida completo do hóspede: da Reserva ao Check-out.

🎯 Visão do Produto
O HotelFlow visa atender pequenos e médios hotéis e pousadas que atualmente sofrem com sistemas de desktop antigos ou pagam caro por soluções em nuvem complexas demais.

A V1.0 foca em fornecer ao staff do hotel (Recepcionistas e Gerentes) as ferramentas essenciais para gerenciar reservas, disponibilidade de quartos, faturamento de hóspedes e operações de governança.

🛠️ Stack Tecnológica
Backend: Django

API: Django Rest Framework (DRF)

Frontend: React

Banco de Dados: PostgreSQL

Autenticação: JWT (JSON Web Tokens)

🏛️ Arquitetura do Backend (Apps Django)
O projeto Django é organizado em apps desacoplados, cada um com uma responsabilidade de negócio clara:

core: Configurações globais do Django (settings.py, urls.py principal).

users: Gerencia os funcionários do hotel (Recepcionistas, Gerentes), autenticação via JWT e permissões de acesso ao sistema.

guests: Funciona como o "CRM" do hotel, gerenciando o perfil dos hóspedes (que são distintos dos funcionários).

Modelos: Hospede

property: Gerencia o "inventário" físico do hotel.

Modelos: TipoQuarto, Quarto, BloqueioQuarto

rates: Motor de precificação do hotel. Define quanto custa cada tipo de quarto.

Modelos: PlanoTarifa, Tarifa

reservations: O "coração" do sistema. Orquestra a reserva, conectando guests, property e rates.

Modelos: Reserva

folio: Gerencia a parte financeira da estadia ("conta corrente" do hóspede).

Modelos: Folio, TransacaoFolio

🚀 Começando (Setup e Instalação)
Siga estes passos para configurar o ambiente de desenvolvimento.

Pré-requisitos
Python 3.10+

Node.js 18+

PostgreSQL em execução

1. Configurando o Backend (Django)
Assumindo que o backend está na pasta /backend:

Bash

# 1. Navegue até a pasta do backend
cd backend/

# 2. Crie e ative um ambiente virtual
python -m venv venv
source venv/bin/activate  # (ou .\venv\Scripts\activate no Windows)

# 3. Instale as dependências
pip install -r requirements.txt

# 4. Configure as variáveis de ambiente
# (Copie .env.example para .env e preencha com seus dados do PostgreSQL)
cp .env.example .env
# Edite o arquivo .env com suas credenciais do banco

# 5. Rode as migrações do banco
python manage.py migrate

# 6. Crie um superusuário (para a Gerente "Maria")
python manage.py createsuperuser

# 7. Inicie o servidor do backend
python manage.py runserver
# O backend estará rodando em http://localhost:8000
2. Configurando o Frontend (React)
Assumindo que o frontend está na pasta /frontend:

Bash

# 1. Em um NOVO terminal, navegue até a pasta do frontend
cd frontend/

# 2. Instale as dependências
npm install

# 3. Inicie o servidor de desenvolvimento
npm start
# O frontend estará rodando em http://localhost:3000
