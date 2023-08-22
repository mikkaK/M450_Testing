FROM nginx:alpine
EXPOSE 443 80

WORKDIR /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY build .
RUN apk add --no-cache bash
CMD ["/bin/bash", "-c", "nginx -g \"daemon off;\""]
