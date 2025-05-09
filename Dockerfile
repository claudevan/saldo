# Use uma imagem base do Node.js
FROM node:18-alpine

# Instale o JSON Server globalmente
RUN npm install -g json-server

# Crie um diretório de trabalho
WORKDIR /app

# Copie o arquivo contasMock.json para o contêiner
COPY contasMock.json /app/contasMock.json

# Exponha a porta padrão do JSON Server
EXPOSE 3000

# Comando para iniciar o JSON Server
CMD ["json-server", "--watch", "contasMock.json", "--host", "0.0.0.0"]