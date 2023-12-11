# Dockerfile
# Use an existing node alpine image as a base image.
FROM node:14
# Set the working directory.
WORKDIR /client
# Copy the package.json file.
COPY /package.json .
# Install application dependencies.
RUN npm install
RUN npm install bootstrap
# Copy the rest of the application files.
COPY . ./
 
# Expose the port.
EXPOSE 8001
# Run the application.
CMD ["npm", "start"]
