FROM node:14.2-alpine

WORKDIR /app

COPY package*.json ./
COPY ./ ./

# RUN npm install -g yarn

RUN npm install

#RUN npm install -g serve@latest_version

#RUN npm run-script build

#CMD [ "serve", "-s", "--no-clipboard", "build"]

CMD ["npm","start"]
