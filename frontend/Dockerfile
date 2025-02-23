#STAGE 1
FROM node:22-alpine AS build
WORKDIR /usr/src/app
COPY angular_workspace/package.json angular_workspace/package-lock.json ./
RUN npm ci
COPY angular_workspace .
RUN npm run build

#STAGE 2
FROM nginx:1.27-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/angular_workspace/browser /usr/share/nginx/html
