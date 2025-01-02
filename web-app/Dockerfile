FROM node:22 AS build
WORKDIR /app

COPY ./web-app/package*.json ./
RUN npm install

COPY ./web-app/ ./

RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist/web-app/browser /usr/share/nginx/html
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
