### Projeto: Gerador de QR Code com Java, AWS S3 e Docker

---

### Sobre o projeto

Este projeto é um **serviço backend em Java** que recebe texto ou URLs, gera códigos QR e os 
armazena diretamente na nuvem (AWS S3), retornando um link público para acesso aos arquivos.
A aplicação é empacotada com **Docker**, facilitando a execução e deploy em qualquer ambiente 
de forma consistente.

---

### Tecnologias e ferramentas utilizadas

* **Java** — linguagem usada para implementar a lógica do gerador de QR Code.
* **AWS S3** — serviço de armazenamento na nuvem utilizado para guardar e servir os arquivos PNG gerados.
* **Docker** — usado para containerizar a aplicação, garantindo portabilidade e deploy simplificado.
* **Biblioteca QR Code (ex: ZXing ou similar)** — para gerar a imagem do QR Code a partir de textos/URLs.
* **AWS SDK para Java** — para conectar e enviar arquivos ao AWS S3.

---

### Pré-requisitos

* Java JDK instalado (recomenda-se versão 17 ou LTS compatível).
* Docker instalado e configurado.
* Conta na AWS com permissões para criação de buckets e upload em S3.
* Opcional: AWS CLI configurado com credenciais.

---

### Como configurar e utilizar

1. **Clone o repositório**

   ```bash
   git clone <URL_DO_REPO>
   cd nome-do-projeto
   ```

2. **Configurar variáveis de ambiente**
   Crie um arquivo `.env` (ou use `docker-compose.env`) com:

   ```
   AWS_ACCESS_KEY_ID=seu-access-key
   AWS_SECRET_ACCESS_KEY=sua-secret-key
   AWS_REGION=região-da-sua-conta
   AWS_S3_BUCKET=nome-do-bucket
   ```

3. **Docker – Build e execução**

   ```bash
   docker build -t qr-generator-java .
   docker run --env-file .env -p 8080:8080 qr-generator-java
   ```

   Isso inicia o servidor localmente na porta 8080 como contêiner.

4. **Uso da API**
   Faça uma requisição **POST** para gerar o QR Code(recomendo utilizar o Insomnia para testar o
   envio das requisições HTTP e analisar as respostas):

   ```bash
   curl -X POST http://localhost:8080/generate \
     -H "Content-Type: application/json" \
     -d '{"text": "https://exemplo.com"}'
   ```

   A resposta deve trazer o link público do QR Code armazenado no AWS S3.

---

### Estrutura esperada

```
├── src/
│   └── main/java/...         # Código-fonte em Java
├── Dockerfile                # Instruções para build do container
├── .env                      # Variáveis de configuração (não versionar)
└── README.md                 # Documentação detalhada
```

---

### Guia rápido

| Etapa    | Ação                                                              |
| -------- | ----------------------------------------------------------------- |
| Build    | `docker build -t qr-generator-java .`                             |
| Execução | `docker run --env-file .env -p 8080:8080 qr-generator-java`       |
| Gerar QR | `POST /generate {"text": "<texto ou URL>"} -> retorna link do S3` |

---

### Benefícios

* **Automação e escalabilidade**: gera QR Codes sob demanda e os publica automaticamente.
* **Portabilidade total**: container Docker que roda em qualquer ambiente.
* **Infraestrutura na nuvem**: armazena os códigos QR no AWS S3, com alta disponibilidade e acesso público.

---

### Possíveis extensões

* Suporte a parâmetros customizados (tamanho, cor, margem do QR Code).
* Autenticação e controle de acesso na API.
* Pipeline CI/CD para deploy contínuo no ECR e cloud (AWS, Heroku etc.).
