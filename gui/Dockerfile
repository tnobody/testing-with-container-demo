FROM node:10 as builder

WORKDIR /app

COPY package.json .
COPY package-lock.json .
RUN npm i

COPY ./src/ ./src
COPY ./public/ ./public
RUN npm run build

FROM nginx

COPY --from=builder /app/build /usr/share/nginx/html

