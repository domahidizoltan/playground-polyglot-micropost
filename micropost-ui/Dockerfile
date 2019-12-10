FROM node:13.3.0-alpine3.10

WORKDIR /app
COPY . .

RUN npm install
RUN npm run build
RUN chmod +x env.sh

EXPOSE 3000
ENTRYPOINT npm start