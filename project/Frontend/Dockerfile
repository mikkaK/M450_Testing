FROM node:16.17.1-alpine as build
WORKDIR /app
COPY . .
RUN yarn install --network-timeout §
RUN yarn run build
FROM nginx:alpine
EXPOSE 443 80

WORKDIR /usr/share/nginx/html
COPY --from=build /app/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/build .
COPY --from=build /app/.env.production .
RUN apk add --no-cache bash
CMD ["/bin/bash", "-c", "nginx -g  \" daemon off;\"" ]
