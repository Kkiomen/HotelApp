FROM node:latest as build
WORKDIR /usr/local/app
COPY ./ /usr/local/app/
ENV NODE_OPTIONS=--openssl-legacy-provider
RUN npm install
RUN npm run build

FROM nginx:latest


COPY --from=build /usr/local/app/dist/deka-frontend /usr/share/nginx/html
EXPOSE 80


